function idCheck() {
    let data = {};
    const user_id = $('#user_id');
    data.user_id = user_id.val();
    axios.post('idCheck', data)
        .then(res => {
            if (res.data) {
                $('idCheck').text('사용 가능한 아이디 입니다.');
                $('idCheckVal').val(1);
            } else {
                $('idCheck').text('이미 사용중인 아이디 입니다.')
                $('idCheckVal').val(0);
            }
        })
        .catch(err => {
            $('idCheck').text('');
            $('idCheckVal').val(0);
        })
}

function pwCheck() {
    const user_pw = $('#user_pw');
    if (user_pw.val() != '') {
        if (user_pw.val() == $('#user_pw2').val()) {
            $('pwCheck').text('비밀번호가 일치 합니다.');
            $('pwCheckVal').val(1);
        } else {
            $('#pwCheck').text('비밀번호가 일치하지 않습니다.');
            $('#pwCheckVal').val(0);
        }
    } else {
        $('#pwCheck').text('');
        $('#pwCheckVal').val(0);
    }
}

function joinCheck() {
    const user_id = $('#user_id');
    if (user_id.val() == '') {
        alert('아이디를 입력해주세요.');
        user_id.focus();
        return false;
    }
    const idCheckVal = $('#idCheckVal');
    if (idCheckVal.val() == 0) {
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
    const pwCheckVal = $('#pwCheckVal');
    if (pwCheckVal.val() == 0) {
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
    //주소 넣어야함
    $('#join_form').submit();
}

// 회원정보수정(추가인증)
    function addPwCheck() {
    const user_pw = $('#user_pw');
    if (user_pw.val() != '') {
        if(user_pw.val() == $('#user_pw')) {
            alert('추가 인증이 완료되었습니다.');
            location.hfef="/user/";    // 컨트롤러 주소 나오면 수정 예정
        }
        else {
            alert('추가 인증이 실패되었습니다.');
            user_pw.focus();
            return false;
        }
    }

    function insertInfo() {

    }
}
