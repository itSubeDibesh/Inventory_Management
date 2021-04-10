const sessionKeyArray = ["id", "phone", "email", "avatar", "getImagePath", "userName","user_id","user_contactNumber","user_loginId","user_address","user_gender","user_fullName","user_dob","user_tpin"];
const handleLogout = () => {
    sessionKeyArray.forEach(key => window.sessionStorage.removeItem(key));
    window.location.href = "/logout";
}