function writebtn() {
    // const username = $('.user_name').val();
    // const userid = $('.user_id').val();
    // const userphonecompany = $('.user_phone_company').val();
    // const userphone = $('.user_phone').val();
    // const useraddr = $('.user_addr').val();
    // const useremail = $('.user_email').val();
    // const useremaildetail = $('.user_email_detail').val();
    //
    //기술자정보
    const engineer_regnum =$('#engineer_regnum').val();
    const insertInfo = $('#insertInfo');
    const engineer_form = $('#engineer_form')
    console.log("엥"+insertInfo);
    console.log(engineer_form);
    if(engineer_regnum!=null){
        alert("수정이 완료되었습니다");
        insertInfo.submit();
        engineer_form.submit();
        // location.href='/mypage/write'


    }else{
        alert("수정이 완료되었습니다");
        insertInfo.submit();
        // location.href='/mypage/write'
    }

}