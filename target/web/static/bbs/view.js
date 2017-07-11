/**
 * Created by hyunhokim on 2017. 4. 18..
 */
$(document).ready(function () {
    var csrf = "?" + $("#csrf").attr("name") + "=" + $("#csrf").val();

    getAttachFiles($("input[name=no]").val());

    getRepliesList(1);

    var formObj = $("form[role='form']");

    $('#btnModify').on("click", function () {
        formObj.attr("action", "/bbs/modify");
        formObj.attr("method", "GET");
        formObj.submit();
    });

    $("#btnDelete").on("click", function () {
        // formObj.attr("action", "/bbs/remove");
        // formObj.attr("method", "POST");
        // formObj.submit();

        var arr = [];
        $(".attachFiles .panel-group").each(function (index) {
           arr.push($(this).attr("data-src"));
        });

        if(arr.length > 0) {
            $.post("/deleteAllFiles" + csrf, {files:arr}, function () {

            });
        }

        formObj.attr("action", "/bbs/remove");
        formObj.attr("method", "POST");
        formObj.submit();

    });

    $("#btnList").on("click", function () {
        // history.go(-1);
        $("input[name=no]").remove();

        formObj.attr("action", "/bbs/list");
        formObj.attr("method", "GET");
        formObj.submit();
    });

    $("#btnReply").on("click", function () {
        var bno = $("input[name=no]").val();
        var replyer = $("#replyer").val();
        var replyText = $("#replyText").val();

        if (!validation()) {
            return false;
        }

        $.ajax({
            type : 'post',
            url : '/replies' + csrf,
            headers : {
                "Content-Type" : "application/json",
                "X-HTTP-Method-Override" : "POST"
            },
            dataType : 'text',
            data : JSON.stringify({
                bno : bno,
                replyer : replyer,
                replyText : replyText
            }),
            success : function (result) {
                if (result == 'SUCCESS') {
                    alert("등록 되었습니다.");

                    $("#replyText").val("");
                }

                getRepliesList(1);
            }
        });
    });

    $(".pagination").on("click", "li a", function () {
        event.preventDefault();

        var replyPage = $(this).attr("href");

        getRepliesList(replyPage);
    });

});

function getRepliesList(page) {
    var bno = $("input[name=no]").val();
    var url = "/replies/"+bno+"/"+page;

    $.getJSON(url, function (data) {
        var str = "";

        $(data.list).each(function () {
            str += "<div class='panel panel-default'>"
                    + "<div class='panel-heading' data-rno='" + this.rno + "'>"
                    + this.replyer + " (" + commonDate_YYYYMMDD(this.reg_date) + ")"
                    + "</div>"
                    + "<div class='panel-body' style='word-break:break-all;'>"
                    + this.replyText
                    + "</div>"
                + "</div>";
        });

        $("#replies").html(str);

        printPaging(data.pageMaker);
    });
}

function printPaging(pageMaker) {
    var str = "";

    if(pageMaker.prev) {
        str += "<li><a href='" + (pageMaker.startPage - 1) + "' > << </a></li>";
    }

    for (var i = pageMaker.startPage; i <= pageMaker.endPage; i++) {
        var strClass = pageMaker.criteria.page == i ? 'class=active' : '';
        str += "<li " + strClass + "><a href='" + i + "'>" + i + "</a></li>"
    }

    if(pageMaker.next) {
        str += "<li><a href='" + (pageMaker.endPage + 1) + "'> >> </a></li>";
    }

    $(".pagination").html(str);
}

function getAttachFiles(bno) {
    var str = "";

    $.getJSON("/bbs/getAttach/"+bno, function (list) {

        $(list).each(function () {

            if(checkImageType(this)) {
                str += "<div class='col-sm-4'>"
                        + "<div class='panel panel-group' data-src='" + this + "'>"
                            + "<div class='panel panel-info'>"
                                + "<div class='panel-body text-center'>"
                                    + "<img src='/displayFile?fileName=" + getThumnailImageLink(this) + "' />"
                                + "</div>"
                                + "<div class='panel-heading'>"
                                    + "<a href='/displayFile?fileName=" + this + "'>"
                                        + getFullName(this)
                                    + "</a>"
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
                                + "</div>"
                            + "</div>"
                        + "</div>"
                    + "</div>";
            }
        });

        $(".attachFiles").html(str);
    });
}

function validation() {
    var replyText = $("#replyText").val();

    if (replyText.length < 1) {
        alert("댓글 내용을 적어주세요.");
        $("#replyText").focus();

        return false;
    }

    return true;
}