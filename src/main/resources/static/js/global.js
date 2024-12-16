window.addEventListener('load', function() {
    if (document.querySelector("#logo") !== null) {
        document.querySelector("#logo").addEventListener('click', function() {
            if (sessionStorage.getItem("CM-token") === null) {
                window.location.href = "/";
            }else{
                fetch("/api/seguridad/obteneerhome", {
                    method:"POST",
                    headers: {
                        'Content-Type': 'application/json',
                        authorization: sessionStorage.getItem("CM-token")
                    }
                }).then(response => {
                    if(response.ok) {
                        return response.text();
                    }
                    throw new Error(response.statusText);
                }).then(data => {
                    window.location.href = location.href = data;
                }).catch(error => {
                    window.location.href = "/";
                });
            }
        });

    }
    //logica para bloquear el acceso a la pagina si no tienes autorizacion





});
