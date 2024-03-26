

document.getElementById('formularioPaciente').addEventListener('submit', function(event) {
    event.preventDefault();

    const formData = new FormData(this);
    const paciente = {
        nombre: formData.get('nombre'),
        apellido: formData.get('apellido'),
        dni: formData.get('dni'),
        fechaIngreso: formData.get('fechaIngreso'),
        domicilio: {
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
           body: JSON.stringify(paciente)
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
           /
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

   function displayPacientes(pacientes) {
       const pacientesList = document.getElementById('pacientes-list');
       pacientesList.innerHTML = '';

       pacientes.forEach(paciente => {
           const pacienteItem = document.createElement('li');
           pacienteItem.textContent = `id: ${paciente.id} -nombre: ${paciente.nombre} ${paciente.apellido}
           ${paciente.dni}` ${paciente.fechaIngreso} ${paciente.calle} ${paciente.numero} ${paciente.localidad}
           ${paciente.provincia};
           pacientesList.appendChild(pacienteItem);
       });
   }



