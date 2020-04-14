<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Filme</title>
<!-- Font-awesome - ícones -->
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css" integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf" crossorigin="anonymous" />
        
<!-- Fonte do Google -->
<link href="https://fonts.googleapis.com/css?family=Luckiest+Guy|Source+Sans+Pro" rel="stylesheet" />

</head>
<body>
	<fmt:setLocale value="pt_BR"/>
	<h1>Filme id: ${filme.id}</h1>
	<p><strong>Título: </strong>${filme.titulo}</p>
	<p><strong>Descrição: </strong>${filme.descricao}</p>
	<p><strong>Diretor: </strong>${filme.diretor}</p>
	<p><strong>Gênero: </strong>${filme.genero.nome}</p>
	<p><strong>Lançamento: </strong>
	<fmt:formatDate value="${filme.dataLancamento}" dateStyle="FULL"/></p>
	<p><strong>Popularidade: </strong>
	<fmt:formatNumber value="${filme.popularidade}" minFractionDigits="2"
	maxFractionDigits="2"/>
	</p>
	<p><img src="${filme.posterPath}">
</body>
</html>