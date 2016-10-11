<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/custom.tld" prefix="custom"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<link rel="stylesheet" href="/resources/css/car.css">
<div class="row-fluid">
				<nav class="navbar navbar-default">
					<div class="container-fluid">
						<div class="collapse navbar-collapse" id="">
							<ul class="nav navbar-nav">
								<li><a href="/admin/classOfCar">ClassOfCar</a></li>
								<li><a href="/admin/statusCar">StatusCar</a></li>
								<li><a href="/admin/modelOfCar">ModelOfCar</a></li>
								<li><a href="/admin/car">Car</a></li>
								<li><a href="/admin/user">User</a></li>
								<li><a href="/admin/custom">Custom</a></li>
								<li class="active"><a href="/admin/accident">Accident</a><span
										class="sr-only">(current)</span></li>
							</ul>
						</div>
					</div>
				</nav>
</div>

<div class="row-fluid">
	<div class="col-md-3 col-xs-12">		
		<h4>Search by Car registrationNamber or User login</h4>
			<form:form action="/admin/accident" class="form-inline" method="get" modelAttribute="filter">
				<custom:hiddenInputs excludeParams="search"/>
				<div class="form-group">
					<form:input path="search" placeholder="search registrationNamber or login" class="form-control"/>
				</div>
				<div class="form-group">
					<button type="submit" class="btn btn-primary">Ok</button>
				</div>
			</form:form>
	</div>
	
	<div class="col-md-7 col-xs-12">
		<form:form class="form-inline" action="/admin/accident" method="post" modelAttribute="accident">
			<form:errors path="*"/>
			<form:hidden path="id" />
			<custom:hiddenInputs excludeParams="dateTime, damage, wastage, customSet, id"/>
			<div class="form-group">
				<label for="dateTime"><form:errors path="dateTime" /></label>
				<form:input path="dateTime" id="dateTime" class="form-control" />
				<label for="damage"><form:errors path="damage" /></label>
				<form:input path="damage" id="damage" class="form-control" />
				<label for="wastage"><form:errors path="wastage" /></label>
				<form:input path="wastage" id="wastage" class="form-control" />
				<label for="customSet"><form:errors path="customSet" /></label>
				<form:select path="customSet">
					<c:forEach items="${customs}" var="custom">
						<form:option value="${custom.id}">
							${custom.id} (${custom.user.fullName} - 
							(${custom.car.modelOfCar.classOfCar.typeClassOfCar}(${custom.car.modelOfCar.classOfCar.price}) 
							${custom.car.modelOfCar.typeModelCar} №${custom.car.registrationNamber})
							- ${custom.dateTimeStart} - ${custom.cityStart})
						</form:option>
					</c:forEach>
				</form:select>
				<button type="submit" class="btn btn-primary">Create</button>
			</div>
		</form:form>
		<div class="row">
			<div class="col-md-2"><h5>DateTime</h5></div>
			<div class="col-md-2"><h5>Damage</h5></div>
			<div class="col-md-1"><h5>Wastage</h5></div>
			<div class="col-md-4"><h5>CustomSet</h5></div>
			<div class="col-md-1"><h5>Delete</h5></div>
			<div class="col-md-1"><h5>Update</h5></div>
		</div>
		<c:forEach items="${page.content}" var="accident">
			<div class="row">
				<div class="col-md-2">${accident.dateTime}</div>
				<div class="col-md-2">${accident.damage}</div>
				<div class="col-md-1">${accident.wastage}</div>
				<div class="col-md-4">
					<c:forEach items="${accident.customSet}" var="custom">
    					${custom.id} (${custom.user.fullName} - Car_RN(${custom.car.registrationNamber}) - 
    					${custom.dateTimeStart} - ${custom.cityStart});
    					<br>
    				</c:forEach>
				</div>
				<div class="col-md-1"><a href="/admin/accident/delete/${accident.id}<custom:allParams/>">delete</a></div>
				<div class="col-md-1"><a href="/admin/accident/update/${accident.id}<custom:allParams/>">update</a></div>
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
						<custom:sort innerHtml="DateTime asc" paramValue="dateTime"/>
						<custom:sort innerHtml="DateTime desc" paramValue="dateTime,desc"/>
						<custom:sort innerHtml="Damage asc" paramValue="damage"/>
						<custom:sort innerHtml="Damage desc" paramValue="damage,desc"/>
						<custom:sort innerHtml="Wastage asc" paramValue="wastage"/>
						<custom:sort innerHtml="Wastage desc" paramValue="wastage,desc"/>
					</ul>
				</div>
			</div>
			<div class="col-md-6">
				<custom:size posibleSizes="1,2,5,10" size="${page.size}" title="Розмір сторінки"/>
			</div>
	</div>
