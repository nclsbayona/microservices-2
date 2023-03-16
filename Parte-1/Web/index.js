var url = "http://localhost:8080/api/paseos"
$(document).ready(function () {

    function _ajax_request(url, data, callback, method, datatype) {
        return jQuery.ajax({
            url: url,
            type: method,
            data: data,
            async: true,
            success: callback,
            headers: { "Accept": datatype, "Content-type":"application/json" }
        });
    }

    jQuery.extend({
        put: function (url, data, callback, datatype) {
            return _ajax_request(url, data, callback, 'PUT', datatype);
        },
        delete: function (url, callback) {
            return _ajax_request(url, null, callback, 'DELETE');
        },
        get: function (url, callback, datatype) {
            return _ajax_request(url, null, callback, 'GET', datatype);
        },
        post: function (url, data, callback) {
            return _ajax_request(url, data, callback, 'POST');
        }
    });

    $("#get-button").click(function () {

        let datatype = $("#get-format").val();
        $.get(url, function (data) {
            console.log(data)
            if (datatype == "application/json")
                $("#get").html(
                    "<pre>" + JSON.stringify(data, null, 4) + "</pre>"
                )
            else if (datatype == "text/xml") {
                var ftext = "";
                $(data).find("paseo").each(function () {
                    ftext += "<h5>ID:" + $(this).find("id").text() + " " + $(this).find("nombre").text() + "</h5>";
                    ftext += "<div>Salida de " + $(this).find("lugar_salida").text() + "</div>";
                    ftext += "<div>Llegada a " + $(this).find("lugar_llegada").text() + "</div>";
                    ftext += "<span>" + $(this).find("fecha").text() + "</span>";
                    ftext += "\n";

                })
                $("#get").html(
                    "<pre>" + ftext + "</pre>"
                )
            }
            else
                $("#get").html(
                    "<p>" + data + "</p>"
                )
        }, datatype
        )
    });

    $("#delete-button").click(function () {

        $.delete(url + "?id=" + $("#delete-id").val(), function (data) {
            console.log(data)
            $("#delete").html(
                "<h6>" + data + "</h6>"
            )
        });
    });

    $("#post-button").click(function () {

        
        $.post(url, JSON.stringify({"lugar_salida":$("#post-salida").val(),"lugar_llegada":$("#post-destino").val(),"nombre":$("#post-nombre").val(),"fecha":$("#post-fecha").val()+'T01:00:00' }), function (data) {
            console.log(data)
            $("#post").html(
                "<h6>" +data + "</h6>"
            )
        }, $("#post-format").val());
    });

    $("#put-button").click(function () {
        let datatype=$("#put-format").val();
        console.log(datatype)
        $.put(url, JSON.stringify({"id":$("#put-id").val(),"lugar_salida":$("#put-salida").val(),"lugar_llegada":$("#put-destino").val()}), function (data) {
            console.log(data)
            let datatype = $("#put-format").val();
            if (datatype == "application/json")
                $("#put").html(
                    "<pre>" + JSON.stringify(data, null, 4) + "</pre>"
                )
            else if (datatype == "text/xml") {
                var ftext = "";
                $(data).find("paseo").each(function () {
                    ftext += "<h5>ID:" + $(this).find("id").text() + " " + $(this).find("nombre").text() + "</h5>";
                    ftext += "<div>Salida de " + $(this).find("lugar_salida").text() + "</div>";
                    ftext += "<div>Llegada a " + $(this).find("lugar_llegada").text() + "</div>";
                    ftext += "<span>" + $(this).find("fecha").text() + "</span>";
                    ftext += "\n";

                })
                $("#put").html(
                    "<pre>" + ftext + "</pre>"
                )
            }
            else
                $("#put").html(
                    "<p>" + data + "</p>"
                )
        }, datatype
        )
    });
});
