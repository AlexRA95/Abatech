<jsp:directive.page contentType="text/html" pageEncoding="UTF-8"/>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:url var="bootstrap" value="/CSS/bootstrap.min.css" scope="application" />
<c:url var="bootstrapJS" value="/JS/bootstrap.bundle.min.js" scope="application" />
<c:url var="estilo" value="/CSS/style.css" scope="application"/>
<c:url var="javaScript" value="/JS/script.js" scope="application"/>
<c:set var="contexto" value="${pageContext.request.contextPath}" scope="application" />
<html>
<head>
    <jsp:include page="INC/metas.jsp">
        <jsp:param name="titulo" value="Abatech - Inicio" />
    </jsp:include>
    <link rel="stylesheet" href="${bootstrap}" />
</head>
<body>
<c:import url="INC/headerAnon.jsp"/>
<main class="container">
    <div class="row">
        <aside class="col-md-3 border border-dark rounded-2">
            <p>Aquí estará el filtro</p>
        </aside>

        <article class="col-md-9">
            <div class="row">
                <c:if test="${not empty requestScope.productos}">
                    <c:forEach var="producto" items="${requestScope.productos}" varStatus="status">
                        <div class="col-md-3 mb-4">
                            <div class="card h-100 d-flex flex-column justify-content-between">
                                <img src="IMG/productos/${producto.imagen}.jpg" class="card-img-top" alt="${producto.nombre}">
                                <div class="card-body d-flex flex-column justify-content-between">
                                    <div>
                                        <h5 class="card-title">${producto.nombre}</h5>
                                        <h6 class="card-subtitle mb-2 text-muted">Marca: ${producto.marca}</h6>
                                        <p class="card-text">${producto.descripcion.substring(0, 25)}...</p>
                                    </div>
                                    <div>
                                        <p class="card-text"><strong>Precio: </strong>${producto.precio} €</p>
                                        <button
                                                type="button"
                                                class="btn btn-primary"
                                                data-bs-toggle="modal"
                                                data-bs-target="#${producto.idProducto}Modal">
                                            Ver detalles
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <c:if test="${status.index % 4 == 3}">
                            <div class="w-100"></div>
                        </c:if>

                        <!-- Modal del producto -->
                        <div class="modal fade" id="${producto.idProducto}Modal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                            <div class="modal-dialog">
                                <div class="modal-dialog">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h3 class="modal-title"> ${producto.nombre}</h3>
                                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                        </div>
                                        <div class="modal-body">
                                            <p>Modal body text goes here.</p>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                                            <button type="button" class="btn btn-primary">Save changes</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </c:if>

                <c:if test="${empty requestScope.productos}">
                    <div class="alert alert-warning m-3" role="alert">
                        No se han encontrado productos que cumplan con los requisitos especificados.
                    </div>
                </c:if>
            </div>
        </article>
    </div>
</main>
<script src="${bootstrapJS}"></script>
<script src="${javaScript}"></script>
</body>
</html>