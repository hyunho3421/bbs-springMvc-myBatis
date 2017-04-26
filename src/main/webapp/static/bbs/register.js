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

});