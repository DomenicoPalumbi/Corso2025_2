<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Elenco Discenti</title>

    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"/>
</head>
<body class="container mt-4">
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container-fluid">
    <img src="/images/logo.jfif" alt="Logo" style="height: 40px; margin-right: 10px;">

        <a class="navbar-brand" href="#">Academy 2025</a>
        <div class="collapse navbar-collapse">
            <ul class="navbar-nav ms-auto">
                <li class="nav-item">
                    <a class="btn btn-outline-light me-2" href="<c:url value='/docenti/lista'/>">Docenti</a>
                </li>
                <li class="nav-item">
                    <a class="btn btn-outline-light" href="<c:url value='/discenti/lista'/>">Discenti</a>
                </li>
            </ul>
        </div>
    </div>
</nav>
<h1>Elenco Discenti</h1>


<a class="btn btn-primary mb-3" href="<c:url value='/discenti/nuovo'/>">Nuovo Discente</a>
  <!-- Pulsanti per ordinare -->
    <div class="mb-3">
        <a class="btn btn-info" href="<c:url value='/discenti/asc'/>">Ordina per Nome (Crescente)</a>
        <a class="btn btn-info" href="<c:url value='/discenti/desc'/>">Ordina per Nome (Decrescente)</a>
        <a class="btn btn-info" href="<c:url value='/discenti/teramo'/>">Mostra Discenti da Teramo</a>

    </div>
<table class="table table-striped">
    <thead>
    <tr>
        <th>ID</th><th>Nome</th><th>Cognome</th><th>Matricola</th><th>Eta</th><th>Citta di Residenza</th><th>Azioni</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="d" items="${discenti}">
        <tr>
            <td>${d.id}</td>
            <td>${d.nome}</td>
            <td>${d.cognome}</td>
            <td>${d.matricola}</td>
            <td>${d.eta}</td>
            <td>${d.cittaResidenza}</td>
            <td>
                <a class="btn btn-sm btn-success" href="<c:url value='/discenti/${d.id}/edit'/>">Modifica</a>
                <a class="btn btn-sm btn-danger"
                   href="<c:url value='/discenti/${d.id}/delete'/>"
                   onclick="return confirm('Sei sicuro?')">Elimina</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
