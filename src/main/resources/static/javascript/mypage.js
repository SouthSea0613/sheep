function writebtn() {

    //기술자정보
    const engineer_regnum =$('#engineer_regnum').val();
    const insert_info = $('#insert_info');
    const engineer_form = $('#engineer_form')
    const user_email_detail = $('#user_email_detail');
    const email_input = $('#email_input');
    let user_email = $('#user_email');

    if(engineer_regnum!=null){
        if (user_email_detail.val() == 'other') {
            user_email.val(user_email.val() + "@" + email_input.val());
        } else {
            user_email.val(user_email.val() + "@" + user_email_detail.val());
        }
        alert("수정이 완료되었습니다");
        insert_info.submit();
        engineer_form.submit();
        // location.href='/mypage/write'


    }else{
        if (user_email_detail.val() == 'other') {
            user_email.val(user_email.val() + "@" + email_input.val());
        } else {
            user_email.val(user_email.val() + "@" + user_email_detail.val());
        }
        alert("수정이 완료되었습니다");
        insert_info.submit();
        // location.href='/mypage/write'
    }

}