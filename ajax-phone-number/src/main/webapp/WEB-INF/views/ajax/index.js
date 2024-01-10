function addNewSmartPhone() {
    let producer = $('#producer').val();
    let model = $('#model').val();
    let price = $('#price').val();
    let newSmartphone = {
        producer: producer,
        model: model,
        price: price
    };

    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        type: "POST",
        data: JSON.stringify(newSmartphone),
        url: "http://localhost:8080/api/smartphones",
        success: console.log("success")
    });

    event.preventDefault();
}

function successHandler() {
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api/smartphones",
        success: function (data) {
            let content = '     <table id="display-list" border="1"<tr>' +
                '<th>Producer</th>\n' +
                '<th>Model</th>\n' +
                '<th>Price</th>\n' +
                '<th>Delete</th>\n' +
                '</tr>'

            for (let i = 0; i < data.length; i++) {
                content += getSmartphone(data[i]);
            }

            content += "</table>"
             $('#smartphoneList').html(content);
        }
    })
}


function getSmartphone(smartphone) {
    return `<tr>` +
        `<td> ${smartphone.producer}</td>` +
        `<td> ${smartphone.model}</td>` +
        `<td> ${smartphone.price}</td>` +
        `<td class="btn"><button class="deleteSmartphone" onclick="deleteSmartphone(${smartphone.id})">Delete</button></td>` +
        `<td class="btn"><button class="editSmartphone" onclick="displayEditForm(${smartphone.id})">Edit</button></td>` +
        `</tr>`
}



function deleteSmartphone(id) {
    $.ajax({
        type: "DELETE",
        url: `http://localhost:8080/api/smartphones/${id}`,
        success: successHandler
    })
}

let id_update = 0;
function displayEditForm(id) {
    document.getElementById("edit-smartphone").style.display = "block";
    id_update = id;

}
function updateSmartphone(){
    let producer = $('#producer2').val();
    let price = $('#price2').val();
    let model = $('#model2').val();
    let updateSmartphone = {
        id : id_update,
        producer: producer,
        model: model,
        price: price
    };
    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        type: "PUT",
        data: JSON.stringify(updateSmartphone),
        url: `http://localhost:8080/api/smartphones/` + id_update,
        success: successHandler
    })
}

function displayFormCreate() {
    document.getElementById("add-smartphone").style.display = "block";
}

