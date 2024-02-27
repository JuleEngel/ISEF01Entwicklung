//resetLogin

function resetLoginInput() {
	var inputField = document.getElementById('mail');
    if (inputField != null) {
        inputField.value = '';
    }
}


document.addEventListener("DOMContentLoaded", function() {
	resetLoginInput();
});