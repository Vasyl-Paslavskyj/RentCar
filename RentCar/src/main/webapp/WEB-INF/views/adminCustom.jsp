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
								<li class="active"><a href="/admin/custom">Custom</a><span
										class="sr-only">(current)</span></li>
								<li><a href="/admin/accident">Accident</a></li>
							</ul>
						</div>
					</div>
				</nav>
</div>
<div class="row-fluid">
	<div class="col-md-2 col-xs-12">
		<h5>Search by User login or Car registrationNamber</h5>
		<form:form action="/admin/custom" class="form-inline" method="get" modelAttribute="filter">
			<custom:hiddenInputs excludeParams="search1"/>
				<div class="form-group">
					<form:input path="search1" placeholder="search by User login OR Car registrationNamber" class="form-control"/>
					<button type="submit" class="btn btn-primary">Ok</button>
				</div>
		</form:form>
	</div>
	<div class="col-md-8 col-xs-12">
	<h4>Create custom</h4>
		<form:form action="/admin/custom" method="post" modelAttribute="custom" class="form-inline">
			<form:errors path="*" />
			<form:hidden path="id" />
			<custom:hiddenInputs excludeParams="dateTimeStart, cityStart, dateTimeFinish, cityFinish, user, car, rentalLength, cost, id"/>
			<div class="form-group">
				<label for="user"><form:errors path="user"/></label>		
				<form:select path="user">
						<option value="0" selected="selected"></option>	
						<c:forEach items="${users}" var="user">
							<c:choose>
								<c:when test="${user.id eq custom.user.id}">
									<option value="${user.id}" selected="selected">
									${user.fullName}(${user.login}) - ${user.drivingExperience}
									</option>
								</c:when>
								<c:otherwise>
									<option value="${user.id}">
									${user.fullName}(${user.login}) - ${user.drivingExperience}
									</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
				</form:select>
				
				<label for="car"><form:errors path="car"/></label>		
				<form:select path="car">
						<option value="0" selected="selected"></option>	
						<c:forEach items="${cars}" var="car">
							<c:choose>
								<c:when test="${car.id eq custom.car.id}">
									<option value="${car.id}" selected="selected">
									${car.modelOfCar.classOfCar.typeClassOfCar} ${car.modelOfCar.typeModelCar} (${car.modelOfCar.classOfCar.price}) - ${car.registrationNamber}
									</option>
								</c:when>
								<c:otherwise>
									<option value="${car.id}">
									${car.modelOfCar.classOfCar.typeClassOfCar} ${car.modelOfCar.typeModelCar} (${car.modelOfCar.classOfCar.price}) - ${car.registrationNamber}
									</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
				</form:select>
				
				<label for="dateTimeStart"><form:errors path="dateTimeStart" /></label>
				<form:input path="dateTimeStart" id="dateTimeStart" class="form-control" placeholder="DateTimeStart"/>
				
				<label for="cityStart"><form:errors path="cityStart"/></label>	
				<form:select path="cityStart">
					<option value="0" selected="selected"></option>	
					<c:forEach items="${cities}" var="cityStart">
						<form:option value="${cityStart}"></form:option>
					</c:forEach>
				</form:select>
				
				<label for="dateTimeFinish"><form:errors path="dateTimeFinish" /></label>
				<form:input path="dateTimeFinish" id="dateTimeFinish" class="form-control" placeholder="DateTimeFinish"/>
				
				<label for="cityFinish"><form:errors path="cityFinish"/></label>	
				<form:select path="cityFinish">
					<option value="0" selected="selected"></option>	
					<c:forEach items="${cities}" var="cityFinish">
						<form:option value="${cityFinish}"></form:option>
					</c:forEach>
				</form:select>
				
				<label for="rentalLength"><form:errors path="rentalLength"/></label>
				<form:input path="rentalLength" id="rentalLength" class="form-control" placeholder="RentalLength" />
				
				<label for="cost"><form:errors path="cost"/></label>
				<form:input path="cost" id="cost" class="form-control" placeholder="Cost" />
				
				<button type="submit" class="btn btn-primary">Create</button>
			</div>
		</form:form>
	</div>
	<div class="col-md-2 col-xs-12">
			<div class="col-md-6">
				<div class="dropdown">
					<button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Sort <span class="caret"></span>
					</button>
					<ul class="dropdown-menu">
						<custom:sort innerHtml="DateTimeStart asc" paramValue="dateTimeStart"/>
						<custom:sort innerHtml="DateTimeStart desc" paramValue="dateTimeStart,desc"/>
						<custom:sort innerHtml="CityStart asc" paramValue="cityStart"/>
						<custom:sort innerHtml="CityStart desc" paramValue="cityStart,desc"/>
						<custom:sort innerHtml="DateTimeFinish asc" paramValue="dateTimeFinish"/>
						<custom:sort innerHtml="DateTimeFinish desc" paramValue="dateTimeFinish,desc"/>
						<custom:sort innerHtml="CityFinish asc" paramValue="cityFinish"/>
						<custom:sort innerHtml="CityFinish desc" paramValue="cityFinish,desc"/>
						<custom:sort innerHtml="User login asc" paramValue="user.login"/>
						<custom:sort innerHtml="User login desc" paramValue="user.login,desc"/>
						<custom:sort innerHtml="ClassOfCar price asc" paramValue="car.modelOfCar.classOfCar.price"/>
						<custom:sort innerHtml="ClassOfCar price desc" paramValue="car.modelOfCar.classOfCar.price,desc"/>
						<custom:sort innerHtml="RentalLength asc" paramValue="rentalLength"/>
						<custom:sort innerHtml="RentalLength desc" paramValue="rentalLength,desc"/>
						<custom:sort innerHtml="Cost asc" paramValue="cost"/>
						<custom:sort innerHtml="Cost desc" paramValue="cost,desc"/>
					</ul>
				</div>
			</div>
			<div class="col-md-6">
				<custom:size posibleSizes="1,2,5,10" size="${page.size}" title="Розмір сторінки"/>
			</div>
	</div>
