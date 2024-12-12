window.addEventListener('load', function() {
    let divs = [];
   fetch("/api/historial/cliente", {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            authorization: sessionStorage.getItem("CM-token")
        },
   }).then(response => {
        if(response.ok) {
            return response.json();
        }
        throw new Error(response.statusText);
   }).then(data => {
       if (data.length === 0) {
           document.getElementById("contendor-historial").innerHTML = "<h2>No hay historial</h2>";
       }
       if (data.length > 0) {
          data.forEach(element => {
            let div = document.createElement("div");
            document.getElementById('contendor-historial').appendChild(div);
            div.classList.add("border-especial");
            div.style.display = "grid";
            crearElementoTexto("h4", element.fecha, div);
            crearElementoTexto("p", "Tipo de servicio: "+element.tipo, div);
            crearElementoTexto("p", "Asistente: "+element.asistente, div);
            let a = crearElementoTexto("a", "Pulse para enviar factura a tu correo", div);
            div.id = element.id;
            a.addEventListener('click', function() {
               fetch("/api/historial/cliente/factura", {
                   method: 'POST',
                   headers: {
                       'Content-Type': 'application/json',
                       authorization: sessionStorage.getItem("CM-token")
                   },
                   body: JSON.stringify(element)
               }).then(response => {
                    return response.text()
               }).then(data => {
                   let datos = {
                       id: 'btn-historial',
                       response: data
                   };
                   console.log(datos);
                   window.parent.postMessage(datos, location.origin);
               }).catch(error => {
                   console.error("Error: "+error);
            });



          });
            divs.push(div);
       })
    }
   }).catch(error => {
         console.error("Error: "+error)
   });


   document.getElementById("buscador").addEventListener('change', function() {
       document.getElementById("buscador").addEventListener('change', function () {
           let busqueda = document.getElementById("buscador").value;
           let contenedor = document.getElementById("contendor-historial");
           let divs2 = divs;

           contenedor.innerHTML = "";

           if (busqueda === "") {
               divs2.forEach(div => {
                   contenedor.append(div);
               });
           } else {
               divs2.forEach(div => {
                   if (div.id.match('^' + busqueda + '-.*$')) {
                       contenedor.append(div);
                       console.log(div);
                   }
               });
           }
       });

   });

});

function crearElementoTexto(tag, contenido,padre){
    let elemento = document.createElement(tag);
    elemento.textContent = contenido;
    padre.appendChild(elemento);
    return elemento;
}