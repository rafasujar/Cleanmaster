window.addEventListener('load', function() {

   fetch("/api/agenda/veragenda/semana",{
           method: 'POST',
           headers: {
               'Content-Type': 'application/json',
           },
           body: sessionStorage.getItem('CM-token')
       }).then(function(response) {
           if (response.status === 200){
               return response.json()
           }if (response.status === 404) {
               crearElementoTexto('h3', "Sin reservas actuales", document.querySelector('main'));
               return;
           }
   }).then( data =>  {

       let maindiv = document.querySelectorAll('div#main > div')
       for (let reservas in data) {
              let contenedor = maindiv[reservas-1];
           if (data[reservas].length === 0) {
               crearElementoTexto('h3', "Sin reservas actuales", contenedor);
               continue;
           }
           data[reservas].forEach(servicio => {
            let div = crearElemento('div', contenedor);
            div.style.display = 'flex';
            div.style.flexDirection = 'column';
            div.classList.add('border-especial');
            div.id = "reserva_id#"+servicio.id;
            crearElementoTexto("h4",servicio.fecha,div);
            crearElementoTexto("p","Tipo de servicio: "+servicio.tipo ,div);
            if (servicio.empleado === true){
                crearElementoTexto("p","Cliente: "+servicio.cliente ,div);
                crearElementoTexto("p", "movil: "+servicio.movil, div);
                let a = crearElemento("a",div);
                a.innerText = "Pulse para ver la dirección";
                a.id = "generardireccion";
                a.addEventListener('click', function () {
                    let datos = {
                        id: 'btn-agenda',
                        direccion: servicio.direccion,
                        idDirecion: servicio.id
                    };
                    window.parent.postMessage(datos, location.origin);
                });

            }else{
                crearElementoTexto("p", "Asistente: "+servicio.asistente, div);
            }

           });
       }
       }).catch(function(err) {
       console.error('Error: ' + err.message);

   });

    document.querySelector('select#semana').addEventListener('change', function () {
        const divscontendeores = document.querySelectorAll('div#main > div'); // Selecciona solo hijos directos de <main>
        const selectedValue = this.value;

        divscontendeores.forEach(div => {
            if (selectedValue === '0') {
                div.style.display = 'flex';
                div.style.flexDirection = 'column';
            } else {
                div.style.display = div.id === selectedValue ? 'flex' : 'none';
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