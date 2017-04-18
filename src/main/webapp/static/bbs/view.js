/**
 * Created by hyunhokim on 2017. 4. 18..
 */
$(document).ready(function () {
   var formObj = $("form[role='form']");

   console.log(formObj);

   $('.btn-warning').on("click", function () {
      formObj.attr("action", "/bbs/modify");
      formObj.attr("method", "GET");
      formObj.submit();
   });

   $(".btn-danger").on("click", function () {
      formObj.attr("action", "/bbs/remove");
      formObj.attr("method", "POST");
      formObj.submit();
   });

    $(".btn-primary").on("click", function () {
       // formObj.attr("action", "/bbs/list");
       // formObj.attr("method", "GET");
       // formObj.submit();
        self.location = "/bbs/list";
    });
});