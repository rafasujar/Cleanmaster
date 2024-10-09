window.addEventListener('load', function() {
    try{
        console.log(sessionStorage)
        if (!sessionStorage.getItem("CM-token") === null || !sessionStorage.getItem("CM-token") === undefined){
            console.log("existe")
        }else {
            console.log("no existe")
        }

    }catch (e){
        console.log(e)
    }
    /*
    if (localStorage.getItem("CM-token").length === 1){
        location.href = "https://www.w3schools.com/js/js_cookies.asp"
    }
    */
});
