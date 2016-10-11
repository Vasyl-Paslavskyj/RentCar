<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>

<h1>Hello</h1>
<security:authorize access="isAuthenticated() and hasRole('ROLE_ADMIN')">
<a href="/admin">Admin panel</a>
</security:authorize>
<security:authorize access="isAuthenticated() and hasRole('ROLE_USER')">
<a href="/user">User panel</a>
</security:authorize>
