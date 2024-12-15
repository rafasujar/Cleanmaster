const divs = [];
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
            div.style.textWrap = 'wrap';
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
            div.style.textAlign = 'left';
            divs.push(div);
        });
   }).catch(error => {
        console.error('Error:', error);
        alerta(error);
   });
})


document.querySelector("#btn-buscar").addEventListener('click', function () {

    let texto = document.querySelector("#buscador").value;
    if (texto !== '') {
        divs.forEach(div => {
            if (div.id.includes(texto)) {
                div.style.display = 'grid';
            } else {
                div.style.display = 'none';
            }
        });
    }
});

document.querySelector("#btn-clear").addEventListener('click', function () {
    document.querySelector("#buscador").value = '';
    divs.forEach(div => {
        div.style.display = 'grid';
    });
});