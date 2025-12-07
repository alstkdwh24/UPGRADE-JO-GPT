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



const document__video = document.getElementById('document__video');
const document__book = document.getElementById('document__book');
const document__web = document.getElementById('document__web');

// radio1을 선택하면 radio2의 선택이 해제되도록 설정
document__video.addEventListener('change', function() {
    if (document__video.checked) {
      document__book.checked = false;
      document__web.checked = false;

      if(document__video.value == 0){
        document.getElementById("post__level").innerHTML = "동영상";
      }

    }
});

// radio2를 선택하면 radio1의 선택이 해제되도록 설정
document__book.addEventListener('change', function() {
    if (document__book.checked) {
      document__video.checked = false;
      document__web.checked = false;

      if(document__book.value == 1){
        document.getElementById("post__level").innerHTML = "교재";
      }
    }
});

// radio2를 선택하면 radio1의 선택이 해제되도록 설정
document__web.addEventListener('change', function() {
  if (document__web.checked) {
    document__video.checked = false;
    document__book.checked = false;

    if(document__web.value == 2){
      document.getElementById("post__level").innerHTML = "웹사이트";
    }
  }
});


var fileInput = document.getElementById('fileInput');
var previewImage = document.getElementById('previewImage');

// 파일 입력 필드에 change 이벤트 리스너 추가
fileInput.addEventListener('change', function(event) {
    // 선택된 파일 가져오기
    var file = event.target.files[0];

    // FileReader 객체 생성
    var reader = new FileReader();

    // 파일 읽기 완료 시 동작할 이벤트 리스너 정의
    reader.onload = function(e) {
        // 이미지 데이터를 미리보기 이미지의 src 속성에 설정하여 표시
        previewImage.src = e.target.result;
    };

    // 파일을 읽기
    reader.readAsDataURL(file);
});

document.getElementById("learn__title").oninput = function() {
  // 입력된 값을 가져와서 output 요소에 출력
  var learn__title = document.getElementById("learn__title").value;
  document.getElementById("post__title").innerHTML = learn__title;
};


document.getElementById("type__select").onchange = function() {
  // 선택된 값을 가져와서 output 요소에 출력
  var selectedValue = document.getElementById("type__select").value;

  if(selectedValue == 0){
    document.getElementById("post__type").innerHTML = "튜토리얼";
  }else if( selectedValue == 1){
    document.getElementById("post__type").innerHTML = "교육영상";
  }else{
    document.getElementById("post__type").innerHTML = " ";
  }
  
};


document.getElementById("select__price__type").onchange = function() {
  // 선택된 값을 가져와서 output 요소에 출력
  var selectedValue = document.getElementById("select__price__type").value;

  if(selectedValue == 1){
    document.getElementById("post__count").innerHTML = "유료";
  }else if( selectedValue == 0){
    document.getElementById("post__count").innerHTML = "무료";
  }else{
    document.getElementById("post__count").innerHTML = " ";
  }
  
};