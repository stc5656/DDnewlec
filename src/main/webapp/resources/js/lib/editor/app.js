window.addEventListener("load", function(event){
    var editorTarget = document.querySelector("textarea");
    var parent = editorTarget.parentElement;

    var webEditor = document.createElement("div"); // 로드
    webEditor.style.background="yellow";

    var request = new XMLHttpRequest();
    request.onload = function(){
        
        webEditor.innerHTML = request.responseText;

        // webeditor를 editorTarget 바로 동생으로 추가드해야 한다.

        editorTarget.after(webEditor);    

        // editor 가로 세로 크기가 입력창에 맞춰지게

        webEditor.


    };

    
    request.open("GET", "template.html", true);
    request.send();


   


});