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
        
        <form:form  action="/teacherAuthAbsent" method="POST">
           Tanév:
          	<select class="form-control form-control-sm" name="selectYear">
               <c:forEach items="${schoolYears}" var="schoolYear">
                	<option value="${schoolYear.schoolYearId}" ${absentChoosenYear == schoolYear.schoolYearId ? 'selected="selected"' : ''}>${schoolYear.startSchoolYear}/${schoolYear.endSchoolYear}</option>
               </c:forEach>
            </select>
            <input type="submit" class="btn btn-primary" value="Kiválasztása">
         </form:form>
        
         <table class="table">
            <thead>
            <tr>
                <th scope="col">Név:</th>
                <th scope="col">Hiányzás:</th>
                <th scope="col">Igazol/Igazolatlan:</th>
                <th></th>
            </tr>
            </thead>
	            <tbody>
	                <c:forEach items="${takingSubjects}" var="takingSubject">
	                <c:forEach items="${takingSubject.absents}" var="absent">
	               	<tr>
	 				    <td>${takingSubject.student.name}</td>
	 				    <td>${absent.cleanDate} - ${absent.endTime}</td>
	 				    <c:if test="${!absent.authAbsent}"> 
	 				    	<td>Igazolatlan</td>
	 				    	<td><a href="/certifyAbsence/${absent.id}">Igazol</a></td>	
	 				    </c:if>
	 				    <c:if test="${absent.authAbsent}"> 
	 				    	<td>Igazolt</td>
	 				    </c:if>
	 				  					
					</tr>
					</c:forEach>
					</c:forEach>
	            </tbody>
            </table>
    </div>
  </body>
</html>