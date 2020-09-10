<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Corona Kit-Add New Product(Admin)</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<hr/>

<form action="admin?action=insertproduct" method="post">
	<div>
		<div><label>Enter Product Name</label> <input type="text" name="name"></div>
		<br/>
		<div><label>Enter Product Description</label> <input type="text" name="description"></div>
		<br/>
		<div><label>Enter Cost</label> <input type="text" name="cost"></div>
		<div> <input type="submit" value="Add"> </div>
	</div>
</form>

<hr/>	
	<jsp:include page="footer.jsp"/>
</body>
</html>