<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
<title>Book catalog</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<body>
	<fmt:setLocale value='<%=request.getSession().getAttribute("lang")%>'/>
	<fmt:setBundle basename = "resourses.lang" var="loc"/>
	<fmt:message bundle="${loc}" key="lang.facultys.title" var="title"/>
	<fmt:message bundle="${loc}" key="lang.nav.your_orders" var="your_orders"/>
	<fmt:message bundle="${loc}" key="lang.nav.all_books" var="all_books"/>
	<fmt:message bundle="${loc}" key="lang.nav.profile" var="profile"/>
	<fmt:message bundle="${loc}" key="lang.nav.logout" var="logout"/>
	<fmt:message bundle="${loc}" key="lang.column.header.name" var="book_name"/>
	<fmt:message bundle="${loc}" key="lang.column.header.author" var="author"/>
	<fmt:message bundle="${loc}" key="lang.facultys.href.order" var="href_order"/>
	<header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: #318CE7">
			<div>
				<a href="https://www.javaguides.net" class="navbar-brand"><c:out value="${title}"/></a>
			</div>
			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/list_reader"
					class="nav-link"><c:out value="${your_orders}"/></a></li>
				<li><a href="<%=request.getContextPath()%>/courses"
					class="nav-link"><c:out value="${books}"/></a></li>
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
			<h3 class="text-center"><c:out value="${books}"/></h3>
			<hr>
			<br>
			<table class="table table-bordered">
				<thead>
					<tr>
						<th><c:out value="${book_name}"/></th>
						<th><c:out value="${author}"/></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="book" items="${books}">
						<tr>
							<td><c:out value="${book.name}" /></td>
							<td><c:out value="${book.author}" /></td>
							<c:if test="${role.equals('STUDENT')}">
								<td><a href="order_book?id=<c:out value='${book.id}' />"><c:out value="${href_order}"/></a></td>
							</c:if>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>