</div>
<div class="row-fluid">
	<div class="col-md-12 col-xs-12">
		<div class="row">
			<div class="col-md-2"><h5>User</h5></div>
			<div class="col-md-2"><h5>Car</h5></div>
			<div class="col-md-1"><h5>DateTimeStart</h5></div>
			<div class="col-md-1"><h5>CityStart</h5></div>
			<div class="col-md-1"><h5>DateTimeFinish</h5></div>
			<div class="col-md-1"><h5>CityFinish</h5></div>
			<div class="col-md-1"><h5>RentalLength</h5></div>
			<div class="col-md-1"><h5>Cost</h5></div>
			<div class="col-md-1"><h5>Delete</h5></div>
			<div class="col-md-1"><h5>Update</h5></div>
		</div>
		<c:forEach items="${page.content}" var="custom">
			<div class="row">
				<div class="col-md-2">${custom.user.fullName}</div>
				<div class="col-md-2">${custom.car.modelOfCar.classOfCar.typeClassOfCar}-${custom.car.modelOfCar.typeModelCar}-${custom.car.registrationNamber}</div>
				<div class="col-md-1">${custom.dateTimeStart}</div>
				<div class="col-md-1">${custom.cityStart}</div>
				<div class="col-md-1">${custom.dateTimeFinish}</div>
				<div class="col-md-1">${custom.cityFinish}</div>
				<div class="col-md-1">${custom.rentalLength}</div>
				<div class="col-md-1">${custom.cost}</div>
				<div class="col-md-1"><a href="/admin/custom/delete/${custom.id}<custom:allParams/>">delete</a></div>
				<div class="col-md-1"><a href="/admin/custom/update/${custom.id}<custom:allParams/>">update</a></div>
			</div>
		</c:forEach>
		<div class="col-md-12 text-center">
			<custom:pageable page="${page}" cell="<li></li>" container="<ul class='pagination'></ul>" />
		</div>
	</div>
</div>

<%--<table>
<tr>
  <th>User</th>
  <th>Car</th>
  <th>DateTimeStart</th>
  <th>CityStart</th>
  <th>DateTimeFinish</th>
  <th>CityFinish</th>
  <th>RentalLength</th>
  <th>Cost</th>
