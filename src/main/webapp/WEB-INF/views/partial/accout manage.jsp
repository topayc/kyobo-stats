<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div style ="padding: 10px;width:100%"  data-options="collapsible:false,toolbar">
	 <table id="account_grid" title="사용자 현황" style="width:700px;">
    </table>
    <br/>
    <span style ="font-size:12px; font-family:Dotum, Gulim;color:#555555">* 해당 행을 우클릭하면 사용자별로 액션을 설정할 수 있습니다</span>
    <br/><br/><br/><br/>
	<div style ="padding: 20px;width:700px" class="easyui-panel" title="사용자 생성"  data-options="collapsible:false">
	     <form id="member_form" method="post">
            <div style="margin-bottom:20px">
                <input class="easyui-textbox" name="id" style="width:90%;height:28px" data-options="label:'아이디'">
            </div>
            <div style="margin-bottom:20px">
                <input class="easyui-textbox" name="password" style="width:90%;height:28px" data-options="label:'비밀번호'">
            </div>
             <div style="margin-bottom:20px">
                <input class="easyui-textbox" name="name" style="width:90%;height:28px" data-options="label:'이름'">
            </div>
         
          
        </form>
         <div style="text-align:right;padding:5px 66px">
            <a href="javascript:void(0)" class="easyui-linkbutton" data-options = "iconCls : 'icon-ok'" onclick="submitForm()" style="width:100px" >사용자 생성</a>
            <a href="javascript:void(0)" class="easyui-linkbutton" data-options = "iconCls : 'icon-no'" onclick="clearForm()" style="margin-left : 5px;width:80px">리셋</a>
        </div>
</div>
</div>
<br/>
 <script type="text/javascript">
 
	function submitForm(){
		var id = $('input[name=id]')[0].value;
		var password = $('input[name=password]')[0].value;
		var name = $('input[name=name]')[0].value;
		
		if (!id|| id.length == 0) {
			openMessageWindow("입력오류","생성할 계정의 ID를 입력해주세요");
	    	return;
		}
		if (!password || password.length == 0) {
			openMessageWindow("입력오류","생성할 계정 비밀번호를 입력해주세요");
	    	return;
		}
		
		if (!name || name.length == 0) {
			openMessageWindow("입력오류","생성할 계정 이름를 입력해주세요");
	    	return;
		}
		$.ajax(
		{
		    type: "POST",
		    cache:false,
		    url: 'createMember',
		    dataType: 'json',
		    async: false,
		    data: {id:id , password : password, name : name},
		    success: function (result) {
		    	if (result.resultCode == 1) {
		    		loadContent("계정 관리","MN310");
		    	}
		    	openMessageWindow("사용자 생성",result.message);
		    	
		    }
		});
	}
	function clearForm(){
		loadContent("계정 관리","MN310");
 	}
	
	function formatLock(val,row){
		if (val == "Y"){
            return '<span style="color:red;font-weight:bold">'+val+'</span>';
        } else {
            return '<span style="color:dodgerblue;font-weight:bold">'+val+'</span>';
        }
	}
   
    $('.easyui-panel').panel({doSize:true});
    $('.easyui-linkbutton').linkbutton();
    $('.easyui-textbox').textbox({labelAlign:'right'});
    var toolbar = [{
       text:'저장',
       iconCls:'icon-save',
       handler:function(){alert('save')}
  	 }];
    //1 : on , 2 : block
    var columns = [[
        {field:'UserID',title:'아이디',width:650 / 7,align:'leftr'},
        {field:'UserNM',title:'이름',width:650 / 8,align:'left'},
        //{field:'UserPWDText',title:'비밀번호',width:650 / 3,align:'left'},
        {field:'LockYN',title:'락 상태',width: 50,align:'center',formatter:formatLock},
        {field:'DtRgst',title:'등록일',width:650 / 4,align:'left'}
    ]];
	 var cmenu;
     function createRowMenu(e, index, row){
         cmenu = $('<div/>').appendTo('body');
         cmenu.menu({
        		onClick : function(item){
    	     		console.log(item);
    	     	},
         });
     }

     function clickMenuItem(row, type) {
    	alert(row.memberId + " : " + type);	 
     }
     
     function deleteUser (id) {
    	 var url = "deleteUser?UserID=" + id;
    	 $.getJSON(url, function(result){
    		 loadContent("","MN310");
    		 openMessageWindow("사용자 삭제", result.message)
    	 });
     }
     
     function changeLockStatus(id, status) {
    	 var url = "changeLock";
    	 var data = {UserID : id, lockStatus : status};
    	 $.getJSON(url, data, function(result){
    		 loadContent("","MN310");
    		 openMessageWindow("블럭 상태 변경", result.message)
    	 });
     }
     
	 $(function(){
		 
	     var accoutGrid = $('#account_grid').datagrid({
	     	url : 'users',
	     	height : '350px',
	     	method : 'get',
	    	singleSelect:true,
	     	selectOnCheck: false,
	     	checkOnSelect : false,
	     	rownumbers:true,
	     	fitColumns : true,
	        columns:columns,
	        onLoadSuccess : function(data){
	        	var heaer = $('#account_grid').datagrid('getPanel');
        		heaer.panel('setTitle' ,"사용자 현황 [" + data.total + "]"); 
	        },
            onRowContextMenu : function(e, index, row) {
            	e.preventDefault();
            	console.log("data grid context menu row : ");
            	console.log(row)
            	$('#account_grid').datagrid("selectRow", index);
            	 cmenu = $('<div/>').appendTo('body');
                 cmenu.menu({
                	onClick : function(item){
                		console.log(item);
                		var id = item.memberInfo.UserID;
                		var lock;
                		if (item.action == "block_account"){
                			lock = "Y";
                		}else if (item.action == "active_account"){
                			lock = "N";
                		}
                		switch(item.action) {
                		case "del_account":
                			deleteUser(id);
                			break;
                		case "block_account":
                			changeLockStatus(id, lock);
                			break;
                		case "active_account": 
                			 changeLockStatus(id, lock);
                			break;
                		}
                	}
                 });
            	 
                 var fields; 
            	 var elNames;
            	 if (row.LockYN == "N") {
            		 fields =   ["계정 블럭", "계정 삭제"];
                	 elNames =  ["block_account", "del_account"];
                	 mClasses = ["icon-lock",'icon-clear'];
            	 }else if (row.LockYN== "Y") {
            		 fields =   ["계정 블럭 해제","계정 삭제"];
                	 elNames =  ["active_account", "del_account"];
                	 mClasses = ["icon-man","icon-clear"];
            	 }
            	 for(var i=0; i<fields.length; i++){
                     cmenu.menu('appendItem', {
                         memberInfo : row,
                    	 text: fields[i],
                         name: elNames[i],
                         action: elNames[i],
                         iconCls: mClasses[i]
                     });
                 }
            
                 cmenu.menu('show', {
                     left:e.pageX,
                     top:e.pageY
                 });
            }
	     });
	  
 	});
 </script>
