window.addEventListener('load', async function () {
    const token = sessionStorage.getItem("CM-token");
    try {
        // Obtener las reservas
        const reservasResponse = await fetch('/api/obtenerReservas', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                authorization: token
            }
        });

        if (reservasResponse.status === 404) {
            let h1 = document.createElement("h1");
            h1.innerHTML = "No hay reservas";
            document.querySelector("#contenedor-servicios").append(h1);
            return; // Detener ejecución si no hay reservas
        }
        if (!reservasResponse.ok) {
            throw new Error('Error al obtener reservas');
        }
        const reservas = await reservasResponse.json();

     // Obtener los empleados
        const empleadosResponse = await fetch('/api/cargarempleados', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                authorization: token
            }
        });

        if (empleadosResponse.status === 404) {
            console.warn("No se encontraron empleados asignados.");
            return; // Detener ejecución si no hay empleados
        }
        if (!empleadosResponse.ok) {
            throw new Error('Error al obtener empleados');
        }
        const empleados = await empleadosResponse.json();

        // Mostrar en consola las respuestas obtenidas
        console.log(reservas);
        console.log(empleados);

        for (let r in reservas) {
            let contenedor = document.querySelectorAll('#contendor-servicios > div')[r-1];
            if (reservas[r].length === 0) {
                crearElementoTexto('h3', "Sin reservas actuales", contenedor);
                continue;
            }
            reservas[r].forEach(servicio => {
              let div = crearElemento("div", contenedor);
                div.style.display = 'grid';
                div.classList.add('border-especial');
                div.id = "reserva_id#"+servicio.id;
                crearElementoTexto("h4",servicio.tipo ,div);
                crearElementoTexto("p", "Fecha: " + servicio.fecha, div);
                crearElementoTexto("p", servicio.cliente, div);
                let divForm = crearElemento("div", div);
                divForm.style.display = 'flex';
                divForm.style.flexWrap = 'wrap';
                divForm.style.justifyContent = 'space-between';
                let labelEmpleado = crearElementoTexto("label", "Asignado a:", divForm);
                labelEmpleado.for = "selectEmpleado";
                let selectEmpleado = crearElemento("select", divForm);
                selectEmpleado.id = "selectEmpleado";
                selectEmpleado.name = "selectEmpleado";
                let optionDefault = crearElementoTexto("option", "Seleccionar empleado", selectEmpleado);
                optionDefault.value = "0";
                empleados.forEach(empleado => {
                    let option = crearElementoTexto("option", empleado.nombre+" "+empleado.apellidos, selectEmpleado);
                    option.value = empleado.id;
                });
                let buttonconfirmar = crearElementoTexto("button", "Confirmar", divForm);
                selectEmpleado.value = 0 ;
                buttonconfirmar.addEventListener('click', function () {
                    if (selectEmpleado.value === "0") {
                        alert("Seleccione un empleado");
                        return;
                    }
                 let datos = {
                     id: servicio.id,
                     idEmpleado: selectEmpleado.value
                 }
                    fetch('/api/asignarempleadoreserva', {
                        method: 'POST',
                        headers: {
                            'Content-Type': 'application/json',
                            authorization: token
                        },
                        body: JSON.stringify(datos)
                    }).then(response => {
                        if (response.status === 200) {
                          div.remove();
                        }
                    })
                 });
            });
        }

    } catch (error) {
        console.error("Ha ocurrido un error:", error.message);
    }
});

function crearElementoTexto(tag, contenido,padre) {
    let elemento = document.createElement(tag);
    elemento.textContent = contenido;
    padre.appendChild(elemento);
    return elemento;
}

function crearElemento(tag, padre) {
    let elemento = document.createElement(tag);
    padre.appendChild(elemento);
    return elemento;
}