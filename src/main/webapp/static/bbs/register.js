/**
 * Created by hyunhokim on 2017. 4. 18..
 */
$(document).ready(function () {
   var formObj = $("form[role='form']");

   // $('#btnRegister').on("click", function () {
   //    formObj.submit();
   // });

    $('#btnRegister').on("click", function () {
        var that = $(formObj);

        var str = "";

        $(".uploadedList small").each(function (index) {
            if(checkImageType($(this).data("src"))) {
                str += "<input type='hidden' name='files[" + index + "]' value='" + getImageLink($(this).data("src")) + "' />";
            } else {
                str += "<input type='hidden' name='files[" + index + "]' value='" + $(this).data("src") + "' />";
            }
        });

        that.append(str);

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

                    str = "<div class='col-sm-4'>"
                            + "<div class='panel panel-group'>"
                                + "<div class='panel panel-info'>"
                                    + "<div class='panel-body text-center'>"
                                        + "<img src='/displayFile?fileName=" + data + "' />"
                                    + "</div>"
                                    + "<div class='panel-heading'>"
                                        + "<a href='/displayFile?fileName="+getImageLink(data)+"'>"
                                        + getFullName(data) + "</a><small data-src='" + data + "' style='cursor: pointer;'> X</small></div>";
                                    + "</div>"
                                + "</div>"
                            + "</div>"
                        + "</div>";

                    //
                    // str = "<div><a href='/displayFile?fileName="+getImageLink(data)+"'>"
                    //     + "<img src='/displayFile?fileName=" + data + "' />"
                    //     + data + "</a><small data-src='" + data + "'>X</small></div>";
                } else {

                    str = "<div class='col-sm-4'>"
                            + "<div class='panel panel-group'>"
                                + "<div class='panel panel-info'>"
                                    + "<div class='panel-body text-center'>"
                                        + "<img src='/resources/img/default.gif' />"
                                    + "</div>"
                                    + "<div class='panel-heading'>"
                                        + "<a href='/displayFile?fileName="+getImageLink(data)+"'>"
                                        + getOriginalName(data) + "</a><small data-src='" + data + "' style='cursor: pointer;'> X</small></div>";
                                    + "</div>"
                                + "</div>"
                            + "</div>"
                        + "</div>";

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
                    that.closest(".col-sm-4").remove();
                }
            }
        });
    });
});

function checkImageType(fileName) {
    var pattern = /jpg|gif|png|jpeg/i;

    return fileName.match(pattern);
}

//파일
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

//이미지
function getFullName(fullName) {

    var result = fullName.substr(14);

    return result.substr(result.indexOf("_") + 1);
}

function getFileInfo(fullName) {
    var fileName, imgsrc, getLink;

    var fileLink;

    if(checkImageType(fullName)) {
        imgsrc = "/displayFile?fileName=" + fullName;
        fileLink = fullName.substr(14);

        var front = fullName.substr(0, 12);
        var end = fullName.substr(14);

        getLink = "/displayFile?fileName=" + front + end;
    } else {
        imgsrc = "/resources/dist/file.png";
        fileLink = fullName.substr(12);
        getLink = fileLink.substr(fileLink.indexOf("_") + 1);
    }
    fileName = fileLink.substr(fileLink.indexOf("_") + 1);

    return {
        fileName: fileName,
        imgsrc: imgsrc,
        getLink: getLink,
        fullName: fullName
    }
}