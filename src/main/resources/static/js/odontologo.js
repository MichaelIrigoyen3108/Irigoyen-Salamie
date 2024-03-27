

document.getElementById('formularioOdontologo').addEventListener('submit', function(event) {
    event.preventDefault();

    const formData = new FormData(this);
    const odontologo = {
        matricula: formData.get('matricula'),
        nombre: formData.get('nombre'),
        apellido: formData.get('apellido')
    };

    fetch('http://localhost:8080/odontologos/registrar', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(odontologo)
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Error al enviar los datos.');
        }
        return response.json();
    })
    .then(data => {
        alert('Odontólogo guardado exitosamente');
        console.log('Respuesta del servidor:', data);

    })
    .catch(error => {
        console.error('Error:', error);
    });
});

document.addEventListener('DOMContentLoaded', function() {
    const listarBtn = document.getElementById('listar-btn');
    listarBtn.addEventListener('click', fetchOdontologos);
});









function fetchOdontologos() {
    const odontologosList = document.getElementById('odontologos-list');

    fetch('http://localhost:8080/odontologos')
        .then(response => {
            if (!response.ok) {
                throw new Error('Error al cargar los odontólogos');
            }
            return response.json();
        })
        .then(data => {
            console.log('Lista de odontólogos:', data);
            odontologosList.innerHTML = '';
            data.forEach(odontologo => {
                const listItem = document.createElement('li');
                listItem.textContent = `ID: ${odontologo.id}, Nombre: ${odontologo.nombre}, Apellido: ${odontologo.apellido}`;
                odontologosList.appendChild(listItem);
            });
        })
        .catch(error => {
            console.error('Error:', error);
        });
}







document.addEventListener('DOMContentLoaded', function() {
    const formularioBuscarOdontologo = document.getElementById('formularioBuscarOdontologo');
    formularioBuscarOdontologo.addEventListener('submit', function(event) {
        event.preventDefault();
        const odontologoId = document.getElementById('odontologoId').value;
        buscarOdontologoPorId(odontologoId);
    });
});

function buscarOdontologoPorId(id) {
    fetch(`http://localhost:8080/odontologos/${id}`)
        .then(response => {
            if (!response.ok) {
                if (response.status === 404) {
                    throw new Error('El ID del odontólogo no existe');
                } else {
                    throw new Error('Error al buscar el odontólogo');
                }
            }
            return response.json();
        })
        .then(data => {
            mostrarDatosOdontologo(data);
        })
        .catch(error => {
            console.error('Error:', error);
            mostrarError(error.message); // Mostrar el mensaje de error en caso de problemas con la solicitud
        });
}

function mostrarDatosOdontologo(odontologo) {
    const datosOdontologo = document.getElementById('datosOdontologo');
    datosOdontologo.innerHTML = '';

    if (odontologo) {
        const nombre = document.createElement('p');
        nombre.textContent = `Nombre: ${odontologo.nombre}`;
        datosOdontologo.appendChild(nombre);

        const apellido = document.createElement('p');
        apellido.textContent = `Apellido: ${odontologo.apellido}`;
        datosOdontologo.appendChild(apellido);
    } else {
        mostrarError('Odontólogo no encontrado');
    }
}

function mostrarError(mensaje) {
    const datosOdontologo = document.getElementById('datosOdontologo');
    datosOdontologo.innerHTML = '';

    const mensajeError = document.createElement('p');
    mensajeError.textContent = mensaje;
    datosOdontologo.appendChild(mensajeError);
}







document.addEventListener('DOMContentLoaded', function() {
    const formularioEliminarOdontologo = document.getElementById('formularioEliminarOdontologo');
    formularioEliminarOdontologo.addEventListener('submit', function(event) {
        event.preventDefault();
        const odontologoId = document.getElementById('odontologoEliminarId').value;
        eliminarOdontologoPorId(odontologoId);
    });
});

function eliminarOdontologoPorId(id) {
    fetch(`http://localhost:8080/odontologos/eliminar/${id}`, {
        method: 'DELETE'
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Error al eliminar el odontólogo.');
        }
        return response.text();
    })
    .then(data => {
        alert('Odontólogo eliminado correctamente');
        console.log(data);
    })
    .catch(error => {
        console.error('Error:', error);
    });
}










document.getElementById('formularioActualizarOdontologo').addEventListener('submit', function(event) {
    event.preventDefault();

    const formData = new FormData(this);
    const odontologoId = formData.get('odontologoActualizarId');
    const odontologo = {
        matricula: formData.get('matriculaActualizar'),
        nombre: formData.get('nombreActualizar'),
        apellido: formData.get('apellidoActualizar')
    };

    actualizarOdontologoPorId(odontologoId, odontologo);
});

function actualizarOdontologoPorId(id, odontologo) {
    fetch(`http://localhost:8080/odontologos/actualizar/${id}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(odontologo)
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Error al actualizar el odontólogo.');
        }
        return response.json();
    })
    .then(data => {
        alert('Odontólogo actualizado correctamente');
        console.log('Respuesta del servidor:', data);

    })
    .catch(error => {
        console.error('Error:', error);
    });
}
