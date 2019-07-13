const xhr = new XMLHttpRequest();
xhr.onreadystatechange = function() {
     if (this.readyState == 4 && this.status == 200) {
         const res = JSON.parse(xhr.response)
         const container = document.querySelector('.container')
    
    console.log({res})

    res.forEach(function(foodtruck) {
        const foodtruckItem = document.createElement('div')

        const name = document.createElement('h2')
        name.innerText = foodtruck.name;

        const tagUrls = [];
        foodtruck.tagsUrls.forEach(tagUrl => {
            const tagUrlElement = document.createElement('li')
            tagUrlElement.innerHTML = `foodtruck tags: <a href = "${tagUrl}">${tagUrl}</a>`
            tagUrls.push(tagUrlElement)
        })

        container.appendChild(foodtruckItem)
        foodtruckItem.appendChild(name)

        tagUrls.forEach(tagUrl => foodtruckItem.appendChild(tagUrl))

     }
 )}
}

xhr.open('GET', 'http://localhost:8080/foodtrucks', true)
xhr.send()


