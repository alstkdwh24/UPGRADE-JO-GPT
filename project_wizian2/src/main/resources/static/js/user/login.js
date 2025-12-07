$(document).ready(function(){

  $(".below__company").hide();
  $(".below__manager").hide();

  $(".above__student").on("click", function(){
    $(".above__student").css({backgroundColor:"white"});
    $(".above__student span").css({color:"black", fontWeight:"bold"});
    $(".above__company").css({backgroundColor:"rgb(49, 100, 241)"});
    $(".above__company span").css({color:"white", fontWeight:"normal"});
    $(".above__manager").css({backgroundColor:"rgb(49, 100, 241)"});
    $(".above__manager span").css({color:"white", fontWeight:"normal"});

    $(".below__student").show();
    $(".below__company").hide();
    $(".below__manager").hide();
  });

  $(".above__company").on("click", function(){
    $(".above__company").css({backgroundColor:"white"});
    $(".above__company span").css({color:"black", fontWeight:"bold"});
    $(".above__student").css({backgroundColor:"rgb(49, 100, 241)"});
    $(".above__student span").css({color:"white", fontWeight:"normal"});
    $(".above__manager").css({backgroundColor:"rgb(49, 100, 241)"});
    $(".above__manager span").css({color:"white", fontWeight:"normal"});

    $(".below__student").hide();
    $(".below__company").show();
    $(".below__manager").hide();
  });

  $(".above__manager").on("click", function(){
    $(".above__manager").css({backgroundColor:"white"});
    $(".above__manager span").css({color:"black", fontWeight:"bold"});
    $(".above__student").css({backgroundColor:"rgb(49, 100, 241)"});
    $(".above__student span").css({color:"white", fontWeight:"normal"});
    $(".above__company").css({backgroundColor:"rgb(49, 100, 241)"});
    $(".above__company span").css({color:"white", fontWeight:"normal"});

    $(".below__student").hide();
    $(".below__company").hide();
    $(".below__manager").show();
  });


});