//Uses jQuery because it was quick. You'll want to write something that works within the context of your app.



$("#previous").on("click", function() {
  var $bar = $(".ProgressBar");
  if ($bar.children(".is-current").length > 0) {
    $bar.children(".is-current").removeClass("is-current").prev().removeClass("is-complete").addClass("is-current");
  } else {
    $bar.children(".is-complete").last().removeClass("is-complete").addClass("is-current");
  }
});

/*
$("#advance").on("click", function() {
  var $bar = $(".ProgressBar");
  if ($bar.children(".is-current").length > 0) {
    $bar.children(".is-current").removeClass("is-current").next().addClass("is-current is-complete");
    
  } else {
    $bar.children().first().addClass("is-current is-complete");
  }
});
*/