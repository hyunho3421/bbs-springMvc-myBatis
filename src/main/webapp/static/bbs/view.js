/**
 * Created by hyunhokim on 2017. 4. 18..
 */
$(document).ready(function () {
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
        self.location = "/bbs/list";
    });
});