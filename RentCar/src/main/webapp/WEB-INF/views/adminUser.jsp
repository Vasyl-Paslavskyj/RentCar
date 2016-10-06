<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/custom.tld" prefix="custom"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
</head>
<body>
<div class="row-fluid">
				<nav class="navbar navbar-default">
					<div class="container-fluid">
						<div class="collapse navbar-collapse" id="">
							<ul class="nav navbar-nav">
								<li><a href="/admin/classOfCar">ClassOfCar</a></li>
								<li><a href="/admin/statusCar">StatusCar</a></li>
								<li><a href="/admin/modelOfCar">ModelOfCar</a></li>
								<li><a href="/admin/car">Car</a></li>
								<li class="active"><a href="/admin/user">User</a><span
										class="sr-only">(current)</span></li>
								<li><a href="/admin/custom">Custom</a></li>
								<li><a href="/admin/accident">Accident</a></li>
							</ul>
						</div>
					</div>
				</nav>
</div>
<form:form action="/admin/user" method="post" modelAttribute="user">
	<form:hidden path="id"/>
	
	<c:forEach items="${param}" var="parameter">
			<c:forEach items="${parameter.value}" var="value">
				<c:if test="${parameter.key ne 'login' and parameter.key ne 'password' 
				and parameter.key ne 'fullName' and parameter.key ne 'drivingExperience' 
				and parameter.key ne 'email' and parameter.key ne 'id'}">
					<input type="hidden" name="${parameter.key}" value="${value}">
				</c:if>
			</c:forEach>
	</c:forEach>
	
	<table>
		<tr><td><form:errors path="login" cssClass="error"/></td></tr>
		<tr><td><form:input path="login" placeholder="String login"/></td></tr>
		<tr><td><form:errors path="password" cssClass="error"/></td></tr>
		<tr><td><form:input path="password" placeholder="String password"/></td></tr>
		<tr><td><form:errors path="fullName" cssClass="error"/></td></tr>
		<tr><td><form:input path="fullName" placeholder="String fullName"/></td></tr>
		<tr><td><form:errors path="drivingExperience" cssClass="error"/></td></tr>
		<tr><td><form:input path="drivingExperience" placeholder="int drivingExperience"/></td></tr>
		<tr><td><form:errors path="email" cssClass="error"/></td></tr>
		<tr><td><form:input path="email" placeholder="String email"/></td></tr>

		<tr>
			<td><input type="submit"></td>
		</tr>
	</table>
</form:form>

<form:form action="/admin/user" method="get" modelAttribute="filter">
	<c:forEach items="${param}" var="parameter">
			<c:forEach items="${parameter.value}" var="value">
				<c:if test="${parameter.key ne 'search'}">
					<input type="hidden" name="${parameter.key}" value="${value}">
				</c:if>
			</c:forEach>
	</c:forEach>
	<table>
			<tr>
				<td><form:input path="search" placeholder="search"/><input type="submit" value="ok"></td>
			</tr>
	</table>
</form:form>

<table>
  <tr>
    <th>Login</th>
    <th>Password</th>
    <th>FullName</th>
    <th>Driving_experience</th>
    <th>Email</th>
  </tr>
  <c:forEach items="${page.content}" var="user">
    <tr>
      <td>${user.login}</td>
      <td>${user.password}</td>
      <td>${user.fullName}</td>
      <td>${user.drivingExperience}</td>
      <td>${user.email}</td>
      <td><a href="/admin/user/delete/${user.id}?page=${page.number+1}&size=${page.size}&sort=${param['sort']}&search=${param['search']}">delete</a></td>
      <td><a href="/admin/user/update/${user.id}?page=${page.number+1}&size=${page.size}&sort=${param['sort']}&search=${param['search']}">update</a></td>
    </tr>
  </c:forEach>
  
 <tr>
  	<c:if test="${!page.isFirst()}">
  		<td>
  			<a href="??page=${page.number}&size=${page.size}&sort=${param['sort']}&search=${param['search']}">Previous</a>
  		</td>
  	</c:if>
  	<c:if test="${!page.isLast()}">
  		<td>
  			<a href="?page=${page.number+2}&size=${page.size}&sort=${param['sort']}&search=${param['search']}">Next</a>
  		</td>
  	</c:if>
</tr>
<tr>
  	<td><a href="?page=1&size=1&sort=${param['sort']}&search=${param['search']}">1</a></td>
  	<td><a href="?page=1&size=3&sort=${param['sort']}&search=${param['search']}">3</a></td>
  	<td><a href="?page=1&size=5&sort=${param['sort']}&search=${param['search']}">5</a></td>
  	<td><a href="?page=1&size=10&sort=${param['sort']}&search=${param['search']}">10</a></td>
</tr>
<tr>
  	<td><a href="?page=1&size=${page.size}&sort=login&search=${param['search']}">Login asc</a></td>
  	<td><a href="?page=1&size=${page.size}&sort=login,desc&search=${param['search']}">Login desc</a></td>
</tr>
<tr>
  	<td><a href="?page=1&size=${page.size}&sort=fullName&search=${param['search']}">FullName asc</a></td>
  	<td><a href="?page=1&size=${page.size}&sort=fullName,desc&search=${param['search']}">FullName desc</a></td>
</tr>
<tr>
  	<td><a href="?page=1&size=${page.size}&sort=drivingExperience&search=${param['search']}">DrivingExperience asc</a></td>
  	<td><a href="?page=1&size=${page.size}&sort=drivingExperience,desc&search=${param['search']}">DrivingExperience desc</a></td>
</tr>
</table>
<div class="col-md-12 text-center">
		<custom:pageable page="${page}" cell="<li></li>" container="<ul class='pagination'></ul>"/>
</div>
</body>
</html>
