var ws;
ws = new WebSocket('ws://localhost:8080/device');
ws.onmessage = function(data){
    showGreeting(data.data);
}

function showGreeting(message) {
    var arr = message;
    console.log(arr);

    $("#greetings").append("<tr><td style='text-align: left') >"
        + message + "</td></tr>");

}