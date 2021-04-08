const Session = window.sessionStorage,
    sessionKey = {
        id: Session.getItem("id"),
        phone: Session.getItem("phone"),
        email: Session.getItem("email"),
        avatar: Session.getItem("avatar"),
        getImagePath: Session.getItem("getImagePath"),
        userName: Session.getItem("userName")
    };

function setUserDetails() {
    // Reloads If No Key is Found
    if (sessionKey.id == null) location.reload()
    else {
        if (Session.length == 0)window.location.href = "/dashboard";
        // Add Image
        document.getElementById("LoggedInUserAvatar").innerHTML += `<img class="img-radius" src="/${sessionKey.avatar != "null" ? sessionKey.getImagePath : "images/defaultUser.png"}" alt="${sessionKey.userName != "null" ? sessionKey.userName : "User-Image"}" >`;
        // Add Name
        const names = document.getElementsByClassName("LoggedInUserName");
        for (let i = 0; i < names.length; i++) {
            names[i].innerHTML += sessionKey.userName;
        }
    }
}

addEventListener('load', setUserDetails);
