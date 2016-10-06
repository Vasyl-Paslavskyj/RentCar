<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/custom.tld" prefix="custom"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<link rel="stylesheet" href="/resources/css/car.css">
<script>
$(function() {
	$('select[name=modelOfCar]').chosen();
	$('select[name=statusCar]').chosen();
	$('select[name=typeClassOfCar]').chosen();
	$('select[name=typeModelCar]').chosen();
});
</script>

<div class="row-fluid">
				<nav class="navbar navbar-default">
					<div class="container-fluid">
						<div class="collapse navbar-collapse" id="">
							<ul class="nav navbar-nav">
								<li><a href="/admin/classOfCar">ClassOfCar</a></li>
								<li><a href="/admin/statusCar">StatusCar</a></li>
								<li><a href="/admin/modelOfCar">ModelOfCar</a></li>
								<li class="active"><a href="/admin/car">Car</a><span
										class="sr-only">(current)</span></li>
								<li><a href="/admin/user">User</a></li>
								<li><a href="/admin/custom">Custom</a></li>
								<li><a href="/admin/accident">Accident</a></li>
							</ul>
						</div>
					</div>
				</nav>
</div>
<div class="row-fluid">
	<div class="col-md-3 col-xs-12">
		<div class="">
			<a href="/admin/car/deleteAllFilters<custom:allParams/>">Delete all filters</a>
		</div>
			<h4>Search by TypeClassOfCar</h4>
			<form:form action="/admin/car" class="form-inline" method="get" modelAttribute="filter">
				<custom:hiddenInputs excludeParams="search"/>
				<div class="form-group">
					<form:input path="search" placeholder="search TypeClassOfCar" class="form-control"/>
				</div>
				<div class="form-group">
					<button type="submit" class="btn btn-primary">Ok</button>
				</div>
			</form:form>
	
			<h4>Filter</h4>
			<form:form action="/admin/car" class="form-inline" method="get" modelAttribute="filter">
				<custom:hiddenInputs excludeParams="min, max, typeModelCar, typeClassOfCar, statusCar, classOfCar, modelOfCar"/>
				
				<div class="form-group">
					<h4>Price</h4>
				</div>
				<div class="form-group">
					<form:input path="min" placeholder="min Price" class="form-control"/>
					<form:input path="max" placeholder="max Price" class="form-control"/>
				</div>
				
				<div class="form-group">
					<h4>TypeClassOfCar</h4>
				</div>
				<div class="form-group">
					<form:checkboxes items="${typeClassOfCars}" path="typeClassOfCar" />
				</div>
<%-- 				<div class="form-group">
					<h4>ClassOfCar</h4>
				</div>
				<div class="form-group">
					<form:checkboxes items="${classOfCars}" path="classOfCar" itemLabel="id" itemValue="id" />
				</div>--%>
				
				<div class="form-group">
					<h4>TypeModelCar</h4>
				</div>
				<div class="form-group">
					<form:checkboxes items="${typeModelCars}" path="typeModelCar" />
				</div>
				
