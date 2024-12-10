document.querySelector('#modificar').addEventListener('click', function () {
    const correo = document.querySelector('#email').value.trim();
    const password = document.querySelector('#password').value.trim();
    const password2 = document.querySelector('#password2').value.trim();
    const telefono = document.querySelector('#telefono').value.trim();
    const nombre = document.querySelector('#nombre').value.trim();
    const apellidos = document.querySelector('#apellidos')?.value.trim(); // Opcional si no es cliente.
    const empleado = !document.querySelector('#escliente');

    // Validaciones
    if (!correo) {
        alerta('Correo no puede estar vacío');
        return;
    }
    if (!/^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/.test(correo)) {
        alerta('Correo no cumple con el formato');
        return;
    }
    if (!password) {
        alerta('Contraseña no puede estar vacía');
        return;
    }
    if (!/^(?=[A-Za-z0-9+/]*[0-9]{1})(?=[A-Za-z0-9+/]*[A-Z]{1})[A-Za-z0-9+/]*={0,2}$/.test(password)) {
        alerta('Contraseña no cumple con el formato, debe contener al menos 8 caracteres y un número y solo permite los caracteres A-Z, a-z, 0-9 y +/=');
        return;
    }
    if (password !== password2) {
        alerta('Las contraseñas no coinciden');
        return;
    }
    if (!telefono) {
        alerta('Teléfono no puede estar vacío');
        return;
    }
    if (!/^[67]\d{8}$/.test(telefono)) {
        alerta('Teléfono no cumple con el formato, debe empezar por 6 o 7 y tener 9 dígitos');
        return;
    }
    if (!empleado && !nombre) {
        alerta('Nombre no puede estar vacío');
        return;
    }
    if (!empleado && !apellidos) {
        alerta('Apellidos no pueden estar vacíos');
        return;
    }
    const fetchUrl = window.location.pathname.replace('modificarperfil', 'savecambios');
    let  user ;
    if (empleado) {
        user = {
            correo: correo,
            nombre: nombre,
            apellidos: apellidos,
            password: btoa(password),
            movil: telefono,

        }
    }else {
        user = {
            email: correo,
            password: btoa(password),
            movil: telefono,
            nombre: nombre
        }
    }

    fetch(fetchUrl, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            "authorization" : sessionStorage.getItem('CM-token'),
        },
        body: JSON.stringify(user),
    })
        .then(response => {
           if (response.ok) {
               return response.text();
           }
           })
        .then(data => {
            sessionStorage.removeItem('CM-token');
            sessionStorage.setItem('CM-token', data);
            window.history.back();
        })
        .catch(error => {
            alerta(`Error: ${error.message}`);
        });
});

    function alerta(mensaje) {
        const showErrors = document.querySelector('#showerrors');
        showErrors.innerText = mensaje;
        showErrors.style.display = 'block';
    }