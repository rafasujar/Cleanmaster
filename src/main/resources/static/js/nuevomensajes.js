window.addEventListener('load', function() {
   const selectUsuario = document.getElementById('personas');

   fetch("/api/agenda/obtenerPersonasByReservas", {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            authorization: sessionStorage.getItem("CM-token")
        }
   }).then(response => {
        if(response.ok) {
            return response.json();
        }
        throw new Error(response.statusText);
   }).then(data => {
        data.forEach(persona => {
            const option = document.createElement('option');
            option.value = persona.id;
            option.text = persona.nombre;
            selectUsuario.appendChild(option);
        });
   })


    document.querySelector("button#enviar").addEventListener('click', function() {
       let mensaje = document.getElementById('mensaje').value;
       let receptor = selectUsuario.value;
       let asunto = document.getElementById('asunto').value;

       if (mensaje == "" || receptor == "" || asunto == "") {
           alerta("Debe completar todos los campos");
           return;
       }
       if (receptor === 0){
              alerta("Debe seleccionar un destinatario");
              return;
       }

       if (mensaje.length > 500) {
           alerta("El mensaje no puede superar los 500 caracteres");
           return;
       }

       if (asunto.length > 50) {
           alerta("El asunto no puede superar los 50 caracteres");
           return;
       }

        let me = {
            mensaje: mensaje,
            asunto: asunto,
            id: receptor
        }

       fetch("/api/mensajes/crearMensaje", {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                authorization: sessionStorage.getItem("CM-token")
            },
            body: JSON.stringify(me)
       }).then(response => {
            if(response.ok) {
                return response.text();
            }
            throw new Error(response.statusText);
       }).then(data => {
           window.history.back();
       }).catch(error => {
            alerta("Error al enviar el mensaje");
       })


    });


});

function alerta(mensaje) {
    document.getElementById('showerrors').innerText = mensaje;
    document.getElementById('showerrors').style.display = 'block';
    setTimeout(function() {
        document.getElementById('showerrors').style.display = 'none';
    }, 3000);
}