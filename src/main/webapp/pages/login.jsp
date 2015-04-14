<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Login</title>
<link href="<c:url value="/res/css/login.css"/>" rel="stylesheet" type="text/css" />
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-sm-6 col-md-4 col-md-offset-4">
				<div class="account-wall">
					<c:if test="${!authentication}">
						<img class="profile-img" src="<c:url value="/res/assets/fail.png"/>">
					</c:if>
					<c:if test="${authentication}">
						<img class="profile-img" src="<c:url value="/res/assets/logo.png"/>">
					</c:if>
					<form class="form-signin" method="post" action="<c:url value="/login"/>">
						<input type="text" class="form-control" placeholder="Username" name="username" required autofocus />
						<input type="password" class="form-control" placeholder="Password" name="password" required />
						<button class="btn btn-lg btn-primary btn-block" type="submit">Login</button>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>