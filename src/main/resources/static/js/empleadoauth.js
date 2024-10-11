window.addEventListener('load', function() {
    console.log('Documento cargado');
    document.getElementById('login').addEventListener('click', function() {
      console.log('Login button clicked');
      let correo = document.getElementById('correo').value;
      let password = document.getElementById('password').value;
      if (correo.length < 0 || password.length < 0) {
          alerta('Correo y/o contraseña no pueden estar vacíos');
      }
      if (/^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/
          .test(correo)) {
          console.log('Correo válido');
          if (/^(?=.*\d)[a-zA-Z\d]{8,}$/
              .test(password)) {

              fetch('/AreaEmpleado/api/login', {
                  method: 'POST',
                      headers: {
                      'Content-Type': 'application/json',
                  },
                  body: JSON.stringify({ correo: correo, password: password }),
              }).then(function(response) {
                  console.log("Response status: " + response.status);
                  console.log("Response status text: " + response.statusText);
                  console.log("Response type: " + response.type);


                  if (response.status === 404) {
                      alerta('No existe el usuario');
                      document.getElementById('password').value = '';
                      return;
                  }
                  if (response.status === 400) {
                      alerta('Correo y/o contraseña no cumplen con el formato');
                      document.getElementById('password').value = '';
                      document.getElementById('correo').value = '';
                      return;
                  }

                  if (response.status === 200) {

                      return response.json();
                  }
                  throw new Error('Error en la respuesta: ' + response.status);
              })
                  .then(function(data) {
                      if (data) {
                            console.log('Data: ' + JSON.stringify(data));
                            sessionStorage.setItem('CM-token', data);
                            window.location.href = '/AreaEmpleado/'+data.id+'/home';
                      }
                  })
                  .catch(function(err) {
                      console.error('Error: ' + err.message); // Capturar y mostrar errores
                  });


          } else {
              alerta('Contraseña no cumple los caracteres mínimos 8 letras y un número');
          }

      }else {
          alerta('Correo no válido');
      }
  });
});


function alerta(mensaje) {
    document.querySelector('#showerrors').innerText = mensaje;
    document.querySelector('#showerrors').style.display = 'block';
}