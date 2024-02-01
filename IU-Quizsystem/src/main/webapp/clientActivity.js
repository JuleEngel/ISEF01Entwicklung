document.addEventListener('DOMContentLoaded', function() {
    var inactiveTimeout = 30 * 60 * 1000; // 30 Minuten in Millisekunden bis zum automatischen Ausloggen
    var checkInterval = 60000; // Überprüfe alle 1 Minute

    var lastActivityTime = Date.now();

    function checkUserActivity() {
		console.log("Überprüfe Aktivität")
        var currentTime = Date.now();
        var inactiveDuration = currentTime - lastActivityTime;
        if (inactiveDuration >= inactiveTimeout) {
            // Benutzer ist inaktiv, logge ihn aus
            window.location.href = 'logout.xhtml';
        }
    }

    // Überprüfe alle 'checkInterval'-Millisekunden die Benutzeraktivität
    setInterval(checkUserActivity, checkInterval);

    // Aktualisiere den Zeitstempel bei Benutzeraktivität

    document.addEventListener('keypress', function() {
        lastActivityTime = Date.now();
    });
});