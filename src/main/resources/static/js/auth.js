
    let apiurl = document.querySelector('#apiUrl').value;
document.getElementById('submit').addEventListener('click', function() {

        let correo = document.getElementById('correo').value;
        let password = document.getElementById('password').value;
        if (correo.length < 0 || password.length < 0) {
            alerta('Correo y/o contraseña no pueden estar vacíos');
        }
        if (/^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/
            .test(correo)) {

            if (/^(?=.*\d)[a-zA-Z\d]{8,}$/
                .test(password)) {

                fetch(apiurl, {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                    },
                    body: JSON.stringify({ correo: correo, password: btoa(password) }),
                }).then(function(response) {


                    if (response.status === 404) {
                        alerta('No existe el usuario');
                        document.getElementById('password').value = '';
                        return;
                    }
                    if (response.status === 400) {
                        alerta('Correo y/o contraseña no cumplen con el formato');
                        document.getElementById('password').value = '';
                        document.getElementById('correo').value = '';
                        return;
                    }



                    if (response.status === 200) {
                        return response.text();
                    }
                    throw new Error('Error en la respuesta: ' + response.status);
                }).then( async function(data) {

                    let user = atob(data)
                    sessionStorage.setItem( 'CM-token' , data );
                    let nombreArea = window.location.href.split("/")[3];
                    let nuevaUrl = `/${nombreArea}/home/${user.id}`;
                    window.location.replace(nuevaUrl);


                }).catch(function(err) {
                        console.error('Error: ' + err.message);
                        console.log(err.toString());
                });


            } else {
                alerta('Contraseña no cumple los caracteres mínimos 8 letras y un número');
            }

        }else {
            alerta('Correo no válido');
        }
    });



function alerta(mensaje) {
    document.querySelector('#showerrors').innerText = mensaje;
    document.querySelector('#showerrors').style.display = 'block';
}