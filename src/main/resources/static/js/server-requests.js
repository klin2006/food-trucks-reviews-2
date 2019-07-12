const tagAddButton = document.querySelector('.add-tag button');
const tagAddInput = document.querySelector('.add-tag input');
const tagsList = document.querySelector('.tags-list ul');

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

function postTags(tagType){
    xhr.open('POST', '/tags/' + tagType, true);
    xhr.send();
}