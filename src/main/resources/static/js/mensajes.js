window.addEventListener('load', function () {
    obtenermensajes();
    setTimeout(obtenermensajes, 10000);
});

function obtenermensajes() {
    fetch('/api/mensajes/obtenerMensaje/inicio', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            Authorization: sessionStorage.getItem('CM-token')
        }
    })
        .then(response => {
            if (response.ok) {
                return response.json();
            }
            if (response.status === 404) {
                mostrarMensajeVacio();
                return []; // Retornar un arreglo vacío para evitar errores posteriores
            }
            throw new Error(`Error ${response.status}: ${response.statusText}`);
        })
        .then(data => {
            actualizarMensajes(data);
        })
        .catch(error => {
            console.error('Error al obtener mensajes:', error);
        });
}

function mostrarMensajeVacio() {
    let main = document.querySelector("main");
    main.classList.add("medio");
    main.innerHTML = ""; // Asegurar que no haya contenido previo
    let h1 = document.createElement("h1");
    h1.textContent = "No hay mensajes para mostrar";
    main.appendChild(h1);
}

function actualizarMensajes(mensajes) {
    let main = document.querySelector("main");

    if (mensajes.length !== main.childNodes.length) {
        main.innerHTML = ""; // Limpiar el contenido existente

        mensajes.forEach(mensaje => {
            let div = document.createElement("div");
            div.classList.add("border-especial");

            let emisor = crearElemento("p", `De: ${mensaje.emisor}`);
            let receptor = crearElemento("p", `Para: ${mensaje.receptor}`);
            let asunto = crearElemento("h2", `Asunto: ${mensaje.asunto}`);
            let enlace = crearEnlaceMensaje(mensaje);

            div.append(asunto, emisor, receptor, enlace);

            main.appendChild(div);
        });
    }
}

function crearElemento(tipo, texto) {
    let elemento = document.createElement(tipo);
    elemento.textContent = texto;
    return elemento;
}

function crearEnlaceMensaje(mensaje) {
    let a = document.createElement("a");
    a.textContent = "Ver mensaje";
    a.href = "#";
    a.addEventListener('click', function () {
        let data = {
            id: "btn-contacto",
            mensaje: mensaje
        };
        window.parent.postMessage(data, location.origin);
    });
    return a;
}
