
    let apiurl = document.querySelector("#apiUrl").value;
    let correo = document.getElementById('correo').value;
document.getElementById('submit').addEventListener('click', function() {

    if (/^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/
        .test(correo)) {
         fetch(apiurl, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({ correo: correo })
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
             document.querySelector("main ").innerHTML = "<h1>Se ha enviado un correo a tu cuenta restableciendo tu contraseña</h1>+<a th:href='${escliente} ? '@{/areaclientes/api/login}' : '@{/AreaEmpleado/api/login}'>Volver a inicio</a>";
         })
    }else {
        alerta('Correo no cumple con el formato');
    }
});

    function alerta(mensaje) {
        document.querySelector('#showerrors').innerText = mensaje;
        document.querySelector('#showerrors').style.display = 'block';
    }