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

const tagAddButton = document.querySelector('.add-tag button');
const tagAddInput = document.querySelector('.add-tag input');
const tagsList = document.querySelector('.tags-list ul');
const foodtruckId = document.querySelector('.foodtruckId');

const xhr = new XMLHttpRequest()
xhr.onreadystatechange = function(){
    if(xhr.readyState === 4 && xhr.status === 200){
    const res = xhr.responseText;
    tagsList.innerHTML = res;
    }
}

tagAddButton.addEventListener('click', function(){
    postTags(tagAddInput.value, foodtruckId.value);
    console.log(tagAddInput.value);
    tagAddInput.value = "";
})

tagsList.addEventListener('click', function(event){
    if(event.target.classList.contains('x')){
    let tagId = event.target.previousElementSibling.previousElementSibling.value;
      console.log(tagId);
      removeTag(tagId);
    }
})


function postTags(tagType, foodtruckIdToAdd){
    xhr.open('POST', '/tags/' + tagType, true);
    // xhr.open('POST', '/tags/' + tagType + '/' + foodtruckIdToAdd, true);
    xhr.send();
}

function removeTag(id){
    xhr.open('POST', '/tags/remove/' + id, true);
    xhr.send();
}