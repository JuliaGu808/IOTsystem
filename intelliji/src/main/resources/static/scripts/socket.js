var ws;
ws = new WebSocket('ws://localhost:8080/device');
ws.onmessage = function(data){
    showGreeting(data.data);
}


function showGreeting(message) {
    var arr = message.split(",");
    var deviceName = arr[0].split("=")[1];
    var deviceHolder = arr[1].split("=")[1];
    var temperature = arr[2].split("=")[1];
    var humidity = arr[3].split("=")[1];
    var record_time = arr[4].split("=")[1];
    var unix = Number(record_time);
    var recordtime = new Date(unix * 1000);
    var dateString = recordtime.getUTCFullYear()
        +"/"+  ("0" + (recordtime.getUTCMonth()+1)).slice(-2) + "/" +
        ("0" + recordtime.getUTCDate()).slice(-2) + " " +
        ("0" + recordtime.getUTCHours()).slice(-2) + ":" +
        ("0" + recordtime.getUTCMinutes()).slice(-2) + ":" +
        ("0" + recordtime.getUTCSeconds()).slice(-2);

    $("#device_real_data").prepend("<tr><td>" + deviceName + "</td>" +
        "<td>" + temperature + "</td>" +
        "<td>" + humidity + "</td>" +
        "<td>" + dateString + "</td>" +
        "<td>" + deviceHolder + "</td></tr>");

}