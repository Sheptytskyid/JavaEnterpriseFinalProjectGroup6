$(document).ready(function() {
     $('.dropdown-toggle').click(function () {
          if ($("ul.dropdown-menu").is(":hidden")) {
               $("ul.dropdown-menu").slideDown("slow");
          } else {
               $("ul.dropdown-menu").slideUp("slow");
          }
     });
});