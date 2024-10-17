document.querySelector('#submit').addEventListener('click', function() {
    let correo = document.querySelector('#correo').value;
    let password = document.querySelector('#password').value;
    let nombre = document.querySelector('#nombre').value;
    let password2 = document.querySelector('#password2').value;
    let telefono = document.querySelector('#telefono').value;


    if (correo.length === 0 ){
        alerta('Correo no puede estar vacío');
        return;
    }
    if (password.length === 0 ){
        alerta('Contraseña no puede estar vacía');
        return;
    }
    if (nombre.length === 0 ){
        alerta('Nombre no puede estar vacío');
        return;
    }
    if (password2.length === 0 ){
        alerta('El campo de repetir la contraseña no puede estar vacío');
        return;
    }
    if (telefono.length === 0 ){
        alerta('Teléfono no puede estar vacío');
        return;
    }

    if (/^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/.test(correo)) {

        if (/^(?=.*\d)[a-zA-Z\d]{8,}$/.test(password)) {

            if (password === password2) {

                if (/^[67]\d{8}$/.test(telefono)) {
                    // Llamada a la API
                    fetch('/areaclientes/api/registro', {
                        method: 'POST',
                        headers: {
                            'Content-Type': 'application/json',
                        },
                        body: JSON.stringify({ correo: correo, password: password, nombre: nombre, movil: telefono.toString() }),
                    })
                        .then(function(response) {
                            if (response.status === 400) {
                                alerta('Correo y/o contraseña no cumplen con el formato');
                                return;
                            }
                            if (response.status === 200) {
                                return response.json();
                            }
                            if (response.status === 409) {
                                alerta('La cuenta de correo ya esta registrada');
                                return;
                            }
                            throw new Error('Error en la respuesta: ' + response.status);
                        })
                        .then(async function (data) {
                            if (typeof data !== 'string') {
                                console.log("passed");
                                sessionStorage.setItem('CM-token', await data);
                                let nuevaUrl = `/areaclientes/home/${data.id}`;

                                window.location.replace(nuevaUrl);
                            }
                        })
                        .catch(function(err) {
                            console.error('Error: ' + err.message);
                        });
                } else {
                    alerta('Teléfono no cumple con el formato, debe empezar por 6 o 7 y tener 9 dígitos');
                }
            } else {
                alerta('Las contraseñas no coinciden');
            }
        } else {
            alerta('Contraseña no cumple con el formato, debe contener al menos 8 caracteres y un número');
        }
    } else {
        alerta('Correo no cumple con el formato');
    }
});

function alerta(mensaje) {
    document.querySelector('#showerrors').innerText = mensaje;
    document.querySelector('#showerrors').style.display = 'block';
}
