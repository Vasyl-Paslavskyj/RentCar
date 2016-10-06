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
								<li><a href="/admin/user">User</a></li>
								<li><a href="/admin/custom">Custom</a></li>
								<li class="active"><a href="/admin/accident">Accident</a><span
										class="sr-only">(current)</span></li>
							</ul>
						</div>
					</div>
				</nav>
</div>
<form:form action="/admin/accident" method="post" modelAttribute="accident">
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
							${custom.id} (${custom.user.fullName} - ${custom.car} - ${custom.dateTimeStart} - ${custom.cityStart})
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

<form:form action="/admin/accident" method="get" modelAttribute="filter">
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


<!--<form action="/admin/accidentIfOneCar" method="post">
	<table>
		<tr>
			<td><input name="dateTimeString" placeholder="String dateTimeString"></td>
			<td><input name="damage" placeholder="String damage"></td>
			<td><input name="wastage" placeholder="int wastage"></td>
			<td>
				<select name="customId">
					<c:forEach items="${customs}" var="custom">
						<option value="${custom.id}">
							${custom}
						</option>
					</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<td><input type="submit"></td>
		</tr>
	</table>
</form>
<form action="/admin/accidentIfTwoCars" method="post">
	<table>
		<tr>
			<td><input name="dateTimeString" placeholder="String dateTimeString"></td>
			<td><input name="damage" placeholder="String damage"></td>
			<td><input name="wastage" placeholder="int wastage"></td>
			<td>
				<select name="customFirstId">
					<c:forEach items="${customs}" var="custom">
						<option value="${custom.id}">
							${custom}
						</option>
					</c:forEach>
				</select>
			</td>
			<td>
				<select name="customSecondId">
					<c:forEach items="${customs}" var="custom">
						<option value="${custom.id}">
							${custom}
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
</div>
</body>
</html>
