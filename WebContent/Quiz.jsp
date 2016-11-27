<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
	import="InteractiveCalculus.Main.*, java.util.*"%>
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
<link href="css/bootstrap.css" rel="stylesheet" />
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
<%if(session.getAttribute("name") != null) 
{%>
<body>
	<ul class="topnav" id="myTopnav">
		<li><a class="active" href="/InteractiveCalculus/Main.jsp">Home</a></li>
		<li><a href="/InteractiveCalculus/Quiz.jsp">Quiz</a></li>
		<li><a href="/InteractiveCalculus/DiscussionBoard.jsp">Discussion
				Board</a></li>
		<li><a href="/InteractiveCalculus/CreateAccount.jsp">Create
				Account</a></li>
		<li><a href="/InteractiveCalculus/LogIn.jsp">Log In</a></li>
		<li class="icon"><a href="javascript:void(0);"
			style="font-size: 15px;" onclick="myFunction()">☰</a></li>
	</ul>

	<center>
		<h1 class="">Derivatives Quiz</h1>
	</center>
	<div align="center">
		<a href="Quiz.jsp?section=1" class="btn">Basic</a> <a
			href="Quiz.jsp?section=2" class="btn">Intermediate</a> <a
			href="Quiz.jsp?section=3" class="btn">More Challenging</a>
	</div>
	<form action="Quiz.jsp">

		<%
	int[] radio = new int[10];
	String[] optradio = {"r1", "r2", "r3", "r4", "r5", 
			"r6", "r7", "r8", "r9", "r10"};
	
	
	for (int i=0; i<10; i++) {
		if (request.getParameter(optradio[i]) != null) {
			radio[i] = Integer.parseInt(request.getParameter(optradio[i]));
		}
		else {
			radio[i] = 0;
		}
	}
	
	QuizDAO dao = new QuizDAO();
	
	QuizQuestion[] q = new QuizQuestion[10];
	
	if (request.getParameter("section") != null || request.getParameter("id") != null) {
		
		int section;
		
		if (request.getParameter("section") == null)
			section = Integer.parseInt(request.getParameter("id"));
		else	
			section = Integer.parseInt(request.getParameter("section"));
		
		
		for (int j=10*section-10; j<10*section; j++) {
			q[j-10*section+10] = dao.findQuestion(j+1);
		}	
		
		for (int k=0; k<q.length; k++) {
			
			String correctAnswer = q[k].answerChoice(q[k].getCorrect());
			String selectedAnswer = q[k].answerChoice(radio[k]);
%>
		<table class="table">
			<tr>
				<td>
					<h4><%=k+1%>.
						<%=q[k].getQuestion()%></h4> <%
				if (radio[k] == q[k].getCorrect()) {
%> <b><span style="color: green">Correct! </span><%=correctAnswer%></b>
					<%
				}
				else if (radio[k] != 0) {
%> <b><span style="color: red">Incorrect (<%=selectedAnswer%>)
					</span></b> <br />Correct Answer: <b><span style="color: green"><%=correctAnswer%></span></b>
					<%
				}

%>
				</td>
			</tr>
			<tr>
				<td><input type="radio" name="<%=optradio[k]%>" value="1">
					<%=q[k].getA1()%></td>
			</tr>
			<tr>
				<td><input type="radio" name="<%=optradio[k]%>" value="2">
					<%=q[k].getA2()%></td>
			</tr>
			<tr>
				<td><input type="radio" name="<%=optradio[k]%>" value="3">
					<%=q[k].getA3()%></td>
			</tr>
			<tr>
				<td><input type="radio" name="<%=optradio[k]%>" value="4">
					<%=q[k].getA4()%></td>
			</tr>

		</table>
		<%	}
%>

		<button class="btn btn-success" name="id"
			value="<%=q[0].getSection()%>">Submit</button>
		<%	}
%>
	</form>

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
<%} else
	{%>
<body>
	<ul class="topnav" id="myTopnav">
		<li><a class="active" href="/InteractiveCalculus/Main.jsp">Home</a></li>
		<li><a href="/InteractiveCalculus/Quiz.jsp">Quiz</a></li>
		<li><a href="/InteractiveCalculus/DiscussionBoard.jsp">Discussion
				Board</a></li>
		<li><a href="/InteractiveCalculus/CreateAccount.jsp">Create
				Account</a></li>
		<li><a href="/InteractiveCalculus/LogIn.jsp">Log In</a></li>
		<li class="icon"><a href="javascript:void(0);"
			style="font-size: 15px;" onclick="myFunction()">☰</a></li>
	</ul>

	<div>
		<center>
			<h1 class="">Derivatives Quiz</h1>
		</center>
	</div>
	<div>
		<center>
			<h1>YOU MUST LOG IN FOR THIS FUNCTIONALITY</h1>
		</center>
	</div>
</body>
<%} %>
</html>