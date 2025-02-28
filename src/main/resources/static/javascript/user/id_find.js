function id_find_email() {
	let data = {};
	const id_find_email = $('#id_find_email');
	data.user_email = id_find_email.val();
	axios.post('/user/id_find_email', data)
		.then(res => {
			if (res.data) {
				$('#id_find_email_check').text('잘못된 형식의 메일 입니다.');
				$('#id_find_val').val(0);
			} else {
				$('#id_find_email_check').text('');
				$('#id_find_val').val(1);
			}
		})
		.catch(err => {
			$('#id_find_email_check').text('');
			$('#id_find_val').val(0);
		})
}

function id_find() {
	let data = {};
	const id_find_email = $('#id_find_email');
	data.user_email = id_find_email.val();
	axios.post('/user/id_find_email_id', data)
		.then(res => {
			const id_find_email_id = res.data;
			if (id_find_email != null) {
				$('#id_find_res').text('회원님의 ID는 ' + id_find_email_id + '입니다.');
				$('#id_find_res_val').val(1);

				$('#id_find_login').css('display', 'block')
				$('#id_find_pwreset').css('display', 'block')
			} else {
				$('#id_find_res').text('등록된 아이디가 없습니다');
				$('#id_find_res_val').val(0);

				$('#id_find_login').css('display', 'none')
				$('#id_find_pwreset').css('display', 'none')
			}
		})
}

