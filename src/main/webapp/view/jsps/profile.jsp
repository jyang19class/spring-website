<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h2>Hello ${userAttributes.get("name")}</h2>
	
	<h4>My Articles:</h4>
	<ul>
		<c:forEach items="${myArticles}" var="a">
			<c:url var="deleteArticle" value="/delete">
					<c:param name="articleid" value="${a.id}"></c:param>
			</c:url>
			
			<li>			
				<a href="${a.url}">${a.title}</a>
				<a href="${deleteArticle}">Remove Article</a>
			</li>
		</c:forEach>
	
	</ul>
</body>
</html>