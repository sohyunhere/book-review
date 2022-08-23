function checkAll() {
    if (!checkExistData(writePostForm.postTitle.value, "제목을")) {
        writePostForm.postTitle.focus();
        return false;
    }
    if(!checkReadDate(writePostForm.readDate.value)){
        writePostForm.readDate.focus();
        return false;
    }

    if (!checkExistData(writePostForm.bookTitle.value, "도서 제목을")) {
        writePostForm.bookTitle.focus();
        return false;
    }
    if (!checkExistData(writePostForm.author.value, "지은이를")) {
        writePostForm.author.focus();
        return false;
    }
    if (!checkExistData(writePostForm.publisher.value, "출판사")) {
        writePostForm.publisher.focus();
        return false;
    }
    if (!checkExistData(writePostForm.categoryId.value, "카테고리")) {
        writePostForm.category.focus();
        return false;
    }
    // if (!checkExistData(writePostForm.content.value, "내용을")) {
    //     writePostForm.content.focus();
    //     return false;
    // }
    if(!checkExistData(editor.getMarkdown(), "내용울")){
        return false;
    }
    alert(editor.getHTML());
    if(confirm("게시글을 등록하시겠습니까?")) {
        let header = $("meta[name='_csrf_header']").attr('content');
        let token = $("meta[name='_csrf']").attr('content');
        let content = editor.getHTML();
        $.ajax({
            async: true,
            type : "post",
            data : JSON.stringify({
                postTitle : $("#postTitle").val().trim(),
                readDate : $("#readDate").val(),
                bookTitle : $("#bookTitle").val().trim(),
                author : $("#author").val().trim(),
                publisher : $("#publisher").val().trim(),
                categoryId : $("#categoryId").val(),
                // content : $("#content").val(),
                content : content,
                formFile : $("#formFile").val()
            }),
            url : "/board/write",
            contentType : "application/json; charset=UTF-8",
            beforeSend: function(xhr){
                xhr.setRequestHeader(header, token);
            },
            success:
                function (postId) {
                    alert("게시글 등록이 완료되었습니다");
                    location.href = "/board/"+ postId;
                },
            error :
                function (request, status, error){
                    alert("게시글 등록 실패"+ "code:"+request.status+"\n"+" message : " + request.responseText +"\n"+"error:"+error);
                }
        });
    }
    return true;
}
function checkReadDate(value){
    if (!checkExistData(value, "읽은 날짜")) {
        return false;
    }

    if(value){
        let today = new Date();

        let year = today.getFullYear();
        let month = ('0' + (today.getMonth() + 1)).slice(-2);
        let day = ('0' + today.getDate()).slice(-2);
        let dateString = year + '-' + month  + '-' + day;

        if(value > dateString){
            alert("책을 읽은 날짜는 오늘 날짜까지 선택 가능합니다.")
            return false;
        }
    }
    return true;
}
function checkExistData(value, dataName) {
    value = value.trim();
    if (value == "") {
        alert(dataName + " 입력해주세요!");
        return false;
    }
    return true;
}