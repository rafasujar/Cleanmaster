window.addEventListener('load', function() {
    const navLinks = document.querySelectorAll('nav a');
    const iframe = document.querySelector('iframe');

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
                    break;
                case 'btn-contacto':
                    iframe.src = location.href + '/contacto';
                    break;
                case 'btn-historial':
                    iframe.src = location.href + '/historial';
                    break;
                case 'btn-direcciones':
                    iframe.src = location.href + '/direcciones';
                    break;
                case 'btn-perfil':
                    iframe.src = location.href + '/perfil';
                    break;
                case 'btn-administrar':
                    iframe.src = location.href + '/administrar';
                    break;
                default:

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