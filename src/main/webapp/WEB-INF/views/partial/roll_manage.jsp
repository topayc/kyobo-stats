<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style type="text/css">
	.tg  {border-collapse:collapse;border-spacing:0;border-color:#ccc;width : 1600px;overfolw-x;auto}
	.tg td{font-family:Arial, sans-serif;font-size:14px;padding:5px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#fff;}
	.tg th{font-family:Arial, sans-serif;font-size:14px;font-weight:normal;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#f0f0f0;}
	.tg .tg-g5xs{font-size:11px;text-align:center;vertical-align:top;height:8px}
	.tg .tg-dovx{font-weight:bold;font-size:13px;text-align:center;}
	.tg .tg-b56p{font-weight:bold;font-size:11px;text-align:center;}
	.tg .tg-szsh{background-color:#f9f9f9;font-size:11px;text-align:center;}
	.tg .user_row:hover td {background-color: lightcyan;} 
	tr {height:9px}
</style>
<div style ="padding: 1px;padding-top:10px" >
<table class="tg" style ="width:100%">
  <tr>
    <th class="tg-b56p"  style ="width:50px" rowspan="2" >사용자</th>
    <c:forEach items = "${programCommandList}" var = "programCommand">
    <th class="tg-dovx" colspan="${fn:length(programCommand.programs)}">${programCommand.primaryProgramName}</th>
    </c:forEach>
  </tr>
  <tr>
  	<c:forEach items = "${programCommandList}"  var = "programCommand">
  		<c:forEach items = "${programCommand.programs}" var = "program">
  			 <c:if test = "${program.useYN == 'Y'}">
  			 <td class="tg-szsh" >${program.programName}</td>
  			 </c:if>
  		</c:forEach>
    </c:forEach>
  </tr>
  <c:forEach items = "${userProgramAuthList}" var ="userProgramAuth">
  	<tr class = "user_row"  style ="height:10px">
	  	<td class="tg-szsh" style ="font-weight:bold">${userProgramAuth.userId}</td>
	  		<c:forEach items = "${programCommandList}" var = "programCommand">
		  		<c:forEach items = "${programCommand.programs}" var = "programDTO">
		  			 <c:if test = "${programDTO.useYN == 'Y'}">
		  				 <td class="tg-g5xs">
		  				 	<input class = "program_code"  type="checkbox"  UserID = "${userProgramAuth.userId}"  PgmID = "${programDTO.programId}"  
		  				 	<c:if test = "${userProgramAuth.userProgramAuthMap[programDTO.programId] == 'Y'}" >checked = "checked"</c:if> name = "roll_check"  />
		  				 </td>
		  			 </c:if>
		  		</c:forEach>
	    </c:forEach>
	    </tr>
  </c:forEach>
</table>
<br/>
<span style = "font-size : 12px; font-family : Dotum, Gulim; color : #666666" >* 사용자 권한별 메뉴 활성/비활성화를 위해서 체크박스를 클릭하세요</span>
</div>
<br/>

<script>

$(document).ready(function(){
	$('.program_code').bind('click', function(e){
		var enabled = $(this).is(":checked") == true ? "Y" : "N";
		var UserID = $(this).attr('UserID');
		var PgmID = $(this).attr('PgmID');
		var data = { UserID : UserID , PgmID : PgmID , enable: enabled};
		console.log("사용자 권한 변경");
		console.log(data);
		$.ajax({
			  dataType: "json",
			  url: 'changeRole',
			  data: data,
			  success: function(result) {
				  //openMessageWindow("권한 변경", result.message);
			  }
		});
	});	
});

</script>