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