</tr>
<c:forEach items="${page.content}" var="custom">
  <tr>
    <td>${custom.user.fullName} (${custom.user.drivingExperience})</td>
    <td>${custom.car.modelOfCar.classOfCar.typeClassOfCar}-${custom.car.modelOfCar.typeModelCar}-${custom.car.registrationNamber}</td>
    <td>${custom.dateTimeStart}</td>
    <td>${custom.cityStart}</td>
    <td>${custom.dateTimeFinish}</td>
    <td>${custom.cityFinish}</td>
    <td>${custom.rentalLength}</td>
    <td>${custom.cost}</td>
    <td><a href="/admin/custom/update/${custom.id}?page=${page.number+1}&size=${page.size}&sort=${param['sort']}&search=${param['search']}">update</a></td>
    <td><a href="/admin/custom/delete/${custom.id}?page=${page.number+1}&size=${page.size}&sort=${param['sort']}&search=${param['search']}">delete</a></td>
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
  	<td><a href="?page=1&size=4&sort=${param['sort']}&search=${param['search']}">4</a></td>
  	<td><a href="?page=1&size=8&sort=${param['sort']}&search=${param['search']}">8</a></td>
  	<td><a href="?page=1&size=20&sort=${param['sort']}&search=${param['search']}">20</a></td>
</tr>
<tr>
  	<td><a href="?page=1&size=${page.size}&sort=dateTimeStart&search=${param['search']}">DateTimeStart asc</a></td>
  	<td><a href="?page=1&size=${page.size}&sort=dateTimeStart,desc&search=${param['search']}">DateTimeStart desc</a></td>
</tr>
<tr>
  	<td><a href="?page=1&size=${page.size}&sort=cityStart&search=${param['search']}">CityStart asc</a></td>
  	<td><a href="?page=1&size=${page.size}&sort=cityStart,desc&search=${param['search']}">CityStart desc</a></td>
</tr>
<tr>
  	<td><a href="?page=1&size=${page.size}&sort=user.login&search=${param['search']}">User asc</a></td>
  	<td><a href="?page=1&size=${page.size}&sort=user.login,desc&search=${param['search']}">User desc</a></td>
</tr>
<tr>
  	<td><a href="?page=1&size=${page.size}&sort=car.modelOfCar.classOfCar.typeClassOfCar&search=${param['search']}">Car asc</a></td>
  	<td><a href="?page=1&size=${page.size}&sort=car.modelOfCar.classOfCar.typeClassOfCar,desc&search=${param['search']}">Car desc</a></td>
</tr>
<tr>
  	<td><a href="?page=1&size=${page.size}&sort=rentalLength&search=${param['search']}">RentalLength asc</a></td>
  	<td><a href="?page=1&size=${page.size}&sort=rentalLength,desc&search=${param['search']}">RentalLength desc</a></td>
</tr>
<tr>
  	<td><a href="?page=1&size=${page.size}&sort=cost&search=${param['search']}">Cost asc</a></td>
  	<td><a href="?page=1&size=${page.size}&sort=cost,desc&search=${param['search']}">Cost desc</a></td>
</tr>
</table>
<div class="col-md-12 text-center">
		<custom:pageable page="${page}" cell="<li></li>" container="<ul class='pagination'></ul>"/>
</div>--%>

