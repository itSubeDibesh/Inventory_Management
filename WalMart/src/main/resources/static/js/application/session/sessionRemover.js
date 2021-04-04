const sessionKeyArray = ["id", "phone", "email", "avatar", "getImagePath", "userName"];
const handleLogout = () => {
    sessionKeyArray.forEach(key => window.sessionStorage.removeItem(key));
    window.location.href = "/logout";
}