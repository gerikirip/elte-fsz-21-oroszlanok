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
        <a class="navbar-brand" href="/adminPage">Enapló - Admin</a>
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
		

  </body>
</html>