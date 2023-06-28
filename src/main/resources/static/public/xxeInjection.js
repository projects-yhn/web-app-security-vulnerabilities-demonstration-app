const APP_URL = 'http://localhost:8080';
const productPath = `xxeInjectionProductPage.html`;
const container = document.getElementById("xxe-injection-container");
let myProducts = null;
const  getMyProducts = () => {
    fetch(APP_URL + "/xxe/products")
        .then(response => response.json())
        .then(productsJson => myProducts = productsJson)
        .then(voidPromise => displayProductsAsList(myProducts, container))
        .then(p => console.log(myProducts))
        .catch(console.log);
}

const displayProductsAsList = (products, container) => {
    container.innerHTML = "";
    const pageHeader = document.createElement('h1');
    pageHeader.innerText = 'My products for selling';
    pageHeader.className = 'pageHeader';

    const productsContainer = document.createElement("div");
    productsContainer.className = 'products';
    for (const product of products) {
        const productContainer = prepareProductContainer(product);
        const id = product["id"];

        productContainer.addEventListener('click', () => {
            window.location = `${productPath}?productId=${id}`;
        });

        productsContainer.appendChild(productContainer);
    }
    container.appendChild(pageHeader);
    container.appendChild(productsContainer);
}

const prepareProductContainer = product => {
    const productContainer = document.createElement("div");
    productContainer.className = 'product';

    const imageContainer = document.createElement("img");
    imageContainer.className = 'productImage'
    imageContainer.src = product.imageUrl;

    const productText = document.createElement('div');
    productText.className = 'productText';

    const productTitle = document.createElement('h1');
    productTitle.className = 'productTitle';
    productTitle.innerText = product.name;
    productText.appendChild(productTitle);

    const productCategory = document.createElement('h5');
    productCategory.className = 'productCategory';
    productCategory.innerText = product.category;
    productText.appendChild(productCategory);

    const productDescription = document.createElement('h4');
    productDescription.className = 'productDescription';
    productDescription.innerText = product.description;
    productText.appendChild(productDescription);

    const productPrice = document.createElement('h2');
    productPrice.className = 'productPrice';
    productPrice.innerText = `${product.price}$`;
    productText.appendChild(productPrice);

    productContainer.appendChild(imageContainer);
    productContainer.appendChild(productText);
    return productContainer;
}

getMyProducts()