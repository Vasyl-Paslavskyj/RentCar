<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/custom.tld" prefix="custom"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

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
		
		<!--<form:form action="/admin/modelOfCar" method="get" modelAttribute="filter">
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
		</form:form>-->
</div>

<div class="col-md-7">
<form:form action="/admin/modelOfCar" method="post" modelAttribute="modelOfCar" class="form-inline" enctype="multipart/form-data">
	<form:errors path="*"/>
	<form:hidden path="id" />
	<form:hidden path="path" />
	<form:hidden path="version" />
	
	<custom:hiddenInputs excludeParams="classOfCar, id, typeModelCar, path, version"/>
		<div class="form-group">
			<!--<form:select path="classOfCar" items="${classOfCars}" itemValue="id">
				<option value="0">ClassOfCar</option>
				${classOfCar.typeClassOfCar} - ${classOfCar.price}
			</form:select>-->
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
	
	<!-- <c:forEach items="${param}" var="parameter">
			<c:forEach items="${parameter.value}" var="value">
				<c:if test="${parameter.key ne 'classOfCar' and parameter.key ne 'typeModelCar' and parameter.key ne 'id'}">
					<input type="hidden" name="${parameter.key}" value="${value}">
				</c:if>
			</c:forEach>
	</c:forEach>
	
	<table>
			<tr>
				<td>
					<form:errors path="classOfCar" cssClass="error"/>
				</td>
			</tr>
			<tr>
				<td>
					<form:select path="classOfCar">
						<option value="0" selected="selected"></option>	
						<c:forEach items="${classOfCars}" var="classOfCar">
							<c:choose>
								<c:when test="${classOfCar.id eq modelOfCar.classOfCar.id}">
									<option value="${classOfCar.id}" selected="selected">${classOfCar.typeClassOfCar}</option>
								</c:when>
								<c:otherwise>
									<option value="${classOfCar.id}">${classOfCar.typeClassOfCar}</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</form:select>
				</td>
			</tr>
			<tr>
				<td>
					<form:errors path="typeModelCar" cssClass="error"/>
				</td>
			</tr>
			<tr>
				<td>
					<form:input path="typeModelCar" placeholder="TypeModelCar"/>
				</td>
			</tr>
			<tr>
				<td><input type="submit"></td>
			</tr>
	</table>-->
</form:form>
</div>

<!-- <form action="/admin/modelOfCar" method="post">
		<table>
			<tr>
				<td>
					<select name="classOfCarId">
						<c:forEach items="${classOfCars}" var="classOfCar">
							<option value="${classOfCar.id}">
								${classOfCar}
							</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td>
					<select name="typeModelCar">
						<c:forEach items="${typeModelCars}" var="typeModelCar">
							<option>
								${typeModelCar}
							</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td><input type="submit"></td>
			</tr>
		</table>
</form>-->

<table>
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
</div>
