const $drop = document.querySelector(".dropBox");
const $title = document.querySelector(".dropBox h5");

// 드래그한 파일 객체가 해당 영역에 놓였을 때
$drop.ondrop = (e) => {
  e.preventDefault();
  $drop.className = "dropBox";

  // 파일 리스트
  const files = [...e.dataTransfer?.files];

  $title.innerHTML = files.map(v => v.name).join("<br>");
}

// ondragover 이벤트가 없으면 onDrop 이벤트가 실핻되지 않습니다.
$drop.ondragover = (e) => {
  e.preventDefault();
}

// 드래그한 파일이 최초로 진입했을 때
$drop.ondragenter = (e) => {
  e.preventDefault();
 
  $drop.classList.add("active");
}

// 드래그한 파일이 영역을 벗어났을 때
$drop.ondragleave = (e) => {
  e.preventDefault();
  
  $drop.classList.remove("active");
}

function updateValue() {
    var checkbox = document.getElementById("important");
    var valueInput = document.getElementsByName("notice_check")[0]; // notice_check의 값을 저장할 input 요소

    if (checkbox.checked) {
        valueInput.value = "1"; // 체크되었을 때 1로 변경
        console.log(valueInput.value);
    } else {
        valueInput.value = "0"; // 체크가 해제되었을 때 0으로 변경
        console.log(valueInput.value);
    }
}




const notice__regist__check = document.getElementById('notice__regist__check');
const notice__future__regist = document.getElementById('notice__future__regist');


// radio1을 선택하면 radio2의 선택이 해제되도록 설정
notice__regist__check.addEventListener('change', function() {
    if (notice__regist__check.checked) {
      notice__future__regist.checked = false;
    }
});

// radio2를 선택하면 radio1의 선택이 해제되도록 설정
notice__future__regist.addEventListener('change', function() {
    if (notice__future__regist.checked) {
      notice__regist__check.checked = false;
    }
});



document.getElementById("notice__title").oninput = function() {
  // 입력된 값을 가져와서 output 요소에 출력
  var notice__title = document.getElementById("notice__title").value;
  document.getElementById("board__title").innerHTML = notice__title;
};

document.getElementById("item__select").onchange = function() {
  // 선택된 값을 가져와서 output 요소에 출력
  var selectedValue = document.getElementById("item__select").value;

  if(selectedValue == 1){
    document.getElementById("board__category").innerHTML = "공통  ";
  }else if( selectedValue == 2){
    document.getElementById("board__category").innerHTML = "상품안내 / ";
  }else if( selectedValue == 3){
    document.getElementById("board__category").innerHTML = "이용안내 / ";
  }else if(selectedValue == 4){
    document.getElementById("board__category").innerHTML = "기타 / ";
  }else{
    document.getElementById("board__category").innerHTML = " ";
  }
  
};



document.addEventListener("DOMContentLoaded", function() {
  // 체크박스 요소들과 출력을 표시할 div 요소 가져오기
  var currentDateCheckbox = document.getElementById("notice__regist__check");
  var selectedDateCheckbox = document.getElementById("notice__future__regist");
  var selectedDateInput = document.getElementById("notice__date");
  var outputDiv = document.getElementById("board__date");

  // 오늘 날짜 출력 체크박스에 이벤트 리스너 추가
  currentDateCheckbox.addEventListener("change", function() {
      if (currentDateCheckbox.checked) {
          outputDiv.innerHTML = getCurrentDate();
          currentDateCheckbox.value = getCurrentDate();
      } else {
          outputDiv.innerHTML = "";
      }
  });

  // 날짜 선택 체크박스에 이벤트 리스너 추가
  selectedDateCheckbox.addEventListener("change", function() {
      if (selectedDateCheckbox.checked) {
          selectedDateInput.style.display = "inline"; // 날짜 선택 input 표시
      } else {
          selectedDateInput.style.display = "none"; // 날짜 선택 input 숨김
          outputDiv.innerHTML = "";
      }
  });

  // input type="date" 값이 변경될 때마다 실행되는 함수
  selectedDateInput.addEventListener("change", function() {
      var selectedDate = selectedDateInput.value;
      var currentDate = new Date();
      var selectedDateObj = new Date(selectedDate);
      var daysDifference = Math.ceil((selectedDateObj - currentDate) / (1000 * 60 * 60 * 24));
      if (daysDifference >= 0) {
          outputDiv.innerHTML = " / " + selectedDate + " D- " + daysDifference;
          outputDiv.style = "color : red";
      } else {
          outputDiv.innerHTML = " 선택한 날짜는 이미 지났습니다.";
          outputDiv.style = "color : red";
      }
  });

  // 현재 날짜를 문자열로 반환하는 함수
  function getCurrentDate() {
      var today = new Date();
      var year = today.getFullYear();
      var month = String(today.getMonth() + 1).padStart(2, "0");
      var day = String(today.getDate()).padStart(2, "0");
      return year + "-" + month + "-" + day;
  }
});


document.getElementById("notice__textArea").oninput = function() {
  // 입력된 값을 가져와서 output 요소에 출력
  var notice__textArea = document.getElementById("notice__textArea").value;
  document.getElementById("board__textArea").innerHTML = notice__textArea;
};


var fileInput = document.getElementById("fileInput");

// 파일 입력(input file)의 값이 변경될 때마다 실행되는 함수
fileInput.addEventListener("change", function() {
    // 선택된 파일 가져오기
    var file = fileInput.files[0];

    // 파일이 선택되었는지 확인
    if (file) {
        // 파일의 이름을 화면에 표시
        document.getElementById("board__file").innerHTML =  file.name;
    } else {
        // 파일이 선택되지 않았을 경우 메시지 표시
        document.getElementById("board__file").innerHTML = "파일을 선택해주세요.";
    }
});


