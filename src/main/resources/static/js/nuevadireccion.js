
const errorp = document.getElementById('errors');


document.querySelector("#add-direccion").addEventListener("click", function() {
    let ciudad = document.getElementById('ciudad').value;
    let  calle = document.getElementById('calle').value;
    let numeroPortal = document.getElementById('numero').value;
    let numeroPuerta = document.getElementById('puerta').value;
    let codigoPostal = document.getElementById('codigoPostal').value;
   if (ciudad === "") {
       errorp.innerHTML = "El campo ciudad es obligatorio";
       errorp.classList.remove("oculto");
       return;
   }
    if (calle === "") {
       errorp.innerHTML = "El campo calle es obligatorio";
        errorp.classList.remove("oculto");
        return;
    }
    if (numeroPortal === "") {
       errorp.innerHTML = "El campo número portal es obligatorio";
        errorp.classList.remove("oculto");
        return;
    }
    if (numeroPuerta === "") {
       errorp.innerHTML = "El campo número puerta es obligatorio";
        errorp.classList.remove("oculto");
        return;
    }
    if (codigoPostal === "") {
       errorp.innerHTML = "El campo código postal es obligatorio";
        errorp.classList.remove("oculto");

        return;
    }
    
    let direccion =  calle+" "+numeroPortal+" "+numeroPuerta+" "+ciudad+" "+codigoPostal;

    console.log(direccion);
    fetch("/api/direciones/guardarNuevaDireccion", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
            "authorization": sessionStorage.getItem("CM-token")
        },
        body: JSON.stringify({
            direccion: direccion,
        }),
    }).then(response => {
            if (response.ok) {
                window.history.back();
            } else {
                errorp.innerHTML = "Error al guardar la dirección";
                errorp.classList.remove("oculto");
            }
    });
    
});