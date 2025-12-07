filterSelection("all")
function filterSelection(c) {
  var x, i;
  x = document.getElementsByClassName("filterDiv");
  if (c == "all") c = "";
  for (i = 0; i < x.length; i++) {
    w3RemoveClass(x[i], "show");
    if (x[i].className.indexOf(c) > -1) w3AddClass(x[i], "show");
  }
}

function w3AddClass(element, name) {
  var i, arr1, arr2;
  arr1 = element.className.split(" ");
  arr2 = name.split(" ");
  for (i = 0; i < arr2.length; i++) {
    if (arr1.indexOf(arr2[i]) == -1) {element.className += " " + arr2[i];}
  }
}

function w3RemoveClass(element, name) {
  var i, arr1, arr2;
  arr1 = element.className.split(" ");
  arr2 = name.split(" ");
  for (i = 0; i < arr2.length; i++) {
    while (arr1.indexOf(arr2[i]) > -1) {
      arr1.splice(arr1.indexOf(arr2[i]), 1);     
    }
  }
  element.className = arr1.join(" ");
}

var btnContainer = document.getElementById("game__divide");
var b12 = btnContainer.getElementsByClassName("game__divide__btn");
for (var i = 0; i < b12.length; i++) {
  b12[i].addEventListener("click", function(){
    var current = document.getElementsByClassName("active");
    current[0].className = current[0].className.replace(" active", "");
    
    this.className += " active";
  });
}


/* 버튼 클릭 */

var game__content__page = document.getElementById("game__content__page");
var page__num = game__content__page.getElementsByClassName("page__num");
for (var i = 0; i < page__num.length; i++) {
  page__num[i].addEventListener("click", function() {
  var current = document.getElementsByClassName("page__active");
  current[0].className = current[0].className.replace(" page__active", "");
  this.className += " page__active";
  });
}