<%--<body>
<form:form action="/admin/custom" method="post" modelAttribute="custom">
	<form:errors path="*"/>
	<form:hidden path="id"/>
	<c:forEach items="${param}" var="parameter">
			<c:forEach items="${parameter.value}" var="value">
				<c:if test="${parameter.key ne 'dateTimeStart' and parameter.key ne 'cityStart' 
				and parameter.key ne 'dateTimeFinish' and parameter.key ne 'cityFinish' 
				and parameter.key ne 'user' and parameter.key ne 'car' 
				and parameter.key ne 'rentalLength' and parameter.key ne 'cost' 
				and parameter.key ne 'id'}">
					<input type="hidden" name="${parameter.key}" value="${value}">
				</c:if>
			</c:forEach>
	</c:forEach>
	<table>
		<tr><td><form:errors path="user" cssClass="error"/></td></tr>
		<tr>
			<td>
				<form:select path="user">
					<option value="0" selected="selected"></option>		
					<c:forEach items="${users}" var="user">
						<c:choose>
							<c:when test="${user.id eq custom.user.id}">
								<option value="${user.id}" selected="selected">
									${user.fullName} (${user.drivingExperience})
								</option>
							</c:when>
							<c:otherwise>
								<option value="${user.id}">
									${user.fullName} (${user.drivingExperience})
								</option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</form:select>
			</td>
		</tr>
		<tr><td><form:errors path="car" cssClass="error"/></td></tr>
		<tr>
			<td>
				<form:select path="car">
					<option value="0" selected="selected"></option>
					<c:forEach items="${cars}" var="car">
						<c:choose>		
							<c:when test="${car.id eq custom.car.id}">
								<option value="${car.id}" selected="selected">
									${car.modelOfCar.classOfCar.typeClassOfCar}-${car.modelOfCar.typeModelCar}-${car.registrationNamber}
								</option>
							</c:when>
							<c:otherwise>
								<option value="${car.id}">
									${car.modelOfCar.classOfCar.typeClassOfCar}-${car.modelOfCar.typeModelCar}-${car.registrationNamber}
								</option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</form:select>
			</td>
		</tr>
		<tr><td><form:errors path="dateTimeStart" cssClass="error"/></td></tr>
		<tr><td><form:input path="dateTimeStart" placeholder="LocalDateTime dateTimeStart" /></td></tr>
		<tr><td><form:errors path="cityStart" cssClass="error"/></td></tr>
		<tr>
			<td>
				<form:select path="cityStart">
					<option value="0" selected="selected"></option>
					<c:forEach items="${cities}" var="cityStart">
						<c:choose>
							<c:when test="${cityStart eq form.cityStart}">
								<option value="${cityStart}" selected="selected">${cityStart}</option>
							</c:when>
							<c:otherwise>
								<option value="${cityStart}">${cityStart}</option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</form:select>
			</td>
		</tr>
		<tr><td><form:errors path="dateTimeFinish" cssClass="error"/></td></tr>
		<tr><td><form:input path="dateTimeFinish" placeholder="LocalDateTime dateTimeFinish" /></td></tr>
		<tr><td><form:errors path="cityFinish" cssClass="error"/></td></tr>
		<tr>
			<td>
				<form:select path="cityFinish">
					<option value="0" selected="selected"></option>
					<c:forEach items="${cities}" var="cityFinish">
						<c:choose>
							<c:when test="${cityFinish eq form.cityFinish}">
								<option value="${cityFinish}" selected="selected">${cityFinish}</option>
							</c:when>
							<c:otherwise>
								<option value="${cityFinish}">${cityFinish}</option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</form:select>
			</td>
		</tr>
		<tr><td><form:errors path="rentalLength" cssClass="error"/></td></tr>
		<tr><td><form:input path="rentalLength" placeholder="int rentalLength" /></td></tr>
		<tr><td><form:errors path="cost" cssClass="error"/></td></tr>
		<tr><td><form:input path="cost" placeholder="int cost" /></td><tr>
		</tr>
		<tr>
			<td><input type="submit"></td>
		</tr>
	</table>
</form:form> --%>

<%-- <form:form action="/admin/custom" method="get" modelAttribute="filter">
	<c:forEach items="${param}" var="parameter">
			<c:forEach items="${parameter.value}" var="value">
				<c:if test="${parameter.key ne 'search1'}">
					<input type="hidden" name="${parameter.key}" value="${value}">
				</c:if>
			</c:forEach>
	</c:forEach>
	<table>
			<tr>
				<td><form:input path="search1" placeholder="search"/><input type="submit" value="ok"></td>
			</tr>
	</table>
</form:form>--%>

<!--<form action="/admin/custom" method="post">
	<table>
		<tr>
			<td>
				<select name="userId">
					<c:forEach items="${users}" var="user">
						<option value="${user.id}">
							${user}
						</option>
					</c:forEach>
				</select>
			</td>
			<td>
				<select name="carId">
					<c:forEach items="${cars}" var="car">
						<option value="${car.id}">
							${car}
						</option>
					</c:forEach>
				</select>
			</td>
			<td><input name="dateTimeStart" placeholder="LocalDateTime dateTimeStart"></td>
			<td>
				<select name="cityStart">
					<c:forEach items="${cities}" var="cityStart">
						<option>
							${cityStart}
						</option>
					</c:forEach>
				</select>
			</td>
			<td><input name="dateTimeFinish" placeholder="LocalDateTime dateTimeFinish"></td>
			<td>
				<select name="cityFinish">
					<c:forEach items="${cities}" var="cityFinish">
						<option>
							${cityFinish}
						</option>
					</c:forEach>
				</select>
			</td>
			<td><input name="rentalLength" placeholder="int rentalLength"></td>
			<td><input name="cost" placeholder="int cost"></td>
		</tr>
		<tr>
			<td><input type="submit"></td>
		</tr>
	</table>
</form>-->