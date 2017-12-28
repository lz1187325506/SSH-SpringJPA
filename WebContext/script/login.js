$(document).ready(function () {
    //json 
    $("#sub1").click(function () { 
        console.log($("#form1").serializeJson());
        $.ajax({
            type: "post",
            url: "/sjpa/ajax",
            data:JSON.stringify($("#form1").serializeJson()),
            dataType:"json",
            contentType:"application/json;charset=utf-8",
            success: function (response) {
                console.log(response);
            }
        });
     });
     //jsonp
     $("#sub2").click(function () { 
        console.log(JSON.stringify($("#form2").serializeJson()));
        $.ajax({
            type: "post",
            url: "/sjpa/ajax",
            data:JSON.stringify($("#form2").serializeJson()),
            dataType:"jsonp",
            contentType:"application/json;charset=utf-8",
            success: function (response) {
                console.log(response);
            },
            error:function (response) { 
                var jsonp =response.responseText;
                console.log("error:"+jsonp);
             }
        });
     });
 });

