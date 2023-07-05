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
    postsContainer.className = "xssPostsContainer"

    for(const p of posts) {
        const pContainer = preparePostContainer(p);
        pContainer.addEventListener('click', () =>
        {
            window.location = `${postPath}?postId=${p.id}`;
        });
        postsContainer.appendChild(pContainer);
    }
    container.appendChild(postsContainer);

}

const preparePostContainer = post => {
    const postContainer = document.createElement("div");
    postContainer.className = "xssPost";

    const postTitle = document.createElement("h2");
    postTitle.className = 'xssPostTitle';
    postTitle.innerText = post.title;
    postContainer.appendChild(postTitle);

    const postAuthor = document.createElement('p');
    postAuthor.className = 'xssPostAuthor';
    postAuthor.innerText = post.userNames;
    postContainer.appendChild(postAuthor);

    const postContent = document.createElement('p');
    postContent.className = 'xssPostContent';
    postContent.innerText = post.content;
    postContainer.appendChild(postContent);

    return postContainer;

}

getAllPosts();