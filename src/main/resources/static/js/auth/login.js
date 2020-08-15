const LoginForm = function(){
    "use constrict";
    //전역변수 선언부

    return {
        init : function(){
            LoginFormEvent();
        }
    };
    //함수 선언부
    function LoginFormEvent(){
        $("#loginBtn").click(function(){
            $("#loginForm").submit();
        });
    }



}();


$(document).ready(function(){
    LoginForm.init();
});