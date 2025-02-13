function loginStatus() {
    if (user) {
        $('#user_name').val(`${user}님`);
        $('.success').css('display','block');
        $('.before').css('display', 'none');
    } else {
        $('.success').css('display', 'none');
        $('.before').css('display', 'block');
    }
}