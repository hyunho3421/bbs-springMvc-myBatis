/**
 * Created by hyunhokim on 2017. 4. 18..
 */
$(document).ready(function () {
    var formObj = $("form[role='form']");
    var bno = $("#no").val();

    getAttachFiles(bno);

    $('#btnCancel').on("click", function () {
        var no = $('#no').val();
        document.location = "/bbs/view?no=" + no;

        formObj.attr("action", "/bbs/view");
        formObj.attr("method", "GET");
        formObj.submit();
    });

    $("#btnModify").on("click", function () {
        formObj.attr("action", "/bbs/modify");
        formObj.attr("method", "POST");

        var str = "";

        $(".attachFiles small").each(function (index) {
            str += "<input type='hidden' name='files[" + index + "]' value='" + $(this).data("src") + "' />";
        });

        formObj.append(str);

        formObj.submit();
    });

    $(".attachFiles").on("click", "small", function () {
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
                                        + getFullName(data) + "</a><small data-src='" + getImageLink(data) + "' style='cursor: pointer;'> X</small></div>";
                                    + "</div>"
                                + "</div>"
                            + "</div>"
                        + "</div>";

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

                $(".attachFiles").append(str);
            }
        });
    });
});

function getAttachFiles(bno) {
    var str = "";

    $.getJSON("/bbs/getAttach/" + bno, function (list) {
        $(list).each(function () {

            if (checkImageType(this)) {
                str += "<div class='col-sm-4'>"
                        + "<div class='panel panel-group'>"
                            + "<div class='panel panel-info'>"
                                + "<div class='panel-body text-center'>"
                                    + "<img src='/displayFile?fileName=" + getThumnailImageLink(this) + "' />"
                                + "</div>"
                                    + "<div class='panel-heading'>"
                                        + "<a href='/displayFile?fileName=" + this + "'>"
                                            + getFullName(this)
                                        + "</a>"
                                        + "<small data-src='" + this + "' style='cursor: pointer;'> X</small>"
                                    + "</div>"
                                + "</div>"
                            + "</div>"
                        + "</div>";
            } else {
                str += "<div class='col-sm-4'>"
                        + "<div class='panel panel-group'>"
                            + "<div class='panel panel-info'>"
                                + "<div class='panel-body text-center'>"
                                    + "<img src='/resources/img/default.gif' />"
                                + "</div>"
                                    + "<div class='panel-heading'>"
                                        + "<a href='/displayFile?fileName=" + this + "'>"
                                        + getOriginalName(this)
                                    + "</a>"
                                    + "<small data-src='" + this + "' style='cursor: pointer;'> X</small>"
                                + "</div>"
                            + "</div>"
                        + "</div>"
                    + "</div>";
            }

        });

        $(".attachFiles").html(str);
    });

}
