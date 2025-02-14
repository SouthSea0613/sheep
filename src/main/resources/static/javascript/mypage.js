function writebtn() {
    // const username = $('.user_name').val();
    // const userid = $('.user_id').val();
    // const userphonecompany = $('.user_phone_company').val();
    // const userphone = $('.user_phone').val();
    // const useraddr = $('.user_addr').val();
    let user_email = $('#user_email');
    // const useremaildetail = $('.user_email_detail').val();
    //
    //기술자정보
    const engineer_regnum = $('#engineer_regnum').val();
    const insertInfo = $('#insert_info');
    const engineer_form = $('#engineer_form')
    const user_email_detail = $('#user_email_detail');
    const email_input = $('#email_input');
    console.log("엥" + insertInfo);
    console.log(engineer_form);
    if (engineer_regnum != null) {
        if (user_email_detail.val() == 'other') {
            user_email.val(user_email.val() + "@" + email_input.val());
        } else {
            user_email.val(user_email.val() + "@" + user_email_detail.val());
        }
        alert("수정이 완료되었습니다");
        insertInfo.submit();
        engineer_form.submit();
        // location.href='/mypage/write'
    } else {
        if (user_email_detail.val() == 'other') {
            user_email.val(user_email.val() + "@" + email_input.val());
        } else {
            user_email.val(user_email.val() + "@" + user_email_detail.val());
        }
        alert("수정이 완료되었습니다");
        insertInfo.submit();
        // location.href='/mypage/write'
    }
}