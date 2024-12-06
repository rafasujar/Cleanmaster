window.addEventListener('load', function() {
    const navLinks = document.querySelectorAll('nav a');
    const iframe = document.querySelector('iframe');
    const iframeDoc = iframe.contentDocument || iframe.contentWindow.document;

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
                    setTimeout(function () {
                        const links = iframeDoc.querySelectorAll('a#generardireccion');
                        if (links.length > 0) {
                            links.forEach(link => {
                                link.addEventListener('click', function (e) {
                                    e.preventDefault(); // Evita el comportamiento predeterminado si es necesario.
                                    console.log(link);
                                    alert('¡Enlace clicado!');
                                });
                            });
                        }
                    });

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
    document.querySelector("a#btn-agenda").click();


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