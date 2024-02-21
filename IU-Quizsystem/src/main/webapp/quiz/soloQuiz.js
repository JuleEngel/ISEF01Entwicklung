function startQuiz() {
	localStorage.setItem('solvedState', 'false');
}

function onSubmit(correctAnswer) {
	document.getElementById("solutionText").style.display = "block";
	
	var allButtons = document.querySelectorAll('.ui-selectonebutton .ui-button');
    allButtons.forEach(function(button) {
		button.style.backgroundColor = 'blue';
	});
    
    var selectedButton = $('.ui-selectonebutton .ui-button.ui-state-active');
    selectedButton.css('background-color', 'yellow');
    
    var correctAnswer = correctAnswer;
    console.log(selectedButton);
    console.log(correctAnswer);
    console.log(allButtons);
}