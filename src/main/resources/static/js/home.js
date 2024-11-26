window.addEventListener('load', function() {
    const navLinks = document.querySelectorAll('nav a');

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



                    break;

            }

        });
    });

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