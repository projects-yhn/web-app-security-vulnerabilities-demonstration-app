const APP_URL = 'http://localhost:8080'
const container = document.getElementById("command-injection-container");
const goBackButton = document.getElementById("go-back-to-parent");
let files = null;
let rootPath = '';

const getDirectoryContent = specificPath => {
    checkForInvalidPath();
    const queryParamStr = window.location.search;
    const url = APP_URL + "/command-injection/files" + (queryParamStr ? queryParamStr : '');
    console.log(url)
    fetch(url, {method: 'GET', redirect: 'follow'})
        .then(resp => {
            if (resp.redirected) {
                window.location.href = resp.url;
            }
            return resp.json();
        })
        .then(respJson => {
            rootPath = respJson.path;
            files = respJson.files;
        })
        .then(displayDirectoryContent)
        .catch(console.log);
}

const displayDirectoryContent = () => {
    container.innerHTML = "";
    const filesContainer = document.createElement("div");
    for (const file of files) {
        const fileNameSpan = document.createElement("span");
        const fileIconSpan = document.createElement("span");

        const fileDataDiv = document.createElement("div");
        fileNameSpan.textContent = file.fileName;
        if (file.isDirectory) {
            fileIconSpan.className = "directoryIcon"
            fileDataDiv.className = "directoryRow";
            fileDataDiv.addEventListener("click", getSpecificDirectoryContent);
        } else {
            fileIconSpan.className = "fileIcon"
            fileDataDiv.className = "fileRow";
            fileDataDiv.addEventListener("click", downloadFile);
        }
        fileDataDiv.appendChild(fileIconSpan);
        fileDataDiv.appendChild(fileNameSpan);

        filesContainer.appendChild(fileDataDiv);
    }
    container.appendChild(filesContainer);
}

const getSpecificDirectoryContent = event => {
    console.log("get directory content");
    checkForInvalidPath();
    const selectedDirectoryName = event.target.innerText;
    const queryParamStr = window.location.search;
    if (queryParamStr) {
        const directoryInQueryParam = queryParamStr.slice(queryParamStr.indexOf("=") + 1);
        const parentDirectories = atob(directoryInQueryParam);
        const directoryTree = parentDirectories + "/" + selectedDirectoryName;
        window.location.search = `?path=${btoa(directoryTree)}`;
    } else {
        window.location.search = `?path=${btoa(selectedDirectoryName)}`;
    }
}

const goBackToParentDirectory = event => {
    event.preventDefault();
    event.stopPropagation();
    console.log("Go back button clicked.");
    checkForInvalidPath();
    const queryParamStr = window.location.search;
    if (queryParamStr) {
        const directoryInQueryParam = queryParamStr.slice(queryParamStr.indexOf("=") + 1);
        console.log(directoryInQueryParam);
        const parentDirectories = atob(directoryInQueryParam);
        console.log(parentDirectories);
        let newDirectoriesPath = parentDirectories;
        const lastIndexOfSeparator = parentDirectories.lastIndexOf("/");
        if (lastIndexOfSeparator === -1) {
            newDirectoriesPath = '';
        } else {
            newDirectoriesPath = parentDirectories.slice(0, parentDirectories.lastIndexOf("/"));
        }
        console.log(newDirectoriesPath);
        window.location.search = `?path=${btoa(newDirectoriesPath)}`;
    }
}

const downloadFile = event => {
    console.log("download file")
    const selectedFileName = event.target.innerText;
    let parentDirectories = '';
    console.log(selectedFileName);
    const queryParamStr = window.location.search;
    if (queryParamStr) {
        const directoryInQueryParam = queryParamStr.slice(queryParamStr.indexOf("=") + 1);
        console.log(directoryInQueryParam);
        parentDirectories = atob(directoryInQueryParam);
        console.log(parentDirectories);
    }
    const fileDownloadPath = parentDirectories ? `${parentDirectories}/${selectedFileName}` : selectedFileName
    const headers = new Headers();
    headers.append("Content-Type", "application/json");
    const reqBody = JSON.stringify({
        "filePath": fileDownloadPath
    });
    fetch(APP_URL + "/command-injection/files/file",
        {method: 'POST', headers: headers, body: reqBody, redirect: 'follow'})
        .then(resp => {
            if (resp.redirected) {
                window.location.href = resp.url;
            }
            return resp.json();
        })
        .then(documentResp => prepareAndDownloadFile(documentResp, selectedFileName))
        .catch(console.log);
}

const prepareAndDownloadFile = (documentResponse, selectedFileName) => {
    const binaryString = atob(documentResponse.fileContent);
    const binaryLength = binaryString.length;
    const bytes = new Uint8Array(binaryLength);
    for (let i = 0; i < binaryLength; i++) {
        bytes[i] = binaryString.charCodeAt(i);
    }
    const blob = new Blob([bytes], {type: "octet/stream"});
    const link = document.createElement('a');
    link.href = window.URL.createObjectURL(blob);
    link.download = selectedFileName;
    link.click();
}

const checkForInvalidPath = () => {
    const queryParam = window.location.search;
    if (queryParam) {
        const directoryInQueryParam = queryParam.slice(queryParam.indexOf("=") + 1);
        if (directoryInQueryParam) {
            const directoryDecoded = atob(directoryInQueryParam);
            const indexOfInvalidChar = directoryDecoded.indexOf(" ");
            if (indexOfInvalidChar >= 0) {
                window.location.search = '';
            }
        }
    }
}

goBackButton.addEventListener("click", goBackToParentDirectory)
getDirectoryContent();
