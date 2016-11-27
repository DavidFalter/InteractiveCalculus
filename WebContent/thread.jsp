<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="InteractiveCalculus.Main. *"
	import="java.util. *"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link href="css/bootstrap.css" rel="stylesheet" />

</head>
<body>

	<h1>Discussion Board</h1>

	<%
Db_access dao = new Db_access();


//controller{
String action = request.getParameter("action");
String name = request.getParameter("name");
String price = request.getParameter("price");
String id = request.getParameter("id");
Forum_table app = new Forum_table();


if("create".equals(action)){
	app = new Forum_table(name, price);
	dao.create(app);
	
} else if ("remove".equals(action)){
	int idInt = Integer.parseInt(id);
	dao.remove(idInt);
}

List<Forum_table> applications = dao.selectAll();



//}
%>


	<form action="thread.jsp">

		<input type="hidden" name="id" value="<%= app.getId() %>" />
		<ul>
			<span><h5>Subject Title</h5>
				<input name="name" class="form-control" style="width: 400px;"
				value="<%= app.getName()%>" /></span>
			<br>
			<span><h5>Description</h5>
				<input name="price" class="form-control"
				style="width: 500px; height: 100px" value="<%= app.getPrice()%>" /></span>
			<span> <br>
				<button class="btn btn-success" name="action" value="create">
					Post</button>
			</span>
		</ul>

		<table class="table">

			<% for(Forum_table ap:applications){
%>
			<tr>
				<td><a href="post.jsp?action=select&id=<%= ap.getId() %>"><%= ap.getName()%></td>
				<td><%= ap.getPrice()%></td>
				<td><a href="thread.jsp?action=remove&id=<%= ap.getId() %>">
						Delete </a></td>
			</tr>

			<%	
}
%>

		</table>

	</form>
</body>
</html>