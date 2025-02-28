function check() {
	const radio = $('input[name=join_additional]:checked').val();
	const addForm_seller = $('#join_addForm_seller');
	const addForm_engineer = $('#join_addForm_engineer');

	if (radio === 'seller') {
		addForm_seller.css('display', 'block')
		addForm_engineer.css('display', 'none')

	} else if (radio === 'engineer') {
		addForm_engineer.css('display', 'block')
		addForm_seller.css('display', 'none')
	}
}

function seller_regnum_check() {
	let data = {};
	const seller_regnum = $('#seller_regnum');
	data.seller_regnum = seller_regnum.val();
	axios.post('/user/seller_regnum_check', data)
		.then(res => {
			console.log(res);
			if (res.data) {
				$('#seller_regnum_check').text('사용 가능한 사업자등록번호 입니다.');
				$('#seller_regnum_val').val(1);
			} else {
				$('#seller_regnum_check').text('이미 사용중인 사업자등록번호 입니다.')
				$('#seller_regnum_val').val(0);
			}
		})
		.catch(err => {
			$('#seller_regnum_check').text('');
			$('#seller_regnum_val').val(0);
		})
}

function join_addCheck_seller() {
	const seller_regnum = $('#seller_regnum');
	if (seller_regnum.val() == '') {
		alert('사업자등록번호 를 입력하세요.');
		seller_regnum.focus();
		return false;
	}
	const seller_regnum_val = $('#seller_regnum_val');
	if (seller_regnum_val.val() == 0) {
		alert('이미 사용중인 사업자등록번호 입니다.');
		seller_regnum.focus();
		return false;
	}
	const seller_name = $('#seller_name');
	if (seller_name.val() == '') {
		alert('대표자 성함을 입력하세요.');
		seller_name.focus();
		return false;
	}
	const seller_addr = $('#seller_addr');
	if (seller_addr.val() == '') {
		alert('사업자 주소를 입력하세요.');
		seller_addr.focus();
		return false;
	}
	$('#join_addForm_seller').submit();
}

function engineer_regnum_check() {
	let data = {};
	const engineer_regnum = $('#engineer_regnum');
	data.engineer_regnum = engineer_regnum.val();
	axios.post('/user/engineer_regnum_check', data)
		.then(res => {
			console.log(res);
			if (res.data) {
				$('#engineer_regnum_check').text('사용 가능한 자격증번호입니다.');
				$('#engineer_regnum_val').val(1);
			} else {
				$('#engineer_regnum_check').text('이미 사용중인 자격증번호입니다.');
				$('#engineer_regnum_val').val(0);
			}
		})
		.catch(err => {
			console.log(err);
			$('#engineer_regnum_check').text('');
			$('#engineer_regnum_val').val(0);
		})
}

function join_addCheck_engineer() {
	const engineer_regnum = $('#engineer_regnum');
	if (engineer_regnum.val() == '') {
		alert('자격증번호를 입력해주세요.');
		engineer_regnum.focus();
		return false;
	}
	const engineer_regdate = $('#engineer_regdate');
	if (engineer_regdate.val() == '') {
		alert('취득일을 입력해주세요.');
		engineer_regdate.focus();
	}
	$('#join_addForm_engineer').submit();
}