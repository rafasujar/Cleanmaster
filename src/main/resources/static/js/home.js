const navLinks = document.querySelectorAll('nav a');
const iframe = document.querySelector('iframe');
const overlay = document.querySelector("#overlay");
const overlayContent = document.querySelector("#datos-overlay");
const contendorButton = document.querySelector("#contendor-button")
const buttonevn = document.querySelector('button#btn-events');
let empleado = document.querySelector("#contendor-button > input[type=hidden]").value
window.addEventListener('load', function() {
    navLinks.forEach(link => {
        link.addEventListener('click', (event) => {
            event.preventDefault();
            navLinks.forEach(otherLink => {
                otherLink.classList.remove('activo');
                otherLink.classList.add('inactivo');
            });
            const clickedLink = event.target;
            clickedLink.classList.remove('inactivo');
            clickedLink.classList.add('activo');

            switch (clickedLink.id) {
                case 'btn-agenda':
                    iframe.src = location.href + '/veragenda';
                    if(empleado === 'false' || empleado === false){
                       contendorButton.classList.add('oculto');
                    }else {
                        buttonevn.innerHTML = '';
                        contendorButton.classList.remove('oculto');
                        let newreserva = crearElementoTexto('a', 'Nueva Reserva', buttonevn);
                        newreserva.href = location.href + '/nuevareserva';
                    }
                    break;
                case 'btn-contacto':
                    iframe.src = location.href + '/vermensajes';
                    contendorButton.classList.remove('oculto');
                    buttonevn.innerHTML = '';
                    let nuevomensaje = crearElementoTexto('a', 'nuevo mensaje', buttonevn);
                    nuevomensaje.href = location.href + '/nuevomensajes';
                    break;
                case 'btn-historial':
                    iframe.src = location.href + '/verhistorial';
                    contendorButton.classList.add('oculto');
                    break;
                case 'btn-direcciones':
                    iframe.src = location.href + '/verdirecciones';
                    contendorButton.classList.remove('oculto');
                    buttonevn.innerHTML = '';
                    let nuevadireccion = crearElementoTexto('a', 'nueva direccion', buttonevn);
                    nuevadireccion.href = location.href + '/nuevadireccion';
                    break;
                case 'btn-perfil':
                    iframe.src = location.href + '/verperfil';
                    contendorButton.classList.remove('oculto');
                    buttonevn.innerHTML = '';
                    let modificarperfil = crearElementoTexto('a', 'Modificar Perfil', buttonevn);
                    modificarperfil.href = location.href + '/modificarperfil';
                    break;
                case 'btn-administrar':
                    iframe.src = location.href + '/administrar';
                    contendorButton.classList.add('oculto');
                    break;
                default:

                    break;

            }

        });
    });
    document.querySelector("a#btn-agenda").click();
    window.addEventListener('message', function(event) {
        let datos = event.data;
        switch (datos.id) {
            case 'btn-agenda':
                mostrarOverlay();
                overlayContent.style.width = 'calc(100% - 30px)';
                let overlayiframe = crearElemento('iframe', overlayContent);
               overlayiframe.src = encodeURI("https://www.google.com/maps?q="+datos.direccion+"&output=embed");
                overlayiframe.style.border = "none";
                overlayiframe.style.width = "100%";
                overlayiframe.style.height = "40%";
                let overlayDatos = crearElemento('div', overlayContent);
                overlayDatos.style.display = 'flex';
                overlayDatos.style.flexDirection = 'column';
                overlayDatos.style.justifyContent = 'center';
                overlayDatos.style.alignItems = 'center';
                overlayDatos.style.padding = '10px';
                overlayDatos.style.height = '60%';
                overlayDatos.style.justifyContent = 'space-around';
                let butonAbrir = crearElemento('button', overlayDatos);
                crearElementoTexto('a', 'Abrir en Google Maps', butonAbrir);
                butonAbrir.classList.add('button-special');
                butonAbrir.addEventListener('click', function() {
                    window.open("https://www.google.com/maps?q="+datos.direccion);
                });
                let butonFinal = crearElemento('button', overlayDatos);
                crearElementoTexto('a', 'Reserva Finalizada', butonFinal);
                butonFinal.classList.add('button-special');
                butonFinal.style.boxShadow = '0px 0px 5px 0px #000';
                butonFinal.addEventListener('click', function() {
                    fetch("/api/agenda/reservarfinalizada", {
                        method: 'POST',
                        headers: {
                            'Content-Type': 'application/json'
                        },
                        body: JSON.stringify({
                            id: datos.idDirecion,
                            token: sessionStorage.getItem('CM-token')
                        })
                    }).then(response => {
                        if (response.ok) {
                            overlay.classList.add('oculto');
                            overlay.classList.remove('overlay');

                        } else {
                            overlayContent.innerHTML = 'codigo de error: ' + response.status;
                        }
                    })
                });
                let butonCerrar = crearElemento('button', overlayDatos);
                crearElementoTexto('a', 'Cerrar', butonCerrar);
                butonCerrar.classList.add('button-special');
                butonCerrar.style.boxShadow = '0px 0px 5px 0px #000';
                butonCerrar.style.width = '100px';
                butonCerrar.addEventListener('click', function() {
                    overlay.classList.add('oculto');
                    overlay.classList.remove('overlay');
                    overlayContent.innerHTML = '';
                });

                break;
            case 'btn-historial':
                document.querySelector('main').style.position = 'relative';
                let div = crearElemento('div', document.querySelector('main'));
                div.classList.add('mensaje-abajo');
                crearElementoTexto('h2', datos.response, div);
                setTimeout(() => {
                    document.querySelector('main').style.position = '';
                    div.remove();
                }, 5000);
                break;
            case 'btn-administrar':
                window.location.href = datos.response;
                break;
            case 'btn-contacto':
                let mensaje = datos.mensaje;
                mostrarOverlay();
                overlayContent.style = ' '
                let contenedor = crearElemento('div', overlayContent);
                contenedor.classList.add('overlay-mensaje')
                crearElementoTexto('h2', 'De: '+mensaje.emisor, contenedor);
                crearElementoTexto('h2', 'Para: '+mensaje.receptor, contenedor);
                crearElementoTexto('h2', 'Asunto: '+mensaje.asunto, contenedor);
                crearElementoTexto('p', mensaje.mensaje, contenedor);
                let butonCerrar2 = crearElemento('button', contenedor);
                crearElementoTexto('a', 'Cerrar', butonCerrar2);
                butonCerrar2.classList.add('button-special');
                butonCerrar2.style.boxShadow = '0px 0px 5px 0px #000';
                butonCerrar2.style.width = '100px';
                butonCerrar2.addEventListener('click', function() {
                    overlay.classList.add('oculto');
                    overlay.classList.remove('overlay');
                    overlayContent.innerHTML = '';
                });
                break;
            default:
                break;
        }
    });

});


function mostrarOverlay() {
    overlayContent.innerHTML = '';
    overlay.classList.remove('oculto');
    overlay.classList.add('overlay');
}
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