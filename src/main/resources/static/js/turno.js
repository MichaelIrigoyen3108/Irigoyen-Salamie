

document.getElementById('formularioTurno').addEventListener('submit', function(event) {
    event.preventDefault();

    const formData = new FormData(this);
    const turnoEntradaDto = {
        pacienteId: formData.get('pacienteId'),
        odontologoId: formData.get('odontologoId'),
        fechaYHora: formData.get('fechaYHora')
    };

    const fechaYHora = formData.get('fechaYHora');
    const formattedFechaYHora = fechaYHora.replace('T', ' ');
    turnoEntradaDto.fechaYHora = formattedFechaYHora;

    fetch('http://localhost:8080/turnos/registrar', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(turnoEntradaDto)
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Error al enviar los datos.');
        }
        return response.json();
    })
    .then(data => {
        alert('Turno registrado exitosamente');
        console.log('Respuesta del servidor:', data);
    })
    .catch(error => {
        console.error('Error:', error);
        alert('Error al registrar el turno. Por favor, inténtelo de nuevo más tarde.');
    });
});

document.addEventListener('DOMContentLoaded', function() {
    const listarBtn = document.getElementById('listar-btn');
    listarBtn.addEventListener('click', fetchTurnos);
});

function fetchTurnos() {
    fetch('http://localhost:8080/turnos')
        .then(response => {
            if (!response.ok) {
                throw new Error('Error al cargar los turnos');
            }
            return response.json();
        })
        .then(data => {
            displayTurnos(data);
        })
        .catch(error => {
            console.error('Error:', error);
        });
}

function displayTurnos(turnos) {
    const turnosList = document.getElementById('turnos-list');
    turnosList.innerHTML = '';

    turnos.forEach(turno => {
        const turnoItem = document.createElement('li');
        turnoItem.textContent = `ID: ${turno.id} - Paciente: ${turno.pacienteId} - Odontólogo: ${turno.odontologoId} - Fecha y Hora: ${turno.fechaYHora}`;
        turnosList.appendChild(turnoItem);
    });
}
