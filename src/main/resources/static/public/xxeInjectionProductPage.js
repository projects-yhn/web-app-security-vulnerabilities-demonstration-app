const APP_URL = 'http://localhost:8080';
const container = document.getElementById("xxe-injection-product-container");
let selectedProduct = null;
const hintButton = document.getElementById("sqli-hints");
const hintText = document.getElementById("hint-text");
const xxeHint = `Open the browser inspector. Find the tab where the javascript files are located. Find the line in the code where data is prepared to be sent to the server and edit it so that you can read information from a file that you cannot access through the web client.`+
    `Use: <?xml version="1.0" encoding="UTF-8"?><!DOCTYPE yhn [ <!ENTITY xxe SYSTEM "file:///C:%5CUsers%5CYalchan%20Niyaziev%5CDocuments%5Cyhn_projects%5CMaster_CyberSecurity%5Cweb-app-security-vulnerabilities-demonstration-app%5CfakeCredentials%5Cpasswords.yaml"> ]><Product>`;

hintText.textContent = xxeHint;

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
    const imageAndButtonsContainer = document.createElement("div");
    imageAndButtonsContainer.className = "buttonsAndImageContainer";

    const imageContainer = document.createElement("div");
    imageContainer.className = 'selectedProductImage';
    const productImage = document.createElement('img');
    productImage.src = imageUrl;
    imageContainer.appendChild(productImage);

    const editButton = document.createElement("button");
    editButton.id = "editButton";
    editButton.className = "xxeButton";
    editButton.textContent = "Edit";
    editButton.addEventListener('click', editButtonClicked);

    const removeButton = document.createElement("button");
    removeButton.id = "removeButton";
    removeButton.className = "xxeButton";
    removeButton.textContent = "Remove Product";
    removeButton.addEventListener('click', removeButtonClicked);

    const buttonsContainer = document.createElement("div");
    buttonsContainer.className = "buttonsContainer";
    buttonsContainer.appendChild(editButton);
    buttonsContainer.appendChild(removeButton);

    imageAndButtonsContainer.appendChild(imageContainer);
    imageAndButtonsContainer.appendChild(buttonsContainer)


    const productTextContainer = document.createElement("div");
    productTextContainer.className = "productTextContainer";

    const productNameContainer = document.createElement("h3");
    productNameContainer.textContent = name;
    productNameContainer.id = 'productName';
    productNameContainer.className = 'selectedProductName editable'
    productTextContainer.appendChild(productNameContainer);
    // productNameContainer.className = 'selectedProductNameContainer';



    const descriptionContainer = document.createElement("p");
    descriptionContainer.textContent = description;
    descriptionContainer.id = 'description';
    descriptionContainer.className = 'selectedProductDescription editable'
    productTextContainer.appendChild(descriptionContainer);

    // descriptionContainer.appendChild(descriptionSpan);
    // descriptionContainer.className = 'selectedProductDescriptionContainer';

    // const priceContainer = document.createElement("div");
    const priceContainer = document.createElement("h4");
    priceContainer.textContent = `${price}$`;
    priceContainer.id = 'price';
    priceContainer.className = 'selectedProductPrice editable'
    productTextContainer.appendChild(priceContainer);

    // priceContainer.appendChild(priceSpan);
    // priceContainer.className = 'selectedProductPriceContainer';

    container.appendChild(imageAndButtonsContainer);
    container.appendChild(productTextContainer);
}

const editButtonClicked = () => {
    const editButton = document.getElementById('editButton');
    const removeButton = document.getElementById('removeButton');
    const editableData = document.querySelectorAll('.editable');
    for (editable of editableData) {
        let editableValue = editable.innerText;
        if (editableValue.endsWith('$')) {
            editableValue = editableValue.replace(`$`, '');
        }
        const inputField = document.createElement('input');
        inputField.className = "editProductInput";
        inputField.value = editableValue;
        inputField.style.width = `${editableValue.length * 10}px`;
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
                editable.innerText = `${product.price}$` ;
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
                    editable.innerText =`${product.price}$` ;
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