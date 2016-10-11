<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/custom.tld" prefix="custom"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<link rel="stylesheet" href="/resources/css/car.css">

<div class="row-fluid">
				<nav class="navbar navbar-default">
					<div class="container-fluid">
						<div class="collapse navbar-collapse" id="">
							<ul class="nav navbar-nav">
								<li><a href="/admin/classOfCar">ClassOfCar</a></li>
								<li><a href="/admin/statusCar">StatusCar</a></li>
								<li class="active"><a href="/admin/modelOfCar">ModelOfCar</a><span
										class="sr-only">(current)</span></li>
								<li><a href="/admin/car">Car</a></li>
								<li><a href="/admin/user">User</a></li>
								<li><a href="/admin/custom">Custom</a></li>
								<li><a href="/admin/accident">Accident</a></li>
							</ul>
						</div>
					</div>
				</nav>
</div>

<div class="row-fluid">

<div class="col-md-3">
		<div class="row">
			<a href="/admin/modelOfCar/deleteAllFilters<custom:allParams/>">Delete all filters</a>
		</div>
		
		<form:form action="/admin/modelOfCar" class="form-inline" method="get" modelAttribute="filter">
				<custom:hiddenInputs excludeParams="search"/>
				<div class="form-group">
 					<form:input path="search" placeholder="search typeClassOfCar" class="form-control" />
					<button type="submit" class="btn btn-danger">Ok</button>
				</div>
		</form:form>
</div>

<div class="col-md-7">
<form:form action="/admin/modelOfCar" method="post" modelAttribute="modelOfCar" class="form-inline" enctype="multipart/form-data">
	<form:errors path="*"/>
	<form:hidden path="id" />
	<form:hidden path="path" />
	<form:hidden path="version" />
	
	<custom:hiddenInputs excludeParams="classOfCar, id, typeModelCar, path, version"/>
		<div class="form-group">
			<label for="classOfCar"><form:errors path="classOfCar"/></label>
			<form:select path="classOfCar">
						<option value="0" selected="selected"></option>	
						<c:forEach items="${classOfCars}" var="classOfCar">
							<c:choose>
								<c:when test="${classOfCar.id eq modelOfCar.classOfCar.id}">
									<option value="${classOfCar.id}" selected="selected">
									${classOfCar.typeClassOfCar} - ${classOfCar.price}
									</option>
								</c:when>
								<c:otherwise>
									<option value="${classOfCar.id}">
									${classOfCar.typeClassOfCar} - ${classOfCar.price}
									</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
			</form:select>
			<label for="typeModelCar"><form:errors path="typeModelCar"/></label>
			<form:input path="typeModelCar" id="typeModelCar" class="form-control" placeholder="TypeModelCar" />
			<label class="btn btn-default btn-file">
        		Browse <input type="file" name="file" style="display: none;">
    		</label>
			<button type="submit" class="btn btn-primary">Create ModelOfCar</button>
		</div>
</form:form>
	<div class="row">
		<div class="col-md-3"><h5>Image</h5></div>
		<div class="col-md-3"><h5>ClassOfCar</h5></div>
		<div class="col-md-2"><h5>TypeModelCar</h5></div>
		<div class="col-md-2"><h5>Delete</h5></div>
		<div class="col-md-2"><h5>Update</h5></div>
	</div>
		<c:forEach items="${page.content}" var="modelOfCar">
			<div class="row">
				<div class="col-md-3"><img class="img-thumbnail" width="100" src="/images/modelOfCar/${modelOfCar.id}${modelOfCar.path}?version=${modelOfCar.version}" /></div>
				<div class="col-md-3">${modelOfCar.classOfCar.typeClassOfCar} - ${modelOfCar.classOfCar.price}</div>
				<div class="col-md-2">${modelOfCar.typeModelCar}</div>
				<div class="col-md-2"><a href="/admin/modelOfCar/delete/${modelOfCar.id}<modelOfCar:allParams/>">delete</a></div>
				<div class="col-md-2"><a href="/admin/modelOfCar/update/${modelOfCar.id}<modelOfCar:allParams/>">update</a></div>
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
						<custom:sort innerHtml="TypeModelCar asc" paramValue="typeModelCar"/>
						<custom:sort innerHtml="TypeModelCar desc" paramValue="typeModelCar,desc"/>
						<custom:sort innerHtml="TypeClassOfCar asc" paramValue="classOfCar.typeClassOfCar"/>
						<custom:sort innerHtml="TypeClassOfCar desc" paramValue="classOfCar.typeClassOfCar,desc"/>
						<custom:sort innerHtml="Price asc" paramValue="classOfCar.price"/>
						<custom:sort innerHtml="Price desc" paramValue="classOfCar.price,desc"/>
					</ul>
				</div>
			</div>
			<div class="col-md-6">
				<custom:size posibleSizes="1,3,5,10" size="${page.size}" title="Розмір сторінки"/>
			</div>
	</div>

<%--<table>
<tr>
	<th>Image</th>
  <th>ClassOfCar</th>
  <th>TypeModelCar</th>
</tr>
<c:forEach items="${page.content}" var="modelOfCar">
  <tr>
  <td><img class="img-thumbnail" width="100" src="/images/modelOfCar/${modelOfCar.id}${modelOfCar.path}?version=${modelOfCar.version}" /></td>
    <td>${modelOfCar.classOfCar.typeClassOfCar} - ${modelOfCar.classOfCar.price}</td>
    <td>${modelOfCar.typeModelCar}</td>
    <td><a href="/admin/modelOfCar/delete/${modelOfCar.id}?page=${page.number+1}&size=${page.size}&sort=${param['sort']}&search=${param['search']}">delete</a></td>
    <td><a href="/admin/modelOfCar/update/${modelOfCar.id}?page=${page.number+1}&size=${page.size}&sort=${param['sort']}&search=${param['search']}">update</a></td>
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
  	<td><a href="?page=1&size=3&sort=${param['sort']}&search=${param['search']}">3</a></td>
  	<td><a href="?page=1&size=10&sort=${param['sort']}&search=${param['search']}">10</a></td>
  	<td><a href="?page=1&size=20&sort=${param['sort']}&search=${param['search']}">20</a></td>
</tr>
<tr>
  	<td><a href="?page=1&size=${page.size}&sort=typeModelCar&search=${param['search']}">TypeModelCar asc</a></td>
  	<td><a href="?page=1&size=${page.size}&sort=typeModelCar,desc&search=${param['search']}">TypeModelCar desc</a></td>
</tr>
<tr>
  	<td><a href="?page=1&size=${page.size}&sort=classOfCar.typeClassOfCar&search=${param['search']}">ClassOfCar asc</a></td>
  	<td><a href="?page=1&size=${page.size}&sort=classOfCar.typeClassOfCar,desc&search=${param['search']}">ClassOfCar desc</a></td>
</tr>
</table>
<div class="col-md-12 text-center">
		<custom:pageable page="${page}" cell="<li></li>" container="<ul class='pagination'></ul>"/>
</div>
</div> --%>
