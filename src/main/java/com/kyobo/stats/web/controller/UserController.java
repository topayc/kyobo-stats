package com.kyobo.stats.web.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kyobo.stats.common.Constants;
import com.kyobo.stats.dao.interfaces.cstmmst.ProgramAuthDao;
import com.kyobo.stats.dao.interfaces.cstmmst.ProgramDao;
import com.kyobo.stats.dao.interfaces.cstmmst.UserDao;
import com.kyobo.stats.model.Program;
import com.kyobo.stats.model.ProgramAuth;
import com.kyobo.stats.model.User;
import com.kyobo.stats.utils.Crypto;
import com.kyobo.stats.utils.Utils;
import com.kyobo.stats.web.dto.AjaxReponse;
import com.kyobo.stats.web.dto.UserSession;

@Controller
@RequestMapping
public class UserController extends ApplicationController {


	@Autowired UserDao userDao;
	@Autowired ProgramDao programDao;
	@Autowired ProgramAuthDao programAuthDao;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model) {
		UserSession userSession = new UserSession();
		model.addAttribute("userSession", userSession);
		return Forward.login_view;
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String submitLogin(@ModelAttribute UserSession userSession, HttpServletRequest request,
			HttpSession httpSession, Model model) throws Exception {
		boolean isLoginResult = false;
		String message = "";
		String returnView = "";
		
		if (userSession != null) {
			//상수로 정의된 어드민 계정의 우선 처리 
			if (userSession.getId().equals(Constants.Account.ADMIN_ACCOUT_ID)){
				if (userSession.getPassword().equals(Constants.Account.ADMIN_ACCOUT_PWD)){
					userSession.setLoginDate(new Date());
					userSession.setRemoteIp(request.getRemoteAddr());
					userSession.setLoginStatus(true);
					userSession.setType(Constants.AccoutType.SUPER);
					
					httpSession.setAttribute("userSession", userSession);
					return Redirect.main_redirect;
				}else {
					message = "관리자 비밀번호 오류";
					model.addAttribute("message", message);
					returnView = Forward.login_view;
					return returnView;
				}
			}
			
			User userOption = new User();
			userOption.setUserID(userSession.getId());
			ArrayList<User> resiteredUsers = this.userDao.getUsers(userOption);
			
			if (resiteredUsers.size() < 1) {
				message = "해당 사용자가 존재하지 않습니다";
				returnView = Forward.login_view;
			} else {
				User user = resiteredUsers.get(0);
				if (user.getLockYN().equals("Y")) {
					message = "블럭된 계정입니다. 관리자에게 문의하세요";
					returnView = Forward.login_view;
				} else {
					if (!user.getUserPWDText().equals(Crypto.getEncMD5(userSession.getPassword()))) {
						user.setPWDErrCnt(user.getPWDErrCnt() + 1);
						user.setLockYN(user.getPWDErrCnt() >=5 ? "Y" : "N");
						user.setPWDErrDt(Utils.getCurrentTimeStamp());
						this.userDao.updateUser(user);

						if (user.getPWDErrCnt() >= 5) {
							message = String.format("%d회 비밀번호 오류 - %s", user.getPWDErrCnt(), "계정 블럭. 관리자에게 문의하세요");
							returnView = Forward.login_view;
						} else {
							message = String.format("%d회 비밀번호 오류 - %s", user.getPWDErrCnt(), "비밀번호가 틀렸습니다");
							returnView = Forward.login_view;
						}
					} else {
						user.setPWDErrCnt(0);
						user.setLockYN("N");
						this.userDao.updateUser(user);
						
						userSession.setLoginDate(new Date());
						userSession.setRemoteIp(request.getRemoteAddr());
						userSession.setLoginStatus(true);
						userSession.setType(Constants.AccoutType.COMMON);

						isLoginResult = true;
						returnView = Redirect.main_redirect;
					}
				}
			}
		} else {
			message = "잘못된 요청입니다";
			returnView = Forward.login_view;
		}
		
		if (isLoginResult) httpSession.setAttribute("userSession", userSession);
		if (message != null  && !message.equals("")){
			model.addAttribute("message", message);
		}
		return returnView;
	}

	@RequestMapping(value = "/logout")
	public String logout(HttpSession httpSession, Model model) {
		httpSession.removeAttribute("userSession");
		return Redirect.login_redirect;
	}

	@RequestMapping(value = "/changePassword", method = RequestMethod.POST)
	@ResponseBody
	public AjaxReponse changePassword(@RequestParam(value = "id", required = true) String id,
			@RequestParam(value = "password", required = true) String password,
			@RequestParam(value = "newPassword", required = true) String newPassword, HttpSession session,
			Model model) throws Exception {
		AjaxReponse response = new AjaxReponse();
		User userOption = new User();
		userOption.setUserID(id);

		ArrayList<User> registerdEsers = this.userDao.getUsers(userOption);
		if (registerdEsers.size() < 1 ) {
			response.setMessage("해당 사용자가 존재하지 않습니다"); 
			response.setResultCode(2); 
			response.setResult("error"); 
		}else {
			User user = registerdEsers.get(0);
			if (!Crypto.getEncMD5(password.trim()).equals(user.getUserPWDText())) {
				response.setMessage("비밀번호가 틀렸습니다"); 
				response.setResultCode(2); 
				response.setResult("error");
			}else {
				user.setUserPWDText(Crypto.getEncMD5(newPassword));
				this.userDao.updateUser(user);
				
				response.setResult("success");
				response.setResultCode(1);
				response.setMessage("비밀번호가 수정되었습니다");
			}
		}

		UserSession userSession = (UserSession) session.getAttribute("userSession");
		userSession.setPassword(password);

		return response;
	}

	@RequestMapping(value = "/createMember", method = RequestMethod.POST)
	@ResponseBody
	public AjaxReponse createMember(@RequestParam(value = "id", required = true) String id,
			@RequestParam(value = "password", required = true) String password,
			@RequestParam(value = "name", required = true) String name, HttpSession session, Model model)
			throws Exception {
		
		AjaxReponse response = new AjaxReponse();
		UserSession rgstUser = ((UserSession)session.getAttribute("userSession"));
		/*
		if (!rgstUser.getType().equals(Constants.AccoutType.SUPER)) {
			response.setResult("error");
			response.setResultCode(ResultCode.ERROR);
			response.setMessage("권한이 없습니다.");
			return response;
		}
		*/
		
		User userOption = new User();
		userOption.setUserID(id);
		ArrayList<User> registeredUsers = this.userDao.getUsers(userOption);

		if (registeredUsers.size() > 0) {
			response.setResult("error");
			response.setResultCode(ResultCode.ERROR);
			response.setMessage("이미 사용자 계정이 존재합니다");
		} else {
			// T_User table 등록
			User user = new User();
			user.setUserID(id);
			user.setUserPWDText(Crypto.getEncMD5(password));
			user.setUserNM(name);
			userDao.insertUser(user);
			
			// T_ProgramAuth  table 등록
			Program programOption = new Program();
			ArrayList<Program> registerdPrograms = this.programDao.getPrograms(programOption);
			ArrayList<ProgramAuth> initProgramAuths = new ArrayList<ProgramAuth>();
			
			if (registerdPrograms.size() > 0) {
				for (int i = 0; i <registerdPrograms.size(); i++ ) {
					if (registerdPrograms.get(i).getPgmID().startsWith("RD") || registerdPrograms.get(i).getPgmID().startsWith("MN")){
						if (!registerdPrograms.get(i).getPgmID().equals("MN310") && !registerdPrograms.get(i).getPgmID().equals("MN410")) {
							ProgramAuth programAuth = new ProgramAuth();
							programAuth.setPgmID(registerdPrograms.get(i).getPgmID());
							programAuth.setUserID(id);
							programAuth.setRgstUserID(rgstUser.getId());
							initProgramAuths.add(programAuth);
						}
					}
				}
				this.programAuthDao.insertProgramAuthBatch(initProgramAuths);
			}
			
			response.setResult("success");
			response.setResultCode(ResultCode.SUCCESS);
			response.setMessage("사용자 생성이 완료되었습니다");
		}
		return response;
	}

	@RequestMapping(value = "/users")
	@ResponseBody
	public ArrayList<User> getUsers(HttpSession session, Model model)
			throws JsonGenerationException, JsonMappingException, IOException {
		ArrayList<User> users = userDao.getUsers(new User());
		//ObjectMapper mapper = new ObjectMapper();
		//String mrdJson = mapper.writeValueAsString(users);
		//logger.info(mrdJson);
		return users;
	}

	@RequestMapping(value = "/changeRole")
	@ResponseBody
	public AjaxReponse changeRole(
			@RequestParam(value = "UserID", required = true) String UserID,
			@RequestParam(value = "PgmID", required = true) String PgmID,
			@RequestParam(value = "enable", required = true) String enable, 
			HttpSession httpSession,
			Model model) {
		AjaxReponse response = new AjaxReponse();
		ProgramAuth programAuth = new ProgramAuth();
		programAuth.setPgmID(PgmID);
		programAuth.setUserID(UserID);
		
		if (enable.equals("Y")) {
			programAuth.setRgstUserID(((UserSession)httpSession.getAttribute("userSession")).getId());
			this.programAuthDao.insertProgramAuth(programAuth);
		}else if (enable.equals("N")){
			this.programAuthDao.deleteProgramAuth(programAuth);
		}
		
		response.setResult("success");
		response.setResultCode(1);
		response.setMessage("사용자 권한이 수정되었습니다");
		return response;
	}

	@RequestMapping(value = "/changeLock")
	@ResponseBody
	public AjaxReponse changeLock(@RequestParam String UserID, String lockStatus)
			throws JsonGenerationException, JsonMappingException, IOException {
		AjaxReponse response = new AjaxReponse();
		User user = new User();
		user.setUserID(UserID);

		ArrayList<User> users = userDao.getUsers(user);
		User sUser;
		if (users.size() == 0) {
			response.setResult("error");
			response.setResultCode(2);
			response.setMessage("존재하지 않는 사용자 입니다");
		} else {
			sUser = users.get(0);
			sUser.setLockYN(lockStatus);
			sUser.setPWDErrCnt(sUser.getLockYN().equals("N") ? 0 : sUser.getPWDErrCnt());

			userDao.updateUser(sUser);
			response.setResult("success");
			response.setResultCode(1);
			response.setMessage("사용자 블럭 상태가 변경되었습니다");
		}
		return response;
	}

	@RequestMapping(value = "/deleteUser")
	@ResponseBody
	public AjaxReponse deleteUser(@RequestParam String UserID)
			throws JsonGenerationException, JsonMappingException, IOException {
		AjaxReponse response = new AjaxReponse();
		
		//사용자 테이블 삭제
		User user = new User();
		user.setUserID(UserID);
		userDao.deleteUser(user);

		//프로그램 권한 테이블 삭제
		ProgramAuth programAuth = new ProgramAuth();
		programAuth.setUserID(UserID);
		this.programAuthDao.deleteProgramAuth(programAuth);
	
		response.setResult("success");
		response.setResultCode(1);
		response.setMessage("사용자 계정이 삭제 되었습니다");
		return response;
	}
}
