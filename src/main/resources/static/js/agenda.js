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
   }).then(async function (data) {

       let maindiv = document.querySelectorAll('main > div')
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
            crearElementoTexto("h4",servicio.fecha,div);
            crearElementoTexto("p","Tipo de servicio: "+servicio.tipo ,div);
            if (servicio.empleado === true){
                crearElementoTexto("p","Cliente: "+servicio.cliente ,div);
                crearElementoTexto("p", "movil: "+servicio.movil, div);
                crearElementoTexto("p","hacer click aqui para mostrar direccion",div);
                let input = crearElemento("input",div);
                input.type = "hidden";
                input.value = servicio.direccion;
            }else{
                crearElementoTexto("p", "Asistente: "+servicio.asistente, div);
            }

           });
       }
       }).catch(function(err) {
       console.error('Error: ' + err.message);

   });

    document.querySelector('select#semana').addEventListener('change', function () {
        const divscontendeores = document.querySelectorAll('main > div'); // Selecciona solo hijos directos de <main>
        const selectedValue = this.value;

        divscontendeores.forEach(div => {
            if (selectedValue === '0') {
                div.style.display = 'flex';
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