<%-- 				<div class="form-group">
					<h4>ModelOfCar</h4>
				</div>
				<div class="form-group">
					<form:checkboxes items="${modelOfCars}" path="modelOfCar" itemLabel="id" itemValue="id" />
				</div>--%>
				
				<div class="form-group">
					<h4>StatusCar</h4>
				</div>
				<div class="form-group">
					<form:checkboxes items="${statusCars}" path="statusCar" itemLabel="status" itemValue="id"/>
				</div>
				
				<div class="form-group">
					<button type="submit" class="btn btn-primary">Ok</button>
				</div>
			</form:form>
		</div>
	<div class="col-md-7 col-xs-12">
	<form:form class="form-inline" action="/admin/car" method="post" modelAttribute="car">
		<form:errors path="*"/>
		<form:hidden path="id" />
		<custom:hiddenInputs excludeParams="modelOfCar, statusCar, registrationNamber, id"/>
					<div class="form-group">
						<label for="registrationNamber"><form:errors path="registrationNamber" /></label>
						<form:input path="registrationNamber" id="registrationNamber" class="form-control" />
						<form:select path="modelOfCar" items="${modelOfCars}" itemLabel="id" itemValue="id">
						</form:select>
						<form:select path="statusCar" items="${statusCars}" itemLabel="status" itemValue="id">
						</form:select>
						<button type="submit" class="btn btn-primary">Create</button>
					</div>
	</form:form>
	<div class="row">
		<div class="col-md-2"><h4>RNamber</h4></div>
		<div class="col-md-2"><h4>ModelOfCar</h4></div>
		<div class="col-md-4"><h4>StatusCar</h4></div>
		<div class="col-md-2"><h4>Delete</h4></div>
		<div class="col-md-2"><h4>Update</h4></div>
	</div>
		<c:forEach items="${page.content}" var="car">
			<div class="row">
				<div class="col-md-2">${car.registrationNamber}</div>
				<div class="col-md-2">${car.modelOfCar.classOfCar.typeClassOfCar}(${car.modelOfCar.classOfCar.price}) - ${car.modelOfCar.typeModelCar}</div>
				<div class="col-md-4">${car.statusCar.status}</div>
				<div class="col-md-2"><a href="/admin/car/delete/${car.id}<custom:allParams/>">delete</a></div>
				<div class="col-md-2"><a href="/admin/car/update/${car.id}<custom:allParams/>">update</a></div>
			</div>
		</c:forEach>
		<div class="col-md-12 text-center">
			<custom:pageable page="${page}" cell="<li></li>" container="<ul class='pagination'></ul>" />
		</div>
	</div>
	<div class="col-md-2 col-xs-12">
			<div class="col-md-6">
				<div class="dropdown">
					<button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Sort <span class="caret"></span>
					</button>
					<ul class="dropdown-menu">
						<custom:sort innerHtml="RegistrationNamber asc" paramValue="registrationNamber"/>
						<custom:sort innerHtml="RegistrationNamber desc" paramValue="registrationNamber,desc"/>
						<custom:sort innerHtml="ClassOfCar typeClassOfCar asc" paramValue="modelOfCar.classOfCar.typeClassOfCar"/>
						<custom:sort innerHtml="ClassOfCar typeClassOfCar desc" paramValue="modelOfCar.classOfCar.typeClassOfCar,desc"/>
						<custom:sort innerHtml="ClassOfCar price asc" paramValue="modelOfCar.classOfCar.price"/>
						<custom:sort innerHtml="ClassOfCar price desc" paramValue="modelOfCar.classOfCar.price,desc"/>
						<custom:sort innerHtml="StatusCar asc" paramValue="statusCar.status"/>
						<custom:sort innerHtml="StatusCar desc" paramValue="statusCar.status,desc"/>
					</ul>
				</div>
			</div>
			<div class="col-md-6">
				<custom:size posibleSizes="1,2,5,10" size="${page.size}" title="Розмір сторінки"/>
			</div>
		</div>
</div>

