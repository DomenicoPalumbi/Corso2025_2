<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>Elenco Corsi</title>
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
<h1>Elenco Corsi</h1>
<!-- Navbar -->
<a class="btn btn-primary mb-3" href="<c:url value='/corsi/nuovo'/>">Nuovo Corso</a>

<table class="table table-striped">
    <thead>
    <tr>
        <th>ID</th><th>Nome</th><th>Anno Accademico</th><th>Id Docente</th><th>Nome e Cognome Docente</th><th>Azioni</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="c" items="${corsi}">
        <tr>
            <td>${c.id}</td>
            <td>${c.nome}</td>
            <td>${c.annoAccademico}</td>
            <td>${c.docente.id}</td>
            <td>${c.docente.nome} ${c.docente.cognome}</td>
            <td>
                <a class="btn btn-sm btn-secondary" href="<c:url value='/corsi/${c.id}/edit'/>">Modifica</a>
                <a class="btn btn-sm btn-danger"
                   href="<c:url value='/corsi/${c.id}/delete'/>"
                   onclick="return confirm('Sei sicuro?')">Elimina</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>