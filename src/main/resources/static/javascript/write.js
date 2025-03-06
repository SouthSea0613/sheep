function toggleUl(){
    console.log("테스트")
    let checkbox = document.querySelectorAll('input[class="major_category_number"]');
    let listdl = document.querySelectorAll("dl")
    console.log(checkbox)
    for (let i = 0; i < checkbox.length; i++) {
        if (checkbox[i].checked) {
            listdl[i].style.display = 'block';
            console.log(listdl[i])
        } else {
            listdl[i].style.display = 'none';
            listdl[i].querySelectorAll('input[type="checkbox"]').forEach(function (innerCheckbox) {
                innerCheckbox.checked = false;
            });
            listdl[i].querySelectorAll('input[type="text"]').forEach(function (textBox) {
                textBox.value = '';
            });
        }
    }
}

document.querySelectorAll('input[class="major_category_number"]').forEach(function (checkbox) {
    checkbox.addEventListener('change', function () {
        toggleUl(this);
    });
});
