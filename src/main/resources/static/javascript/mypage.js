function writebtn() {
    //기술자정보
    const engineer_regnum =$('#engineer_regnum').val();
    const insertInfo = $('#insertInfo');
    const engineer_form = $('#engineer_form')
    if(engineer_regnum!=null){
        alert("수정이 완료되었습니다");
        insertInfo.submit();
        engineer_form.submit();
    }else{
        alert("수정이 완료되었습니다");
        insertInfo.submit();
        // location.href='/mypage/write'
    }

}