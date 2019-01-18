<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>
<h1>Simple Search</h1>

<form action="search_result.jsp" method="post">
	<select name="field">
		<option value = "author">author</option>
		<option value = "title">title</option>
		<option value = "journal">journal</option>
		<option value = "abstract">abstract</option>		
	</select>
	<input type = "text" name = "query"/>
	<input type = "submit" value = "submit"/> 
</form>

</body>
</html>