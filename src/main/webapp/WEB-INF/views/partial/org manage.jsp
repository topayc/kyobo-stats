<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style type="text/css">
	.tg  {border-collapse:collapse;border-spacing:0;border-color:#ccc;width:600px}
	.tg td{font-family:Dotum, Gulim;font-size:12px;padding:5px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#fff;}
	.tg th{font-family:Dotum, Gulim;font-size:12px;font-weight:normal;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#f0f0f0;}
	.tg .tg-baqh{text-align:center;vertical-align:top;}
	.tg .tg-bold{font-weight:bold}
	.tg .tg-yw4l{text-align:center;vertical-align:top}
	.tg .tg-b7b8{text-align:center;background-color:#f9f9f9;vertical-align:top}
	.tg .tg-dzk6{text-align:center;background-color:#f9f9f9;text-align:center;vertical-align:top}
	.tg tr:hover td {background-color: lightcyan;} 
</style>
<div style ="width:100%;height:100%" class="easyui-panel" title="계정 관리"  data-options="collapsible:true">
	<div id="org_mamage" class="easyui-tabs" style="width:100%;height:98%;">
	    <div title="상담그룹" data-options="closable:false" style="padding:20px;display:none;">
			<table class="tg">
			  <tr>
			    <th class="tg-baqh tg-bold">NO</th>
			    <th class="tg-baqh tg-bold">상담 그룹</th>
			    <th class="tg-baqh tg-bold">상담 직원</th>
			  </tr>
			<c:forEach items = "${agentSearchList}" var = "agentSearch" varStatus="status">
			   <tr>
			    <td class="${(status.index % 2) == 0 ? 'tg-dzk6': 'tg-baqh'}">${status.index + 1}</td>
			     <td class="${(status.index % 2) == 0 ? 'tg-dzk6': 'tg-baqh'}">${agentSearch.skillGroupKor}</td>
			    <td class="${(status.index % 2) == 0 ? 'tg-dzk6': 'tg-baqh'}">${agentSearch.agentName}</td>
			  </tr>
			   </c:forEach>
			</table>
	    </div>
	    <div title="상담팀" data-options="closable:false" style="overflow:auto;padding:20px;display:none;">
			<table class="tg">
			  <tr>
			    <th class="tg-baqh tg-bold">NO</th>
			    <th class="tg-baqh tg-bold">상담팀</th>
			    <th class="tg-baqh tg-bold">상담 직원</th>
			  </tr>
			   <c:forEach items = "${agentSearchList}" var = "agentSearch" varStatus="status">
			   	 <tr>
			    <td class="${(status.index % 2) == 0 ? 'tg-dzk6': 'tg-baqh'}">${status.index + 1}</td>
			    <td class="${(status.index % 2) == 0 ? 'tg-dzk6': 'tg-baqh'}">${agentSearch.agentTeamKor}</td>
			    <td class="${(status.index % 2) == 0 ? 'tg-dzk6': 'tg-baqh'}">${agentSearch.agentName}</td>
			  </tr>
			   </c:forEach>
			</table>
	    </div>
	    <div title="상담 직원" data-options="closable:false" style="padding:20px;display:none;">
	  		<table class="tg">
				  <tr>
				    <th class="tg-baqh tg-bold">NO</th>
				    <th class="tg-baqh tg-bold">상담 직원</th>
				    <th class="tg-baqh tg-bold">상담 그룹</th>
				    <th class="tg-baqh tg-bold">상담팀</th>
				  </tr>
				  <c:forEach items = "${agentSearchList}" var = "agentSearch" varStatus="status">
				    <tr>
				    <td class="${(status.index % 2) == 0 ? 'tg-dzk6': 'tg-baqh'}">${status.index + 1}</td>
				    <td class="${(status.index % 2) == 0 ? 'tg-dzk6': 'tg-baqh'}">${agentSearch.agentName}</td>
				    <td class="${(status.index % 2) == 0 ? 'tg-dzk6': 'tg-baqh'}">${agentSearch.skillGroupKor}</td>
				    <td class="${(status.index % 2) == 0 ? 'tg-dzk6': 'tg-baqh'}">${agentSearch.agentTeamKor}</td>
				  </tr>
				  </c:forEach>
			</table>
	    </div>
	</div>
</div>
<div>
</div>
   <script type="text/javascript" src="js/datagrid-filter.js"></script>
   <script type="text/javascript">
	 $(function(){
		 $('#org_mamage').tabs({});
 	});
 </script>