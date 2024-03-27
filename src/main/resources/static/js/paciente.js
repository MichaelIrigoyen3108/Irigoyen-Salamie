
document.getElementById('formularioPaciente').addEventListener('submit', function(event) {
    event.preventDefault();

    const formData = new FormData(this);
    const pacienteEntradaDto = {
        nombre: formData.get('nombre'),
        apellido: formData.get('apellido'),
        dni: formData.get('dni'),
        fechaIngreso: formData.get('fechaIngreso'),
        domicilioEntradaDto: {
            calle: formData.get('calle'),
            numero: formData.get('numero'),
            localidad: formData.get('localidad'),
            provincia: formData.get('provincia')
        }
    };

    fetch('http://localhost:8080/pacientes/registrar', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(pacienteEntradaDto)
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Error al enviar los datos.');
        }
        return response.json();
    })
    .then(data => {
        alert('Paciente guardado exitosamente');
        console.log('Respuesta del servidor:', data);
    })
    .catch(error => {
        console.error('Error:', error);
        alert('Error al registrar el paciente. Por favor, inténtelo de nuevo más tarde.');
    });
});



document.addEventListener('DOMContentLoaded', function() {
    const listarBtn = document.getElementById('listar-btn');
    listarBtn.addEventListener('click', fetchPacientes);
});

function fetchPacientes() {
    const pacientesList = document.getElementById('pacientes-list');

    fetch('http://localhost:8080/pacientes')
        .then(response => {
            if (!response.ok) {
                throw new Error('Error al cargar los pacientes');
            }
            return response.json();
        })
        .then(data => {
            console.log('Lista de pacientes:', data);
            pacientesList.innerHTML = '';
            data.forEach(paciente => {
                const listItem = document.createElement('li');
                listItem.textContent = `ID: ${paciente.id}, Nombre: ${paciente.nombre}, Apellido: ${paciente.apellido}, DNI: ${paciente.dni}, Fecha de Ingreso: ${paciente.fechaIngreso}`;
                pacientesList.appendChild(listItem);
            });
        })
        .catch(error => {
            console.error('Error:', error);
        });
}

document.addEventListener('DOMContentLoaded', function() {
    const formularioBuscarPaciente = document.getElementById('formularioBuscarPaciente');
    formularioBuscarPaciente.addEventListener('submit', function(event) {
        event.preventDefault();
        const pacienteId = document.getElementById('pacienteId').value;
        buscarPacientePorId(pacienteId);
    });
});

function buscarPacientePorId(id) {
    fetch(`http://localhost:8080/pacientes/${id}`)
        .then(response => {
            if (!response.ok) {
                if (response.status === 404) {
                    throw new Error('El ID del paciente no existe');
                } else {
                    throw new Error('Error al buscar el paciente');
                }
            }
            return response.json();
        })
        .then(data => {
            mostrarDatosPaciente(data);
        })
        .catch(error => {
            console.error('Error:', error);
            mostrarError(error.message);
        });
}

function mostrarDatosPaciente(paciente) {
    const datosPaciente = document.getElementById('datosPaciente');
    datosPaciente.innerHTML = '';

    if (paciente) {
        const nombre = document.createElement('p');
        nombre.textContent = `Nombre: ${paciente.nombre}`;
        datosPaciente.appendChild(nombre);

        const apellido = document.createElement('p');
        apellido.textContent = `Apellido: ${paciente.apellido}`;
        datosPaciente.appendChild(apellido);

        const dni = document.createElement('p');
        dni.textContent = `DNI: ${paciente.dni}`;
        datosPaciente.appendChild(dni);

        const fechaIngreso = document.createElement('p');
        fechaIngreso.textContent = `Fecha de Ingreso: ${paciente.fechaIngreso}`;
        datosPaciente.appendChild(fechaIngreso);
    } else {
        mostrarError('Paciente no encontrado');
    }
}









document.addEventListener('DOMContentLoaded', function() {
    const formularioEliminarPaciente = document.getElementById('formularioEliminarPaciente');
    formularioEliminarPaciente.addEventListener('submit', function(event) {
        event.preventDefault();
        const pacienteId = document.getElementById('pacienteEliminarId').value;
        eliminarPacientePorId(pacienteId);
    });
});

function eliminarPacientePorId(id) {
    fetch(`http://localhost:8080/pacientes/eliminar/${id}`, {
        method: 'DELETE'
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Error al eliminar el paciente.');
        }
        return response.text();
    })
    .then(data => {
        alert('Paciente eliminado correctamente');
        console.log(data);
    })
    .catch(error => {
        console.error('Error:', error);
    });
}
















document.getElementById('formularioActualizarPaciente').addEventListener('submit', function(event) {
 event.preventDefault();

        const formData = new FormData(this);
        const pacienteId = formData.get('pacienteActualizarId');
        const paciente = {
            nombre: formData.get('nombreActualizar'),
            apellido: formData.get('apellidoActualizar'),
            dni: formData.get('dniActualizar'),
            fechaIngreso: formData.get('fechaIngresoActualizar'),
            domicilioEntradaDto: {
                calle: formData.get('calleActualizar'),
                numero: formData.get('numeroActualizar'),
                localidad: formData.get('localidadActualizar'),
                provincia: formData.get('provinciaActualizar')
            }
        };

        actualizarPacientePorId(pacienteId, paciente);
    });

    function actualizarPacientePorId(id, paciente) {
        fetch(`http://localhost:8080/pacientes/actualizar/${id}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(paciente)
        })
        .then(response => {
            if (!response.ok) {
                throw new Error('Error al actualizar el paciente.');
            }
            return response.json();
        })
        .then(data => {
            alert('Paciente actualizado correctamente');
            console.log('Respuesta del servidor:', data);
        })
        .catch(error => {
            console.error('Error:', error);
        });
    }
});

