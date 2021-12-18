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

    <title>Diák oldal</title>
  </head>
  <body>
    <nav class="navbar navbar-expand-lg navbar-dark bg-primary">
        <a class="navbar-brand" href="/studentPage">Enapló - Diák</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
          <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
              <a href="/studentPage"><input type="button" class="btn btn-outline-light" value="Napló"></a>
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
        <div class="col-sm-2 my-3">
        	<div>Évfolyam: ${takingSubject.schoolYear.startSchoolYear}/${takingSubject.schoolYear.endSchoolYear}</div>
        	<div>Tantárgy: ${takingSubject.subject.subjectName}</div>
        </div>
        
        <div>A jegy ekkor került be: ${mark.shortDate}</div>
         <table class="table">
            <thead>
            <tr>
                <th scope="col">Jegy módosítva erről:</th>
                <th scope="col">Erre:</th>
                <th scope="col">Időpont</th>
            </tr>
            </thead>
	            <tbody>
	                <c:forEach items="${mark.markHistories}" var="markHistory">
	               	<tr>
	 				    <td>${markHistory.preChangedMark}</td>
						<td>${markHistory.postChangedMark}</td>
						<td>${markHistory.getShortDate()}</td>
					</tr>
					</c:forEach>
	            </tbody>
            </table>
    </div>
  </body>
</html>