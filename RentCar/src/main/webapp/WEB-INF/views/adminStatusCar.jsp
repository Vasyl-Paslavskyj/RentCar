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
								<li class="active"><a href="/admin/statusCar">StatusCar</a><span
										class="sr-only">(current)</span></li>
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
<form:form action="/admin/statusCar" method="post" modelAttribute="statusCar">
	<form:hidden path="id"/>
	<table>
		<tr>
			<td>
				<form:radiobuttons items="${statuss}" path="status"/>
<%-- 				<form:select path="status">
					<c:forEach items="${statuss}" var="status">
						<form:option value="${status}"></form:option>
					</c:forEach>
				</form:select>--%>
			</td>
		</tr>
		<tr>
				<td><input type="submit"></td>
		</tr>
	</table>
</form:form>

<!--<form action="/admin/statusCar" method="post">
		<table>
			<tr>
				<td>
					<select name="status">
						<c:forEach items="${statuss}" var="status">
							<option>
								${status}
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
    <th>Status</th>
  </tr>
  <c:forEach items="${page.content}" var="statusCar">
    <tr>
      <td>${statusCar.status}</td>
      <td><a href="/admin/statusCar/delete/${statusCar.id}?page=${page.number}&size=${page.size}&sort=${param['sort']}">delete</a></td>
      <td><a href="/admin/statusCar/update/${statusCar.id}?page=${page.number}&size=${page.size}&sort=${param['sort']}">update</a></td>
    </tr>
  </c:forEach>
  
  <tr>
  	<c:if test="${!page.isFirst()}">
  		<td>
  			<a href="?page=${page.number}&size=${page.size}&sort=${param['sort']}">Previous</a>
  		</td>
  	</c:if>
  	<c:if test="${!page.isLast()}">
  		<td>
  			<a href="?page=${page.number+2}&size=${page.size}&sort=${param['sort']}">Next</a>
  		</td>
  	</c:if>
  </tr>
  <tr>
  	<td><a href="?page=1&size=1&sort=${param['sort']}">1</a></td>
  	<td><a href="?page=1&size=2&sort=${param['sort']}">2</a></td>
  	<td><a href="?page=1&size=3&sort=${param['sort']}">3</a></td>
  	<td><a href="?page=1&size=5&sort=${param['sort']}">5</a></td>
  </tr>
  <tr>
  	<td><a href="?page=1&size=${page.size}&sort=status">Status asc</a></td>
  	<td><a href="?page=1&size=${page.size}&sort=status,desc">Status desc</a></td>
  </tr>
</table>
<div class="col-md-12 text-center">
		<custom:pageable page="${page}" cell="<li></li>" container="<ul class='pagination'></ul>"/>
</div>
</body>
</html>