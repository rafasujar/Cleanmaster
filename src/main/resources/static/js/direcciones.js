window.addEventListener('load', function () {
    fetch("/api/direciones/obtenerDirecciones", {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: sessionStorage.getItem('CM-token')
    })
        .then(response => {
            if (response.ok) {
                return response.json();
            }
            throw new Error(response.statusText);
        })
        .then(data => {
            console.log(data);
            for (let i = 0; i < data.length; i++) {
                let div = crearElemento('div', document.querySelector('main'));
                div.classList.add("border-especial");

                let h2 = crearElemento('h2', div);
                h2.innerHTML = "Direccion: " + (i + 1);
                let p = crearElemento('p', div);
                p.innerHTML = data[i].direccion;

                let a = crearElemento('a', div);
                a.href = "#";
                a.innerHTML = "pulsar para eliminar direccion";
                let direccion = data[i]
                a.addEventListener('click', function(event) {
                    event.preventDefault();
                    fetch("/api/direciones/eleminarDireccion", {
                        method: "POST",
                        headers: {
                            'Content-Type': 'application/json',
                            'Authorization': sessionStorage.getItem('CM-token')
                        },
                        body: JSON.stringify(direccion)
                    })
                        .then(response => {
                            if (response.ok) {
                                div.remove();
                            }
                        })
                        .catch(error => {
                            console.error("Error:", error);
                            document.querySelector('main').innerHTML = "Error de conexión";
                        });
                });
            }
        })
        .catch(error => {
            console.error("Error:", error);
            document.querySelector('main').innerHTML = "Error al cargar las direcciones";
        });
});


function crearElemento(elemento, padre) {
    let element = document.createElement(elemento);
    padre.appendChild(element);
    return element;
}