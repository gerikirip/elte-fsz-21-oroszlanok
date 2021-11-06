<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!doctype html>
<html lang="hu">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <%@ page contentType="text/html; charset=UTF-8" %>

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

    <title>StudentPage</title>
  </head>
  <body>
    <nav class="navbar navbar-expand-lg navbar-dark bg-primary">
        <a class="navbar-brand" href="/studentPage">Enapló - Admin</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
      
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
          <ul class="navbar-nav mr-auto">
            <li class="nav-item">
              <a href="/adminPage/createschoolYear"><input type="button" class="btn btn-outline-light" value="Évfolyam hozzáadása"></a>
            </li>
            <li class="nav-item">
              	<a href="/adminPage/createSchoolClass"><input type="button" class="btn btn-outline-light" value="Osztály hozzáadása"></a>
            </li>
            <li class="nav-item">
              <a href="/adminPage/createTeacher"><input type="button" class="btn btn-outline-light" value="Tanár hozzáadása"></a>
            </li>
            <li class="nav-item">
              <a href="/adminPage"><input type="button" class="btn btn-outline-light" value="Diák hozzáadása"></a>
            </li>
            <li class="nav-item">
              <form:form action="/logout" method="POST">
              	<input type="submit" class="btn btn-outline-light" value="Kijelentkezés">
              </form:form>
            </li>
          </ul>
        </div>
      </nav>
		
		<div class="container">
        <div class="col-sm-2 my-3 mx-auto">
            <form:form  action="/adminPage/createSchoolClass" method="POST">
               	Osztály száma: <input type="number" min=1 max=12 value=1 name="classNumber">
               	Osztály neve: <input type="text"  maxlength="2" name="classText">
                <input type="submit" class="btn btn-primary" value="Osztály hozzáadása">
            </form:form>
       </div>
       
        <table class="table">
            <thead>
            <tr>
            	<th scope="col">Id</th>
                <th scope="col">Osztály neve</th>
            </tr>
            </thead>        
            <c:forEach items="${schoolClasses}" var="schoolClass">
            <tbody>
            <tr>
            	<th scope="row">${schoolClass.classId}</th>
 				<td>${schoolClass.className}</td>
			</tr>
			</c:forEach>
            </tbody>
        </table>
       </div>
  </body>
		
		
  </body>
</html>