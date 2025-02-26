function pw_check() {
	const user_pw = $('#user_pw');
	if (user_pw.val() != '') {
		if (user_pw.val() == $('#user_pw2').val()) {
			$('#pw_check').text('비밀번호가 일치 합니다.');
			$('#pw_check_val').val(1);
		} else {
			$('#pw_check').text('비밀번호가 일치하지 않습니다.');
			$('#pw_check_val').val(0);
		}
	} else {
		$('#pw_check').text('');
		$('#pw_check_val').val(0);
	}
}

// 회원정보수정(추가인증)
function addPwCheck() {
	const user_pw = $('#user_pw');
	if (user_pw.val() != '') {
		if (user_pw.val() == $('#user_pw')) {
			alert('추가 인증이 완료되었습니다.');
			location.hfef = "/user/";    // 컨트롤러 주소 나오면 수정 예정
		} else {
			alert('추가 인증이 실패되었습니다.');
			user_pw.focus();
			return false;
		}
	}
}

function test(user_email) {
	let data = {};
	data.user_email = user_email;
	axios.post('/user/email_check', data)
		.then(res => {
			console.log(res);
			if (res.data) {
				$('#email_check').text('사용 가능한 이메일입니다.');
				$('#email_check_val').val(1);
			} else {
				$('#email_check').text('이미 사용중인 이메일입니다.')
				$('#email_check_val').val(0);
			}
		})
		.catch(err => {
			$('#id_check').text('');
			$('#id_check_val').val(0);
		});
}

function self_insert() {
	const user_email_detail = $('#user_email_detail');
	const email_input = $('#email_input')

	if (user_email_detail.val() === 'other') {
		email_input.css('display', 'block')
	} else {
		email_input.css('display', 'none')

		let user_email_id = $('#user_email');
		const user_email = user_email_id.val() + "@" + user_email_detail.val();
		test(user_email);
	}
}

function self_insert_check() {
	let user_email_id = $('#user_email');
	const email_input = $('#email_input');
	const user_email = user_email_id.val() + "@" + email_input.val();
	test(user_email);
}