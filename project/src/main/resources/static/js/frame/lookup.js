/* 버튼 클릭 */

var game__content__page = document.querySelector(".table__page__list");
var page__num = game__content__page.querySelectorAll(".page__button");
for (var i = 0; i < page__num.length; i++) {
  page__num[i].addEventListener("click", function() {
  var current = document.getElementsByClassName("page__active");
  current[0].className = current[0].className.replace(" page__active", "");
  this.className += " page__active";
  });
}





const purchaseButton = document.getElementById('purchase');
  const addBasketButton = document.getElementById('add__basket');

  // 초기에는 버튼을 숨깁니다.
  purchaseButton.classList.add('hidden');
  addBasketButton.classList.add('hidden');

  // 체크박스들의 NodeList 가져오기
  const checkboxes = document.querySelectorAll('.tbody__checkBox');

  // 체크박스들에 이벤트 리스너 추가
  checkboxes.forEach(checkbox => {
    checkbox.addEventListener('change', () => {
      let anyCheckboxChecked = false;

      // 체크된 체크박스가 있는지 확인
      checkboxes.forEach(checkbox => {
        if (checkbox.checked) {
          anyCheckboxChecked = true;
        }
      });

      // 체크된 체크박스가 하나 이상 있는 경우 버튼 표시
      if (anyCheckboxChecked) {
        purchaseButton.classList.remove('hidden');
        addBasketButton.classList.remove('hidden');
      } else { // 그렇지 않으면 버튼 숨김
        purchaseButton.classList.add('hidden');
        addBasketButton.classList.add('hidden');
      }
    });
  });

// 구매 버튼 클릭시 ajax로 controller 전송
  const submitBtn = document.getElementById('purchase');

// 버튼에 클릭 이벤트 리스너 추가
submitBtn.addEventListener('click', () => {
    // 선택된 체크박스의 값을 수집
    const checkboxes = document.querySelectorAll('.tbody__checkBox:checked');
    const selectedValues = Array.from(checkboxes).map(checkbox => checkbox.value);

    // AJAX 요청을 통해 서버의 컨트롤러로 데이터 전송
    const xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function() {
        if (xhr.readyState === XMLHttpRequest.DONE) {
            if (xhr.status === 200) {
                // 요청이 성공적으로 처리됨
                console.log('Data sent successfully');
            } else {
                // 요청이 실패함
                console.error('Error sending data');
            }
        }
    };

    // POST 요청 보내기
    xhr.open('POST', '/submitData', true);
    xhr.setRequestHeader('Content-Type', 'application/json');
    xhr.send(JSON.stringify({ selectedValues: selectedValues }));
});


