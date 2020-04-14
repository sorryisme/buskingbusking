const MemberForm = function(){
    "use constrict";
    //전역변수 선언부

    return {
        init : function(){
            MemberFormEvent();
        }
    };
    //함수 선언부
    function MemberFormEvent(){
        $("#addBtn").click(function(){
            $("#formData").submit();
        });
    }



}();


$(document).ready(function(){
    MemberForm.init();
});