<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
 
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>
</head>
<body>

<h1>Hi, ${user_id}</h1>
<br/>
<h1>${err_message}</h1>

<c:url var="logoUrl" value="/resources/openidlogosmall.png" />
<p><img src="${logoUrl}"></img>
<c:url var="openIDLoginUrl" value="/j_spring_openid_security_check" />
<form action="${openIDLoginUrl}" method="post" >
	<label for="openid_identifier">Login via AppDirect</label>
	<input id="openid_identifier" type="hidden" name="openid_identifier" value="${openid_url}" />
	<input  type="submit" value="Login"/>								
</form>

<hr/>
</p>
<c:url var="googleLogoUrl" value="/resources/google-logo.png" />
<c:url var="openIDLoginUrl" value="/j_spring_openid_security_check" />
<img src="${googleLogoUrl}"></img>
<form action="${openIDLoginUrl}" method="post">
	   Login via Google
	  <input name="openid_identifier" type="hidden" value="https://www.google.com/accounts/o8/id"/>
	  <input type="submit" value="Login"/>
</form>

</body>
</html>