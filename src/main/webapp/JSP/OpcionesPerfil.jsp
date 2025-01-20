<jsp:directive.page contentType="text/html" pageEncoding="UTF-8"/>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html lang="es">
<head>
    <jsp:include page="../INC/metas.jsp">
        <jsp:param name="titulo" value="Abatech - Perfil Usuario" />
    </jsp:include>
    <link rel="stylesheet" href="${bootstrap}"/>
</head>
<body>

<c:choose>
    <c:when test="${empty sessionScope.usuario}">
        <c:import url="../INC/headerAnon.jsp"/>
    </c:when>
    <c:when test="${not empty sessionScope.usuario}">
        <c:import url="../INC/headerLogged.jsp"/>
    </c:when>
</c:choose>

<main class="container">
    <div class="row">
        <div class="col-4">
            <form action="${applicationScope.contexto}/CambiarDatosGenerales" id="datosGenerales" method="post">
                <div class="mb-3">
                    <label for="nombre" class="form-label">Nombre</label>
                    <input type="text" class="form-control" id="nombre" name="nombre" value="${sessionScope.usuario.nombre}">
                </div>
                <div class="mb-3">
                    <label for="apellidos" class="form-label">Apellidos</label>
                    <input type="text" class="form-control" id="apellidos" name="apellidos" value="${sessionScope.usuario.apellidos}">
                </div>
                <div class="mb-3">
                    <label for="nif" class="form-label">NIF</label>
                    <input type="text" class="form-control" id="nif" name="nif" value="${sessionScope.usuario.nif}">
                </div>
                <div class="mb-3">
                    <label for="telefono" class="form-label">Tel&eacute;fono</label>
                    <input type="tel" class="form-control" id="telefono" name="telefono" value="${sessionScope.usuario.telefono}">
                </div>
                <div class="mb-3">
                    <label for="direccion" class="form-label">Direcci&oacute;n</label>
                    <input type="text" class="form-control" id="direccion" name="direccion" value="${sessionScope.usuario.direccion}">
                </div>
                <div class="mb-3">
                    <label for="codigoPostal" class="form-label">C&oacute;digo Postal</label>
                    <input type="text" class="form-control" id="codigoPostal" name="codigoPostal" value="${sessionScope.usuario.codigoPostal}">
                </div>
                <div class="mb-3">
                    <label for="localidad" class="form-label">Localidad</label>
                    <input type="text" class="form-control" id="localidad" name="localidad" value="${sessionScope.usuario.localidad}">
                </div>
                <div class="mb-3">
                    <label for="provincia" class="form-label">Provincia</label>
                    <input type="text" class="form-control" id="provincia" name="provincia" value="${sessionScope.usuario.provincia}">
                </div>
                <button type="submit" class="btn btn-primary" name="opcion" value="datosGen" disabled>Cambiar Datos Generales</button>
            </form>
        </div>
        <div class="col-4 border border-top-0 border-bottom-0 border-dark-subtle">
            <form action="${contexto}/CambiarContrasenia" method="post">
                <div class="mb-3">
                    <label for="contraseniaActual" class="form-label">Contrase&ntilde;a Actual</label>
                    <input type="password" class="form-control" id="contraseniaActual" name="contraseniaActual" required>
                </div>
                <div class="mb-3">
                    <label for="nuevaContrasenia" class="form-label">Nueva Contrase&ntilde;a</label>
                    <input type="password" class="form-control" id="nuevaContrasenia" name="nuevaContrasenia" required>
                </div>
                <div class="mb-3">
                    <label for="ConfNuevaContrasenia" class="form-label">Confirmar nueva contrase&ntilde;a</label>
                    <input type="password" class="form-control" id="ConfNuevaContrasenia" name="ConfNuevaContrasenia" required>
                </div>
                <button type="submit" class="btn btn-primary" disabled>Cambiar Contrase&ntilde;a</button>
            </form>
        </div>
        <div class="col-4">
            <form action="${contexto}/CambiarAvatar" method="post" enctype="multipart/form-data">
                <div class="mb-3">
                    <label for="avatar" class="form-label">Avatar</label>
                    <input type="file" class="form-control" id="avatar" name="avatar" required>
                </div>
                <button type="submit" class="btn btn-primary">Cambiar Avatar</button>
            </form>
        </div>
    </div>
</main>
<script src="${bootstrapJS}"></script>
<script src="${javaScript}"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
</body>
</html>