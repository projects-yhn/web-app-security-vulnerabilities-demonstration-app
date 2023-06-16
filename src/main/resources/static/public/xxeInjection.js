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
    const productsContainer = document.createElement("div");
    for (const product of products) {
        const productContainer = document.createElement("div");

        productContainer.className = "product";
        for (const productKey in product) {
            const productDataSpan = document.createElement("span");
            productDataSpan.textContent = product[productKey];
            productDataSpan.className = "productData";
            productContainer.appendChild(productDataSpan);
        }
        const id = product["id"];

        productContainer.addEventListener('click', () =>
        {
            window.location = `${productPath}?productId=${id}`;
        });

        productsContainer.appendChild(productContainer);
    }
    container.appendChild(productsContainer);

}

getMyProducts()