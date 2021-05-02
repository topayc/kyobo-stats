<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta http-equiv="Expires" content="-1"> 
  <meta http-equiv="Pragma" content="no-cache"> 
  <meta http-equiv="Cache-Control" content="No-Cache"> 
  <title>Stats | Main</title>
  <link rel="stylesheet" type="text/css" href="themes/default/easyui.css">
  <link rel="stylesheet" type="text/css" href="themes/icon.css">
  <link rel="stylesheet" type="text/css" href="css/default.css">
  <link rel="stylesheet" href="resources/plugins/iCheck/square/blue.css"/>
  <script type="text/javascript" src="js/jquery.min.js"></script>
  <script type="text/javascript" src="js/jquery.easyui.min.js"></script>
  <script src="resources/plugins/iCheck/icheck.min.js"></script>
  <script src="js/utils.js"></script>
</head>
<body>
    <div class="container easyui-layout" style="width:100%">
        <div data-options="region:'north',border : false" style="height:50px;" >
        	<img style ="margin-left : 10px;margin-top:2px;width:130px;height:40px" src ="images/logo.png"/>
        	<div style="position: absolute;right : 0px;margin-top:-20px;margin-right:40px" class ="member-panel">
        		<span style ="font-weight: bold">${userSession.id} (${userSession.type}) 님</span>
        		<a href ="#" id = "pass_chn_btn" request_form= "user_form"  style ="margin-left : 30px;font-weight: bold">비밀번호 관리</a>
				<a href ="logout" style ="margin-left : 10px;font-weight: bold">로그아웃</a>
			</div>
        </div>
        <div data-options="region:'south',split:false" style="height:1px;"></div>
        <div data-options="region:'west',split:true" title="&nbsp;보고서 관리 메뉴" style="width:280px;">
        	<ul id = "submenu_tree" class="easyui-tree" style ="margin : 5px;margin-top : 10px" >
	            <li id ="root_node">
	                <span><span class="report_sub_menu" deps= '1'>보고서 관리</span></span>
	                <ul>
	                	<c:forEach items = "${programCommandList}" var = "programCommand">
	                    <li>
	                        <span><span class="report_sub_menu" deps= '2' >${programCommand.primaryProgramName}</span></span>
	                        <ul>
	                        	<c:if test = "${programCommand.primaryProgramID == '7'}">
	                        	 <li><a deps= '3' class= "report_sub_menu"  request_code= "MN210" ><span>조직 관리</span></a></li>
	                        	</c:if>
	                        	<c:forEach items = "${programCommand.programs}" var = "programDTO">
	                        			<li><a deps= '3' class= "report_sub_menu"  request_code= "${programDTO.programId}" ><span>${programDTO.programName}</span></a></li>
	                            </c:forEach>
	                        </ul>
	                    </li>
	                    </c:forEach>
	                </ul>
	            </li>
        	</ul>
        </div>
        <div id="content_container"  data-options="region:'center'" title=".." style="padding:5px">
        </div>
    </div>
 <script src="js/main.js"></script>
</body>
</html>
