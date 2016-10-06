<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/custom.tld" prefix="custom"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
								<li class="active"><a href="/admin/classOfCar">ClassOfCar</a><span
										class="sr-only">(current)</span></li>
								<li><a href="/admin/statusCar">StatusCar</a></li>
								<li><a href="/admin/modelOfCar">ModelOfCar</a></li>
								<li><a href="/admin/car">Car</a></li>
								<li><a href="/admin/user">User</a></li>
								<li><a href="/admin/custom">Custom</a></li>
								<li><a href="/admin/accident">Accident</a></li>
							</ul>
						</div>
					</div>
				</nav>
</div>
<form:form action="/admin/classOfCar" method="post" modelAttribute="classOfCar">
	<form:hidden path="id"/>
	
	<c:forEach items="${param}" var="parameter">
			<c:forEach items="${parameter.value}" var="value">
				<c:if test="${parameter.key ne 'typeClassOfCar' and parameter.key ne 'price' and parameter.key ne 'id'}">
					<input type="hidden" name="${parameter.key}" value="${value}">
				</c:if>
			</c:forEach>
	</c:forEach>
	
	<table>
		<tr>
			<td><form:errors path="typeClassOfCar" cssClass="error"/></td>
		</tr>
		<tr>
			<td><form:input path="typeClassOfCar" placeholder="String typeClassOfCar"/></td>
		</tr>
		<tr>
			<td><form:errors path="price" cssClass="error"/></td>
		</tr>
		<tr>
			<td><form:input path="price" placeholder="int price"/></td>
		</tr>
		<tr>
				<td><input type="submit"></td>
		</tr>
	</table>
</form:form>

<form:form action="/admin/classOfCar" method="get" modelAttribute="filter">
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


<!--<form action="/admin/classOfCar" method="post">
		<table>
			<tr>
				<td>
					<select name="typeClassOfCar">
						<c:forEach items="${typeClassOfCars}" var="typeClassOfCar">
							<option>
								${typeClassOfCar}
							</option>
						</c:forEach>
					</select>
				</td>
				<td><input name="price" placeholder="int price"></td>
			</tr>
			<tr>
				<td><input type="submit"></td>
			</tr>
		</table>
</form>-->

<table>
  <tr>
    <th>TypeClassOfCar</th>
    <th>Price</th>
  </tr>
  <c:forEach items="${page.content}" var="classOfCar">
    <tr>
      <td>${classOfCar.typeClassOfCar}</td>
      <td>${classOfCar.price}</td>
      <td><a href="/admin/classOfCar/delete/${classOfCar.id}?page=${page.number+1}&size=${page.size}&sort=${param['sort']}&search=${param['search']}">delete</a></td>
      <td><a href="/admin/classOfCar/update/${classOfCar.id}?page=${page.number+1}&size=${page.size}&sort=${param['sort']}&search=${param['search']}">update</a></td>
    </tr>
  </c:forEach>
  
  <tr>
  	<c:if test="${!page.isFirst()}">
  		<td>
  			<a href="?page=${page.number}&size=${page.size}&sort=${param['sort']}&search=${param['search']}">Previous</a>
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
  	<td><a href="?page=1&size=5&sort=${param['sort']}&search=${param['search']}">5</a></td>
  	<td><a href="?page=1&size=10&sort=${param['sort']}&search=${param['search']}">10</a></td>
  	<td><a href="?page=1&size=20&sort=${param['sort']}&search=${param['search']}">20</a></td>
  </tr>
  <tr>
  	<td><a href="?page=1&size=${page.size}&sort=typeClassOfCar&search=${param['search']}">TypeClassOfCar asc</a></td>
  	<td><a href="?page=1&size=${page.size}&sort=typeClassOfCar,desc&search=${param['search']}">TypeClassOfCar desc</a></td>
  </tr>
  <tr>
  	<td><a href="?page=1&size=${page.size}&sort=price&search=${param['search']}">Price asc</a></td>
  	<td><a href="?page=1&size=${page.size}&sort=price,desc&search=${param['search']}">Price desc</a></td>
  </tr>
</table>
<div class="col-md-12 text-center">
		<custom:pageable page="${page}" cell="<li></li>" container="<ul class='pagination'></ul>"/>
</div>
</body>
</html>
