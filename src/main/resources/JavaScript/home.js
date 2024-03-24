

document.getElementById("registroForm").addEventListener("submit", function(event) {
    event.preventDefault();

    let matricula = document.getElementById("matricula").value;
    let nombre = document.getElementById("nombre").value;
    let apellido = document.getElementById("apellido").value;

    let odontologo = {
        matricula: matricula,
        nombre: nombre,
        apellido: apellido
    };

    fetch('/odontologos/registrar', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(odontologo)
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Error al registrar el odontólogo');
        }
        return response.json();
    })
    .then(data => {
        alert('Odontólogo registrado correctamente');
        // Aquí puedes redirigir a otra página o realizar otras acciones después de registrar el odontólogo
    })
    .catch(error => {
        alert(error.message);
    });
});
