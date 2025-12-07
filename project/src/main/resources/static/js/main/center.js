var notice = document.getElementById("notice");
var faq = document.getElementById("faq");
var qna = document.getElementById("qna");
var center__below__notice = document.querySelector(".center__below__notice");
var center__below__faq = document.querySelector(".center__below__faq");
var center__below__qna = document.querySelector(".center__below__qna");

notice.style.borderTop = "1px solid rgb(224, 224, 224)";
notice.style.borderLeft = "1px solid rgb(224, 224, 224)";
notice.style.borderRight = "1px solid rgb(224, 224, 224)";
notice.style.color = "rgb(243, 138, 19)";
center__below__notice.style.display = "block";
center__below__faq.style.display = "none";
center__below__qna.style.display = "none";

// 공지사항 버튼 클릭시 
notice.addEventListener("click", function(){

  notice.style.borderTop = "1px solid rgb(224, 224, 224)";
  notice.style.borderLeft = "1px solid rgb(224, 224, 224)";
  notice.style.borderRight = "1px solid rgb(224, 224, 224)";
  notice.style.color = "rgb(243, 138, 19)";
  center__below__notice.style.display = "block";


  center__below__faq.style.display = "none";
  faq.style.borderBottom = "1px solid rgb(224, 224, 224)";
  faq.style.color ="black";

  center__below__qna.style.display = "none";
  qna.style.borderBottom = "1px solid rgb(224, 224, 224)";
  qna.style.color ="black";

});

// faq 버튼 클릭시 
faq.addEventListener("click", function(){

  faq.style.borderTop = "1px solid rgb(224, 224, 224)";
  faq.style.borderLeft = "1px solid rgb(224, 224, 224)";
  faq.style.borderRight = "1px solid rgb(224, 224, 224)";
  faq.style.color = "rgb(243, 138, 19)";
  center__below__faq.style.display = "block";

  center__below__notice.style.display = "none";
  notice.style.borderBottom = "1px solid rgb(224, 224, 224)";
  notice.style.color ="black";

  center__below__qna.style.display = "none";
  qna.style.borderBottom = "1px solid rgb(224, 224, 224)";
  qna.style.color ="black";

});


// qna 버튼 클릭시 
qna.addEventListener("click", function(){

  qna.style.borderTop = "1px solid rgb(224, 224, 224)";
  qna.style.borderLeft = "1px solid rgb(224, 224, 224)";
  qna.style.borderRight = "1px solid rgb(224, 224, 224)";
  qna.style.color = "rgb(243, 138, 19)";
  center__below__qna.style.display = "block";

  center__below__faq.style.display = "none";
  faq.style.borderBottom = "1px solid rgb(224, 224, 224)";
  faq.style.color ="black";

  center__below__notice.style.display = "none";
  notice.style.borderBottom = "1px solid rgb(224, 224, 224)";
  notice.style.color ="black";

});




var coll = document.getElementsByClassName("section__sub__menu");

for (var i = 0; i < coll.length; i++) {
  coll[i].addEventListener("click", function() {
    // 현재 클릭된 메뉴 요소
    var currentMenu = this;
    
    // 현재 클릭된 메뉴의 드롭다운 컨텐츠
    var content = currentMenu.nextElementSibling;
    
    // 현재 클릭된 메뉴를 제외한 다른 모든 드롭다운 메뉴 닫기
    for (var j = 0; j < coll.length; j++) {
      if (coll[j] !== currentMenu) {
        coll[j].classList.remove("active");
        coll[j].nextElementSibling.style.maxHeight = null;
      }
    }

    // 현재 클릭된 메뉴의 드롭다운 메뉴 열고 닫기
    currentMenu.classList.toggle("active");
    if (content.style.maxHeight){
      content.style.maxHeight = null;
    } else {
      content.style.maxHeight = content.scrollHeight + "px";
    } 
  });
}

