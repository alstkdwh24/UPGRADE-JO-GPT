
// 학생 유효성 검사

//아이디
$(document).ready(function(){

  $("#student__id").keyup(function(){

    var student__id = $("#student__id").val();

    if(student__id === ""){
      $(".message__student__id").html("아이디는 필수입니다.");
    }else{
      $(".message__student__id").html("");
    }

  });

});

// 비밀번호
$(document).ready(function(){

  $("#student__pw").keyup(function(){

    var pw = $("#student__pw").val();
    var num = pw.search(/[0-9]/g);
    var eng = pw.search(/[a-z]/ig);
    var spe = pw.search(/[`~!@@#$%^&*|₩₩₩'₩";:₩/?]/gi);
  
    if(pw.length < 8 || pw.length > 20){

      $(".message__student__pw").html("8자리 ~ 20자리 이내로 입력해주세요.");
      return false;
    }else if(pw.search(/\s/) != -1){
      $(".message__student__pw").html("비밀번호는 공백 없이 입력해주세요.");
      return false;
    }else if(num < 0 || eng < 0 || spe < 0 ){
      $(".message__student__pw").html("영문,숫자, 특수문자를 혼합하여 입력해주세요.");
      return false;
    }else {
      console.log("통과"); 
      return true;
    }

  });

});

/*// 생년월일
$(document).ready(function(){

  $("#student__birth").keyup( function(){
    var student__birth = $("#student__birth").val();
    var pattern = /^(19|20)\d{2}(0[1-9]|1[0-2])(0[1-9]|[12][0-9]|3[01])$/;
    if (!pattern.test(student__birth)) {
      $('.message__student__birth').html('올바른 생년월일 형식을 입력하세요 (YYYYMMDD)');
      $(this).focus();
    } else {
      $('.message__student__birth').html('');
      // 여기서 유효한 생년월일에 대한 추가 작업 수행
    }
  });

});*/

// 휴대번호
$(document).ready(function(){
  $('#student__phone').on('input', function(){
      var phoneNumber = $(this).val();
      var formattedPhoneNumber = formatPhoneNumber(phoneNumber);
      $(this).val(formattedPhoneNumber);
  });

  function formatPhoneNumber(phoneNumber) {
    phoneNumber = phoneNumber.replace(/[^0-9]/g, ''); // 숫자 이외의 문자 제거
    var formattedPhoneNumber = '';

/*    if (phoneNumber.length >= 3) {
        formattedPhoneNumber += phoneNumber.substr(0, 3);
        if (phoneNumber.length >= 7) {
            formattedPhoneNumber += '-' + phoneNumber.substr(3, 4) + '-' + phoneNumber.substr(7, 4);
        } else if (phoneNumber.length > 3) {
            formattedPhoneNumber += '-' + phoneNumber.substr(3);
        }
    } else {
        formattedPhoneNumber += phoneNumber;
    }*/
	formattedPhoneNumber += phoneNumber;
    return formattedPhoneNumber;
}

  $('#student__phone').keyup(function(event){
      var phoneNumber = $('#student__phone').val();
      var phonePattern = /^010-\d{4}-\d{4}$/;

      if (!phonePattern.test(phoneNumber)){
          $('.message__student__phone').html("옳바른 형식을 입력해주세요.");
          event.preventDefault(); // 제출 이벤트 중지
      }else{
        $('.message__student__phone').html("");
      }
  });
});


// 기업 유효성 검사

//아이디
$(document).ready(function(){

  $("#company__id").keyup(function(){

    var company__id = $("#company__id").val();

    if(company__id === ""){
      $(".message__company__id").html("사업자 등록번호는 필수입니다.");
    }else{
      $(".message__company__id").html("");
    }

  });

});

// 비밀번호
$(document).ready(function(){

  $("#company__pw").keyup(function(){

    var pw = $("#company__pw").val();
    var num = pw.search(/[0-9]/g);
    var eng = pw.search(/[a-z]/ig);
    var spe = pw.search(/[`~!@@#$%^&*|₩₩₩'₩";:₩/?]/gi);
  
    if(pw.length < 8 || pw.length > 20){

      $(".message__company__pw").html("8자리 ~ 20자리 이내로 입력해주세요.");
      return false;
    }else if(pw.search(/\s/) != -1){
      $(".message__company__pw").html("비밀번호는 공백 없이 입력해주세요.");
      return false;
    }else if(num < 0 || eng < 0 || spe < 0 ){
      $(".message__company__pw").html("영문,숫자, 특수문자를 혼합하여 입력해주세요.");
      return false;
    }else {
      console.log("통과"); 
      return true;
    }

  });

});


// 휴대번호
$(document).ready(function(){
  $('#company__phone').on('input', function(){
      var phoneNumber = $(this).val();
      var formattedPhoneNumber = formatPhoneNumber(phoneNumber);
      $(this).val(formattedPhoneNumber);
  });

  function formatPhoneNumber(phoneNumber) {
    phoneNumber = phoneNumber.replace(/[^0-9]/g, ''); // 숫자 이외의 문자 제거
    var formattedPhoneNumber = '';

 /*   if (phoneNumber.length >= 3) {
        formattedPhoneNumber += phoneNumber.substr(0, 3);
        if (phoneNumber.length >= 7) {
            formattedPhoneNumber += '-' + phoneNumber.substr(3, 4) + '-' + phoneNumber.substr(7, 4);
        } else if (phoneNumber.length > 3) {
            formattedPhoneNumber += '-' + phoneNumber.substr(3);
        }
    } else {
        formattedPhoneNumber += phoneNumber;
    }*/
	formattedPhoneNumber += phoneNumber;
    return formattedPhoneNumber;
}

  $('#company__phone').keyup(function(event){
      var phoneNumber = $('#company__phone').val();
      var phonePattern = /^010-\d{4}-\d{4}$/;

      if (!phonePattern.test(phoneNumber)){
          $('.message__company__phone').html("옳바른 형식을 입력해주세요.");
          event.preventDefault(); // 제출 이벤트 중지
      }else{
        $('.message__company__phone').html("");
      }
  });
});


// 교직원 유효성 검사

//아이디
$(document).ready(function(){

  $("#manager__id").keyup(function(){

    var manager__id = $("#manager__id").val();

    if(manager__id === ""){
      $(".message__manager__id").html("사업자 등록번호는 필수입니다.");
    }else{
      $(".message__manager__id").html("");
    }

  });

});

// 비밀번호
$(document).ready(function(){

  $("#manager__pw").keyup(function(){

    var pw = $("#manager__pw").val();
    var num = pw.search(/[0-9]/g);
    var eng = pw.search(/[a-z]/ig);
    var spe = pw.search(/[`~!@@#$%^&*|₩₩₩'₩";:₩/?]/gi);
  
    if(pw.length < 8 || pw.length > 20){

      $(".message__manager__pw").html("8자리 ~ 20자리 이내로 입력해주세요.");
      return false;
    }else if(pw.search(/\s/) != -1){
      $(".message__manager__pw").html("비밀번호는 공백 없이 입력해주세요.");
      return false;
    }else if(num < 0 || eng < 0 || spe < 0 ){
      $(".message__manager__pw").html("영문,숫자, 특수문자를 혼합하여 입력해주세요.");
      return false;
    }else {
      console.log("통과"); 
      return true;
    }

  });

});

// 휴대번호
$(document).ready(function(){
  $('#manager__phone').on('input', function(){
      var phoneNumber = $(this).val();
      var formattedPhoneNumber = formatPhoneNumber(phoneNumber);
      $(this).val(formattedPhoneNumber);
  });

  function formatPhoneNumber(phoneNumber) {
    phoneNumber = phoneNumber.replace(/[^0-9]/g, ''); // 숫자 이외의 문자 제거
    var formattedPhoneNumber = '';

 /*   if (phoneNumber.length >= 3) {
        formattedPhoneNumber += phoneNumber.substr(0, 3);
        if (phoneNumber.length >= 7) {
            formattedPhoneNumber += '-' + phoneNumber.substr(3, 4) + '-' + phoneNumber.substr(7, 4);
        } else if (phoneNumber.length > 3) {
            formattedPhoneNumber += '-' + phoneNumber.substr(3);
        }
    } else {
        formattedPhoneNumber += phoneNumber;
    }*/
	formattedPhoneNumber += phoneNumber;
    return formattedPhoneNumber;
}

  $('#manager__phone').keyup(function(event){
      var phoneNumber = $('#manager__phone').val();
      var phonePattern = /^010-\d{4}-\d{4}$/;

      if (!phonePattern.test(phoneNumber)){
          $('.message__manager__phone').html("옳바른 형식을 입력해주세요.");
          event.preventDefault(); // 제출 이벤트 중지
      }else{
        $('.message__manager__phone').html("");
      }
  });
});


$(document).ready(function(){

  $(".join__company").hide();
  $(".join__manager").hide();

  $(".join__sel__student").on("click", function(){

    $(".join__student").show();
    $(".join__company").hide();
    $(".join__manager").hide();

  });

  $(".join__sel__company").on("click", function(){

    $(".join__student").hide();
    $(".join__company").show();
    $(".join__manager").hide();

  });

  $(".join__sel__manager").on("click", function(){

    $(".join__student").hide();
    $(".join__company").hide();
    $(".join__manager").show();

  });

});

