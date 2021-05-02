<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:choose>
	 <c:when test = "${requestCode == 'intro'}">
		<style>img.intro_brand {width : 1200px;height : 600;position : absolute;left:50%;top:50%;margin-left : -600px;margin-top : -290px;}</style>
		<!-- <img class = "intro_brand"  src = "images/intro.png"/> -->
	</c:when>
	<c:when test = "${requestCode == 'user_form'}"><%@ include file="../partial/user_form.jsp"%></c:when>
	<c:when test = "${requestCode == 'MN210'}"><%@ include file="../partial/org manage.jsp" %></c:when>
	<c:when test = "${requestCode == 'MN310'}"><%@ include file="../partial/accout manage.jsp"%></c:when>
	<c:when test = "${requestCode == 'MN410'}"><%@ include file="../partial/roll_manage.jsp"%></c:when>
	<c:otherwise>
		<div  id ="search_container" class="easyui-layout" data-options="fit:true">
		   <div id ="data_grid_region" data-options="region:'west',split:true" title ="검색 그룹" style="width:290px;overflow-x:hidden;border:0px solid #dddddd">
		   	 <div  id = "group_grid_container" class = "component RD140 RD210 RD220 RD230 RD310 RD320 RD330 RD340 RD350"  style="width:100%;height:25%;border:none">
			   	 <table  id = "group_grid"  class="group_grid easyui-datagrid" title="상담 그룹" style="width:100%;height:100%;border:none;"
				          data-options="rownumbers:true,singleSelect:false,collapsible:false,tools:'#groutool'">
				      <thead>
				          <tr>
				              <th data-options="field:'groupID',hidden:true,align:'center'">그룹 ID</th>
				              <th data-options="field:'name',width:'100%',align:'center'">상담그룹</th>
				              <th data-options="field:'identifier',hidden:true,align:'center'">식별자</th>
				          </tr>
				      </thead>
				      <tbody> 
				        <c:forEach items = "${groupList}" var ="group">
					        <tr><td>${group.code}</td><td>${group.name}</td><td>G</td></tr>
				      	</c:forEach>
				      </tbody>
				      </tbody>
				  </table>
		  	</div>
		  	
		  	<div  id = "team_grid_container" class = "component RD120 RD130 RD170 RD180 RD190 RD310 RD320 RD330 RD340 RD350" style="width:100%;height:25%;border:none;">
			  <table id = "team_grid"  class="team_grid easyui-datagrid" title="상담팀" style="width:100%;height:100%;border:none"
			         data-options="rownumbers:true,singleSelect:false,collapsible:false,tools:'#team_tool'">
			      <thead>
			          <tr>
			              <th data-options="field:'teamID',hidden:true,align:'center'">팀 ID</th>
			              <th data-options="field:'name',width:'100%',align:'center'">상담팀</th>
			              <th data-options="field:'identifier',hidden:true,align:'center'">식별자</th>
			          </tr>
			      </thead>
			      <tbody>
			          <c:forEach items = "${teamList}" var ="team">
					        <tr><td>${team.code}</td><td>${team.name}</td><td>T</td></tr>
				      	</c:forEach>
			      </tbody>
			  </table>
			  </div>
			  <div  id = "member_grid_container"  class = "component RD190 RD310 RD320 RD330 RD340 RD350" style="width:100%;height:25%;border:none">
			  	 <table id = "member_grid"  class="member_grid easyui-datagrid" title="상담원" style="width:100%;height:100%;border:none"
			          data-options="rownumbers:true,singleSelect:false,collapsible:false,tools:'#member_tool'">
				      <thead>
				          <tr>
				              <th data-options="field:'agentID',hidden:true,align:'center'">상담원 ID</th>
				              <th data-options="field:'name',width:'100%',align:'center'">상담원</th>
				              <th data-options="field:'identifier',hidden:true,align:'center'">식별자</th>
				          </tr>
				      </thead>
				      <tbody>
			          <c:forEach items = "${agentSearchList}" var ="agentSearch">
					        <tr><td>${agentSearch.agentID}</td><td>${agentSearch.agentName}</td><td>M</td></tr>
				      	</c:forEach>
			      </tbody>
				  </table>
			  </div>
			  
			  <div  id = "branch_grid_container" class = "component RD510"  style="width:100%;height:25%;border:none">
			  	 <table id = "branch_grid"  class="branch easyui-datagrid" title="지점" style="width:100%;height:100%;border:none"
			         data-options="rownumbers:true,singleSelect:false,collapsible:false,tools:'#branch_tool'" >
				      <thead>
				          <tr>
				              <th data-options="field:'selBRCode',hidden:true,align:'center'">지점 코드</th>
				              <th data-options="field:'name',width:110,align:'center'">지점명</th>
				              <th data-options="field:'identifier',hidden:true,align:'center'">식별자</th>
				          </tr>
				      </thead>
				      <tbody>
				           <c:forEach items = "${branchList}" var ="branch">
					        <tr><td>${branch.code}</td><td>${branch.name}</td><td>B</td></tr>
				      	</c:forEach>
				      </tbody>
				  </table>
			  </div>
		   </div>
		   
		   <div data-options="region:'center'" >
		 		 <div class="easyui-layout" data-options="fit:true">
			         <div data-options="region:'south',collapsible:true,split:false,iconCls:'icon-search'" style="height:50px;overflow-y:hidden" >
			         	 <div style="position:absolute; right : 15px;padding:10px;">
					        <a href="#" id ="reset_btn"  class="easyui-linkbutton"  data-options="iconCls:'icon-no'">리셋&nbsp;</a>
					        <a href="#" id ="search_btn" class="easyui-linkbutton" style="margin-left:10px" data-options="iconCls:'icon-search'">조회&nbsp;</a>
					    </div>
			         </div>
			         
			         <div data-options="region:'west',split:true,collapsible:true" title= "&nbsp;검색 옵션" style="width:250px;padding:10px;overflow-x:hidden">
			            <div id = "startDateCotainer"  class = "component RD110 RD120 RD130 RD140 RD190 RD210 RD220 RD230 RD310 RD320 RD330 RD340 RD350 RD410 RD420 RD430 RD440 RD450 RD510 RD610 RD620 RD630 RD640" >
			         		<span class = "date_title">시작일</span>
			         		<div id="searchStartDate" style="width:100%;height:170px;"></div>
			         	</div>
			         	
			         	<div id = "endDateCotainer"  class = "component RD110 RD120 RD130 RD140 RD190 RD210 RD220 RD230 RD310 RD320 RD330 RD340 RD350 RD410 RD420 RD430 RD440 RD450 RD510 RD610 RD620 RD630 RD640"  style ="margin-top:9px">
			         		<span class = "date_title">종료일</span>
			         		<div id="searchEndDate" style="width:100%;height:170px;"></div>
			         	</div>
			         	
			         	 <div id = "dateContainer"  class = "component RD150 RD160 RD170 RD180"  style ="margin-top:1px">
			         		<span class = "date_title">조회일</span>
			         		<div id="searchDate" style="width:100%;height:170px;"></div>
			         	</div>
			         	
			         	<div id = "startTimeCotainer" class = "component RD110 RD120 RD130 RD140 RD150 RD160 RD170 RD180 RD190 RD210 RD220 RD230 RD310 RD320 RD330 RD340 RD350 RD410 RD420 RD430 RD440 RD450 RD510 RD610 RD620 RD630 RD640"  style ="font-weight:bold;margin-top:7px">
			         		<input id="searchStartTime" name ="frTM" label="시작 시각"  labelPosition="top" style="width:100%;height:48px"/>
			         	</div>
			         	
			         	<div id = "startTimeCotainer" class = "component RD110 RD120 RD130 RD140 RD150 RD160 RD170 RD180 RD190 RD210 RD220 RD230 RD310 RD320 RD330 RD340 RD350 RD410 RD420 RD430 RD440 RD450 RD510 RD610 RD620 RD630 RD640"  style ="font-weight:bold;margin-top:7px">
			         		<input id="searchEndTime" name ="toTM"  label="종료 시각"  labelPosition="top" style="width:100%;height:48px"/>
			         	</div>
			         	
			         	<div id = "aniCotainer" class = "component RD190" style ="margin-top:7px"><input id="ani" name ="ani" label="발신 번호" value =""  labelPosition="top" style="width:100%;height:48px"/></div>
			         	<div id = "sidCotainer" class = "component RD190" style ="margin-top:7px"><input id="sid" name ="sid" label="계좌 번호" labelPosition="top" style="width:100%;height:48px"/></div>
			         	
				    	 <div id ="selectWeekContainer"  class = "component RD110 RD120 RD130 RD140 RD210 RD220 RD230 RD310 RD320 RD330 RD340 RD350 RD410 RD420 RD430 RD440 RD450 RD510 RD610 RD620 RD630 RD640"  style ="margin-top:13px">
		     	    	 	<span class = "date_title">검색 요일 선택</span>
		     	    	 	<div class = "easyui-panel" style ="margin-top:3px;width:100%;padding:10px">
			     	    	 	<div class ="selweek_margin"><input id ="week_all"  class = "selweek all" type ="checkbox"  week = "all"  name = "selweek"  value ="all" ><span>전체</span></div>
					    	 	<div style = "float:left" class ="selweek_margin"><input id ="week_sun" class = "selweek sun" week = "sun" type ="checkbox" name = "selweek" value ="1"><span>일</span></div>
					    	 	<div style = "float:left" class ="selweek_margin"><input id ="week_mon" class = "selweek mon" week = "mon" type ="checkbox" name = "selweek" value ="2"><span>월</span></div>
					    	 	<div style = "float:left" class ="selweek_margin"><input id ="week_tue" class = "selweek tue" week = "tue" type ="checkbox" name = "selweek" value ="3"><span>화</span></div>
					    	 	<div style = "float:left" class ="selweek_margin"><input id ="week_wed" class = "selweek wed" week = "wed" type ="checkbox" name = "selweek" value ="4"><span>수</span></div>
					    	 	<div style = "float:left" class ="selweek_margin"><input id ="week_thu" class = "selweek thu" week = "thu" type ="checkbox" name = "selweek" value ="5"><span>목</span></div>
					    	 	<div style = "float:left" class ="selweek_margin"><input id ="week_fri" class = "selweek fri" week = "fri" type ="checkbox" name = "selweek" value ="6"><span>금</span></div>
					    	 	<div style = "float:left" class ="selweek_margin"><input id ="week_sat" class = "selweek sat" week = "sat" type ="checkbox" name = "selweek" value ="7"><span>토</span></div>
							</div>
				    	</div>
				    	
				    	 <div id ="selectAccTypeContainer"  class = "component RD420"  style ="margin-top:7px">
		     	    	 	 <input id ="accType"  name = "accType" style ="width:100%;height:48px" class="easyui-combobox"  data-options="
				                    showItemIcon: true,
				                    data: [
				                        {value:'V',text:' 음성',iconCls:'icon-ok'},
				                        {value:'B',text:' 버튼식',iconCls:'icon-ok'},
				                        {value:'D',text:' 음성 / 버튼식',iconCls:'icon-ok',selected:true}
				                    ],
				                    editable: false,
				                    panelHeight: 'auto',
				                    label: '사용 방식',
				                    labelPosition: 'top',
				                    multiple:false,
				                    required:true
				                    ">
				    	</div>
				    	
				    	<div id ="selectCallTreatTypeContainer" class = "component RD190"  style ="margin-top:7px">
				    		<select id ="calltreat" name = "calltreat" style ="width:100%;height:48px;margin-top:7px" class="easyui-combobox"   data-options="panelHeight : 'auto'"  label="콜 처리 결과" labelPosition="top" >
				                <c:forEach items = "${callTreatList}" var = "calltreat">
				                	<option value="${calltreat.code}">${calltreat.name}</option>
				                </c:forEach>
				            </select>
				    	</div>
				    	
				    	<div id ="selectRonaTypeContainer" class = "component RD190"  style ="margin-top:7px">
				    		<input id ="ronaYN" name = "ronaYN"  style ="width:100%;height:48px" class="easyui-combobox"  data-options="
				                    showItemIcon: true,
				                    data: [
				                        {value:'N',text:' 발생 안함',iconCls:'icon-ok'},
				                        {value:'Y',text:' 발생',iconCls:'icon-ok'},
				                        {value:'YN',text:' 전체',iconCls:'icon-ok',selected:true}
				                    ],
				                    editable: false,
				                    panelHeight: 'auto',
				                    label: 'RONA 발생 여부',
				                    labelPosition: 'top',
				                    multiple:false,
				                    required:true
				                    ">
				    	</div>
			         	
			         </div>
		     
		     	    <div style="padding-top:6px;padding-left:20px;padding-right:10px" data-options="region:'center'" title ="&nbsp;출력 옵션">
				    	<div id ="selectTimeFormatContainer" class = "component RD110 RD120 RD130 RD140 RD160 RD180 RD210 RD220 RD230 RD310 RD320 RD330 RD340 RD350 RD410 RD440 RD450"  style ="margin-bottom:20px">
				    		<input id ="secType"  name = "secType" style ="width:60%;height:50px" class="easyui-combobox"  data-options="
				                    showItemIcon: true,
				                    data: [
				                        {value:'SS',text:' 초',iconCls:'icon-ok'},
				                        {value:'MM:SS',text:' 분:초',iconCls:'icon-ok'},
				                        {value:'HH:MM:SS',text:' 시:분:초',iconCls:'icon-ok',selected:true}
				                    ],
				                    editable: false,
				                    panelHeight: 'auto',
				                    label: '시간 포맷 선택',
				                    labelPosition: 'top',
				                    multiple:false,
				                    required:true
				                    ">
				    	</div>
				    	<div id ="selectTGroupByContainer" class = "component RD110 RD130 RD140 RD210 RD220 RD230 RD310 RD320 RD330 RD340 RD350 RD410 RD430 RD440 RD450 RD510 RD610 RD620 RD630 RD640"  style ="margin-bottom:20px">
				    		 <input id ="groupBy"  name = "groupBy"  style ="width:60%;height:50px" class="easyui-combobox"  data-options="
				                    showItemIcon: true,
				                    data: [
				                        {value:'D',text:' 일별',iconCls:'icon-ok',selected:true},
				                        {value:'M',text:' 월별',iconCls:'icon-ok'},
				                        {value:'W',text:' 요일별',iconCls:'icon-ok'},
				                        <c:if test = "${requestCode != 'RD130' && requestCode != 'RD310' && requestCode != 'RD350' && requestCode != 'RD450'}">
				                        {value:'T60',text:' 시간대별(60분)',iconCls:'icon-ok'},
				                    	</c:if>
				                        <c:if test = "${requestCode != 'RD130' && requestCode != 'RD310' && requestCode != 'RD350' && requestCode != 'RD430' && requestCode != 'RD450'}">
				                        {value:'T30',text:' 시간대별(30분)',iconCls:'icon-ok'},
				                        {value:'T15',text:' 시간대별(15분)',iconCls:'icon-ok'},
				                        {value:'DT60',text:' 일자 + 시간대별(60분)',iconCls:'icon-ok'},
				                        {value:'DT30',text:' 일자 + 시간대별(30분)',iconCls:'icon-ok'},
				                        {value:'DT15',text:' 일자 + 시간대별(15분)',iconCls:'icon-ok'}
				                    	</c:if>
				                    ],
				                    editable: false,
				                    panelHeight: 'auto',
				                    label: '주기',
				                    labelPosition: 'top',
				                    multiple:true,
				                    required:true,
				                     multiple:false,
				                    required:true
				                    ">
				    	</div>
				    	<div  id ="summaryContainer" class = "component RD110"  style ="margin-bottom:20px">
				    				 <input id ="subYN"  name = "subYN" style ="width:60%;height:50px" class="easyui-combobox"  data-options="
				                    showItemIcon: true,
				                    data: [
				                        {value:'Y',text:' 출력',iconCls:'icon-ok',selected:true},
				                        {value:'N',text:' 미출력',iconCls:'icon-ok'}
				                    ],
				                    editable: false,
				                    panelHeight: 'auto',
				                    label : '일자별 소계',
				                    labelPosition: 'top',
				                    multiple:true,
				                    required:true,
				                     multiple:false,
				                    required:true
				                    ">
				    	</div>
				    	
				    	<div id ="menuDepsContainer"  class = "component RD420 RD430 RD440"  style ="margin-top:10px">
		     	    	 	<span class = "date_title" style ="margin-bottom:5px">메뉴 Deps</span>
		     	    	 	<div  style ="width:100%;padding-top:10px">
			     	    	 	<div class ="selweek_margin"><input id ="sLvl1"  class = "menu_deps_check sLvl" type ="radio"   name = "sLvl"  value ="1" checked ><span>1단계</span></div>
					    	 	<div style = "margin-left :13px;float:left" class ="selweek_margin sLvl"><input id ="sLvl2" class = "menu_deps_check" type ="radio" name = "sLvl" value ="2"><span>2단계</span></div>
					    	 	<div style = "margin-left :13px;float:left" class ="selweek_margin sLvl"><input id ="sLvl3" class = "menu_deps_check" type ="radio" name = "sLvl"  value ="3"><span>3단계</span></div>
					    	 	<div style = "margin-left :13px;float:left" class ="selweek_margin sLvl"><input id ="sLvl14" class = "menu_deps_check" type ="radio" name = "sLvl" value ="4"><span>4단계</span></div>
							</div>
				    	</div>
			         </div>   
		         </div>
		     </div>
		   </div>
		<!--  검색 시작일 -->
		<input id ="frDT" type = "hidden" name = "frDT"/>
		<!--  검색 종료일 -->
		<input id ="toDT" type = "hidden" name = "toDT"/>
		   
		<!-- tool for datagrid -->
		<div id="groutool"><input type ="checkbox"  id="grouall_check" onchange = "changeAll('group_grid',this)" /></div>
    	<div id="team_tool"><input type ="checkbox"  id="team_all_check"  onchange = "changeAll('team_grid',this)" /></div>
    	<div id="member_tool"><input type ="checkbox"  id="member_all_check"  onchange = "changeAll('member_grid',this)" /></div>
    	<div id="branch_tool"><input type ="checkbox"  id="branch_all_check" onchange = "changeAll('branch_grid',this)" /></div>
		<!-- end tool for datagrid -->
		
		<script>
			var requestCode = '${requestCode}';
		</script>
		 <script src="js/content.js"></script>
	</c:otherwise>
</c:choose>