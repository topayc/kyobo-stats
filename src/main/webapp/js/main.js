var selectedMenu = {};
var appNavigation = {deps1 : "",deps2 : "",deps3 :""};


function loadContent(codeName, requestCode) {
	$.ajaxSetup({async : true});
	updateNavigation();
	$('#content_container').load("/stats/content?requestCode=" + requestCode, function(response, status, xhr){
		if (status != "success") location.href = 'login';
	});
}

function updateNavigation(){
	var contentPanel = $('.container').layout('panel','center');
	var naviArr = [];
	for (var property in appNavigation){
		if (!appNavigation[property] ){
			continue;
		}
		naviArr.push(appNavigation[property]);
	}
	var naviStr = naviArr.join(' > ');
	contentPanel.panel({title :  naviStr});
}

$(document).ready(function(){
	$('#pass_chn_btn').bind('click', function(e){
		e.preventDefault();
		appNavigation = {deps1 : "비밀번호 관리", deps2 : '', deps3:''};
		updateNavigation();
		loadContent("","user_form");
	})
	
	$('#submenu_tree').tree({
		animate : true,
		lines : true,
		onSelect: function(node){
			var deps = $(node.target).find('.report_sub_menu').attr('deps');
			var selectedCode = $(node.target).find('.report_sub_menu').attr("request_code");
			var selectedText = $(node.text).text();
			switch (deps) {
			case "1":
				appNavigation = {deps1 : selectedText, deps2 : '', deps3:''};
				loadContent('', 'intro');
				return;
			case "2":
				appNavigation['deps2'] = selectedText;
				appNavigation['deps3'] = "";
				loadContent('', 'intro');
				break;
			case "3":
				var parentNode = $('#submenu_tree').tree('getParent', node.target);
				var p_parentNode = $('#submenu_tree').tree('getParent', parentNode.target);
				appNavigation['deps1'] = $(p_parentNode.text).text();
				appNavigation['deps2'] = $(parentNode.text).text();
				appNavigation['deps3'] = selectedText;
				
				selectedMenu.requestCode = selectedCode;
				selectedMenu.text = selectedText;
				loadContent(selectedText,selectedCode);
				break;
			}
		}
	});
	var initNode = $('#submenu_tree').tree('find','root_node');
	$('#submenu_tree').tree('select', initNode.target);
});