		var codeInfos = [];
			var months = ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'];
			var weeks =  ['일','월','화','수','목','금','토'];
			var param = {};
			var curSelWeek = {};
			
			$('.report_sub_menu').each(function(){
				var code = $(this).attr('request_code');
				var progName = $(this).find('span').text();
				codeInfos.push({code : code, progName : progName});
			});
			
			if (requestCode != 'intro') {
				setViewInit(requestCode);
				setViewLayout();
				setFormInit();
				//loadAgents();
			}
			
			function openReportWindow(){
				var reportUrl = "report";
				param.requestCode = requestCode;
				if ($('input[name=frDT]').length) param.frDT = $('input[name=frDT]').val();
				if ($('input[name=toDT]').length) param.toDT = $('input[name=toDT]').val();
				if ($('input[name=frTM]').length) param.frTM = $('input[name=frTM]').val();
				if ($('input[name=toTM]').length) param.toTM = $('input[name=toTM]').val();;
				if ($('input[name=ani]').length) param.ani = $('input[name=ani]').val();
				if ($('input[name=sid]').length) param.sid= $('input[name=sid]').val();
				if ($('input[name=selweek]').length){ 
					var selweeks = [];  
					for (var property in curSelWeek) {
						if (property == "all") {
							if (curSelWeek[property].checked == true ) {
								selweeks = ['1','2','3','4','5','6','7'];
								break;
							}
						}else {
							if (curSelWeek[property].checked == true ) {
								selweeks.push(curSelWeek[property].value);		
							}
						}
					}
					selweeks.sort();
					param.selWeek = selweeks.join(',');
				}
				if ($('input[name=sLvl]').length){ 
					//메뉴 뎁스 값은 라디오 버튼 이벤트에서 처리함
				}
				
				if ($('input[name=accType]').length) param.accType = $('input[name=accType]').val();
				if ($('input[name=calltreat]').length) param.calltreat = $('input[name=calltreat]').val();
				if ($('input[name=ronaYN]').length) param.ronaYN = $('input[name=ronaYN]').val();
				if ($('input[name=secType]').length) param.secType = $('input[name=secType]').val();
				if ($('input[name=groupBy]').length) param.groupBy = $('input[name=groupBy]').val();
				if ($('input[name=subYN]').length) param.subYN = $('input[name=subYN]').val();
				
				/* 상담 그룹, 상담팀, 상담직원, 지점 그리드 데이타 조사*/
				var gridDataArrs = [];
				if ($('#group_grid').length) {
					var selectedGroupRows = $('#group_grid').datagrid('getSelections');
					for (var i =0 ; i<selectedGroupRows.length; i++){
						gridDataArrs.push(selectedGroupRows[i].groupID);
					}
					param.groupID = gridDataArrs.join(',');
					gridDataArrs.length = 0;
				}
				if ($('#team_grid').length) {
					var selectedTeamRows = $('#team_grid').datagrid('getSelections');
					for (var i =0 ; i<selectedTeamRows.length; i++){
						gridDataArrs.push(selectedTeamRows[i].teamID);
					}
					param.teamID = gridDataArrs.join(',');
					gridDataArrs.length = 0;
				}
				if ($('#member_grid').length) {
					var selectedMemberRows = $('#member_grid').datagrid('getSelections');
					for (var i =0 ; i<selectedMemberRows.length; i++){
						gridDataArrs.push(selectedMemberRows[i].agentID);
					}
					param.agentID = gridDataArrs.join(',');
					gridDataArrs.length = 0;
				}
				if ($('#branch_grid').length) {
					var selectedBranchRows = $('#branch_grid').datagrid('getSelections');
					for (var i =0 ; i<selectedBranchRows.length; i++){
						gridDataArrs.push(selectedBranchRows[i].selBRCode);
					}
					param.selBRCode = gridDataArrs.join(',');
					gridDataArrs.length = 0;
				}
				/* 상담 그룹, 상담팀, 상담직원, 지점 그리드 데이타 조사 끝*/
				
				if (!validateParameter(param)) return;
				var formURLParam = $.param(param);
				//복수 윈도우 허용
				var myWindow = window.open(reportUrl + "?" + formURLParam, "", "width=1200,height=900,location=no");
			}
			
			function validateParameter (param) {
				if ($('input[name=frTM]').length && !param.frTM ) {
					$('#win').text(" 시작 시각을 입력하지 않았습니다");
					$('#win').window({width:400,height:200,modal:true,title:'&nbsp;입력 오류'});
					return false;
				}
				
				if ($('input[name=toTM]').length && !param.toTM ) {
					$('#win').text(" 종료 시각을 입력하지 않았습니다");
					$('#win').window({width:400,height:200,modal:true,title:'입력 오류'});
					return false;
				}
				return true;
			}
			
			/* 데이타 테이블 전체 선택 및 해제 */
			function changeAll(type, elem) {
				var selector = "#" + type;;
				var gridMethod = ""
				if (elem.checked ) gridMethod = "selectAll";
				else gridMethod = "unselectAll";
				$(selector).datagrid(gridMethod);
				if (type == "group_grid" || type == "team_grid"){
					if (gridMethod == "selectAll") {
						if (type == 'group_grid') {
							//$('#team_grid').datagrid("unselectAll");
							//$('#team_all_check').attr('checked', false);
						}else if (type == 'team_grid'){
							//$('#group_grid').datagrid("unselectAll");
							//$('#grouall_check').attr('checked', false);
						}
					}
					$('#member_grid').datagrid("unselectAll");
					$('#member_all_check').attr('checked', false)	
					loadAgents();
				}
			}
			
			/* 메뉴별 그리드 및 검색 옵션을 표시 및 초기화,반드시 뷰의 컴포넌트를 초기화 하기 전에 호출해야 함 */
			function setViewInit(code){
				var notFilter = "."+code;
				$('.component').not(notFilter).remove();
				if ($('.easyui-datagrid').length == 0) {
					//$('#search_container').layout('collapse','west');
				}
			}
			
			
			function loadAgents (){
				var searchParams = {};
				var gridDataArrs = [];
				if ($('#group_grid').length) {
					var selectedGroupRows = $('#group_grid').datagrid('getSelections');
					for (var i =0 ; i<selectedGroupRows.length; i++){
						gridDataArrs.push(selectedGroupRows[i].groupID);
					}
					searchParams.groupID = gridDataArrs.join(',');
					gridDataArrs.length = 0;
				}
				if ($('#team_grid').length) {
					var selectedTeamRows = $('#team_grid').datagrid('getSelections');
					for (var i =0 ; i<selectedTeamRows.length; i++){
						gridDataArrs.push(selectedTeamRows[i].teamID);
					}
					searchParams.teamID = gridDataArrs.join(',');
					gridDataArrs.length = 0;
				}
				
				$.getJSON(
					"getAgents", {groupID : searchParams.groupID , teamID :  searchParams.teamID },
					function(result){
						var agentData = [];
						for (var i =0; i < result.length ; i++){
							var agent = result[i];
							agentData.push({agentID: agent.agentID, name : agent.agentName, identifier: "M"});
						}
						$('#member_grid').datagrid({data : agentData});
					}
				);
			}
			/* 요청 코드별 검색 레이아웃 조정*/
			function setViewLayout() {
				
				if ($('.easyui-datagrid').length){
					$('.easyui-datagrid').datagrid({
						fitColumns: true,
						onUnselect : function(index, row) {
							var identifier = row.identifier;
							if (identifier == "G" || identifier == "T") {
								if (identifier == "G" ) {
									//$('#team_grid').datagrid("unselectAll");
									//$('#team_all_check').attr('checked', false);
								}else if (identifier == "T" ) {
									//$('#group_grid').datagrid("unselectAll");
									//$('#grouall_check').attr('checked', false);
								}
								$('#member_grid').datagrid("unselectAll");
								$('#member_all_check').attr('checked', false)	
							}
							if (identifier && (identifier == "G" || identifier == "T")){
								loadAgents();
							}
						},
						onSelect: function(index,row){
							var identifier = row.identifier;
							if (identifier == "G" || identifier == "T") {
								if (identifier == "G" ) {
									//$('#team_grid').datagrid("unselectAll");
									//$('#team_all_check').attr('checked', false);
								}else if (identifier == "T" ) {
									//$('#group_grid').datagrid("unselectAll");
									//$('#grouall_check').attr('checked', false);
								}
								$('#member_grid').datagrid("unselectAll");
								$('#member_all_check').attr('checked', false)	
							}
							if (identifier && (identifier == "G" || identifier == "T")){
								loadAgents();
							}
						}
					});		
				}
			
				$('.easyui-linkbutton').linkbutton();
				$('.easyui-panel').panel();
				$('.easyui-datetimebox').datetimebox();
				$('#search_btn').bind("click", function(){openReportWindow();});
				$('#reset_btn').bind("click", function(){
					loadContent(selectedMenu.text, selectedMenu.requestCode);
				});
				
				var startDate = new Date();
				var endDate = new Date();
				$('#searchStartDate').calendar({
					months : months,
					weeks : weeks,
					current:startDate,
					onSelect : function(date) {
						$('#frDT').val(date.getFullYear()+"-"+(formatDate(date.getMonth()+1))+"-"+ formatDate(date.getDate()));	
					
					}
				});
				$('#searchDate').calendar({
					months : months,
					weeks : weeks,
					current:endDate,
					onSelect : function(date) {
						$('#frDT').val(date.getFullYear()+"-"+ (formatDate(date.getMonth()+1)) +"-"+formatDate(date.getDate()));	
					
					}
				});
				$('#searchEndDate').calendar({
					months : months,
					weeks : weeks,
					current:new Date(),
					onSelect : function(date) {
						$('#toDT').val(date.getFullYear()+"-"+ (formatDate(date.getMonth()+1)) +"-"+ formatDate(date.getDate()));	
					
					}
				});
				//set default date whew this form first loaded;
				$('#frDT').val(startDate.getFullYear()+"-"+formatDate((startDate.getMonth()+1))+"-"+formatDate(startDate.getDate()));
				$('#toDT').val(endDate.getFullYear()+"-"+formatDate((endDate.getMonth()+1))+"-"+formatDate(endDate.getDate()));
				
				//timpspinner formmater 
				$.fn.timespinner.defaults.parser = function(s){
					var opts = $(this).timespinner('options');
					var label = opts.label;
					if (!s){return null;}
					var tt = s.split(opts.separator);
					if (tt.length == 1 ) {
						var parseDate; 
						switch(s.length){
							//입력 문자열의 길이가 1~ 2개일 경우 시간으로 간주 
							case 1:
							case 2:
								parseDate = new Date(
										1900,
										0,
										0,
										isNaN(s.substr(0,2)) ? (label == "시작 시각"? 7 : 20) :parseInt(s.substr(0,2),10),
										parseInt(tt[1],10)||0,
										0);
								break;
						
							//입력 문자열의 길이가 3~ 4개일 경우 시간, 분으로 간주  
							case 3:
							case 4:
								parseDate = new Date(1900,
										0,
										0,
										isNaN(s.substr(0,2)) ? (label == "시작 시각"? 7 : 20) :parseInt(s.substr(0,2),10),  
										parseInt(s.substr(2),10)||0, 
										0);
								break;
								
							//그외의 경우 잘못 입력한 것으로 간주, 디폴트 시간, 분으로 세팅
							default:
								parseDate = new Date(
										1900,
										0,
										0,
										(label == "시작 시각"? 7 : 20),
										0, 
										0);
								break;
						}
						return parseDate;
					}else if (tt.length > 1 ){
						return new Date(
								1900,
								0,
								0, 
								isNaN(tt[0]) ? (label == "시작 시각"? 7 : 20) : parseInt(tt[0],10),
								parseInt(tt[1],10)||0, 
								parseInt(tt[2],10)||0);
					}
				}
				
				if ($('#searchStartTime').length) {
					$('#searchStartTime').timespinner({value : "07:00"});
				}
				if ($('#searchEndTime').length) $('#searchEndTime').timespinner({value: "20:00"});
				if ($('#searchTime').length) $('#searchTime').timespinner({value : "07:0 b0"});
				
				if ($('.menu_deps_check').length) {
					$('.menu_deps_check').iCheck({
					      checkboxClass: 'icheckbox_square-blue',
					      radioClass: 'iradio_square-blue',
					      increaseArea: '20%' // optional
					 });
					$('.menu_deps_check').on('ifChecked',function(event){
						param.sLvl = $(this).val();
					});
				}
				if ($('.selweek').length){ 
					$('.selweek').iCheck({
					      checkboxClass: 'icheckbox_square-blue',
					      radioClass: 'iradio_square-blue',
					      increaseArea: '20%' // optional
					 });
					
					$('.selweek').on('ifChecked', function(event){
						  if ($(this).val() == "all") {
							  selweek_auto_trigger = false;
							  $('.selweek').each(function(){
								if ($(this).val() != 'all'){
									curSelWeek[$(this).attr('week')] = {checked :true, value : $(this).val()}
									$(this).iCheck('check');
								}
							});
						  }else {
							  selweek_auto_trigger = false;
							  curSelWeek[$(this).attr('week')] = {checked : true, value : $(this).val()}
						  }
					});
					var selweek_auto_trigger = false;
					$('.selweek').on('ifUnchecked', function(event){
						 if ($(this).val() == "all") {
							 if(selweek_auto_trigger){
								 selweek_auto_trigger = false;
							 }else {
								 $('.selweek').each(function(){
									if ($(this).val() != 'all'){
										curSelWeek[$(this).attr('week')] = {checked : false, value : $(this).val()}
										$(this).iCheck('uncheck');
									}
								});
								 selweek_auto_trigger = false
							 }
						 }else {
							 selweek_auto_trigger  = true;
						 	$('#week_all').iCheck("uncheck");	
							curSelWeek[$(this).attr('week')] = {checked : false, value : $(this).val()}
						 }
					});
				}
		
				if ($('#calltreat').length) $('#calltreat').combobox({});
				if ($('#secType').length) $('#secType').combobox();
				if ($('#ronaYN').length) $('#ronaYN').combobox();
				if ($('#subYN').length) $('#subYN').combobox();
				if ($('#groupBy').length) $('#groupBy').combobox();
				if ($('#accType').length) $('#accType').combobox();
				if ($('#ani').length) $('#ani').textbox();
				if ($('#sid').length) $('#sid').textbox();
				
				//레이아웃 조정은 다른 컴포넌트가 적용된 후 호출
				$('.easyui-layout').layout();
				//데이타 그리드가 없는 경우 해당 레이아웃을 제거 
				if ($('.easyui-datagrid').length == 0) {
					$('#search_container').layout('remove','west');
				}
			}
			 
			function setFormInit(){
				//메뉴 뎁스 라디오버튼 초기화
				if ($('.sLvl').length) {
					$('#sLvl1').iCheck("check");
					param.sLvl = "1";
				}
				
				//요일 체크박스 초기화 
				$('.selweek').each(function(){
					if ($(this).val() != 'all' && $(this).val() != '7' && $(this).val() != '1'){
						$(this).iCheck('check');
						curSelWeek[$(this).attr('week')] = {checked : true, value : $(this).val()}
					}else {
						$(this).iCheck('uncheck');
						curSelWeek[$(this).attr('week')] = {checked : false, value : $(this).val()}
					}
				});
			}