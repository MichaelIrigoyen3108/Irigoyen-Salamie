
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
        // Puedes añadir más lógica aquí, como redireccionar a otra página
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
    fetch('/odontologos')
        .then(response => {
            if (!response.ok) {
                throw new Error('Error al cargar los odontólogos');
            }
            return response.json();
        })
        .then(data => {
            displayOdontologos(data);
        })
        .catch(error => {
            console.error('Error:', error);
        });
}

function displayOdontologos(odontologos) {
    const odontologosList = document.getElementById('odontologos-list');
    odontologosList.innerHTML = '';

    odontologos.forEach(odontologo => {
        const odontologoItem = document.createElement('li');
        odontologoItem.textContent = `id: ${odontologo.id} -nNombre: ${odontologo.nombre} ${odontologo.apellido}`;
        odontologosList.appendChild(odontologoItem);
    });
}


