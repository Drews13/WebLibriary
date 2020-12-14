<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
<title>Student Main Page</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<body>
	<fmt:setLocale value='<%=request.getSession().getAttribute("lang")%>'/>
	<fmt:setBundle basename="resourses.lang" var="loc"/>
	<fmt:message bundle="${loc}" key="lang.student_main.title" var="title"/>
	<fmt:message bundle="${loc}" key="lang.nav.reader_orders" var="reader_orders"/>
	<fmt:message bundle="${loc}" key="lang.nav.books" var="books"/>
	<fmt:message bundle="${loc}" key="lang.nav.profile" var="profile"/>
	<fmt:message bundle="${loc}" key="lang.nav.logout" var="logout"/>
	<fmt:message bundle="${loc}" key="lang.column.header.name" var="book_name"/>
	<fmt:message bundle="${loc}" key="lang.column.header.author" var="author"/>
	<fmt:message bundle="${loc}" key="lang.student_main.href.close_order" var="href_close"/>
	<header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: #318CE7">
			<div>
				<a href="https://www.javaguides.net" class="navbar-brand"><c:out value="${title}"/></a>
			</div>
			
			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/reader_orders"
					class="nav-link"><c:out value="${reader_orders}"/></a></li>
				<li>
    				<a href="<%=request.getContextPath()%>/books" class="nav-link"><c:out value="${books}"/></a>
				</li>
				<li><a href="<%=request.getContextPath()%>/profile"
					class="nav-link"><c:out value="${profile}"/></a></li>
				<li><a href="<%=request.getContextPath()%>/logout"
					class="nav-link"><c:out value="${logout}"/></a></li>
			</ul>
			<div>
				<form method="post" action="change_lang">
					<input type="hidden" name="lang" value="ru" />
					<input type="submit" value="RU" /></input>
				</form>
				<form method="post" action="change_lang">
					<input type="hidden" name="lang" value="en" />
					<input type="submit" value="EN" /></input>
				</form>
			</div>
		</nav>
	</header>
	<br>
	<div class="row">

		<div class="container">
			<h3 class="text-center"><c:out value="${reader_orders}"/></h3>
			<hr>
			<br>
			<table class="table table-bordered">
				<thead>
					<tr>
						<th><c:out value="${name}"/></th>
						<th><c:out value="${author}"/></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="order" items="${orders}">

						<tr>
							<td><c:out value="${order.book_name}" /></td>
							<td><c:out value="${order.book_author}" /></td>						
							<td><a href="close_order?id=<c:out value='${order.id}' />"><c:out value="${href_close}"/></a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>