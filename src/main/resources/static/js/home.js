window.addEventListener('load', function() {
  let contacto = document.querySelector("#btn-contacto");
  let agenda = document.querySelector("#btn-agenda");
  let historial = document.querySelector("#btn-historial");
  let direcciones = document.querySelector("#btn-direcciones");
  let perfil = document.querySelector("#btn-perfil");

    contacto.addEventListener('click', function() {
        activarBoton(contacto);
        alert(window.location.pathname)
    });





});

function activarBoton(boton) {
    document.querySelector(("nav a")).forEach(a => {
        a.removeClass("activo");
        a.addClass("incativo");
    });
    boton.removeClass("inactivo");
    boton.addClass("activo");
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