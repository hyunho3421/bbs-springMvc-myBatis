/**
 * Created by hyunhokim on 2017. 4. 19..
 */
$(document).ready(function () {
    $("#btnRegister").on("click", function(){
        //TODO: 페이지, 검색 정보가 등록 페이지로 이동해도 유지 하도록 수정
        self.location = "/bbs/register";
    });

    $("#btnSearch").on("click", function () {
        self.location = "/bbs/list"
        + '?page=1&perPageNum=10'
        + '&searchType=' + $("select option:selected").val()
        + '&keyword=' + $("#keyword").val();
    });

    //엔터키 눌렀을 떄 검색 되도록
    $("#keyword").keypress(function (e) {
        if (e.keyCode == 13) {
            self.location = "/bbs/list"
                + '?page=1&perPageNum=10'
                + '&searchType=' + $("select option:selected").val()
                + '&keyword=' + $("#keyword").val();
        }
    })

});