<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="InteractiveCalculus.Main. *"
	import="java.util. *"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Interactive Calculus Aid</title>
<meta name="viewport" content="width=device-width, initial-scale=1"
	charset="utf-8">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="https://wzrd.in/standalone/function-plot@1.17.3"></script>
<script src="http://d3js.org/d3.v3.min.js"></script>
<script
	src="http://maurizzzio.github.io/function-plot/js/function-plot.js"></script>
<style>
body {
	margin: 0;
}

div {
	padding-top: 50px;
	padding-bottom: 50px;
}

div.answers {
	padding-top: 50px;
	padding-bottom: 50px;
	boarder-style: solid;
	boarder-right: thick double;
}

ul.topnav {
	list-style-type: none;
	margin: 0;
	padding: 0;
	overflow: hidden;
	background-color: #333;
}

ul.topnav li {
	float: left;
}

ul.topnav li a {
	display: inline-block;
	color: #f2f2f2;
	text-align: center;
	padding: 14px 16px;
	text-decoration: none;
	transition: 0.3s;
	font-size: 17px;
}

ul.topnav li a:hover {
	background-color: #555;
}

ul.topnav li.icon {
	display: none;
}

@media screen and (max-width:680px) {
	ul.topnav li:not (:first-child ) {
		display: none;
	}
	ul.topnav li.icon {
		float: right;
		display: inline-block;
	}
}

@media screen and (max-width:680px) {
	ul.topnav.responsive {
		position: relative;
	}
	ul.topnav.responsive li.icon {
		position: absolute;
		right: 0;
		top: 0;
	}
	ul.topnav.responsive li {
		float: none;
		display: inline;
	}
	ul.topnav.responsive li a {
		display: block;
		text-align: left;
	}
}
</style>
</head>
<body>
<ul class="topnav" id="myTopnav">
		<li><a class="active" href="/InteractiveCalculus/Main.jsp">Home</a></li>
		<li><a href="/InteractiveCalculus/Quiz.jsp">Quiz</a></li>
		<li><a href="/InteractiveCalculus/DiscussionBoard.jsp">Discussion Board</a></li>
		<%if(session.getAttribute("name") == null)
    	  {%>
		<li><a href="/InteractiveCalculus/CreateAccount.jsp">Create Account</a></li>
		<li><a href="/InteractiveCalculus/LogIn.jsp">Log In</a></li>
		<%} %>
		<%if(session.getAttribute("name") != null)
    	  {%>	
		<li><a href="/InteractiveCalculus/LogOut.jsp">Log Out</a></li>
		<%} %>
		<li class="icon"><a href="javascript:void(0);"
			style="font-size: 15px;" onclick="myFunction()">☰</a></li>
	</ul>
	<div align="center">
		<form action="LogOut.jsp">
						<button class="btn-info" name="action" value="logout">Log Out</button>
			</center>
		</form>
	</div>

	<div>
		<%
 String action = request.getParameter ("action");
 
 if("logout".equals(action))
 {
  
      session.removeAttribute("name");
      session.invalidate();
      response.sendRedirect("Main.jsp");
 }

 %>
 
	</div>

	<script>
function myFunction() {
    var x = document.getElementById("myTopnav");
    if (x.className === "topnav") {
        x.className += " responsive";
    } else {
        x.className = "topnav";
    }
}

</script>
</body>
</html>