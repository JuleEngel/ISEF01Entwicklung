//clientActivity.js

//Listener zur Überprüfung der Aktivität
document.addEventListener('DOMContentLoaded', function() {
    var inactiveTimeout = 60 * 30 * 1000; // 30 Minuten in Millisekunden bis zum automatischen Ausloggen
    var checkInterval = 60 * 1000; // Überprüfe alle 1 Minute

    var lastActivityTime = Date.now();

	//Überprüfe Aktivität anhand der "currentTime"
    function checkUserActivity() {
        var currentTime = Date.now();
        var inactiveDuration = currentTime - lastActivityTime;
        if (inactiveDuration >= inactiveTimeout) {
            // Benutzer ist inaktiv, logge ihn aus
            window.location.href = 'login/logout.xhtml';
        }
    }

    // Überprüfe alle 'checkInterval'-Millisekunden die Benutzeraktivität
    setInterval(checkUserActivity, checkInterval);

    // Aktualisiere den Zeitstempel bei Benutzeraktivität
    document.addEventListener('keypress', function() {
        lastActivityTime = Date.now();
    });
});