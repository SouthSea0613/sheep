function login() {
	let data = {};
	const user_id = $('#user_id');
	const user_pw = $('#user_pw');
	data.user_id = user_id.val();
	data.user_pw = user_pw.val();
	axios.post('/user/login', data)
		.then(res => {
			console.log(res);
			if (res.data) {
				location.href = "/";
			} else {
				$('#error_user').text('아이디 비밀번호를 확인해주세요.')
				$('#error_user_val').val(0);
			}
		})
		.catch(err => {
			$('#error_user').text('');
			$('#error_user_val').val(0);
		});
}
