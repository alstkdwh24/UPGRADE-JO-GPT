//화면에 따른 아이콘 위치 조정
function updateSearchIconPosition(inputElement, searchIcon) {
   const inputStyles = getComputedStyle(inputElement);
   const paddingRight = parseFloat(inputStyles.paddingRight);
   const inputWidth = inputElement.offsetWidth;

   // 아이콘 위치를 input 오른쪽 끝에서 패딩 10px 위치로 설정
   searchIcon.style.left = `${inputWidth - paddingRight - 10}px`;
}

document.addEventListener('DOMContentLoaded', () => {
   const inputElement = document.getElementById('realAppBarMenuSearchInput');
   const searchIcon = document.querySelector(".searchIcon");

   if (inputElement && searchIcon) {
      updateSearchIconPosition(inputElement, searchIcon);

      window.addEventListener('resize', () => {
         updateSearchIconPosition(inputElement, searchIcon);
      });
   }
   // 배열에 따른 버튼 생성
   const realAppBarMenuLogoMenu=document.querySelector(".realAppBarMenuLogoMenu");
   const realAppBarMenuLogoMenuButton=["강의", "로드맵", "공부기록", "커뮤니티"];

   realAppBarMenuLogoMenuButton.forEach((buttonText) => {
      const button = document.createElement("button");
      button.className = "realAppBarMenuLogoMenuButton";
      button.textContent = buttonText;
      realAppBarMenuLogoMenu.appendChild(button);
      button.style="font-size:11px; height:100%; border:none; background-color:transparent; color:black; cursor:pointer;";
   });

   const realAppBarMenuLogoMenuLogo2=document.querySelector(".realAppBarMenuLogoMenuLogo2");
});




