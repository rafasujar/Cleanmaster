window.document.addEventListener('DOMContentLoaded', function () {
    // Definición de elementos
    const nombre = document.getElementById("nombre");
    const apellido = document.getElementById("apellidos");
    const correo = document.getElementById("correo");
    const telefono = document.getElementById("telefono");
    const numss = document.getElementById("numSS");
    const iban = document.getElementById("iban");
    const password = document.getElementById("password");
    const errorContainer = document.querySelector('#showerrors');
    const submitButton = document.getElementById("submit");

    // Cargar tipos de servicio
    fetch("/api/obtenerTiposServicios")
        .then(response => response.json())
        .then(data => {
            data.forEach(servicio => {
                const input = document.createElement("input");
                input.type = "checkbox";
                input.name = "tipoServicio";
                input.value = servicio.id;

                const label = document.createElement("label");
                label.innerHTML = servicio.nombre;

                const contenedor = document.createElement("div");
                contenedor.append(input, label);
                contenedor.style.display = "flex";

                document.querySelector("#contenedor-tipos").append(contenedor);
            });
        })
        .catch(error => mostrarError(`Error cargando servicios: ${error.message}`));

    // Validar formulario
    function validarFormulario() {
        if (!nombre.value.trim()) return 'Nombre no puede estar vacío';
        if (!apellido.value.trim()) return 'Apellidos no pueden estar vacíos';
        if (!correo.value.includes('@')) return 'Correo no es válido';
        if (!telefono.value.match(/^\d{9}$/)) return 'Teléfono debe tener 9 dígitos';
       // if (!iban.value.match(/^ES\d{22}$/)) return 'IBAN no es válido';
        if (password.value.length < 6) return 'Contraseña debe tener al menos 6 caracteres';
        const tiposServicios = Array.from(document.querySelectorAll('input[name="tipoServicio"]:checked'));
        if (tiposServicios.length === 0) return 'Debe seleccionar al menos un tipo de servicio';
        return null; // Sin errores
    }

    // Mostrar mensaje de error
    function mostrarError(mensaje) {
        errorContainer.innerText = mensaje;
        errorContainer.style.display = 'block';
    }

    // Crear empleado y asignar servicios
    async function enviarFormulario() {
        const error = validarFormulario();
        if (error) return mostrarError(error);

        submitButton.disabled = true; // Deshabilitar botón mientras se procesa

        const empleado = {
            nombre: nombre.value,
            apellidos: apellido.value,
            correo: correo.value,
            movil: telefono.value,
            numss: numss.value,
            iban: iban.value,
            password: btoa(password.value)
        };

        try {
            // Crear empleado
            const response = await fetch('/api/altaempleado', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    authorization: sessionStorage.getItem("CM-token")
                },
                body: JSON.stringify(empleado)
            });

            if (!response.ok) throw new Error('Error al crear empleado');
            const idEmpleado = await response.text();

            // Asignar servicios
            const tiposServicios = Array.from(document.querySelectorAll('input[name="tipoServicio"]:checked')).map(input => input.value);
            await asignarTiposServicios(idEmpleado, tiposServicios);

            alert('Empleado y servicios dados de alta correctamente');
        } catch (error) {
            mostrarError(`Ocurrió un error: ${error.message}`);
        } finally {
            submitButton.disabled = false; // Habilitar botón nuevamente
        }
    }

    // Asignar tipos de servicios al empleado
    async function asignarTiposServicios(idEmpleado, tiposServicios) {
        const url = `/api/altaempleado/servicios/${idEmpleado}`;
        const response = await fetch(url, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                authorization: sessionStorage.getItem("CM-token")
            },
            body: JSON.stringify(tiposServicios)
        });

        if (!response.ok) {
            throw new Error(`Error asignando servicios: ${response.status} ${response.statusText}`);
        }

        await window.history.back();
    }

});
