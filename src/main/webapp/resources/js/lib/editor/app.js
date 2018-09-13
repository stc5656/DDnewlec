
// test 페이지가 로드 되었을 때
window.addEventListener("load", function(event){
    var editorTarget = document.querySelector("textarea");
    var parent = editorTarget.parentElement;

    // 넓이, 높이를 각 크기에 맞게해주는 코드
    var width = window.getComputedStyle(editorTarget,null).getPropertyValue("width");
    var height = window.getComputedStyle(editorTarget,null).getPropertyValue("height");    
    
    var webEditor = document.createElement("div"); // 로드
    webEditor.contentEditable = "true";
    webEditor.style.width = width;
    webEditor.style.height = height;   
    webEditor.style.background = "pink";

    var request = new XMLHttpRequest();

    // template이 로드됨
    request.onload = function(){
        
        webEditor.innerHTML = request.responseText;

        // webeditor를 editorTarget 바로 동생으로 추가해야 한다.

        editorTarget.after(webEditor);    
        
        var iframe = webEditor.querySelector("iframe");
        var win = iframe.contentWindow;



    // body 페이지가 로드 되었을 때 
    win.addEventListener("load", function(){

        var doc = win.document;
        var boldButton = webEditor.querySelector(".bold-button");

        boldButton.onclick = function(e){
            doc.execCommand("bold");

            }
       })

   
    }
    
    request.open("GET", "template.html", true);
    request.send();



});