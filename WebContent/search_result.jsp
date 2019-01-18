<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="widget.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%
		String field = request.getParameter("field");
		String query_string = request.getParameter("query");
		SearchWidget widget = new SearchWidget();
		widget.search(field, query_string);

		String[][] res_array = widget.getResult();

		for (int i = 0; i < res_array.length; i++) {
	
	%>
	title:
	<% out.print(res_array[i][0]); %>
	<br/>
	
	author:
	<% out.print(res_array[i][1]); %>
	<br/>
	
	journal:
	<% out.print(res_array[i][2]); %>
	<br/>
	
	abstract:
	<% out.print(res_array[i][3]); %>	
	<br/>
	<br/>
			
	<%
		}
	%>

</body>
</html>