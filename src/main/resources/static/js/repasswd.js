
    let apiurl = document.querySelector("#apiUrl").value;
document.getElementById('submit').addEventListener('click', function() {
    let correo = document.querySelector("#correo").value ;
    alert('correo: ' + correo+'  &&  apiurl: '+apiurl);
    if (correo === '') {
        alerta('Debes ingresar un correo');
        return;
    }

    if (/^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/
        .test(correo)) {
         fetch(apiurl, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: correo
        }).then(function(response) {
            alert(response.status);
            if (response.status === 404) {
                alerta('No existe el usuario');
                document.getElementById('correo').value = '';
                return;
            }
            if (response.status === 400) {
                alerta('Correo no cumple con el formato');
                document.getElementById('correo').value = '';
                return;
            }
            if (response.status === 200) {
                return response.json();
            }
            throw new Error('Error en la respuesta: ' + response.status);
         }).then(function(data) {
             console.log(JSON.stringify(data));
             console.log(data.toString());
             let respuesta = JSON.stringify(data);
             if(data === true ){
               alert('Correo enviado');
                 let nombreArea = window.location.href.split("/")[3];
                 let nuevaUrl = `/${nombreArea}/auth`;
                document.querySelector("main").innerHTML = `<h1>Se ha restablecido la contraseña compruebe su correo </h1> <a href="${nuevaUrl}">Volver a iniciar sesion</a>`;
           }

         });
    }else {
        alerta('Correo no cumple con el formato');
    }
});

    function alerta(mensaje) {
        document.querySelector('#showerrors').innerText = mensaje;
        document.querySelector('#showerrors').style.display = 'block';
    }