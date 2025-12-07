function my__home(){
  $("#iframe").attr("src", "/user_co/co_myhome");
}

function my__apply(){
  $("#iframe").attr("src", "/user_co/apply_co");
}

function my__purpose(){
  $("#iframe").attr("src", "/user_co/purpose_co");
}

function my__hire(){
  $("#iframe").attr("src", "/user_co/hire_co");
}

function my__resume(){
  $("#iframe").attr("src", "/job/resume_jobpost");
};


function my_hirePost(){
	$("#iframe").attr("src", "/user_co/hirePost");
};


function submitForm() {
    // 체크박스들의 상태를 확인합니다
    var checkbox1Checked = document.getElementsByName("checkbox1")[0].checked;
    var checkbox2Checked = document.getElementsByName("checkbox2")[0].checked;
    var checkbox3Checked = document.getElementsByName("checkbox3")[0].checked;

	console.log(checkbox1Checked);
    // form의 action을 동적으로 변경합니다
    var form = document.getElementById("myForm");
    if (checkbox1Checked) {
        form.action = "/user_co/hire_co_document"; // 체크박스 1이 선택된 경우
    } else if (checkbox2Checked) {
        form.action = "/user_co/hire_co_interview"; // 체크박스 2가 선택된 경우
    } else if (checkbox3Checked) {
        form.action = "/user_co/hire_co_pass"; // 체크박스 3이 선택된 경우
    } else {
        alert("적어도 하나의 체크박스를 선택해주세요.");
        return; // 어떤 체크박스도 선택되지 않은 경우 폼 전송을 막습니다.
    }

    // form을 전송합니다
    form.submit();
}







