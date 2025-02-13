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

function userType() {
    if(type == 0 || type == null) {
        $('.user').css('display', 'inline-block');
        $('.seller').css('display', 'none');
        $('.engineer').css('display', 'none');
    } else if(type == 1) {
        $('.null').css('display', 'none');
        $('.user').css('display', 'none');
        $('.seller').css('display', 'inline-block');
        $('.engineer').css('display', 'none');
    } else {
        $('.null').css('display', 'none');
        $('.user').css('display', 'none');
        $('.seller').css('display', 'none');
        $('.engineer').css('display', 'inline-block');
    }
}