<!--
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
</head>
<body>
<form:form action="/admin/car" method="post" modelAttribute="car">
	<form:hidden path="id"/>
	<c:forEach items="${param}" var="parameter">
			<c:forEach items="${parameter.value}" var="value">
				<c:if test="${parameter.key ne 'modelOfCar' and parameter.key ne 'statusCar' and parameter.key ne 'id'}">
					<input type="hidden" name="${parameter.key}" value="${value}">
				</c:if>
			</c:forEach>
	</c:forEach>
	
	<table>
		<tr><td><form:errors path="modelOfCar" cssClass="error"/></td></tr>
		<tr>
			<td>
				<form:select path="modelOfCar">
					<option value="0" selected="selected"></option>	
					<c:forEach items="${modelOfCars}" var="modelOfCar">
						<c:choose>		
							<c:when test="${modelOfCar.id eq car.modelOfCar.id}">
								<option value="${modelOfCar.id}" selected="selected">
								${modelOfCar.classOfCar.typeClassOfCar}(${modelOfCar.classOfCar.price}) - ${modelOfCar.typeModelCar}
								</option>
							</c:when>
							<c:otherwise>
								<option value="${modelOfCar.id}">
								${modelOfCar.classOfCar.typeClassOfCar}(${modelOfCar.classOfCar.price}) - ${modelOfCar.typeModelCar}
								</option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</form:select>
			</td>
		</tr>
		<tr><td><form:errors path="statusCar" cssClass="error"/></td></tr>
		<tr>
			<td>
				<form:select path="statusCar">
					<option value="0" selected="selected"></option>
					<c:forEach items="${statusCars}" var="statusCar">
						<c:choose>		
							<c:when test="${statusCar.id eq car.statusCar.id}">
								<option value="${statusCar.id}" selected="selected">${statusCar.status}</option>
							</c:when>
							<c:otherwise>
								<option value="${statusCar.id}">${statusCar.status}</option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</form:select>
			</td>
		</tr>
		<tr><td><form:errors path="registrationNamber" cssClass="error"/></td></tr>
		<tr>
			<td><form:input path="registrationNamber"/></td>
		</tr>
		<tr>
				<td><input type="submit"></td>
		</tr>
	</table>
</form:form>

<form:form action="/admin/car" method="get" modelAttribute="filter">
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

<!--<form action="/admin/car" method="post">
	<table>
		<tr>
			<td>
				<select name="modelOfCarId">
					<c:forEach items="${modelOfCars}" var="modelOfCar">
						<option value="${modelOfCar.id}">
							${modelOfCar}
						</option>
					</c:forEach>
				</select>
			</td>
			<td>
				<select name="statusCarId">
					<c:forEach items="${statusCars}" var="statusCar">
						<option value="${statusCar.id}">
							${statusCar}
						</option>
					</c:forEach>
				</select>
			</td>
			<td><input name="registrationNamber" placeholder="Registration Namber"></td>
		</tr>
		<tr>
			<td><input type="submit"></td>
		</tr>
	</table>
</form>-->
<!--
<table>
  <tr>
    <th>ModelOfCar</th>
    <th>StatusCar</th>
    <th>RegistrationNamber</th>
  </tr>
  <c:forEach items="${page.content}" var="car">
    <tr>
      <td>${car.modelOfCar.classOfCar.typeClassOfCar}(${car.modelOfCar.classOfCar.price}) - ${car.modelOfCar.typeModelCar}</td>
      <td>${car.statusCar.status}</td>
      <td>${car.registrationNamber}</td>
      <td><a href="/admin/car/update/${car.id}?page=${page.number+1}&size=${page.size}&sort=${param['sort']}&search=${param['search']}">update</a></td>
      <td><a href="/admin/car/delete/${car.id}?page=${page.number+1}&size=${page.size}&sort=${param['sort']}&search=${param['search']}">delete</a></td>
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
  	<td><a href="?page=1&size=${page.size}&sort=modelOfCar.classOfCar.typeClassOfCar&search=${param['search']}">ModelOfCar asc</a></td>
  	<td><a href="?page=1&size=${page.size}&sort=modelOfCar.classOfCar.typeClassOfCar,desc&search=${param['search']}">ModelOfCar desc</a></td>
</tr>
<tr>
  	<td><a href="?page=1&size=${page.size}&sort=statusCar.status&search=${param['search']}">StatusCar asc</a></td>
  	<td><a href="?page=1&size=${page.size}&sort=statusCar.status,desc&search=${param['search']}">StatusCar desc</a></td>
</tr>
<tr>
  	<td><a href="?page=1&size=${page.size}&sort=registrationNamber&search=${param['search']}">RegistrationNamber asc</a></td>
  	<td><a href="?page=1&size=${page.size}&sort=registrationNamber,desc&search=${param['search']}">RegistrationNamber desc</a></td>
</tr>
</table>
<div class="col-md-12 text-center">
		<custom:pageable page="${page}" cell="<li></li>" container="<ul class='pagination'></ul>"/>
</div>
</body>
</html>
 -->
