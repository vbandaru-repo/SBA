<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Corona Kit-Home</title>
</head>
<body>
<div>
<jsp:include page="header.jsp"/>
<hr/>
	<h2 style="color:blue">Admin Login</h2>
	<form action="admin?action=login" method="post">
		<div>
			<div><label for="loginid">Enter Login Id</label> </div>
			<div><input type="text" id="loginid" name="loginid"> </div>
		</div>
		<div>
			<div><label for="password">Enter Password</label> </div>
			<div><input type="text" id="password" name="password"> </div>
		</div>
		<br>
		<div>
			<div><input type="submit" value="Login"> </div>
		</div>
	</form>
</div>
<hr/>
<h2 style="color:blue">Welcome Visitor</h2>
<h5><i>Click on below button to view and order products</i></h5>
<div>
	<a href="user?action=newuser"><button>Create Corona Kit</button></a>
</div>
<hr/>	
	<jsp:include page="footer.jsp"/>
</body>
</html>