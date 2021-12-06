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

    <title>Tanár oldal</title>
  </head>
  <body>
    <nav class="navbar navbar-expand-lg navbar-dark bg-primary">
        <a class="navbar-brand" href="/teacherPage">Enapló - Tanár</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
          <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
              <a href="/teacherPage"><input type="button" class="btn btn-outline-light" value="Napló"></a>
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
     	<div>Név: ${takingSubject.student.name}</div>
        <div>Tantárgy: ${takingSubject.subject.subjectName}</div>
        <div>Évfolyam: ${takingSubject.schoolYear.startSchoolYear}/${takingSubject.schoolYear.endSchoolYear}</div>
        <div>Osztály: ${studentClass.schoolClass.className}</div>
        <div class="col-sm-2 my-3">
            <form:form  action="/teacherMarkAdd" method="POST">
           		<input type="number" name="markScore" min="1" max="5" value="3">
           		<input type="hidden" name="takingSubjectId" value="${takingSubject.takingSubjectId}">
           		<input type="submit" class="btn btn-primary" value="Hozzáadd">
            </form:form>
        </div>
    </div>
  </body>
</html>