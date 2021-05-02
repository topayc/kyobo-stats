package com.kyobo.stats.web.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import com.kyobo.stats.web.dto.UserSession;

public class ApplicationController {
	public class Forward{
		public static final String login_view = "user/login";
		public static final String login_fail_view= "user/loginFail";
		public static final String login_success_view= "user/loginSuccess";
		public static final String main_view= "main/main";
		public static final String content_view= "main/content";
		public static final String report_view= "main/report";
	}
	
	public class Redirect {
		public static final String main_redirect ="redirect:/" ;
		public static final String login_redirect ="redirect:/login" ;
	}
	
	public class ResultCode {
		public static final int SUCCESS = 1;
		public static final int ERROR = 2;
	}
	
	@Inject protected HttpSession session;
	protected UserSession prepareUserSession(){
		return (UserSession)session.getAttribute("userSession");
	}
}
