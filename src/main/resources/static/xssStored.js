const ALL_POSTS_SCREEN_TITLE = "Select post";
const APP_URL = 'http://localhost:8080';
const postPath = `xssStoredPostPage.html`;

const container = document.getElementById("xss-stored-container");
let posts = null;
const getAllPosts = () => {
    fetch(APP_URL + `/xss/stored/posts`)
        .then(response => response.json())
        .then(postsResponse => posts = postsResponse.posts)
        .then(p => displayPostsAsList(posts, container))
        .then(p => console.log(posts))
        .catch(console.log);
}

const displayPostsAsList = (posts, container) => {
    container.innerHTML = "";
    const postsContainer = document.createElement("div");
    for (const p of posts) {
        const pContainer = document.createElement("div");

        pContainer.className = "post";
        for (const key in p) {
            const pDataSpan = document.createElement("span");
            pDataSpan.textContent = p[key];
            pDataSpan.className = "postData";
            pContainer.appendChild(pDataSpan);
        }
        const id = p["id"];

        pContainer.addEventListener('click', () =>
        {
            window.location = `${postPath}?postId=${id}`;
        });

        postsContainer.appendChild(pContainer);
    }
    container.appendChild(postsContainer);
}

getAllPosts();