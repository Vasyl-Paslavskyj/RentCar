<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>

<nav class="navbar navbar-default navbar-fixed-top">
  <div class="container">
    <div class="navbar-header">
      <a class="navbar-brand" href="/"><img class="img-thumbnail" width="80" src="/resources/image/maxresdefault.jpg?version=1" /></a>
    </div>
    <ul class="nav navbar-nav">
<!--       	<li><a>Home</a></li> -->
<!--       	<li><a>Page 1</a></li> -->
<!--       	<li><a>Page 2</a></li> -->
    </ul>
    <ul class="nav navbar-nav navbar-right">
    	<li><a>${authUser.login}</a></li>
    <security:authorize access="isAuthenticated()">
			<li>
				<form:form action="/logout" method="post"
					class="navbar-form navbar-right">
					<button type="submit" class="btn btn-default">Logout</button>
				</form:form>
			</li>
		</security:authorize>
		<security:authorize access="!isAuthenticated()">
			<li>
				<form:form action="/login" method="get"
					class="navbar-form navbar-right">
					<button type="submit" class="btn btn-default">Login</button>
				</form:form>
			</li>
	</security:authorize>
    </ul>
  </div>
</nav>
