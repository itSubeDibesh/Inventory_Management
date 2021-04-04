function SessionsUserData(){
    for (const key in UserData) {
        const value = UserData[key];
        window.sessionStorage.setItem(key,value);
    }
}

if (UserData.id != null) {
    SessionsUserData();
} else window.location.href = "/logout";