</div>


<%--<form:form action="/admin/accident" method="post" modelAttribute="accident">
	<form:hidden path="id"/>
	<c:forEach items="${param}" var="parameter">
			<c:forEach items="${parameter.value}" var="value">
				<c:if test="${parameter.key ne 'dateTime' and parameter.key ne 'damage' 
				and parameter.key ne 'wastage' and parameter.key ne 'id'}">
					<input type="hidden" name="${parameter.key}" value="${value}">
				</c:if>
			</c:forEach>
	</c:forEach>
	
	<table>
		<tr><td><form:errors path="dateTime" cssClass="error"/></td></tr>
		<tr><td><form:input path="dateTime" placeholder="String dateTimeString" /></td></tr>
		<tr><td><form:errors path="damage" cssClass="error"/></td></tr>
		<tr><td><form:input path="damage" placeholder="String damage" /></td></tr>
		<tr><td><form:errors path="wastage" cssClass="error"/></td></tr>
		<tr><td><form:input path="wastage" placeholder="int wastage" /></td></tr>
		<tr><td><form:errors path="customSet" cssClass="error"/></td></tr>
		<tr>
			<td>
				<form:select path="customSet">
					<c:forEach items="${customs}" var="custom">
						<form:option value="${custom.id}">
							${custom.id} (${custom.user.fullName} - 
							(${custom.car.modelOfCar.classOfCar.typeClassOfCar}(${custom.car.modelOfCar.classOfCar.price}) 
							${custom.car.modelOfCar.typeModelCar} №${custom.car.registrationNamber})
							- ${custom.dateTimeStart} - ${custom.cityStart})
						</form:option>
					</c:forEach>
				</form:select>
			</td>
		</tr>
		<tr>
			<td><input type="submit"></td>
		</tr>
	</table>
</form:form>

<table>
<tr>
  <th>DateTime</th>
  <th>Damage</th>
  <th>Wastage</th>
  <th>Custom Set</th>
</tr>
<c:forEach items="${page.content}" var="accident">
  <tr>
    <td>${accident.dateTime}</td>
    <td>${accident.damage}</td>
    <td>${accident.wastage}</td>
    <td>
    	<c:forEach items="${accident.customSet}" var="custom">
    		${custom.id} (${custom.user.fullName} - Car_RN(${custom.car.registrationNamber}) - ${custom.dateTimeStart} - ${custom.cityStart})
    	</c:forEach>
    </td>
    <td><a href="/admin/accident/delete/${accident.id}?page=${page.number+1}&size=${page.size}&sort=${param['sort']}&search=${param['search']}">delete</a></td>
    <td><a href="/admin/accident/update/${accident.id}?page=${page.number+1}&size=${page.size}&sort=${param['sort']}&search=${param['search']}">update</a></td>
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
  	<td><a href="?page=1&size=2&sort=${param['sort']}&search=${param['search']}">2</a></td>
  	<td><a href="?page=1&size=5&sort=${param['sort']}&search=${param['search']}">5</a></td>
  	<td><a href="?page=1&size=10&sort=${param['sort']}&search=${param['search']}">10</a></td>
</tr>
<tr>
  	<td><a href="?page=1&size=${page.size}&sort=dateTime&search=${param['search']}">DateTime asc</a></td>
  	<td><a href="?page=1&size=${page.size}&sort=dateTime,desc&search=${param['search']}">DateTime desc</a></td>
</tr>
<tr>
  	<td><a href="?page=1&size=${page.size}&sort=wastage&search=${param['search']}">Wastage asc</a></td>
  	<td><a href="?page=1&size=${page.size}&sort=wastage,desc&search=${param['search']}">Wastage desc</a></td>
</tr>
</table>
<div class="col-md-12 text-center">
		<custom:pageable page="${page}" cell="<li></li>" container="<ul class='pagination'></ul>"/>
</div>--%>
