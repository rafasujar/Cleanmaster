const servicios = document.querySelector("#servicios");
const direcciones = document.querySelector("#direcciones");

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

    document.querySelector("#add-direccion").src = location.href.replace("nuevareserva", "nuevadireccion");

    fetch("/api/obtenerDirecciones", {
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
                direcciones.appendChild(radio);
                direcciones.appendChild(label);
            }
        } else {
            let p = document.createElement("p");
            p.textContent = "No hay direcciones guardadas";
            direcciones.appendChild(p);
        }
    }).catch(error => {
        console.error(error);
    });

});

function crearlemento(tag, padre) {
    let elemento = document.createElement(tag);
    padre.appendChild(elemento);
    return elemento;
}