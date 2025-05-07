<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>Nuovo Discente</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"/>
</head>
<body class="container mt-4">
<h1>${discente.id != null ? "Modifica Discente" : "Nuovo Discente"}</h1>

<form action="<c:url value='${discente.id != null ? "/discenti/" += discente.id : "/discenti"}'/>" method="post">

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
</form>

</body>
</html>
