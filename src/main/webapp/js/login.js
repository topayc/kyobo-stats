$(function() {
	$('#loginForm').submit(function() {
		$('.blue_message').text("");
		var id = $('#id').val();
		var password = $('#password').val();
		if (id.trim().length == 0) {
			openMessageWindow("입력 오류", 'ID가 입력되지 않았습니다');
			return false;
		}

		if (password.trim().length == 0) {
			openMessageWindow("입력 오류", '비밀번호가 입력되지 않았습니다');
			return false;
		}
		return true;
	});
	$('#id').focus();
});