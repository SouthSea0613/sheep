function toggleUl() {

    let checkbox = document.querySelectorAll(".chk")
    let listul = document.querySelectorAll("ul")

    for (let i = 0; i < checkbox.length; i++) {
        if (checkbox[i].checked) {

            listul[i].style.display = 'block';


        } else {

            listul[i].style.display = 'none';
            listul[i].querySelectorAll('input[type="checkbox"]').forEach(function (innerCheckbox) {
                innerCheckbox.checked = false;
            });
            listul[i].querySelectorAll('input[type="text"]').forEach(function (textBox) {
                textBox.value = '';
            });
        }
    }
    document.querySelectorAll('input[name="category"]').forEach(function (checkbox) {
        checkbox.addEventListener('change', function () {
            toggleUl(this);
        });
    });
}

