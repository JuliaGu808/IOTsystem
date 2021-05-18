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
                    $("#device_data").append("<tr><td>" + item.deviceName + "</td>" +
                        "<td>" + item.temperature + "</td>" +
                        "<td>" + item.humidity + "</td>" +
                        "<td>" + item.recordtime + "</td>" +
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
