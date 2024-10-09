window.addEventListener('load', function() {
  document.getElementById('login').addEventListener('click', function() {
      let correo = document.getElementById('correo').value;
      let password = document.getElementById('password').value;
      if (correo.length < 0 || password.length < 0) {
          alerta('Correo y/o contraseña no pueden estar vacíos');
      }
      if (/^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/
          .test(correo)) {
          if (/^(?=.*\d)[a-zA-Z\d]{8,}$/
              .test(password)) {
              let usuario = {
                  correo: correo,
                  password: password
              };

          } else {
              alerta('Contraseña no cumple los caracteres mínimos 8 letras y un número');
          }

      }else {
          alerta('Correo no válido');
      }
  });
});


function alerta(mensaje) {
    document.querySelector('h2').innerText = mensaje;
    document.querySelector('h2').style.display = 'block';
}