function calcularDescuentos() {
    const salario = parseFloat(document.getElementById('salario').value);
    
    if (isNaN(salario) || salario <= 0) {
        console.error("El salario debe ser un número positivo.");
        return;
    }

    const salud = salario * 0.04;
    const pension = salario * 0.04;
    const solidaridad = salario > (4 * 1080) ? salario * 0.01 : 0; // 1080 es el valor del SMMLV en Colombia

    // Actualiza el contenido de los elementos <span>
    
    document.getElementById('descuentos_salud').textContent = salud.toFixed(2);
    document.getElementById('descuentos_pension').textContent = pension.toFixed(2);
    document.getElementById('descuentos_solidaridad').textContent = solidaridad.toFixed(2);
}

function calcularEdad() {
    const fechaNacimiento = new Date(document.getElementById('fecha_nacimiento').value);
    const hoy = new Date();
    let edad = hoy.getFullYear() - fechaNacimiento.getFullYear();
    const m = hoy.getMonth() - fechaNacimiento.getMonth();
    
    if (m < 0 || (m === 0 && hoy.getDate() < fechaNacimiento.getDate())) {
        edad--;
    }
    
    // Actualiza el contenido del span en lugar de usar .value
    document.getElementById('edad').textContent = edad >= 0 ? edad : 0; 
}

document.getElementById('fecha_nacimiento').addEventListener('change', calcularEdad);


function agregarProfesor() {
    // Falta implementar la lógica para agregar profesor
}

function eliminarProfesor() {
    // Falta implementar la lógica para eliminar profesor
}

function consultarProfesor() {
    // Falta implementar la lógica para consultar profesor
}

function editarProfesor() {
    // Falta implementar la lógica para editar profesor
}
