<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-08">
<title>Lista de Filmes</title>
<!-- Font-awesome - ícones -->
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css" integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf" crossorigin="anonymous" />
        
<!-- Fonte do Google -->
<link href="https://fonts.googleapis.com/css?family=Luckiest+Guy|Source+Sans+Pro" rel="stylesheet" />

</head>
<body>
<h1>Lista de Filmes</h1>



<c:forEach var="filme" items="${filmes}">
	
	<div style="">
		<form action="manter_filmes.do" method="GET">
			
			<p><button type="submit" name="acao" value="page_atualizar">Atualizar Filme</button></p>
			<p>${filme}</p>
			<img src="${filme.posterPath}"></img>
			<input type="hidden" name="id_atualizar" value="${filme.id}"/>
			
		</form>
	
	</div>
	<hr>

</c:forEach>

</body>
</html>