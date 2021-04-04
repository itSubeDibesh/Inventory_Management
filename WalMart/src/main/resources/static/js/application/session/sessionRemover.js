const sessionKey = ["id", "phone", "email", "avatar", "getImagePath", "userName"];
const handleLogout = () => {
    sessionKey.forEach(key => window.sessionStorage.removeItem(key));
    window.location.href = "/logout";
}