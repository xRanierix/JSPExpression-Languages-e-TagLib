<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-08">
<title>Lista de Filmes</title>
</head>
<body>
<h1>Lista de Filmes</h1>

<c:forEach var="filme" items="${filmes}">
<p>${filme}</p>
<img src="${filme.posterPath}"></img>


</c:forEach>

</body>
</html>