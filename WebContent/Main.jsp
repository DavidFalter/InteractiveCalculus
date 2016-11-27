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
}

li.login {
	float: right;
	verticle-align: middle;
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

	<%
	String equation = request.getParameter("equation");
	String action = request.getParameter("action");
	Controller controller = new Controller();
	Integral integral = new Integral();
	integral.setSteps(null);
	integral.setOrignal(" ");
	integral.setIntegral(" ");	
	integral.setX1(" ");
	integral.setX2(" ");
	
	Derivative derivative = new Derivative();
	derivative.setDerivative(" ");
	derivative.setOrignal(" ");
	derivative.setSteps(null);	
	Original original = new Original();
	original.setOriginalEquation(" ");
	if("solve".equals(action) || "integrate".equals(action))
	{
		integral = controller.solveIntegral(equation);
		derivative = controller.solveDerivative(equation);
		original = controller.getOriginal(equation);
	}
	String x1 = request.getParameter("x1");
	String x2 = request.getParameter("x2");
	if("integrate".equals(action))
	{
		integral.setX1(x1);
		integral.setX2(x2);
	}
%>


	<ul class="topnav" id="myTopnav">
		<li><a class="active" href="/InteractiveCalculus/Main.jsp">Home</a></li>
		<li><a href="/InteractiveCalculus/Quiz.jsp">Quiz</a></li>
		<li><a href="/InteractiveCalculus/DiscussionBoard.jsp">Discussion
				Board</a></li>
		<li><a href="/InteractiveCalculus/CreateAccount.jsp">Create
				Account</a></li>
		<%if(session.getAttribute("name") == null)
    	  {%>
		<li><a href="/InteractiveCalculus/LogIn.jsp">Log In</a></li>
		<%} %>
		<%if(session.getAttribute("name") != null)
    	  {%>
		<li><a>
				<button>Log Out</button>
		</a></li>
		<%} %>
		<li class="icon"><a href="javascript:void(0);"
			style="font-size: 15px;" onclick="myFunction()">☰</a></li>
	</ul>

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



	<h1 class="text-center">Interactive Calculus Aid</h1>

	<form action="Main.jsp">
		<div>
			<table align="center" padding="50px">
				<tr>
					<td>
						<%if(derivative.getOrignal() != " ") {%> <input name="equation"
						class="form-control" value="<%=derivative.getOrignal() %>" /> <%} else { %>
						<input name="equation" class="form-control" /> <%} %>
					</td>
					<td>
						<button class="btn btn-info" name="action" value="solve">
							Solve!</button>
					</td>
				</tr>
			</table>
		</div>
	</form>


	<%-- <% 
UserDAO dao = new UserDAO();

List<User> users = dao.selectAll();
%>
<ul> 
<% for(User user : users) {%>
	<li> 
			<span><%= user.getUserName() %></span>
			<span><%= user.getPrivilege() %></span>
	</li>
	<%} %>


</ul>

<% %>
<ul>

</ul> --%>
	<%if("solve".equals(action))
	{%>
	<div>
		<h2 align="center">Solved!</h2>
	</div>
	<%} %>

	<div class="container" align="center">
		<div class="answers" style="float: left; width: 33%;">
			<button type="button" class="btn btn-info" data-toggle="collapse"
				data-target="#original">Original</button>
			<div id="original" class="collapse">
				<%if(!derivative.getOrignal().contains("ln"))
  	    	  {%>
				<div>
					<center>
						<b><p>
								y =
								<%=derivative.getOrignal() %></p></b>
					</center>
				</div>
				<div id="orig" style="width: 100%; height: 100%"></div>
				<%} else{%>
				<div>
					<center>
						<b><p>
								y =
								<%=derivative.getOrignal() %></p></b>
					</center>
				</div>
				<div id="orig1" style="width: 100%; height: 100%"></div>
				<%} %>
			</div>
		</div>
		<div style="float: left; width: 33%;">
			<button type="button" class="btn btn-info" data-toggle="collapse"
				data-target="#derivative">Derivative</button>
			<div id="derivative" class="collapse">
				<%if(derivative.getOrignal() != " "){%>
				<div>
					<center>
						<b><p>
								y =
								<%=derivative.getDerivative() %></p></b>
					</center>
				</div>
				<div id="deriv" style="width: 100%; height: 100%"></div>

				<div align="left">
					<h2>Derivative Solution</h2>
					<%int i = 0;
  	 			 if(derivative.getSteps() != null)
  	 			 {
  				for(String step : derivative.getSteps()) 
  				{
  					i++;%>
					<li>Step <%=i %>) <%=step %>
					</li>
					<%}} i = 0; %>
				</div>
				<%} %>
			</div>
		</div>
		<div style="float: left; width: 33%;">
			<button type="button" class="btn btn-info" data-toggle="collapse"
				data-target="#integral">Integral</button>
			<div id="integral" class="collapse">
				<%if(derivative.getOrignal() != " "){%>
				<div>
					<center>
						<b><p>
								y =
								<%=integral.getIntegral() %></p></b>
					</center>
				</div>
				<div id="integ" style="width: 100%; height: 100%"></div>
				<div align="left">
					<h2 align="center">Integral Solution</h2>
					<%int j = 0;
  	  	if(integral.getSteps() != null)
  	  	{
  		for(String step : integral.getSteps()) 
  		{
  		j++;%>
					<li>Step <%=j %>) <%=step %>
					</li>
					<%}} j = 0;%>
				</div>
				<%} %>
				<form action="Main.jsp">
					<div>
						<h2>
							Find Definite Integral
							</h1>
							<table align="right" padding="50px">
								<tr>
									<td><input name="x1" class="form-control" /></td>
									<td><input name="x2" class="form-control" /></td>
									<td>
										<button class="btn btn-info" name="action" value="integrate">
											Integrate</button>
									</td>
								</tr>
							</table>
					</div>
				</form>
				<%if(integral.getX1() != " " && integral.getX2() != " "){%>
				<div id="definteg" style="width: 100%; height: 100%"></div>
				<%} %>
			</div>
		</div>
	</div>
	<script>
functionPlot({

	  target: '#orig',
	  height:300,
	  width:300,
	  data: [{
	    fn: '<%=derivative.getOrignal()%>',
	    derivative: {
	      fn: '<%=derivative.getDerivative()%>',
	      updateOnMouseMove: true
	    }
	  }]
	});
	
functionPlot({
	  target: '#orig1',
	  height:300,
	  width:300,
	  data: [{
	    fn: '<%=derivative.getOrignal()%>',
	  }]
	});
	

functionPlot({
	  target: '#deriv',
	  height:300,
	  width:300,
	  data: [{
	    fn: '<%=derivative.getDerivative()%>',
	  }]
	});
	
functionPlot({
	  target: '#integ',
	  height:300,
	  width:300,
	  data: [{
	    fn: '<%=integral.getIntegral()%>',
	  }]
	});
	
functionPlot({
	  target: '#definteg',
	  height:300,
	  width:300,
	  data: [{
	    fn: '<%=integral.getIntegral()%>',
	    range: [<%=integral.getX1()%>, <%=integral.getX2()%>],
	    closed: true
	  }]
	});
	

</script>
</body>
</html>


