<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>Nuovo Discente</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"/>
</head>
<body class="container mt-4">

<h1>
    <c:choose>
        <c:when test="${discente.id != null}">
            Modifica Discente
        </c:when>
        <c:otherwise>
            Nuovo Discente
        </c:otherwise>
    </c:choose>
</h1>

<c:choose>
    <c:when test="${discente.id != null}">
        <form action="${pageContext.request.contextPath}/discenti/${discente.id}/edit" method="post">
    </c:when>
    <c:otherwise>
        <form action="${pageContext.request.contextPath}/discenti/nuovo" method="post">
    </c:otherwise>
</c:choose>

    <div class="mb-3">
        <label for="nome" class="form-label">Nome</label>
        <input type="text" class="form-control" id="nome" name="nome" value="${discente.nome}" required>
    </div>

    <div class="mb-3">
        <label for="cognome" class="form-label">Cognome</label>
        <input type="text" class="form-control" id="cognome" name="cognome" value="${discente.cognome}" required>
    </div>
    <div class="mb-3">
        <label for="matricola" class="form-label">Matricola</label>
        <input type="number" class="form-control" id="matricola" name="matricola" value="${discente.matricola}" required>
    </div>

    <div class="mb-3">
        <label for="eta" class="form-label">Eta</label>
        <input type="number" class="form-control" id="eta" name="eta" value="${discente.eta}" required>
    </div>

    <div class="mb-3">
        <label for="cittaResidenza" class="form-label">Citta di Residenza</label>
        <input type="text" class="form-control" id="cittaResidenza" name="cittaResidenza" value="${discente.cittaResidenza}" required>
    </div>

    <button type="submit" class="btn btn-primary">Salva</button>
       <a href="<c:url value='/docenti/lista'/>" class="btn btn-secondary">Annulla</a>
</form>

</body>
</html>
