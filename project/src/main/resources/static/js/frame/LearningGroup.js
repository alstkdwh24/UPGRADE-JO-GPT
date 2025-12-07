const radio1 = document.getElementById('tutorial');
const radio2 = document.getElementById('learning');

// radio1을 선택하면 radio2의 선택이 해제되도록 설정
radio1.addEventListener('change', function() {
    if (radio1.checked) {
        radio2.checked = false;

        if(radio1.value == 0){
          document.getElementById("post__type").innerHTML = "튜토리얼";
        }else{
          document.getElementById("post__type").innerHTML = "교육영상";
        }

    }
});

// radio2를 선택하면 radio1의 선택이 해제되도록 설정
radio2.addEventListener('change', function() {
    if (radio2.checked) {
        radio1.checked = false;

        if(radio2.value == 1){
          document.getElementById("post__type").innerHTML = "교육영상";
        }else{
          document.getElementById("post__type").innerHTML = "튜토리얼";
        }
    }
});



document.getElementById("counter").oninput = function() {
  // 입력된 값을 가져와서 output 요소에 출력
  var counter = document.getElementById("counter").value;
  document.getElementById("post__count").innerHTML = counter;
};

document.getElementById("gameContent__title").oninput = function() {
  // 입력된 값을 가져와서 output 요소에 출력
  var gameContent__title = document.getElementById("gameContent__title").value;
  document.getElementById("post__title").innerHTML = gameContent__title;
};


const first__level = document.getElementById('first__level');
const second__level = document.getElementById('second__level');
const third__level = document.getElementById('third__level');
const fourth__level = document.getElementById('fourth__level');


// first__level을 선택하면 radio2의 선택이 해제되도록 설정
first__level.addEventListener('change', function() {
  if (first__level.checked) {
    second__level.checked = false;
    third__level.checked = false;
    fourth__level.checked = false;

    if(first__level.value == 1){
      document.getElementById("post__level").innerHTML = "초급";
    }
  }
});

// second__level을 선택하면 radio2의 선택이 해제되도록 설정
second__level.addEventListener('change', function() {
  if (second__level.checked) {
    first__level.checked = false;
    third__level.checked = false;
    fourth__level.checked = false;

    if(second__level.value == 2){
      document.getElementById("post__level").innerHTML = "중급";
    }
  }
});

// third__level을 선택하면 radio2의 선택이 해제되도록 설정
third__level.addEventListener('change', function() {
  if (third__level.checked) {
    first__level.checked = false;
    second__level.checked = false;
    fourth__level.checked = false;

    if(third__level.value == 3){
      document.getElementById("post__level").innerHTML = "고급";
    }
  }
});

// third__level을 선택하면 radio2의 선택이 해제되도록 설정
fourth__level.addEventListener('change', function() {
  if (fourth__level.checked) {
    first__level.checked = false;
    second__level.checked = false;
    third__level.checked = false;

    if(fourth__level.value == 4){
      document.getElementById("post__level").innerHTML = "프로";
    }
  }
});




function calculateDateRange() {
  const monthsInput = document.getElementById('monthsInput').value;
  const startDateInput = new Date();
  const endDateInput = new Date();

  // 현재 날짜를 기준으로 개월 수를 더하여 종료 날짜 계산
  endDateInput.setMonth(endDateInput.getMonth() + parseInt(monthsInput));

  // 시작 날짜와 종료 날짜를 YYYY-MM-DD 형식으로 변환하여 표시
  const startDate = startDateInput.toISOString().split('T')[0];
  const endDate = endDateInput.toISOString().split('T')[0];

  document.getElementById('startDate').value = startDate;
  document.getElementById('endDate').value = endDate;
}




function addMoney() {
  const origin__price = document.getElementById("origin__price");
  const price__value = origin__price.value;

  // 입력된 값이 있을 경우 "원" 추가
  if (price__value) {
      origin__price.value = price__value + " 원";
  }
}

function addDiscount() {
  const discount__rate = document.getElementById("discount__rate");
  const discount__value = discount__rate.value;

  // 입력된 값이 있을 경우 "원" 추가
  if (discount__value) {
      discount__rate.value = discount__value + " %";
  }
}


function calculateSalePrice() {
  const price = parseFloat(document.getElementById('origin__price').value);
  const discount = parseFloat(document.getElementById('discount__rate').value);

  if (!isNaN(price) && !isNaN(discount)) {
      const result__price = price * (1 - discount / 100);
      document.getElementById('result__price').value = result__price.toFixed(0) + ' 원';
  } else {
      document.getElementById('result__price').value = '';
  }
}

// 정가와 할인율이 변경될 때마다 판매가를 다시 계산
document.getElementById('origin__price').addEventListener('input', calculateSalePrice);
document.getElementById('discount__rate').addEventListener('input', calculateSalePrice);



