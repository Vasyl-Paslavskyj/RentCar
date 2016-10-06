<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="container">
	<div class="row">
	<c:if test="${param.fail}">
		<div class="col-md-12 col-xs-12">
			<p style="color: red;">Fail</p>
		</div>
	</c:if>
		<form:form action="/registration" class="form-group" method="post" modelAttribute="user">
				<form:errors path="*"/>
				<div class="form-group">
					<label for="login"><form:errors path="login" /></label>
					<input name="login" placeholder="Login" class="form-control" />
					<label for="password"><form:errors path="password" /></label>
					<input name="password" type="password" placeholder="Some like ***" class="form-control" />
					<label for="email"><form:errors path="email" /></label>
					<input name="email" placeholder="E-mail" class="form-control" />
					<label for="fullName"><form:errors path="fullName" /></label>
					<input name="fullName" placeholder="Full Name" class="form-control" />
					<label for="drivingExperience"><form:errors path="drivingExperience" /></label>
					<input name="drivingExperience" placeholder="Driving Experience" class="form-control" />
					<button type="submit" class="btn btn-primary">Ok</button>
				</div>
		</form:form>
	</div>
</div>