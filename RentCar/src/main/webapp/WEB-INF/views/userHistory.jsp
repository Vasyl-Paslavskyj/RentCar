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
								<li class="active"><a href="/user/history">History</a><span
										class="sr-only">(current)</span></li>
								<li><a href="/user/custom">Custom</a></li>
								<li><a href="/user/accident">Accident</a></li>
							</ul>
						</div>
					</div>
				</nav>
</div>

<div class="row-fluid">
	<div class="col-md-2 col-xs-12">
		<div class="">
			<a href="/user/history/deleteAllFilters<custom:allParams/>">Delete all filters</a>
		</div>
		<h3>Filter</h3>
		<form:form action="/user/history" class="form-inline" method="get" modelAttribute="filter">
				<custom:hiddenInputs excludeParams="min, max, accidentSet, id, car, modelOfCar, classOfCar, typeClassOfCar"/>				
				<div class="form-group">
					<h4>LocalDateTime Start or Finish Rent</h4>
				</div>
				<div class="form-group">
					<form:input path="min" placeholder="min LocalDateTime" class="form-control"/>
					<form:input path="max" placeholder="max LocalDateTime" class="form-control"/>
				</div>
				
				<div class="form-group">
					<h4>TypeClassOfCar</h4>
				</div>
				<div class="form-group">
					<form:checkboxes items="${typeClassOfCars}" path="typeClassOfCar" />
				</div>
				
				<div class="form-group">
					<h4>AccidentSet</h4>
				</div>
				<div class="form-group">
					<form:select items="${accidentSetIds}" path="id" />
				</div>
				
				<div class="form-group">
					<button type="submit" class="btn btn-primary">Ok</button>
				</div>
		</form:form>
	</div>
	<div class="col-md-10 col-xs-12">
		<div class="row">
			<div class="col-md-3"><h4>Car</h4></div>
			<div class="col-md-2"><h4>DateTimeStart</h4></div>
			<div class="col-md-1"><h4>CityStart</h4></div>
			<div class="col-md-2"><h4>DateTimeFinish</h4></div>
			<div class="col-md-1"><h4>CityFinish</h4></div>
			<div class="col-md-2"><h4>RentalLength</h4></div>
			<div class="col-md-1"><h4>Cost</h4></div>
		</div>
		<c:forEach items="${page.content}" var="history">
			<div class="row">
				<div class="col-md-3">${history.car.modelOfCar.classOfCar.typeClassOfCar}-${history.car.modelOfCar.typeModelCar}-${history.car.registrationNamber}</div>
				<div class="col-md-2">${history.dateTimeStart}</div>
				<div class="col-md-1">${history.cityStart}</div>
				<div class="col-md-2">${history.dateTimeFinish}</div>
				<div class="col-md-1">${history.cityFinish}</div>
				<div class="col-md-2">${history.rentalLength}</div>
				<div class="col-md-1">${history.cost}</div>
			</div>
		</c:forEach>
		<div class="col-md-12 text-center">
			<custom:pageable page="${page}" cell="<li></li>" container="<ul class='pagination'></ul>" />
		</div>
	</div>
</div>