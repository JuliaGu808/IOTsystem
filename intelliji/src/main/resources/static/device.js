window.onload=function (){
    refresh();
}

function refresh(){
    $.ajax({
        url:'http://localhost:8080/DeviceDBService/all',
        type:'GET',
        success:[function (result){
            if(result!==null){
                document.getElementById("device_data").innerHTML="";
                result.forEach(function (item){
                    var unix = Number(item.recordtime);
                    var recordtime = new Date(unix * 1000);
                    var dateString = recordtime.getUTCFullYear()
                        +"/"+  ("0" + (recordtime.getUTCMonth()+1)).slice(-2) + "/" +
                        ("0" + recordtime.getUTCDate()).slice(-2) + " " +
                        ("0" + recordtime.getUTCHours()).slice(-2) + ":" +
                        ("0" + recordtime.getUTCMinutes()).slice(-2) + ":" +
                        ("0" + recordtime.getUTCSeconds()).slice(-2);
                    $("#device_data").append("<tr><td>" + item.deviceName + "</td>" +
                        "<td>" + item.temperature + "</td>" +
                        "<td>" + item.humidity + "</td>" +
                        "<td>" + dateString + "</td>" +
                        "<td>" + item.deviceHolder + "</td></tr>");
                })
            }
            else{
            }
        }],
        error : [function(error)
        {
            alert(error);
        }]
    });

}
