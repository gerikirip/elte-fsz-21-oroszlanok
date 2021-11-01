<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
	<head>
		<title>Ediary</title>
		<!-- Required meta tags -->
   		<meta charset="utf-8">
    	<meta name="viewport" content="width=device-width, initial-scale=1">
		<%@ page contentType="text/html; charset=UTF-8" %>
	
    	<!-- Bootstrap CSS -->
    	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

	</head>
<body>
	<div class="container">
		 <div class="row col-sm-3 mx-auto">		 
		  <div id="formContent">     
		     <form:form action="loginPage" method='POST'>
		     <div class="form-group">
			    <label for="username">Felhasználónév</label>
			    <input type="text" class="form-control" name="username" placeholder="Felhasználónév">
		  	</div>
		 	 <div class="form-group">
			    <label for="password">Jelszó</label>
			    <input type="password" class="form-control"  name="password" placeholder="Jelszó">
		  	</div>
		  		<input type="submit" class="btn btn-primary" value="Bejelentkezés">
		    </form:form>
		    </div>
		     	<c:if test="${param.error != null}"> 
		     		<div class="alert alert-info">Hibás felhasználónév vagy jelszó! </div>
		     	</c:if>  	
		  </div>
	</div>
</body>
</html>





