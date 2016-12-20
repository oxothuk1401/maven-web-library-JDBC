<%@ page language="java" contentType="text/html; charset=utf-8"
		 pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="ctg" uri="custom-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<link rel="stylesheet" type="text/css" href="style/style.css" />
	<link rel="stylesheet" type="text/css" href="style/bootstrap.css" />
	<fmt:setLocale value="${sessionScope.locale}" />
	<fmt:setBundle var="loc" basename="localization.locale" />
	<fmt:message var="bookList" bundle="${loc}" key="locale.title.book" />
	<title>${bookList }</title>
</head>
<body class="body2">
<%@ include file="include/header.jsp"%>
<div class="content">
	<table align="center"></table>
	<ctg:show-all-books bookList="${sessionScope.bookList }"/>
</div>
<%@ include file="include/footer.jsp"%>

</body>
</html>