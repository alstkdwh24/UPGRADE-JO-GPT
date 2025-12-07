const apply__group = document.getElementById('apply__group');

  // 초기에는 버튼을 숨깁니다.
  apply__group.classList.add('apply__hidden');

  // 체크박스들의 NodeList 가져오기
  const tbody__box = document.querySelectorAll('.tbody__checkBox');

  // 체크박스들에 이벤트 리스너 추가
  tbody__box.forEach(checkbox => {
    checkbox.addEventListener('change', () => {
      let anyCheckboxChecked = false;

      // 체크된 체크박스가 있는지 확인
      tbody__box.forEach(checkbox => {
        if (checkbox.checked) {
          anyCheckboxChecked = true;
        }
      });

      // 체크된 체크박스가 하나 이상 있는 경우 버튼 표시
      if (anyCheckboxChecked) {
        apply__group.classList.remove('apply__hidden');
      } else { // 그렇지 않으면 버튼 숨김
        apply__group.classList.add('apply__hidden');
      }
    });
  });