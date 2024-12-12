
window.addEventListener('load', async function () {
    const reservas = await obtenerReservas();
    const empleados = await obtenerEmpleados();


});


async function  obtenerReservas() {
    let reservas = await fetch('/api/obtenerReservas', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            authorization: sessionStorage.getItem("CM-token")
        }
    });
    if (reservas.status === 404) {
        reservas = <h1>No hay reservas</h1>
    }
    if (!reservas.ok) {
        throw new Error('Error al obtener reservas');
    }
    return reservas.json();
}

async function obtenerEmpleados() {
    let empleados = await fetch('/api/obtenerEmpleadosAsignadosEncargados', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            authorization: sessionStorage.getItem("CM-token")
        }
    });
    if (!empleados.ok) {
        throw new Error('Error al obtener empleados');
    }
    return empleados.json();
}