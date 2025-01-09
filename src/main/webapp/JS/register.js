//Funcion para comprobar que se ha introducido correctamente el nombre
function checkNombre(element) {
    let re = /^[a-zA-Z]{1,20}$/;
        if (re.test(element.value)) {
            cambiarColor(element, true);
        } else {
            cambiarColor(element, false);
        }
}
//Funcion para comprobar que se ha introducido correctamente los apellidos
function checkApellidos(element) {
    let re = /^[a-zA-Z]{1,30}$/;
        if (re.test(element.value)) {
            cambiarColor(element, true);
        } else {
            cambiarColor(element, false);
        }
}
//Funcion para comprobar que se ha introducido correctamente la contraseña
function checkPass(element, conf) {
    let re = /^.{1,100}$/;
    if (re.test(element.value)) {
        //Si esa contraseña está bien, ahora comprobamos si la de confirmación coincide con la que se ha dado ahora
        if (element.value() === conf.value()){
            cambiarColor(element, true);
            cambiarColor(conf, true);
        }else {
            cambiarColor(element, true);
            cambiarColor(conf, false);
        }
    } else {
        cambiarColor(element, false);
        cambiarColor(conf, false);
    }
}
//Funcion para cambiar el color de los campos dependiendo si estan correctos o no
function cambiarColor(elemento, tipo) {
    if (tipo) {
        elemento.setAttribute('class', 'bien');
    } else {
        elemento.setAttribute('class', 'mal');
    }
}
//Evento para que cuando se haga click en subir, que se compruebe si todo esta correcto y luego subir la cancion
document.getElementById('user-song-upload').addEventListener('click', () => {
    checkAllFields();
});
//Comprobamos todos los campos del modal y subir la cancion si todo esta correcto
function checkAllFields() {
    checkCancionAudio(document.getElementById('user-song-link'));
    checkCancionTitulo(document.getElementById('user-song-title'));
    checkCancionArtista(document.getElementById('user-song-artist'));
    checkCancionCover(document.getElementById('user-song-cover'));

    let allFieldsValid = true;

    // Verifica si todos los campos tienen la clase "bien"
    let fields = ['user-song-link', 'user-song-title', 'user-song-artist', 'user-song-cover'];
    fields.forEach((fieldId) => {
        let field = document.getElementById(fieldId);
        if (!field.classList.contains('bien')) {
            allFieldsValid = false;
        }
    });
    if (allFieldsValid) {
        //Todos los campos son correctos
        //Si todos son correctos, lo subimos al servidor
        uploadSong();
    } else {
        //Si todos los campos no son validos se manda un alert de que algun campo es incorrecto
        alert("Algun campo introducido no es valido");
    }
}