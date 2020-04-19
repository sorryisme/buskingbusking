const NoticeForm = function(){

    //전역 선언부
    return {
        init: function(){
            noticeFormEvent();
        }
    };

    function noticeFormEvent() {
        $("#addBtn").click(function(){
            $("#addForm").submit();
        });
    }

}();


$(document).ready(function(){
    NoticeForm.init();
});

