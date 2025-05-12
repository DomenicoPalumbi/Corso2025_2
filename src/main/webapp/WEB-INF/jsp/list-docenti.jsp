<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>Elenco Docenti</title>
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"/>
</head>
<body class="container mt-4">
<nav class="navbar navbar-expand-lg navbar-light bg-light">
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
                <li class="nav-item">
                                                    <a class="btn btn-outline-light" href="<c:url value='/corsi/lista'/>">Corsi</a>
                                </li>
            </ul>
        </div>
    </div>
</nav>
<h1>Elenco Docenti</h1>
<!-- Navbar -->
<a class="btn btn-primary mb-3" href="<c:url value='/docenti/nuovo'/>">Nuovo Docente</a>

<table class="table table-striped">
    <thead>
    <tr>
        <th>ID</th><th>Nome</th><th>Cognome</th><th>Email</th><th>Azioni</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="d" items="${docenti}">
        <tr>
            <td>${d.id}</td>
            <td>${d.nome}</td>
            <td>${d.cognome}</td>
            <td>${d.email}</td>
            <td>
                <a class="btn btn-sm btn-secondary" href="<c:url value='/docenti/${d.id}/edit'/>">Modifica</a>
                <a class="btn btn-sm btn-danger"
                   href="<c:url value='/docenti/${d.id}/delete'/>"
                   onclick="return confirm('Sei sicuro?')">Elimina</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
