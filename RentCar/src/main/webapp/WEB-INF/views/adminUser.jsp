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
								<li class="active"><a href="/admin/user">User</a><span
										class="sr-only">(current)</span></li>
								<li><a href="/admin/custom">Custom</a></li>
								<li><a href="/admin/accident">Accident</a></li>
							</ul>
						</div>
					</div>
				</nav>
</div>
<div class="row-fluid">
	<div class="col-md-2 col-xs-12">
		<h5>Search by User login</h5>
		<form:form action="/admin/user" class="form-inline" method="get" modelAttribute="filter">
			<custom:hiddenInputs excludeParams="search"/>
				<div class="form-group">
					<form:input path="search" placeholder="search by User login" class="form-control"/>
					<button type="submit" class="btn btn-primary">Ok</button>
				</div>
		</form:form>
	</div>
	<div class="col-md-8 col-xs-12">
		<h4>Add user</h4>
		<form:form action="/admin/user" method="post" modelAttribute="user" class="form-inline">
			<form:errors path="*" />
			<form:hidden path="id" />
			<custom:hiddenInputs excludeParams="login, password, fullName, drivingExperience, email, id"/>
			<div class="form-group">
				<label for="login"><form:errors path="login" /></label>
				<form:input path="login" id="login" class="form-control" placeholder="login"/>
				<label for="password"><form:errors path="password" /></label>
				<form:input path="password" id="password" class="form-control" placeholder="password"/>
				<label for="fullName"><form:errors path="fullName" /></label>
				<form:input path="fullName" id="fullName" class="form-control" placeholder="fullName"/>
				<label for="drivingExperience"><form:errors path="drivingExperience" /></label>
				<form:input path="drivingExperience" id="drivingExperience" class="form-control" placeholder="drivingExperience"/>
				<label for="email"><form:errors path="email" /></label>
				<form:input path="email" id="email" class="form-control" placeholder="email"/>
				<button type="submit" class="btn btn-primary">Create</button>
			</div>
		</form:form>
		<div class="row">
			<div class="col-md-2"><h5>Login</h5></div>
<%--			<div class="col-md-3"><h5>Password</h5></div>--%>
			<div class="col-md-3"><h5>FullName</h5></div>
			<div class="col-md-2"><h5>Driving <br> experience</h5></div>
			<div class="col-md-3"><h5>Email</h5></div>
			<div class="col-md-1"><h5>Delete</h5></div>
			<div class="col-md-1"><h5>Update</h5></div>
		</div>
		<c:forEach items="${page.content}" var="user">
			<div class="row">
				<div class="col-md-2">${user.login}</div>
<%--				<div class="col-md-3">${user.password}</div>--%>
				<div class="col-md-3">${user.fullName}</div>
				<div class="col-md-2">${user.drivingExperience}</div>
				<div class="col-md-3">${user.email}</div>
				<div class="col-md-1"><a href="/admin/user/delete/${user.id}<custom:allParams/>">delete</a></div>
				<div class="col-md-1"><a href="/admin/user/update/${user.id}<custom:allParams/>">update</a></div>
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
						<custom:sort innerHtml="Login asc" paramValue="login"/>
						<custom:sort innerHtml="Login desc" paramValue="login,desc"/>
						<custom:sort innerHtml="FullName asc" paramValue="fullName"/>
						<custom:sort innerHtml="FullName desc" paramValue="fullName,desc"/>
						<custom:sort innerHtml="DrivingExperience asc" paramValue="drivingExperience"/>
						<custom:sort innerHtml="DrivingExperience desc" paramValue="drivingExperience,desc"/>
					</ul>
				</div>
			</div>
			<div class="col-md-6">
				<custom:size posibleSizes="1,3,5,10" size="${page.size}" title="Розмір сторінки"/>
			</div>
	</div>
</div>