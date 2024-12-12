window.addEventListener('load', function () {
   fetch("/api/cargarempleados", {
       method: 'POST',
        headers: {
          'Content-Type': 'application/json',
            authorization: sessionStorage.getItem("CM-token")
       }
   }).then(response => {
        if (response.status === 200) {
            return response.json();
        } else {
            throw new Error('Error en la respuesta: ' + response.status);
        }
   }).then(data => {
       console.log(data);
        data.forEach(empleado => {
            let div = document.createElement('div');
            document.querySelector("#contenedor-empleados").append(div);
            div.id = empleado.correo;
            div.classList.add('border-especial');
            let p = document.createElement('h3');
            div.appendChild(p);
            p.textContent = 'Nombre: ' + empleado.nombre + ' ' + empleado.apellidos;
            let p2 = document.createElement('p');
            div.appendChild(p2);
            p2.textContent = 'Número de empleado: ' + empleado.id;
            let a = document.createElement('a');
            div.appendChild(a);
            a.href = '/areaencargados/empleado/baja/verempleado/'+ empleado.correo;
            a.textContent = 'Ver empleado datos completos';
            div.style.display = 'grid';

        });
   }).catch(error => {
        console.error('Error:', error);
        alerta(error);
   });
});