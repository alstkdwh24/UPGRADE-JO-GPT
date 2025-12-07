var swiper = new Swiper('.swiper', {
  slidesPerView: 4,
  direction: getDirection(),
  navigation: {
    nextEl: '.swiper-button-next',
    prevEl: '.swiper-button-prev',
  },
  on: {
    resize: function () {
      swiper.changeDirection(getDirection());
    },
  },
});

function getDirection() {
  var windowWidth = window.innerWidth;
  var direction = window.innerWidth <= 1245 ? 'vertical' : 'horizontal';

  return direction;
}

var modal = document.getElementById("myModal");

// Get the button that opens the modal
var btn = document.querySelectorAll("#myBtn");

// Get the <span> element that closes the modal
var span = document.getElementsByClassName("close")[0];

// When the user clicks the button, open the modal 
for(var i=0; i<btn.length; i++){

  btn[i].addEventListener("click", function(){
    modal.style.display = "block";
  });

}

// When the user clicks on <span> (x), close the modal
span.onclick = function() {
  modal.style.display = "none";
}

// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
  if (event.target == modal) {
    modal.style.display = "none";
  }
}



