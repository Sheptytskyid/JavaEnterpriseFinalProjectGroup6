$(window).scroll(function() {
if ($(this).scrollTop() > 1){  
    $('.hamburger.is-closed').addClass("sticky");
  }
  else{
    $('.hamburger.is-closed').removeClass("sticky");
  }
});
	