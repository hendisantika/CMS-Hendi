<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title><sitemesh:write property='title' /></title>
<sitemesh:write property='head' />
<link href="<c:url value="/res/css/plugins/bootstrap-datepicker.css"/>" rel="stylesheet" type="text/css" />
<link href="<c:url value="/res/css/plugins/bootstrap-table.min.css"/>" rel="stylesheet" type="text/css" />
<link href="<c:url value="/res/css/plugins/sweet-alert.css"/>" rel="stylesheet" type="text/css" />
<link href="<c:url value="/res/css/master.css"/>" rel="stylesheet" type="text/css" />
</head>
<body>
	<script src="<c:url value="/res/js/plugins/bootstrap-datepicker.js"/>" type="text/javascript"></script>
	<script src="<c:url value="/res/js/plugins/bootstrap-table.min.js"/>" type="text/javascript"></script>
	<script src="<c:url value="/res/js/plugins/jquery.alphanum.js"/>" type="text/javascript"></script>
	<script src="<c:url value="/res/js/plugins/moment.js"/>" type="text/javascript"></script>
	<script src="<c:url value="/res/js/plugins/spin.min.js"/>" type="text/javascript"></script>
	<script src="<c:url value="/res/js/plugins/sweet-alert.min.js"/>" type="text/javascript"></script>
	<script src="<c:url value="/res/js/common.js"/>" type="text/javascript"></script>
	<sitemesh:write property='body' />
</body>
</html>