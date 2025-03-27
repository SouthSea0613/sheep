function writebtn() {
    const engineer_regnum =$('#engineer_regnum').val();
    const insert_info = $('#insert_info');
    const engineer_form = $('#engineer_form')
    const user_email_detail = $('#user_email_detail');
    const email_input = $('#email_input');
    let user_email = $('#user_email');

    const address = $('#sample6_address');
    const detail_address = $('#sample6_detailAddress');
    const  user_addr = $('#user_addr');
    user_addr.val(address.val() +'@'+ detail_address.val());

    if (address.val() == '') {
        alert('주소를 입력하세요.');
        return false;
    }
    if (detail_address.val() == '') {
        alert('상세주소를 입력하세요.');
        detail_address.focus();
        return false;
    }

    if(engineer_regnum!=null){
        if (user_email_detail.val() == 'other') {
            user_email.val(user_email.val() + "@" + email_input.val());
        } else {
            user_email.val(user_email.val() + "@" + user_email_detail.val());
        }
        alert("수정이 완료되었습니다");
        insert_info.submit();
        engineer_form.submit();
    } else {
        if (user_email_detail.val() == 'other') {
            user_email.val(user_email.val() + "@" + email_input.val());
        } else {
            user_email.val(user_email.val() + "@" + user_email_detail.val());
        }
        alert("수정이 완료되었습니다");
        insert_info.submit();
    }
}