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

    <title>teacherPage</title>
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
              <a href="/teacherPage"><input type="button" class="btn btn-outline-light" value="Jegybeírás"></a>
            </li>
            <li class="nav-item">
             <a href="/teacherPage"><input type="button" class="btn btn-outline-light" value="Hiányzássbeírás"></a>
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
            <form:form  action="/teacherPage" method="POST">
                Tantárgy:
                <select class="form-control form-control-sm" name="selectSubject">
                 <c:forEach items="${teacherSubjects}" var="teacherSubject">
                      <option value="${teacherSubject.subjectId}" ${selectedSubject == teacherSubject.subjectId ? 'selected="selected"' : ''}>${teacherSubject.subjectName}</option>
                 </c:forEach>
                </select>
                Tanév:
                <select class="form-control form-control-sm" name="selectYear">
                     <c:forEach items="${schoolYears}" var="schoolYear">
                		<option value="${schoolYear.schoolYearId}" ${selectedYear == schoolYear.schoolYearId ? 'selected="selected"' : ''}>${schoolYear.startSchoolYear}/${schoolYear.endSchoolYear}</option>
                	</c:forEach>
                </select>
                Osztály:
                <select class="form-control form-control-sm" name="selectSchoolClass">
                     <c:forEach items="${schoolClasses}" var="schoolClass">
                		<option value="${schoolClass.classId}" ${selectedSchoolClass == schoolClass.classId ? 'selected="selected"' : ''}>${schoolClass.className}</option>
                	</c:forEach>
                </select>
                <input type="submit" class="btn btn-primary" value="Kiválasztása">
            </form:form>
        </div>



        <table class="table">
            <thead>
            <tr>
                <th scope="col">Hónap:</th>
                <th scope="col">09</th>
                <th scope="col">10</th>
                <th scope="col">11</th>
                <th scope="col">12</th>
                <th scope="col">I.</th>
                <th scope="col">01</th>
                <th scope="col">02</th>
                <th scope="col">03</th>
                <th scope="col">04</th>
                <th scope="col">05</th>
                <th scope="col">06</th>
                <th scope="col">II.</th>
                <th scope="col"></th>
                <th scope="col"></th>
            </tr>
            </thead>
	            <c:forEach items="${takingSubjects}" var="takingSubject">
	            <tbody>
	            <tr>
	                <th scope="row">${takingSubject.student.name}</th>
	 				<c:forEach var = "i" begin = "9" end = "12">
	 				    <td>
						<c:set var="monthmarks" value="${takingSubject.marks.stream().filter(p -> p.getMonth() == i).toList()}"/>
						<c:forEach items="${monthmarks}" var="mark">
							<a href="/teacherMarkChange/${mark.markId}">${mark.markScore}</a>
						</c:forEach>
						</td>
					</c:forEach>
					<td></td>
					<c:forEach var = "i" begin = "1" end = "6">
	 				    <td>
						<c:set var="monthmarks" value="${takingSubject.marks.stream().filter(p -> p.getMonth() == i).toList()}"/>
						<c:forEach items="${monthmarks}" var="mark">
							<a href="/teacherMarkChange/${mark.markId}">${mark.markScore}</a>
						</c:forEach>
						</td>
					</c:forEach> 
				</tr>
	            </tbody>
	            </c:forEach>
            </table>
            
            	<c:if test="${alert != null}"> 
		     		<div class="alert alert-info"> ${alert} </div>
		     	</c:if>  	
    </div>
  </body>
</html>