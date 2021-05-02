String.prototype.trim = function(){return this.replace(/(^\s*)|(\s*$)/gi, "");}

function openMessageWindow(t,m) {
	var elStr = '<div id="message_window" style ="padding:30px;font-weight:bold;font-family:Gulim, Dotum;font-size:12px;color:#0092DC"></div>';
	$(elStr).appendTo('body');
	$('#message_window').text(m);
	$('#message_window').window({
		width:400,
		height:200,
		modal:true,
		title:t,
		onClose : function(){
			$("#message_window").window('destroy');
		}
	});
}

function formatDate(date) {
	var sDate;
	if (date < 10) {
		sDate = "0"+ date;
	}else {
		sDate = date.toString();
	}
	
	return sDate;
}

