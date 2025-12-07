var swiper = new Swiper('.swiper', {
  slidesPerView: 3.4,
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
  var direction = windowWidth.innerWidth <= 760 ? 'vertical' : 'horizontal';

  return direction;
}