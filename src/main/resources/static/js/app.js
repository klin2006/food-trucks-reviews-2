
function openNav() {
    document.getElementById("mySidenav").style.width = "250px";
    document.getElementById("main").style.marginLeft = "250px";
    }
      
function closeNav() {
    document.getElementById("mySidenav").style.width = "0";
    document.getElementById("main").style.marginLeft= "0";
    }

var dropdown = document.getElementsByClassName("dropdown-btn");
var i;

for (i = 0; i < dropdown.length; i++) {
  dropdown[i].addEventListener("click", function() {
  this.classList.toggle("active");
  var dropdownContent = this.nextElementSibling;
  if (dropdownContent.style.display === "block") {
  dropdownContent.style.display = "none";
  } else {
  dropdownContent.style.display = "block";
  }
  });
}

var slideIndex = 1;
showSlides(slideIndex);

function plusSlides(n){
showSlides(slideIndex += n);
}
function currentSlide(n){
showSlides(slideIndex = n);
}

function showSlides(n){
   var i;
    var slides = document.getElementsByClassName("slideshow");
    if (n >slides.length) {slideIndex = 1}
    if(n < 1) {slideIndex = slides.length}
        for (i = 0; i < slides.length; i ++){
            slides[i].style.display = "none";
      }
  slides[slideIndex-1].style.display = "block";
    }

