<header class="p-3 bg-light text-dark mb-3 border-bottom shadow-sm">
    <div class="container">
        <form method="post" action="${applicationScope.contexto}/Buscar" class="row align-items-center">
            <!-- Logo -->
            <div class="col-12 col-lg-auto mb-3 mb-lg-0 text-center">
                <button type="submit" name="opcion" value="nada" class="btn border-0 bg-transparent p-0">
                    <img class="img-fluid" src="IMG/logo/Abatech.png" alt="Abatech Logo" style="max-height: 60px;">
                </button>
            </div>

            <!-- Barra de búsqueda -->
            <div class="col-12 col-lg mb-3 mb-lg-0">
                <div class="input-group">
                    <input
                            id="searchHeader"
                            name="nombre"
                            type="search"
                            class="form-control rounded-pill"
                            placeholder="Buscar..."
                            aria-label="Buscar">
                    <button
                            class="btn btn-outline-success rounded-pill ms-2"
                            name="opcion"
                            value="buscar"
                            type="submit"
                            id="searchBtnHeader"
                            disabled>
                        Buscar
                    </button>
                </div>
            </div>

            <!-- Botones de inicio de sesión y registro -->
            <div class="col-12 col-lg-auto text-center">
                <button
                        type="button"
                        class="btn btn-outline-dark rounded-pill me-2"
                        data-bs-toggle="modal"
                        data-bs-target="#loginModal">
                    Iniciar sesi&oacute;n
                </button>
                <button
                        type="button"
                        class="btn btn-primary rounded-pill"
                        data-bs-toggle="modal"
                        data-bs-target="#registroModal">
                    Registrarse
                </button>
            </div>
        </form>
    </div>
</header>


<!-- Modal de registro -->
<div class="modal fade" id="registroModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <form action="${applicationScope.contexto}" method="post" class="modal-content">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="exampleModalLabel">Formulario de registro</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <div class="form-floating mb-3">
                    <input type="text" name="nombre" class="form-control" id="floatingName" placeholder="Nombre">
                    <label for="floatingName">Nombre</label>
                </div>
                <div class="form-floating mb-3">
                    <input type="text" name="apellidos" class="form-control" id="floatingSurname"
                           placeholder="Apellidos">
                    <label for="floatingSurname">Apellidos</label>
                </div>
                <div class="form-floating mb-3">
                    <input type="text" name="NIF" class="form-control" id="floatingNIF" placeholder="NIF">
                    <label for="floatingNIF">NIF</label>
                </div>
                <div class="form-floating mb-3">
                    <input type="email" name="email" class="form-control" id="floatingEmail"
                           placeholder="name@example.com">
                    <label for="floatingEmail">Correo Electr&oacute;nico</label>
                </div>
                <div class="form-floating mb-3">
                    <input type="password" name="password" class="form-control" id="floatingPassword"
                           placeholder="Password">
                    <label for="floatingPassword">Contrase&ntilde;a</label>
                </div>
                <div class="form-floating mb-3">
                    <input type="password" name="passwordConf" class="form-control" id="floatingPasswordConf"
                           placeholder="Password">
                    <label for="floatingPasswordConf">Confirmar contrase&ntilde;a</label>
                </div>
                <div class="form-floating mb-3">
                    <input type="number" name="telefono" class="form-control" id="floatingPhone" placeholder="telefono">
                    <label for="floatingPhone">N&uacute;mero de telefono</label>
                </div>
                <div class="form-floating mb-3">
                    <input type="text" name="provincia" class="form-control" id="floatingProvincia"
                           placeholder="Provincia">
                    <label for="floatingProvincia">Provincia</label>
                </div>
                <div class="form-floating mb-3">
                    <input type="text" name="localidad" class="form-control" id="floatingLocalidad"
                           placeholder="localidad">
                    <label for="floatingLocalidad">Localidad</label>
                </div>
                <div class="form-floating mb-3">
                    <input type="text" name="direccion" class="form-control" id="floatingDireccion"
                           placeholder="direccion">
                    <label for="floatingDireccion">Direcci&oacute;n</label>
                </div>
                <div class="form-floating mb-3">
                    <input type="text" name="cdPostal" class="form-control" id="floatingcdPostal"
                           placeholder="cdPostal">
                    <label for="floatingcdPostal">C&oacute;digo postal</label>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-danger" data-bs-dismiss="modal">Cancelar</button>
                <button type="submit" name="opcion" value="Registrar" class="btn btn-success btn-lg">
                    Registrarse
                </button>
            </div>
        </form>
    </div>
</div>

<!-- Modal de inicio de sesión -->
<div class="modal fade" id="loginModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <form action="${applicationScope.contexto}" method="post" class="modal-content">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="exampleModalLabelLog">Inicio de sesi&oacute;n</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <div class="form-floating mb-3">
                    <input type="email" name="email" class="form-control" id="floatingEmailLog"
                           placeholder="name@example.com">
                    <label for="floatingEmail">Correo Electr&oacute;nico</label>
                </div>
                <div class="form-floating mb-3">
                    <input type="password" name="password" class="form-control" id="floatingPasswordLog"
                           placeholder="Password">
                    <label for="floatingPassword">Contrase&ntilde;a</label>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-danger" data-bs-dismiss="modal">Cancelar</button>
                <button type="submit" name="opcion" value="Iniciar" class="btn btn-success btn-lg">
                    Iniciar sesi&oacute;n
                </button>
            </div>
        </form>
    </div>
</div>