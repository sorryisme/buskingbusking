const MemberForm = function(){
    "use constrict";
    //전역변수 선언부

    return {
        init : function(){
            memberFormInit();
            memberFormEvent();
        }
    };
    //함수 선언부

    function memberFormInit() {
        $("#InterestInfoDiv").hide();
    }

    function memberFormEvent(){
        $("#addBtn").click(function () {
            $("#formData").submit();
        });

        $("#type1").click(function () {
            $("#InterestInfoDiv").hide();
            $(".mainDiv").css("height", "1024px");
        });

        $("#type2").click(function () {
            $("#InterestInfoDiv").show();
            $(".mainDiv").css("height", "1240px");
        })

        $("#msgY").click(function () {
            $("#messageIdDiv").show();
        })

        $("#msgN").click(function () {
            $("#messageIdDiv").hide();
        })
    }



}();


$(document).ready(function(){
    MemberForm.init();
});