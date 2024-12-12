const servicios = document.querySelector("#servicios");
const direcciones = document.querySelector("#direciones");

window.addEventListener('load', function() {

    fetch("/api/obtenerTiposServicios").then(response => {
        if (response.ok) {
            return response.json();

        }
        }).then(data => {
            for (let i = 0; i < data.length; i++) {
                let option = document.createElement("option");
                option.value = data[i].id;
                option.textContent = data[i].nombre;
                servicios.appendChild(option);
            }
        })

    document.querySelector("#add-direccion").href = location.href.replace("nuevareserva", "nuevadireccion");

    fetch("/api/direciones/obtenerDirecciones", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: sessionStorage.getItem('CM-token')
    }).then(response => {
        if (response.ok) {
            return response.json();
        }
    }).then(data => {
        if (data !== null) {
            for (let i = 0; i < data.length; i++) {
                let radio = document.createElement("input");
                radio.type = "radio";
                radio.name = "direccion";
                radio.value = data[i].id;
                let label = document.createElement("label");
                label.textContent = data[i].direccion;
                direcciones.innerHTML += radio.outerHTML + label.outerHTML + "<br>";
            }
        } else {
            let p = document.createElement("p");
            p.textContent = "No hay direcciones guardadas";
            direcciones.appendChild(p);
        }
    }).catch(error => {
        console.error(error);
    });


    document.querySelector("#reservar").addEventListener('click', function() {
        let servicio = servicios.value;
        let fechacita = document.querySelector("#fechaSeleccionada").value;
        let direccion ;
        let espe = document.querySelector("#espe").value;

        if (document.querySelector('input[name="direccion"]:checked') === null) {
        let errorDireccion = document.querySelector("#error-direcciones");
        errorDireccion.textContent = "Seleccione una dirección";
        errorDireccion.classList.remove("oculto");
        errorDireccion.style.color = "red";
        return;
        }else {
            direccion = document.querySelector('input[name="direccion"]:checked').value;
        }

        if (servicio === "") {
            let errorServicio = document.querySelector("#error-tipos");
            errorServicio.textContent = "Seleccione un tipo de servicio";
            errorServicio.classList.remove("oculto");
            errorServicio.style.color = "red";
            return;
        }


        if (fechacita === "") {
            let errorFecha = document.querySelector("#error-fecha");
            errorFecha.textContent = "Seleccione una fecha";
            errorFecha.classList.remove("oculto");
            errorFecha.style.color = "red";
            return;
        }


        let [day, month, year] = fechacita.split("/");

        let fecha = new Date(year, month - 1, day);
        console.log(fecha)
        console.log(fechacita)
        if (isNaN(fecha.getTime()) || fecha.getTime() <= Date.now()) {
            let errorFecha = document.querySelector("#error-fecha");
            errorFecha.textContent =
                "La fecha seleccionada no puede ser más antigua que la fecha actual";
            errorFecha.classList.remove("oculto");
            errorFecha.style.color = "red";
            return;
        }

        if (!direccion) {
            let errorDireccion = document.querySelector("#error-direcciones");
            errorDireccion.textContent = "Seleccione una dirección";
            errorDireccion.classList.remove("oculto");
            errorDireccion.style.color = "red";
            return;
        }

        if (espe.textLength >= 200) {
            let errorEspe = document.querySelector("#error-espe");
            errorEspe.textContent =
                "La descripción no puede tener más de 200 caracteres";
            errorEspe.classList.remove("oculto");
            errorEspe.style.color = "red";
            return;
        }
        let reserva = {
            "idTipoServicio": servicio,
            "fecha": fecha,
            "idDireccion": direccion,
            "especificaciones": espe
        };
        fetch("/api/agenda/reservarcita", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                "Authorization": sessionStorage.getItem('CM-token')
            },
            body: JSON.stringify(reserva)
        }).then(response => {
            if (response.ok) {
                return response.text();
            }
            throw new Error("Error al reservar cita");
        }).then(data => {
            setTimeout(() => {
                window.history.back();
            },1000);
        })

    });
});

