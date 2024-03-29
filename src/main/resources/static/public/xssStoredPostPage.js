const loggedUserNames = 'John Doe';
const loggedUserId = 'attacker';
const APP_URL = 'http://localhost:8080';
const hintText = document.getElementById("hint-text");
const xssiHint = "To successfully execute a stored XSS attack you must send malicious javascipt code or HTML code. Insert<><img src=1 onerror=alert(document.cookie)> as a comment and see the results.";
hintText.textContent = xssiHint;

const container = document.getElementById("xss-stored-post-container");
let post = {
    id: null,
    title: null,
    content: null,
    userId: null,
    userProfilePicture: null,
    userNames: null,
    creationTs: null,
    comments: null
};

const getPostWithComments = () => {
    const queryParam = window.location.search;
    const postId = queryParam.slice(queryParam.indexOf('=') + 1);
    fetch(APP_URL + `/xss/stored/posts/${postId}`)
        .then(response => {
            console.log(response);
            return response.json();
        })
        .then(p => {
            console.log(p)
            initializePost(p.id, p.title, p.content, p.userId, p.userProfilePicture, p.userNames, p.creationTs, p.comments);
            displayPost();

        })
        .catch(console.log);
}

const initializePost = (id, title, content, userId, userProfilePicture, userNames, creationTs, comments) => {
    post.id = id;
    post.title = title;
    post.content = content;
    post.userId = userId;
    post.userProfilePicture = userProfilePicture;
    post.userNames = userNames;
    post.creationTs = creationTs;
    post.comments = comments;
}

const displayPost = () => {
    // const imageContainer = document.createElement("div");
    // imageContainer.className = 'postImage';
    // const image = document.createElement('img');
    // image.src = post.userProfilePicture;
    // imageContainer.appendChild(image);
    const postContainer = document.createElement('div');
    postContainer.className = "selectedXssPostContainer";
    const postTitle = document.createElement('h2');
    postTitle.innerText = post.title;
    postTitle.className = 'selectedXssPostTitle';
    postContainer.appendChild(postTitle);

    const postContent = document.createElement('p');
    postContent.className = 'selectedXssPostContent';
    postContent.innerText = post.content;
    postContainer.appendChild(postContent)

    const postCreationDate = document.createElement('p');
    postCreationDate.className = 'selectedXssPostDate';
    postCreationDate.innerText = post.creationTs;
    postContainer.appendChild(postCreationDate);

    const postCreatorSignature = document.createElement('p');
    postCreatorSignature.className = 'selectedXssPostAuthor';
    postCreatorSignature.innerText = post.userNames;
    postContainer.appendChild(postCreatorSignature);

    const commentsContainer = document.createElement('div');
    commentsContainer.id = 'selectedXssPostCommentsContainer';
    attachComments(post.comments, commentsContainer);
    const addCommentForm = document.createElement('form');
    addCommentForm.className = "addCommentForm";
    addCommentForm.addEventListener('submit', addNewComment)
    const addCommentInput = document.createElement('input');
    addCommentInput.type = 'textarea';
    addCommentInput.name = 'newComment';
    addCommentInput.className = "newXSSCommentInput";
    const submitButton = document.createElement('input');
    submitButton.type = 'submit';
    submitButton.value = 'Add Comment';
    submitButton.className = "newXSSCommentButton";
    addCommentForm.appendChild(addCommentInput);
    addCommentForm.appendChild(submitButton);

    container.appendChild(postContainer);
    container.appendChild(commentsContainer);
    container.appendChild(addCommentForm);
}

const attachComments = (comments, commentsContainer) => {
    if (!commentsContainer) {
        commentsContainer = document.getElementById('selectedXssPostCommentsContainer');
    }
    commentsContainer.innerHTML = '';
    for (const comment of comments) {
        const commentContainer = document.createElement('div');
        commentContainer.className = 'selectedXssPostComment';

        const commentAuthor = document.createElement('h5');
        commentAuthor.innerText = comment.userNames;
        commentAuthor.className = 'selectedXssPostCommentAuthor';
        commentContainer.appendChild(commentAuthor);

        const commentContent = document.createElement('p');
        commentContent.innerHTML = comment.content;
        commentContent.className = 'selectedXssPostCommentContent';
        commentContainer.appendChild(commentContent);

        const commentCreationDate = document.createElement('div');
        commentCreationDate.innerText = comment.creationTs;
        commentCreationDate.className = 'selectedXssPostCommentDate';
        commentContainer.appendChild(commentCreationDate);
        commentsContainer.appendChild(commentContainer);
    }
}

const addNewComment = event => {
    event.preventDefault();
    event.stopPropagation();
    console.log("aaaaaaaaaaaaaaa");
    const data = new FormData(event.target);

    // const dataObject = Object.fromEntries(data.entries());
    // console.log(dataObject);
    //
    // data.forEach((value, key) => {
    //     console.log(`${key}: ${value}`);
    // });
    //
    // const comment = data.get("newComment");
    // console.log(`comment: ${newComment}`);
    const comment = data.get("newComment");
    const headers = new Headers();
    headers.append("Content-Type", "application/json");
    const reqBody = JSON.stringify({
        comment: comment,
        userId: 1,
        userNames: loggedUserNames,
        userProfilePicture: 'sss'
    });
    fetch(APP_URL + `/xss/stored/posts/${post.id}/comments`, {
        method: 'POST', headers: headers, body: reqBody
    })
        .then(response => {
            console.log(response);
            // const textResponse = response.text();
            // textResponse.then(text => console.log(text));
            return response.text();
        })
        .then(comments => {
            comments = JSON.parse(comments)
            console.log(comments);
            post.comments = comments;
            attachComments(comments);

        })
        .catch(console.log);
}

getPostWithComments();
