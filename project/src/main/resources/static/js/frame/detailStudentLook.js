var ds__collapsible = document.querySelectorAll(".ds__collapsible");
var i;

for (i = 0; i < ds__collapsible.length; i++) {
  ds__collapsible[i].addEventListener("click", function() {
    this.classList.toggle("ds__active");
    var ds__content = this.nextElementSibling;
    if (ds__content.style.maxHeight){
      ds__content.style.maxHeight = null;
    } else {
      ds__content.style.maxHeight = ds__content.scrollHeight + "px";
    } 
  });
}