<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>Tontine</title>
<link href="${pageContext.request.contextPath}/bootstrap.min.css" type="text/css" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/style1.css" type="text/css" rel="stylesheet" />

</head>
<body>
<div class="jumbotron">
	<div class="container">
		<div class="row">
            <div class="col-md-12">
            	<h2 id="logo"><span class="text-color">Tontine Group: liste des participants</span></h2>                                  
			</div>
		</div>
	</div>
</div>
<div class="main">
<div class="formulaire">
    <c:if test="${ !empty erreur }"><p style="color:red;"><c:out value="${ erreur }" /></p></c:if>
<form method="post" action="Accueil">
<p>
<table>
	<label for="nom">Nom: <br>
	<td>
		<input type="text" name="nom" id="nom">
	</td>
</table>
</p>
<p>
<table>
	<label for="prenom"> Prénom:<br>
		<td><input type="text" height="150" width="120" name="prenom" id="prenom"></td>
</table>
</p>
<p>
					<input type="submit" value="Inscrire" /><p>
    </form>
</div>
    <ol>
	    <c:forEach var="membre" items="${ membres }">
	    	<li><c:out value="${ membre.prenom }" /> <c:out value="${ membre.nom }" /></li>
	    </c:forEach>
	</ol> 
</div>   
</body>
</html>