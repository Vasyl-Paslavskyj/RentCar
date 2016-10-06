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
								<li><a href="/user/history">History</a></li>
								<li><a href="/user/custom">Custom</a></li>
								<li class="active"><a href="/user/accident">Accident</a><span
										class="sr-only">(current)</span></li>
							</ul>
						</div>
					</div>
				</nav>
</div>

<div class="rpw-fluid">
	<div class="col-md-3 col-xs-12">
		<div class="">
			<a href="/user/accident/deleteAllFilters<custom:allParams/>">Delete all filters</a>
		</div>
		
		<h3>Filter</h3>
		<form:form action="/user/accident" class="form-inline" method="get" modelAttribute="filter">
			<custom:hiddenInputs excludeParams="search, min, max, customSet, car, modelOfCar, classOfCar, typeClassOfCar"/>
				<div class="form-group">
					<h4>Search by Car registrationNamber</h4>
				</div>
				<div class="form-group">
					<form:input path="search" placeholder="search by Car registrationNamber" class="form-control"/>
				</div>
				<br>
				<div class="form-group">
					<h4>LocalDateTime Accident</h4>
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
					<button type="submit" class="btn btn-primary">Ok</button>
				</div>
		</form:form>
	</div>
	<div class="col-md-7 col-xs-12">
		<h3>Fill in the form of accident</h3>
		<form:form action="/user/accident" method="post" modelAttribute="accident" class="form-inline">
			<form:errors path="*" />
			<form:hidden path="id" />
			<custom:hiddenInputs excludeParams="dateTime, damage, wastage, customSet, id"/>
			<div class="form-group">
				<label for="dateTime"><form:errors path="dateTime" /></label>
				<form:input path="dateTime" id="dateTime" class="form-control" placeholder="DateTime"/>
				<label for="damage"><form:errors path="damage"/></label>
				<form:input path="damage" id="damage" class="form-control" placeholder="Damage" />
				<label for="wastage"><form:errors path="wastage"/></label>
				<form:input path="wastage" id="wastage" class="form-control" placeholder="Wastage" />
				<label for="customSet"><form:errors path="customSet"/></label>
				<form:select path="customSet">
					<c:forEach items="${customs}" var="custom">
						<form:option value="${custom.id}">
							${custom.id} (${custom.user.fullName} - (${custom.car.modelOfCar.classOfCar.typeClassOfCar} 
							${custom.car.modelOfCar.typeModelCar} ${custom.car.registrationNamber}) - 
							${custom.dateTimeStart} - (${custom.cityStart}-${custom.cityFinish}))
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
			<div class="col-md-4"><h5>Custom Set</h5></div>
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
    					${custom.id} (${custom.user.fullName} - (${custom.car.modelOfCar.classOfCar.typeClassOfCar} 
							${custom.car.modelOfCar.typeModelCar} ${custom.car.registrationNamber}) - 
							${custom.dateTimeStart} - (${custom.cityStart}-${custom.cityFinish}))
    				</c:forEach>
				</div>
				<div class="col-md-1"><a href="/user/accident/delete/${accident.id}<custom:allParams/>">delete</a></div>
				<div class="col-md-1"><a href="/user/accident/update/${accident.id}<custom:allParams/>">update</a></div>
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
						<custom:sort innerHtml="Wastage asc" paramValue="wastage"/>
						<custom:sort innerHtml="Wastage desc" paramValue="wastage,desc"/>
						<custom:sort innerHtml="TypeClassOfCar asc" paramValue="customSet.car.modelOfCar.classOfCar.typeClassOfCar"/>
						<custom:sort innerHtml="TypeClassOfCar desc" paramValue="customSet.car.modelOfCar.classOfCar.typeClassOfCar,desc"/>						
					</ul>
				</div>
			</div>
			<div class="col-md-6">
				<custom:size posibleSizes="1,2,5,10" size="${page.size}" title="Size"/>
			</div>
	</div>
</div>