$(document).ready(function(){

  $(".siteMap__item").on("mouseover", function(){

    $(this).find("#site__img").attr("src", "/img/KBA_mascot_black2.png");
  });

  $(".siteMap__item").on("mouseout", function(){

    $(this).find("#site__img").attr("src", "/img/KBA_mascot_black.png");
  });

});