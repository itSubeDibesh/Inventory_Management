function SessionsUserData() {
    for (const key in UserData) {
        const value = UserData[key];
        window.sessionStorage.setItem(key, value);
    }
    fetchLoggedInUserDetails();
}

/**
 * Redirects To Page Default (Dashboard)
 * @param link
 */
function redirect(link = "dashboard") {
    window.location.href = `/${link}`;
}

function fetchLoggedInUserDetails() {
    if (Session.length != 0) {
        if (UserData.id !== null) {
            fetch(`/api/users/byLogin/${UserData.id}`)
                .then(res => res.json())
                .then(response => {
                    for (const key in response) {
                        const value = response[key];
                        window.sessionStorage.setItem("user_" + key, value);
                    }
                })
                .catch(err => redirect("logout"))
        } else redirect()
    } else redirect("logout")
}

UserData.id != null ? SessionsUserData() : redirect("logout");