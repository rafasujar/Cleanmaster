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
           }
   }).then(async function (data) {
        let text =   crearElementoTexto('p', JSON.stringify(data),document.querySelector('main'))
       }).catch(function(err) {
       console.error('Error: ' + err.message);
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