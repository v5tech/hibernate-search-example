<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Hibernate Search Example</title>
</head>
<body>
	
	共查到[<b><font color='red'>${lists.size()}</font></b>]处结果!<br/>
	
	<c:forEach items="${lists }" var="book">
		编号 :<c:out value="${book.id }"></c:out><br/>
		书名 :<c:out value="${book.name }" escapeXml="false"></c:out><br/>
		作者 :
		<c:forEach var="authors" items="${book.authors }">
			<c:out value="${authors.name }" escapeXml="false"></c:out>&nbsp;
		</c:forEach><br/>
		描述 :<c:out value="${book.description }" escapeXml="false"></c:out><br/>
		出版日期 :<c:out value="${book.publicationDate }"></c:out><br/>
		<hr/>
	</c:forEach>
</body>
</html>