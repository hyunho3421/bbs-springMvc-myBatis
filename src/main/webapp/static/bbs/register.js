/**
 * Created by hyunhokim on 2017. 4. 18..
 */
$(document).ready(function () {
   var formObj = $("form[role='form']");

   $('#btnRegister').on("click", function () {
      formObj.submit();
   });

   $("#btnList").on("click", function () {
      // self.location = "/bbs/list";

       formObj.attr("method", "GET");
       formObj.attr("action", "/bbs/list");
       formObj.submit();
   });
   
    $(".fileDrop").on("dragenter dragover", function (event) {
        event.preventDefault();
    });

    $(".fileDrop").on("drop", function (event) {
        event.preventDefault();

        var files = event.originalEvent.dataTransfer.files;

        var file = files[0];

        var formData = new FormData();

        formData.append("file", file);

        $.ajax({
            url: '/uploadAjax',
            data: formData,
            dataType: 'text',
            processData: false,
            contentType: false,
            type: 'POST',
            success: function (data) {
                alert(data);

                var str = "";

                if (checkImageType(data)) {
                    str = "<div><a href='/displayFile?fileName="+getImageLink(data)+"'>"
                        + "<img src='/displayFile?fileName=" + getImageLink(data) + "' />"
                        + data + "</a><small data-src='" + data + "'>X</small></div>";
                } else {
                    str = "<div><a href='/displayFile?fileName=" + data + "'>"
                        + getOriginalName(data)
                        + "</a><small data-src='" + data + "'> X</small></div>";
                }

                $(".uploadedList").append(str);
            }
        });
    });

    $(".uploadedList").on("click", "small", function () {
        var that = $(this);

        $.ajax({
            url: "/deleteFile",
            type: "POST",
            data: {
                fileName: $(this).attr("data-src")
            },
            dataType: "text",
            success: function (result) {
                if (result == 'deleted') {
                    that.parent("div").remove();
                }
            }
        });
    });
});

function checkImageType(fileName) {
    var pattern = /jpg|gif|png|jpeg/i;

    return fileName.match(pattern);
}

function getOriginalName(fileName) {
    if(checkImageType(fileName)) {
        return;
    }

    var idx = fileName.indexOf("_") + 1;

    return fileName.substr(idx);
}

function getImageLink(fileName) {
    if (!checkImageType(fileName)) {
        return;
    }

    var front = fileName.substr(0,12);

    var end = fileName.substr(14);

    return front + end;
}