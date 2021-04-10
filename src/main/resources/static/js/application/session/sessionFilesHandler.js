const Session = window.sessionStorage,
    sessionKey = {
        id: Session.getItem("id"),
        phone: Session.getItem("phone"),
        email: Session.getItem("email"),
        avatar: Session.getItem("avatar"),
        getImagePath: Session.getItem("getImagePath"),
        userName: Session.getItem("userName"),
        user_id: Session.getItem("user_id"),
        user_contactNumber: Session.getItem("user_contactNumber"),
        user_loginId: Session.getItem("user_loginId"),
        user_address: Session.getItem("user_address"),
        user_address: Session.getItem("user_address"),
        user_gender: Session.getItem("user_gender"),
        user_fullName: Session.getItem("user_fullName"),
        user_dob: Session.getItem("user_dob"),
        user_tpin: Session.getItem("user_tpin"),
    };


function setUserDetails() {
    // Reloads If No Key is Found
    if (sessionKey.id == null) {
        if (window.location.href.includes("dashboard")) {
            location.reload()
        } else {
            window.location.href = "/dashboard";
        }
    } else {
        if (Session.length == 0) window.location.href = "/dashboard";
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
