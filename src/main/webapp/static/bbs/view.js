/**
 * Created by hyunhokim on 2017. 4. 18..
 */
$(document).ready(function () {
    getRepliesList(1);

    var formObj = $("form[role='form']");

    $('#btnModify').on("click", function () {
        formObj.attr("action", "/bbs/modify");
        formObj.attr("method", "GET");
        formObj.submit();
    });

    $("#btnDelete").on("click", function () {
        formObj.attr("action", "/bbs/remove");
        formObj.attr("method", "POST");
        formObj.submit();
    });

    $("#btnList").on("click", function () {
        // history.go(-1);
        formObj.attr("action", "/bbs/list");
        formObj.attr("method", "GET");
        formObj.submit();
    });

    $("#btnReply").on("click", function () {
        var bno = $("input[name=no]").val();
        var replyer = $("#replyer").val();
        var replyText = $("#replyText").val();

        $.ajax({
            type : 'post',
            url : '/replies',
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
                    getRepliesList();

                    $("#replyer").val("");
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
                    + "<div class='panel-body'>"
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

    for (var i = pageMaker.startPage; i< pageMaker.endPage; i++) {
        var strClass = pageMaker.criteria.page == i ? 'class=active' : '';
        str += "<li " + strClass + "><a href='" + i + "'>" + i + "</a></li>"
    }

    if(pageMaker.next) {
        str += "<li><a href='" + (pageMaker.endPage + 1) + "'> >> </a></li>";
    }

    $(".pagination").html(str);
}