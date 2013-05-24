<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Hibernate Search Example</title><base>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/jquery-1.9.1.js"></script>
<script type="text/javascript">
	function del(id){
		if(window.confirm("确定要删除["+id+"]吗?")){
			var url="${pageContext.request.contextPath }/delete/"+id;
			$.get(url,function(data){
				alert(data);
			});
		}
	}
	
	function load(id){
		
		document.modifyform.id.value="";
		document.modifyform.name.value="";
		document.modifyform.description.innerHTML="";
		document.modifyform.author[0].value="";
		document.modifyform.author[1].value="";
		
		var url="${pageContext.request.contextPath }/load/"+id;
		$.get(url,function(data){
			var dt=eval(data);
			document.modifyform.id.value=id;
			document.modifyform.name.value=dt.name;
			document.modifyform.description.innerHTML=dt.description;
			document.modifyform.author[0].value=dt.authors[0].name;
			document.modifyform.author[1].value=dt.authors[1].name;
		},"json");
	}
	
</script>
</head>
<body>
	<h2>Spring3.2+Hibernate4.2+Hibernate Search4.2整合(IKAnalyzer、paoding)实现中文分词索引实时同步</h2>
	<div style="float: left;">
	搜索:
	<form name="searchform" method="get" action="${pageContext.request.contextPath }/search">
		<input type="hidden" name="start" value="0">
		<input type="hidden" name="pagesize" value="5">
		<input name="keyword" type="text" value="${keyword }">
		<input type="submit" value="搜索">
	</form>
	</div>
	<div style="float: left;">
	新增:
	<form name="saveform" method="post" action="${pageContext.request.contextPath }/save">
		书名:<input type="text" name="name"><br/>
		作者:<input type="text" name="author"><br/>
		作者:<input type="text" name="author"><br/>
		描述:<textarea name="description" cols="60" rows="8"></textarea><br/>
		<input type="submit" value="保存">
	</form>
	</div>
	修改:
	<form name="modifyform" method="post" action="${pageContext.request.contextPath }/modify">
		<input type="hidden" name="id">
		书名:<input type="text" name="name"><br/>
		作者:<input type="text" name="author"><br/>
		作者:<input type="text" name="author"><br/>
		描述:<textarea name="description" cols="60" rows="8"></textarea><br/>
		<input type="submit" value="保存">
	</form>
	</div>
	<hr/>
	<c:if test="${queryResult.searchresultsize>0}" var="res">
		共查到[<b><font color='red'>${queryResult.searchresultsize}</font></b>]处结果!<br/>
		<c:forEach items="${queryResult.searchresult }" var="book">
			编号 :<c:out value="${book.id }"></c:out><br/>
			书名 :<c:out value="${book.name }" escapeXml="false"></c:out><br/>
			作者 :
			<c:forEach var="authors" items="${book.authors }">
				<c:out value="${authors.name }" escapeXml="false"></c:out>&nbsp;
			</c:forEach><br/>
			描述 :<c:out value="${book.description }" escapeXml="false"></c:out><br/>
			出版日期 :<c:out value="${book.publicationDate }"></c:out><br/>
			<a href="javascript:del(<c:out value='${book.id }'></c:out>);">删除</a>
			<a href="javascript:load(<c:out value='${book.id }'></c:out>);">修改</a>
			<hr/>
		</c:forEach>
	</c:if>
	<c:if test="${!res}">
		<font color="red">没有要查询的内容!</font>
	</c:if>
	</div>
	
</body>
</html>