var map = new kakao.maps.Map(document.getElementById('map'), {
	center: new kakao.maps.LatLng(37.43887833749142, 126.67511828936453),
	level: 3
});

var marker = new kakao.maps.Marker({
	position: map.getCenter()
});

marker.setMap(map);
kakao.maps.event.addListener(map, 'click', function(mouseEvent) {
	var latlng = mouseEvent.latLng;
	marker.setPosition(latlng);

	var message = '클릭한 위치의 위도는 ' + latlng.getLat() + ' 이고, ';
	message += '경도는 ' + latlng.getLng() + ' 입니다';

	var resultDiv = document.getElementById('clickLatlng');
	resultDiv.innerHTML = message;
});

function userType(user_id, type_id) {
	if (user_id) {
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