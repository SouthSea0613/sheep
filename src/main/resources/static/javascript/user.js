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
    //  통신사 추가
    const user_phone_company = $('#user_phone_company');
    if (user_phone_company.val() == 'none') {
        alert('통신사를 선택해주세요.');
        user_phone_company.focus();
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

    // 이메일 추가
    let user_email = $('#user_email');
    const user_email_detail = $('#user_email_detail');
    if (user_email.val() == '' || user_email_detail.val() == 'none') {
        alert('이메일을 바르게 입력해주세요.');
        return false;
    } else {
        if (user_email_detail.val() == 'none') {
            const email_input = $('#email_input');
            user_email.val(user_email.val() + '@' + email_input.val());
        } else {
            user_email.val(user_email.val() + '@' + user_email_detail.val());
        }
    }
    $('#join_form').submit();
}


// 이메일 비동기
function test(user_email) {
    let data = {};
    data.user_email = user_email;
    axios.post('/user/email_check', data)
        .then(res => {
            console.log(res);
            if (res.data) {
                $('#email_check').text('사용 가능한 이메일 입니다.');
                $('#email_check_val').val(1);
            } else {
                $('#email_check').text('이미 사용중인 아이디 입니다.')
                $('#email_check_val').val(0);
            }
        })
        .catch(err => {
            $('#id_check').text('');
            $('#id_check_val').val(0);
        });
}

function self_insert(){
    const user_email_detail = $('#user_email_detail').val();
    const email_input = $('#email_input')

    if(user_email_detail === '직접입력'){
        email_input.css('display', 'block')
    }else {
        email_input.css('display', 'none')

        let user_email_id = $('#user_email');
        const user_email_detail = $('#user_email_detail');
        const user_email = user_email_id.val() + '@' + user_email_detail.val();
        test(user_email);
    }
}

function self_insert_check(){
    let user_email_id = $('#user_email');
    const email_input = $('#email_input');
    const user_email = user_email_id.val() + '@' + email_input.val();
    test(user_email);
}

// 로그인 비동기
function login() {
    let data = {};
    const user_id = $('#user_id');
    const user_pw = $('#user_pw');
    data.user_id = user_id.val();
    data.user_pw = user_pw.val();
    console.log(data);
    axios.post('/user/login', data)
        .then(res => {
            console.log(res);
            if (res.data) {
                location.href="/";
            } else {
                $('#error_user').text('아이디 비밀번호를 확인해주세요.')
                $('#error_user_val').val(0);
            }
        })
        .catch(err => {
            $('#error_user').text('');
            $('#error_user_val').val(0);
        })
}
