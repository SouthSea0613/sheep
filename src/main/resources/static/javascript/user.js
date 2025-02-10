function id_check() {
    let data = {};
    const user_id = $('#user_id');
    data.user_id = user_id.val();
    axios.post('/user/id_check', data)
        .then(res => {
            console.log(res);
            if (res.data) {
                $('#id_check').text('사용 가능한 아이디 입니다.');
                $('#id_check_val').val(1);
            } else {
                $('#id_check').text('이미 사용중인 아이디 입니다.')
                $('#id_check_val').val(0);
            }
        })
        .catch(err => {
            $('#id_check').text('');
            $('#id_check_val').val(0);
        })
}

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

function join_check() {
    const user_id = $('#user_id');
    if (user_id.val() == '') {
        alert('아이디를 입력해주세요.');
        user_id.focus();
        return false;
    }
    const id_check_val = $('#id_check_val');
    if (id_check_val.val() == 0) {
        alert('이미 사용중인 아이디 입니다.');
        user_id.focus();
        return false;
    }
    const user_pw = $('#user_pw');
    if (user_pw.val() == '') {
        alert('비밀번호를 입력해주세요.');
        user_pw.focus();
        return false;
    }
    const pw_check_val = $('#pw_check_val');
    if (pw_check_val.val() == 0) {
        alert('비밀번호가 일치하지 않습니다.');
        $('#user_pw2').focus();
        return false;
    }
    const user_name = $('#user_name');
    if (user_name.val() == '') {
        alert('이름을 입력해주세요.');
        user_name.focus();
        return false;
    }
    const user_phone = $('#user_phone');
    if (user_phone.val() == '') {
        alert('전화번호를 입력해주세요.');
        user_phone.focus();
        return false;
    }
    const user_addr = $('#user_addr');
    if (user_addr.val() == '') {
        alert('주소를 입력해주세요.');
        user_addr.focus();
        return false;
    }

    $('#join_form').submit();
}