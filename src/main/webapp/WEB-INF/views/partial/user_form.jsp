<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style type="text/css">.blue_message2 {font-weight:bold;font-family:Gulim, Dotum;font-size:12px;color:#0092DC}</style>
<div style ="margin: 30px;padding: 30px;width:650px;border : 0px solid #cccccc" >
   <span class = "blue_message">${message}</span>
   <form id="member_form" method="post" action = "/stats/changePassword">
    <div style="margin-bottom:20px">
        <input disabled class="easyui-textbox" name="id" style="width:90%;height:28px" data-options="label:'아이디&nbsp;&nbsp;'"  value = "${userSession.id}" >
    </div>
    <div style="margin-bottom:20px">
        <input <c:if test = "${userSession.type == 'SUPER'}"> disabled</c:if> id ="password" class="easyui-textbox" name="password" style="width:90%;height:28px" data-options="label:'기존 비밀번호'" value = "" >
    </div>
    <div style="margin-bottom:20px">
        <input <c:if test = "${userSession.type == 'SUPER'}"> disabled</c:if> id ="password" class="easyui-textbox" name="newPassword" style="width:90%;height:28px" data-options="label:'변경 비밀번호'" value = "" >
    </div>
</form>
<c:choose>
	<c:when test = "${userSession.type == 'SUPER'}">
		<div style="text-align:left;padding:5px 86px">
		    <span class ="blue_message2">* 현재 계정은 SUPER 계정입니다. 이 계정의 비밀번호는 변경할 수 없습니다</span>
		</div>
	</c:when>
	<c:otherwise>
		<div style="text-align:left;padding:5px 86px">
		    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()" style="width:120px">비밀번호 변경</a>
		    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()" style="width:80px">리셋</a>
		</div>
	</c:otherwise>
</c:choose>
</div>
<br/>

<script>
	$('.easyui-linkbutton').linkbutton();
	$('.easyui-textbox').textbox({labelAlign:'right'});
	
	function submitForm(){
		var id = $('input[name=id]')[0].value;
		var password = $('input[name=password]')[0].value;
		var newPassword = $('input[name=newPassword]')[0].value;
		if (!password || password.length == 0) {
			openMessageWindow("입력 오류", '기존 비밀번호를 입력해주세요');
	    	return;
		}
		
		if (!newPassword || newPassword.length == 0) {
			openMessageWindow("입력 오류", '변경할 비밀번호를 입력해주세요');
	    	return;
		}
		 //$('#member_form').form('submit');
		$.ajax(
		{
		    type: "POST",
		    cache:false,
		    url: 'changePassword',
		    dataType: 'json',
		    async: false,
		    data: {id:id , password : password, newPassword :newPassword},
		    success: function (result) {
		    	openMessageWindow("비밀번호 수정", result.message);
		    }
		});
	}
	function clearForm(){
		loadContent("비밀번호 관리","user_form");
 	}
</script>