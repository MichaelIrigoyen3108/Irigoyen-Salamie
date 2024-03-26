
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