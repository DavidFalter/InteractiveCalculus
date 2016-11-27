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

	<%
Db_access key = new Db_access();


//controller{
String action = request.getParameter("action");
String author = request.getParameter("author");
String body = request.getParameter("body");
String id = request.getParameter("id");
String rid = request.getParameter("rid");
String name = request.getParameter("name");
Forum_post app = new Forum_post();
Forum_table app3 = new Forum_table();

%>

	<h1></h1>

	<%


if("create2".equals(action)){
	int tempid = Integer.parseInt(id);
	System.out.println(tempid);
	app = new Forum_post(author, body);
	key.create2(app, tempid,"" );
	
} else if ("remove".equals(action)){
	int idInt = Integer.parseInt(rid);
	key.removee(idInt);
} 

List<Forum_post> applications = key.selectAll2();

%>


	<form action="post.jsp">

		<input type="hidden" name="id" value="<%= app3.getId()%>" />




		<ul>
			<span><h5>Author</h5>
				<input name="author" class="form-control" style="width: 400px;"
				value="<%= app.getPost_author() %>" /></span>
			<br>
			<span><h5>Body</h5>
				<input name="body" class="form-control"
				style="width: 500px; height: 100px"
				value="<%= app.getPost_body() %>" /></span>
			<span> <br>
				<button class="btn btn-success" name="action" value="create2">
					Post</button>
			</span>
		</ul>

		<table class="table">

			<% for(Forum_post ap:applications ){


	System.out.println(app3.getId());
%>
			<% if( app3.getId() == ap.getOp_id()) {%>
			<tr>
				<td><%= ap.getPost_body() %></td>
				<td><%= ap.getPost_author() %></td>
				<td><a href="post.jsp?action=remove&rid=<%= ap.getPost_id() %>">
						Delete </a></td>
			</tr>

			<%	
	}
}
%>

		</table>

	</form>
</body>
</html>