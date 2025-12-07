var coll = document.getElementsByClassName("in__sub__menu");

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