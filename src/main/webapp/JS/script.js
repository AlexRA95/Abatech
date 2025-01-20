document.getElementById('searchHeader').addEventListener('input', (event)=>{
    if (event.target.value.length >= 3){
        document.getElementById('searchBtnHeader').disabled = false;
    }
});

//Valida formulario de registro y de cambio de datos generales
document.addEventListener('DOMContentLoaded', function () {
    // Validación del formulario de registro
    (function validarRegistro() {
        const form = document.querySelector('#registroModal form');
        if (!form) return; // Asegúrate de que el formulario exista en el DOM.
        const inputs = form.querySelectorAll('input');
        const submitButton = form.querySelector('button[type="submit"]');

        inputs.forEach(input => {
            input.addEventListener('blur', function () {
                validarInput(input);
                checkFormulario();
            });
        });

        function validarInput(input) {
            const value = input.value;
            const id = input.id;
            let todoBien = true;

            switch (id) {
                case 'floatingEmail':
                    todoBien = validarEmail(value);
                    break;
                case 'floatingPassword':
                    todoBien = value.length >= 8 && value.length <= 100;
                    break;
                case 'floatingPasswordConf':
                    const password = document.querySelector('#floatingPassword').value;
                    todoBien = value === password;
                    break;
                case 'floatingName':
                    todoBien = value.length > 0 && value.length <= 20;
                    break;
                case 'floatingSurname':
                    todoBien = value.length > 0 && value.length <= 30;
                    break;
                case 'floatingNIF':
                    todoBien = /^[0-9]{8}$/.test(value);
                    break;
                case 'floatingPhone':
                    todoBien = !value || /^[0-9]{9}$/.test(value);
                    break;
                case 'floatingDireccion':
                    todoBien = value.length > 0 && value.length <= 40;
                    break;
                case 'floatingcdPostal':
                    todoBien = /^(0[1-9][0-9]{3}|[1-4][0-9]{4}|5[0-2][0-9]{3})$/.test(value);
                    break;
                case 'floatingLocalidad':
                    todoBien = value.length > 0 && value.length <= 40;
                    break;
                case 'floatingProvincia':
                    todoBien = value.length > 0 && value.length <= 30;
                    break;
            }

            if (todoBien) {
                input.classList.remove('is-invalid');
                input.classList.add('is-valid');
            } else {
                input.classList.remove('is-valid');
                input.classList.add('is-invalid');
                showAlert(mensajeError(id));
            }
        }

        function checkFormulario() {
            const allValid = Array.from(inputs).every(input => input.classList.contains('is-valid'));
            submitButton.disabled = !allValid;
        }

        function showAlert(message) {
            Swal.fire({
                icon: 'error',
                title: 'Error de validación',
                text: message,
            });
        }

        function validarEmail(email) {
            const re = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
            return re.test(email);
        }

        function mensajeError(id) {
            switch (id) {
                case 'floatingEmail':
                    return 'El correo electrónico no es válido.';
                case 'floatingPassword':
                    return 'La contraseña debe tener entre 8 y 100 caracteres.';
                case 'floatingPasswordConf':
                    return 'Las contraseñas no coinciden.';
                case 'floatingName':
                    return 'El nombre no puede estar vacío y debe tener menos de 20 caracteres.';
                case 'floatingSurname':
                    return 'Los apellidos no pueden estar vacíos y deben tener menos de 30 caracteres.';
                case 'floatingNIF':
                    return 'El NIF no es válido.';
                case 'floatingPhone':
                    return 'El número de teléfono no es válido.';
                case 'floatingDireccion':
                    return 'La dirección no puede estar vacía y debe tener menos de 40 caracteres.';
                case 'floatingcdPostal':
                    return 'El código postal no es válido.';
                case 'floatingLocalidad':
                    return 'La localidad no puede estar vacía y debe tener menos de 40 caracteres.';
                case 'floatingProvincia':
                    return 'La provincia no puede estar vacía y debe tener menos de 30 caracteres.';
                default:
                    return 'Este campo no es válido.';
            }
        }
    })();

    // Validación del formulario de cambio de datos generales
    (function validarDatosGenerales() {
        const form = document.querySelector('form[action$="CambiarDatosGenerales"]');
        if (!form) return; // Asegúrate de que el formulario exista en el DOM.
        const inputs = form.querySelectorAll('input');
        const submitButton = form.querySelector('button[type="submit"]');

        inputs.forEach(input => {
            input.addEventListener('blur', function () {
                validarInput(input);
                checkFormulario();
            });
        });

        function validarInput(input) {
            const value = input.value;
            const id = input.id;
            let todoBien = true;

            switch (id) {
                case 'nombre':
                    todoBien = value.length > 0 && value.length <= 20;
                    break;
                case 'apellidos':
                    todoBien = value.length > 0 && value.length <= 30;
                    break;
                case 'nif':
                    todoBien = /^[0-9]{8}$/.test(value);
                    break;
                case 'telefono':
                    todoBien = !value || /^[0-9]{9}$/.test(value);
                    break;
                case 'direccion':
                    todoBien = value.length > 0 && value.length <= 40;
                    break;
                case 'codigoPostal':
                    todoBien = /^(0[1-9][0-9]{3}|[1-4][0-9]{4}|5[0-2][0-9]{3})$/.test(value);
                    break;
                case 'localidad':
                    todoBien = value.length > 0 && value.length <= 40;
                    break;
                case 'provincia':
                    todoBien = value.length > 0 && value.length <= 30;
                    break;
            }

            if (todoBien) {
                input.classList.remove('is-invalid');
                input.classList.add('is-valid');
            } else {
                input.classList.remove('is-valid');
                input.classList.add('is-invalid');
                showAlert(mensajeError(id));
            }
        }

        function checkFormulario() {
            const allValid = Array.from(inputs).every(input => input.classList.contains('is-valid'));
            submitButton.disabled = !allValid;
        }

        function showAlert(message) {
            Swal.fire({
                icon: 'error',
                title: 'Error de validación',
                text: message,
            });
        }

        function mensajeError(id) {
            switch (id) {
                case 'nombre':
                    return 'El nombre no puede estar vacío y debe tener menos de 20 caracteres.';
                case 'apellidos':
                    return 'Los apellidos no pueden estar vacíos y deben tener menos de 30 caracteres.';
                case 'nif':
                    return 'El NIF no es válido.';
                case 'telefono':
                    return 'El número de teléfono no es válido.';
                case 'direccion':
                    return 'La dirección no puede estar vacía y debe tener menos de 40 caracteres.';
                case 'codigoPostal':
                    return 'El código postal no es válido.';
                case 'localidad':
                    return 'La localidad no puede estar vacía y debe tener menos de 40 caracteres.';
                case 'provincia':
                    return 'La provincia no puede estar vacía y debe tener menos de 30 caracteres.';
                default:
                    return 'Este campo no es válido.';
            }
        }
    })();

    //Validacion de contraseñas
    (function validatePasswordForm() {
        const form = document.querySelector('form[action$="CambiarContrasenia"]');
        console.log("dentro");
        if (!form) return;
        const inputs = form.querySelectorAll('input');
        const submitButton = form.querySelector('button[type="submit"]');

        inputs.forEach(input => {
            input.addEventListener('blur', function () {
                validarInput(input);
                checkFormulario();
            });
        });

        function validarInput(input) {
            const value = input.value;
            const id = input.id;
            let todoBien = true;

            switch (id) {
                case 'contraseniaActual':
                    todoBien = value.length > 0;
                    break;
                case 'nuevaContrasenia':
                    todoBien = value.length >= 8 && value.length <= 100;
                    break;
                case 'ConfNuevaContrasenia':
                    const newPassword = document.querySelector('#nuevaContrasenia').value;
                    todoBien = value === newPassword;
                    break;
            }

            if (todoBien) {
                input.classList.remove('is-invalid');
                input.classList.add('is-valid');
            } else {
                input.classList.remove('is-valid');
                input.classList.add('is-invalid');
                showAlert(mensajeError(id));
            }
        }

        function checkFormulario() {
            const allValid = Array.from(inputs).every(input => input.classList.contains('is-valid'));
            submitButton.disabled = !allValid;
        }

        function showAlert(message) {
            Swal.fire({
                icon: 'error',
                title: 'Error de validación',
                text: message,
            });
        }

        function mensajeError(id) {
            switch (id) {
                case 'contraseniaActual':
                    return 'La contraseña actual no puede estar vacía.';
                case 'nuevaContrasenia':
                    return 'La nueva contraseña debe tener entre 8 y 100 caracteres.';
                case 'ConfNuevaContrasenia':
                    return 'Las contraseñas no coinciden.';
                default:
                    return 'Este campo no es válido.';
            }
        }
    })();
});

document.getElementById('precio').addEventListener('input', (event)=>{
    const value = event.target.value;
    const formattedValue = new Intl.NumberFormat('es-ES', { style: 'currency', currency: 'EUR' }).format(value);
    document.getElementById('precioValue').textContent = formattedValue;
});


