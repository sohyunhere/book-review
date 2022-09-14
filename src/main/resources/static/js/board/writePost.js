let lan = "";
let lng = "";
$(document).ready(function() {

    if (navigator.geolocation) {
        // GeoLocation을 이용해서 접속 위치를 얻어옵니다
        navigator.geolocation.getCurrentPosition(function (position) {
            lan = position.coords.latitude; // 위도
            lng = position.coords.longitude; // 경도

        });
    }
});

const Editor = toastui.Editor;

const editor = new Editor({
    el: document.querySelector('#editor'),
    height: '600px',
    initialEditType: 'wysiwyg',
    initialValue: '',
    previewStyle: 'vertical',

    hooks : {
        addImageBlobHook : async (blob, callback) => {
            let upload = await uploadImage(blob);
            callback(upload, '');
        }
    }
});

function uploadImage(blob) {
    let header = $("meta[name='_csrf_header']").attr('content');
    let token = $("meta[name='_csrf']").attr('content');

    let formData = new FormData();
    let fileUrlData;
    formData.append('image', blob); // 이미지를 폼데이터 file로 변경 'image'가 input name이다.
    $.ajax({
        url : 'image/editorUpload',
        enctype: 'multipart/form-data',
        type: 'POST',
        data: formData,
        processData: false,
        contentType: false,
        async: false, // 비동기를 동기로 변경.
        beforeSend: function(xhr){
            xhr.setRequestHeader(header, token);
        },
        success:
            function (data){
                fileUrlData = data
            },
        error :
            function (request, status, error){
                alert("이미지 없로드 실패"+ "code:"+request.status+"\n"+" message : " + request.responseText +"\n"+"error:"+error);
            }
    });
    return fileUrlData;
}

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
    if(!checkExistData(editor.getMarkdown(), "내용울")){
        return false;
    }

    if(confirm("게시글을 등록하시겠습니까?")) {
        let header = $("meta[name='_csrf_header']").attr('content');
        let token = $("meta[name='_csrf']").attr('content');
        let content = editor.getHTML();
        let formData = new FormData();
        let fileUpload = $("#formFile");
        let files = fileUpload[0].files;

        $(files).each(function (i, file) { // 여러 개의 파일을 업로드할 때 formData에 저장
            formData.append("uploadfile", file);

        });
        console.log(formData);

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
            writtenDate : moment().format(),
            content : content,
            formFile : $("#formFile").val(),
            lat : lan,
            lng : lng
            }),
            url : "/board/write",
            // processData: false,
            contentType : "application/json; charset=UTF-8",
            // contentType : false,
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
        let today = moment().format();
        if(value > today){
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