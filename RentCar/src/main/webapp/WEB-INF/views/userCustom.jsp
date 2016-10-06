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
								<li class="active"><a href="/user/custom">Custom</a><span
										class="sr-only">(current)</span></li>
								<li><a href="/user/accident">Accident</a></li>
							</ul>
						</div>
					</div>
				</nav>
</div>

<div class="row-fluid">
	<div class="col-md-4 col-xs-12">
		<div class="">
			<a href="/user/custom/deleteAllFilters<custom:allParams/>">Delete all filters</a>
		</div>
		
		<h3>Filter</h3>
		<form:form action="/user/custom" class="form-inline" method="get" modelAttribute="filter">
				<custom:hiddenInputs excludeParams="search, minCost, maxCost, min, max, typeClassOfCar, car, modelOfCar, classOfCar"/>
				
				<div class="form-group">
					<h4>Search by Car registrationNamber</h4>
				</div>
				<div class="form-group">
					<form:input path="search" placeholder="search by Car registrationNamber" class="form-control"/>
				</div>
				<br>			
				<div class="form-group">
					<h4>Cost</h4>
				</div>
				<div class="form-group">
					<form:input path="minCost" placeholder="min Cost" class="form-control"/>
					<form:input path="maxCost" placeholder="max Cost" class="form-control"/>
				</div>
				
				<div class="form-group">
					<h4>LocalDateTime Start Rent</h4>
				</div>
				<div class="form-group">
					<form:input path="min" placeholder="min LocalDateTime Start" class="form-control"/>
					<form:input path="max" placeholder="max LocalDateTime Start" class="form-control"/>
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
	<div class="col-md-5 col-xs-12">
	</div>
	<div class="col-md-3 col-xs-12">
			<div class="col-md-6">
				<div class="dropdown">
					<button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Sort <span class="caret"></span>
					</button>
					<ul class="dropdown-menu">
						<custom:sort innerHtml="DateTimeStart asc" paramValue="dateTimeStart"/>
						<custom:sort innerHtml="DateTimeStart desc" paramValue="dateTimeStart,desc"/>
						<custom:sort innerHtml="CityStart asc" paramValue="cityStart"/>
						<custom:sort innerHtml="CityStart desc" paramValue="cityStart,desc"/>
						<custom:sort innerHtml="ClassOfCar typeClassOfCar asc" paramValue="car.modelOfCar.classOfCar.typeClassOfCar"/>
						<custom:sort innerHtml="ClassOfCar typeClassOfCar desc" paramValue="car.modelOfCar.classOfCar.typeClassOfCar,desc"/>
						<custom:sort innerHtml="RentalLength asc" paramValue="rentalLength"/>
						<custom:sort innerHtml="RentalLength desc" paramValue="rentalLength,desc"/>
						<custom:sort innerHtml="Cost asc" paramValue="cost"/>
						<custom:sort innerHtml="Cost desc" paramValue="cost,desc"/>
					</ul>
				</div>
			</div>
			<div class="col-md-6">
				<custom:size posibleSizes="1,2,5,10" size="${page.size}" title="Size"/>
			</div>
	</div>
</div>
<div class="rpw-fluid">
	<div class="col-md-12 col-xs-12">
	<h3>Create custom</h3>
		<form:form action="/user/custom" method="post" modelAttribute="custom" class="form-inline">
			<form:errors path="*" />
			<form:hidden path="id" />
			<custom:hiddenInputs excludeParams="dateTimeStart, cityStart, dateTimeFinish, cityFinish, car, rentalLength, cost, id"/>
			<div class="form-group">
								
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
		
		<div class="row">
			<div class="col-md-2"><h4>Car</h4></div>
			<div class="col-md-2"><h4>DateTimeStart</h4></div>
			<div class="col-md-1"><h4>CityStart</h4></div>
			<div class="col-md-2"><h4>DateTimeFinish</h4></div>
			<div class="col-md-1"><h4>CityFinish</h4></div>
			<div class="col-md-1"><h4>RentalLength</h4></div>
			<div class="col-md-1"><h4>Cost</h4></div>
			<div class="col-md-1"><h4>Delete</h4></div>
			<div class="col-md-1"><h4>Update</h4></div>
		</div>
		<c:forEach items="${page.content}" var="custom">
			<div class="row">
				<div class="col-md-2">${custom.car.modelOfCar.classOfCar.typeClassOfCar}-${custom.car.modelOfCar.typeModelCar}-${custom.car.registrationNamber}</div>
				<div class="col-md-2">${custom.dateTimeStart}</div>
				<div class="col-md-1">${custom.cityStart}</div>
				<div class="col-md-2">${custom.dateTimeFinish}</div>
				<div class="col-md-1">${custom.cityFinish}</div>
				<div class="col-md-1">${custom.rentalLength}</div>
				<div class="col-md-1">${custom.cost}</div>
				<div class="col-md-1"><a href="/user/custom/delete/${custom.id}<custom:allParams/>">delete</a></div>
				<div class="col-md-1"><a href="/user/custom/update/${custom.id}<custom:allParams/>">update</a></div>
			</div>
		</c:forEach>
		<div class="col-md-12 text-center">
			<custom:pageable page="${page}" cell="<li></li>" container="<ul class='pagination'></ul>" />
		</div>
	</div>
</div>