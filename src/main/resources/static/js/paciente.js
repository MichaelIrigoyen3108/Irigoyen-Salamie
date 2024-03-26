
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
    fetch('http://localhost:8080/pacientes')
        .then(response => {
            if (!response.ok) {
                throw new Error('Error al cargar los pacientes');
            }
            return response.json();
        })
        .then(data => {
            displayPacientes(data);
        })
        .catch(error => {
            console.error('Error:', error);
        });
}

function displayPacientes(pacientes) {
    const pacientesList = document.getElementById('pacientes-list');
    pacientesList.innerHTML = '';

    pacientes.forEach(paciente => {
        const pacienteItem = document.createElement('li');
        pacienteItem.textContent = `ID: ${paciente.id} - Nombre: ${paciente.nombre} ${paciente.apellido}`;
        pacientesList.appendChild(pacienteItem);
    });
}
