const APP_URL = 'http://localhost:8080';
const container = document.getElementById("xxe-injection-product-container");
let selectedProduct = null;
let product = {
    id: null,
    productName: null,
    imageUrl: null,
    price: null,
    description: null
}

const getProductInfo = () => {
    const queryParam = window.location.search;
    const productId = queryParam.slice(queryParam.indexOf("=") + 1);
    fetch(APP_URL + `/xxe/products/${productId}`)
        .then(response => response.json())
        .then(p => {
            product.id = p.id;
            product.productName = p.name;
            product.imageUrl = p.imageUrl;
            product.price = p.price;
            product.description = p.description;
            displayProduct(p.id, p.name, p.imageUrl, p.price, p.description);
        })
        .catch(console.log);
}

const displayProduct = (id, name, imageUrl, price, description) => {
    const imageContainer = document.createElement("div");
    imageContainer.className = 'productImage';
    const image = document.createElement('img');
    image.src = imageUrl;
    imageContainer.appendChild(image);

    const productNameContainer = document.createElement("div");
    const productNameSpan = document.createElement("span");
    productNameSpan.textContent = name;
    productNameSpan.id = 'productName';
    productNameSpan.className = 'productName editable'
    productNameContainer.appendChild(productNameSpan);
    productNameContainer.className = 'productNameContainer';

    const priceContainer = document.createElement("div");
    const priceSpan = document.createElement("span");
    priceSpan.textContent = price;
    priceSpan.id = 'price';
    priceSpan.className = 'price editable'
    priceContainer.appendChild(priceSpan);
    priceContainer.className = 'priceContainer';

    const descriptionContainer = document.createElement("div");
    const descriptionSpan = document.createElement("span");
    descriptionSpan.textContent = description;
    descriptionSpan.id = 'description';
    descriptionSpan.className = 'description editable'
    descriptionContainer.appendChild(descriptionSpan);
    descriptionContainer.className = 'descriptionContainer';

    const editButton = document.createElement("button");
    editButton.id = "editButton";
    editButton.textContent = "Edit";
    editButton.addEventListener('click', editButtonClicked);

    const removeButton = document.createElement("button");
    removeButton.id = "removeButton";
    removeButton.textContent = "Remove Product";
    removeButton.addEventListener('click', removeButtonClicked);

    container.appendChild(imageContainer);
    container.appendChild(productNameContainer);
    container.appendChild(priceContainer);
    container.appendChild(descriptionContainer);
    container.appendChild(editButton);
    container.appendChild(removeButton);

}

const editButtonClicked = () => {
    const editButton = document.getElementById('editButton');
    const removeButton = document.getElementById('removeButton');
    const editableData = document.querySelectorAll('.editable');
    for (editable of editableData) {
        const editableValue = editable.innerText;
        const inputField = document.createElement('input');
        inputField.value = editableValue;
        inputField.type = 'text';
        inputField.name = editable.id;
        inputField.id = `${editable.id}-input`
        editable.innerText = '';
        editable.appendChild(inputField);
    }
    editButton.textContent = 'Save';
    removeButton.textContent = 'Cancel';
    editButton.removeEventListener('click', editButtonClicked);
    editButton.addEventListener('click', sendProductUpdateRequest);
    removeButton.removeEventListener('click', removeButtonClicked);
    removeButton.addEventListener('click', cancelEditingButtonCLicked);
}

const removeButtonClicked = () => {

}

const cancelEditingButtonCLicked = () => {
    const editButton = document.getElementById('editButton');
    const removeButton = document.getElementById('removeButton');

    const editableData = document.querySelectorAll('.editable');
    for (editable of editableData) {
        switch (editable.id) {
            case 'description': {
                editable.innerText = product.description
                break;
            }
            case 'productName': {
                editable.innerText =  product.productName;
                break;
            }
            case 'price': {
                editable.innerText = product.price ;
                break;
            }
        }
    }

    editButton.textContent = 'Edit';
    removeButton.textContent = 'Remove Product';
    editButton.removeEventListener('click', sendProductUpdateRequest);
    editButton.addEventListener('click', editButtonClicked);
    removeButton.removeEventListener('click', cancelEditingButtonCLicked);
    removeButton.addEventListener('click', removeButtonClicked);
}

const sendProductUpdateRequest = event => {

    const editButton = document.getElementById('editButton');
    const removeButton = document.getElementById('removeButton');
    const description = document.getElementById('description-input').value;
    const price = document.getElementById('price-input').value;
    const name = document.getElementById('productName-input').value;
    const xmlRequest = prepareXmlRequest(name, description, price);
    fetch(APP_URL + `/xxe/products/${product.id}`, {
        method: "PUT",
        headers: {
            "Content-Type": "text/plain",
        },
        body: xmlRequest
    })
        .then(response => response.json())
        .then(p => {
            product.id = p.id;
            product.productName = p.name;
            product.imageUrl = p.imageUrl;
            product.price = p.price;
            product.description = p.description;
        }).then(p => {
        const editableData = document.querySelectorAll('.editable');
        for (editable of editableData) {
            switch (editable.id) {
                case 'description': {
                    editable.innerText = product.description
                    break;
                }
                case 'productName': {
                    editable.innerText =  product.productName;
                    break;
                }
                case 'price': {
                    editable.innerText = product.price ;
                    break;
                }
            }
        }
        editButton.textContent = 'Edit';
        removeButton.textContent = 'Remove Product';
        editButton.removeEventListener('click', sendProductUpdateRequest);
        editButton.addEventListener('click', editButtonClicked);
        removeButton.removeEventListener('click', cancelEditingButtonCLicked);
        removeButton.addEventListener('click', removeButtonClicked)

    })
        .catch(console.log);

}

const prepareXmlRequest = (productName, description, price) => {
    let xmlReqBody = `<?xml version="1.0" encoding="UTF-8"?><Product>`;
    if (productName) {
        xmlReqBody = xmlReqBody.concat(`<name>${productName}</name>`);
    }
    if (description) {
        xmlReqBody = xmlReqBody.concat(`<description>${description}</description>`);
    }
    if (price) {
        xmlReqBody = xmlReqBody.concat(`<price>${price}</price>`);
    }
    xmlReqBody = xmlReqBody.concat(`</Product>`)
    console.log(xmlReqBody);
    return xmlReqBody;
}


getProductInfo();