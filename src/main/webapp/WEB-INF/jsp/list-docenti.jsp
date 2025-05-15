<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Elenco Docenti</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet">
    <style>
        table {
            box-shadow: 0px 4px 6px rgba(0, 0, 0, 0.1);
        }
        .btn:hover {
            opacity: 0.9;
            transition: opacity 0.3s ease-in-out;
        }
        .navbar {
            border-bottom: 2px solid #fff;
        }
        .table th, .table td {
            vertical-align: middle;
        }
        .table-hover tbody tr:hover {
            background-color: #f1f1f1;
        }
        .navbar-nav .btn {
            border-radius: 25px;
        }
        .card-body {
            background-color: #f8f9fa;
        }
        .table-striped {
            border-radius: 8px;
        }
    </style>
</head>
<body class="container mt-4">
<nav class="navbar navbar-expand-lg navbar-dark bg-primary mb-4">
    <div class="container-fluid">
        <img src="/images/logo.jfif" alt="Logo" style="height: 40px; margin-right: 10px;">
        <a class="navbar-brand text-white" href="#">Academy 2025</a>
        <div class="collapse navbar-collapse">
            <ul class="navbar-nav ms-auto">
                <li class="nav-item">
                    <a class="btn btn-outline-light me-2" href="<c:url value='/docenti/lista'/>">
                        <i class="bi bi-person-lines-fill"></i> Docenti
                    </a>
                </li>
                <li class="nav-item">
                    <a class="btn btn-outline-light me-2" href="<c:url value='/discenti/lista'/>">
                        <i class="bi bi-person-badge"></i> Discenti
                    </a>
                </li>
                <li class="nav-item">
                    <a class="btn btn-outline-light" href="<c:url value='/corsi/lista'/>">
                        <i class="bi bi-book"></i> Corsi
                    </a>
                </li>
            </ul>
        </div>
    </div>
</nav>

<h1 class="display-4 text-center text-primary mb-4">Elenco Docenti</h1>
<a class="btn btn-primary mb-3" href="<c:url value='/docenti/nuovo'/>">
    <i class="bi bi-person-plus"></i> Nuovo Docente
</a>

<table class="table table-striped table-bordered shadow-sm rounded table-hover">
    <thead>
    <tr>
        <th>ID</th><th>Nome</th><th>Cognome</th><th>Azioni</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="d" items="${docenti}">
        <tr>
            <td>${d.id}</td>
            <td>${d.nome}</td>
            <td>${d.cognome}</td>

            <td>
                <a class="btn btn-sm btn-outline-secondary rounded-pill" href="<c:url value='/docenti/${d.id}/edit'/>">
                    <i class="bi bi-pencil-square"></i> Modifica
                </a>
                <a class="btn btn-sm btn-outline-danger rounded-pill" href="<c:url value='/docenti/${d.id}/delete'/>"
                   onclick="return confirm('Sei sicuro di voler eliminare questo docente?')">
                    <i class="bi bi-trash"></i> Elimina
                </a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
