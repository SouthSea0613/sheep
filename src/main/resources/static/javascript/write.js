function toggleUl(checkbox) {
    let ul = checkbox.parentElement.nextElementSibling;

    if (checkbox.checked) {
        ul.style.display = 'block';
        console.log(checkbox.id + ' 체크되었습니다.');
    } else {
        ul.style.display = 'none';
        ul.querySelectorAll('input[type="checkbox"]').forEach(function(innerCheckbox) {
            innerCheckbox.checked = false;
        });
        ul.querySelectorAll('input[type="text"]').forEach(function (textBox) {
            textBox.value = '';
        });
    }
}

document.querySelectorAll('input[name="pcategory"]').forEach(function(checkbox) {
    checkbox.addEventListener('change', function() {
        toggleUl(this);
    });
});