<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
        <a class="navbar-brand" href="/studentPage">Enapló - Diák</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
      
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
          <ul class="navbar-nav mr-auto">
            <li>
              <a href="/studentPage/studentAbsent"><input type="button" class="btn btn-outline-light" value="Hiányzások"></a>
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
            <form:form  action="/studentPage" method="POST">
                <select class="form-control form-control-sm" name="selectYear">
                <c:forEach items="${schoolYears}" var="schoolYear">
                	<option value="${schoolYear.schoolYearId}" ${choosenYears == schoolYear.schoolYearId ? 'selected="selected"' : ''}>${schoolYear.startSchoolYear}/${schoolYear.endSchoolYear}</option>
                </c:forEach>
                </select>
                <input type="submit" class="btn btn-primary" value="Tanév kiválasztása">
            </form:form>
       </div>
       
        <table class="table">
            <thead>
            <tr>
                <th scope="col">Tárgy neve:</th>
                <th scope="col">Szeptember</th>
                <th scope="col">Október</th>
                <th scope="col">November</th>
                <th scope="col">December</th>
                <th scope="col">I. félév</th>
                <th scope="col">Január</th>
                <th scope="col">Február</th>
                <th scope="col">Március</th>
                <th scope="col">Április</th>
                <th scope="col">Május</th>
                <th scope="col">Június</th>
                <th scope="col">II. félév</th>
		    <th scope="col">Lezárt jegy</th>  
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${takingSubjects}" var="takingSubject">
         
            <tr class='${takingSubject.suYear ? "bg-success" : takingSubject.suYear == false ? "bg-danger" : ""}'>
                <th scope="row">${takingSubject.subject.subjectName}</th>
 				<c:forEach var = "i" begin = "9" end = "12">
 				    <td>
					<c:set var="monthmarks" value="${takingSubject.marks.stream().filter(p -> p.getMonth() == i).toList()}"/>
					<c:forEach items="${monthmarks}" var="mark">
                            <a href="/studentChange/${mark.markId}" class="link-dark">${mark.markScore}</a>
                        </c:forEach>
					</td>
				</c:forEach>
				<td><fmt:formatNumber type="number" maxFractionDigits="2" minFractionDigits="2" value="${takingSubject.firstAvg}"/></td>
				<c:forEach var = "i" begin = "1" end = "6">
 				    <td>
					<c:set var="monthmarks" value="${takingSubject.marks.stream().filter(p -> p.getMonth() == i).toList()}"/>
					<c:forEach items="${monthmarks}" var="mark">
                            <a href="/studentChange/${mark.markId}" class="link-dark">${mark.markScore}</a>
                        </c:forEach>
					</td>
				</c:forEach>
				<td><fmt:formatNumber type="number" maxFractionDigits="2" minFractionDigits="2" value="${takingSubject.secondAvg}"/></td>
			<c:if test="${takingSubject.suYear != null}"> 
						<td class="">${takingSubject.endMark}</td><td></td><td></td><td></td> 
					</c:if>
		    </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
  </body>
</html>
