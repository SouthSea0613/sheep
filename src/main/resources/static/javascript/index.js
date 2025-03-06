var map = new kakao.maps.Map(document.getElementById('map'), {
    center: new kakao.maps.LatLng(37.43887833749142, 126.67511828936453),
    level: 3
});

var marker = new kakao.maps.Marker({
    map: map,
    position: new kakao.maps.LatLng(37.43887833749142, 126.67511828936453)
});

var content =
    '<div class="wrap">' +
    '    <div class="info">' +
    '        <div class="title">' +
    '            인천일보 아카데미' +
    '            <div class="close" onclick="closeOverlay()" title="닫기"></div>' +
    '        </div>' +
    '        <div class="body">' +
    '            <div class="img">' +
    '                <img src="https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/thumnail.png" width="73" height="70">' +
    '           </div>' +
    '            <div class="desc">' +
    '                <div class="ellipsis">미추홀구 매소홀로 488번길 6-32</div>' +
    '                <div class="jibun ellipsis">4층</div>' +
    '                <div><a href="https://www.google.com/" target="_blank" class="link">홈페이지</a></div>' +
    '            </div>' +
    '        </div>' +
    '    </div>' +
    '</div>';

var overlay = new kakao.maps.CustomOverlay({
    content: content,
    map: map,
    position: marker.getPosition()
});

kakao.maps.event.addListener(marker, 'click', function() {
    overlay.setMap(map);
});

function closeOverlay() {
    overlay.setMap(null);
}

function sample6_execDaumPostcode() {
    new daum.Postcode({
        oncomplete : data => {
            if (data.userSelectedType === 'R') {
                $("#sample6_address").val(data.roadAddress);
            }
            else {
                $("#sample6_address").val(data.jibunAddress);
            }
            $("#sample6_detailAddress").focus();
        }
    }).open();
}

function userType(common_user_id, type_id) {
    if (common_user_id) {
        $('#user_name').val(`${user}님`);
        $('.success').css('display','block');
        $('.before').css('display', 'none');
    }
    else {
        $('.success').css('display', 'none');
        $('.before').css('display', 'block');
    }

    if(type_id == 0 || type == null) {
        $('.user').css('display', 'inline-block');
        $('.seller').css('display', 'none');
        $('.engineer').css('display', 'none');
    }
    else if(type_id == 1) {
        $('.null').css('display', 'none');
        $('.user').css('display', 'none');
        $('.seller').css('display', 'inline-block');
        $('.engineer').css('display', 'none');
    }
    else {
        $('.null').css('display', 'none');
        $('.user').css('display', 'none');
        $('.seller').css('display', 'none');
        $('.engineer').css('display', 'inline-block');
    }
}