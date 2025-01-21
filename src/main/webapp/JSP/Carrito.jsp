<jsp:directive.page contentType="text/html" pageEncoding="UTF-8"/>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html lang="es">
<head>
    <jsp:include page="../INC/metas.jsp">
        <jsp:param name="titulo" value="Abatech - Carrito" />
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
        <div class="row">
            <table class="table">
                <thead>
                <tr>
                    <th scope="col">Imagen</th>
                    <th scope="col">Producto</th>
                    <th scope="col">Cantidad</th>
                    <th scope="col" class="text-end">Precio</th>
                    <th scope="col" class="text-end">Subtotal</th>
                    <th scope="col">Acciones</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="linea" items="${sessionScope.carrito.lineasPedido}">
                    <tr>
                        <td><img src="${applicationScope.contexto}/IMG/productos/${linea.producto.imagen}.jpg" alt="${linea.producto.nombre}" width="50" height="50"></td>
                        <td>${linea.producto.nombre}</td>
                        <td>${linea.cantidad}</td>
                        <td class="text-end"><fmt:formatNumber value="${linea.producto.precio}" type="currency" currencySymbol="€"/></td>
                        <td class="text-end"><fmt:formatNumber value="${linea.cantidad * linea.producto.precio}" type="currency" currencySymbol="€"/></td>
                        <td>
                            <form action="ActualizarCantidad" method="post" style="display:inline;">
                                <input type="hidden" name="idProducto" value="${linea.producto.idProducto}">
                                <button type="submit" name="accion" value="incrementar" class="btn btn-success btn-sm">+</button>
                                <button type="submit" name="accion" value="decrementar" class="btn btn-danger btn-sm">-</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</main>
<c:import url="../INC/footer.jsp"/>
<script src="${bootstrapJS}"></script>
<script src="${javaScript}"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
</body>
</html>