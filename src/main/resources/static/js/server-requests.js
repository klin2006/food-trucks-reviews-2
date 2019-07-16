const tagAddButton = document.querySelector('.add-tag button');
const tagAddInput = document.querySelector('.add-tag input');
const tagsList = document.querySelector('.tags-list ul');
const commentAddButton = document.querySelector('.add-comment button')
const commentAddInput = document.querySelector('.add-comment input')


const xhr = new XMLHttpRequest()
xhr.onreadystatechange = function(){
    if(xhr.readyState === 4 && xhr.status === 200){
    const res = xhr.responseText;
    tagsList.innerHTML = res;
    }
}

tagAddButton.addEventListener('click', function(){
    postTags(tagAddInput.value);
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


function postTags(tagType){
    xhr.open('POST', '/tags/' + tagType, true);
    xhr.send();
}

function removeTag(id){
    xhr.open('POST', '/tags/remove/' + id, true);
    xhr.send();
}

// const xhr = new XMLHttpRequest()
// xhr.onreadystatechange = function(){
//     if(xhr.readyState === 4 && xhr.status === 200){
//     const res = xhr.responseText;
//     commentAddInput.innerHTML = res;
//     }
// }

// commentAddButton.addEventListener('click', function(){
//     postTags(commentAddInput.value);
//     console.log(commentAddInput.value);
//     commentAddInput.value = "";
// })

// tagsList.addEventListener('click', function(event){
//     if(event.target.classList.contains('x')){
//     let tagId = event.target.previousElementSibling.previousElementSibling.value;
//       console.log(tagId);
//       removeTag(tagId);
//     }
// })


// function postTags(tagType){
//     xhr.open('POST', '/comments/' + tagType, true);
//     xhr.send();
// }

// function removeTag(id){
//     xhr.open('POST', '/comments/remove/' + id, true);
//     xhr.send();
// }