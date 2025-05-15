<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>
        <c:choose>
            <c:when test="${not empty docente.id}">
                Modifica Docente
            </c:when>
            <c:otherwise>
                Nuovo Docente
            </c:otherwise>
        </c:choose>
    </title>
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"/>
</head>
<body class="container mt-4">
<h1>
    <c:choose>
        <c:when test="${not empty docente.id}">
            Modifica Docente
        </c:when>
        <c:otherwise>
            Nuovo Docente
        </c:otherwise>
    </c:choose>
</h1>

<form action="<c:url value='${not empty docente.id ? "/docenti/" + docente.id + "/edit" : "/docenti/nuovo"}'/>" method="post">

    <div class="mb-3">
        <label for="nome" class="form-label">Nome</label>
        <input type="text" name="nome" id="nome" class="form-control" value="${docente.nome}" required>
    </div>
    <div class="mb-3">
        <label for="cognome" class="form-label">Cognome</label>
        <input type="text" name="cognome" id="cognome" class="form-control" value="${docente.cognome}" required>
    </div>

    <button type="submit" class="btn btn-success">Salva</button>
    <a href="<c:url value='/docenti/lista'/>" class="btn btn-secondary">Annulla</a>
</form>
</body>
</html>
