<%@page import="com.iiht.evaluation.coronokit.model.ProductMaster"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Corona Kit-Edit Product(Admin)</title>
</head>
<body>
<%
		// fetch the shared data
		ProductMaster product =  (ProductMaster) request.getAttribute("productMaster");
%>
<jsp:include page="header.jsp"/>
<hr/>

<form action="admin?action=updateproduct&id=<%=product.getId()%>" method="post">
	<div>
		<div><label>Product Name</label> <input type="text" name="name" value=<%=product.getProductName()%>></div>
		<br/>
		<div><label>Product Description</label> <input type="text" name="description" value=<%=product.getProductDescription()%>></div>
		<br/>
		<div><label>Cost</label> <input type="text" name="cost" value=<%=product.getCost()%>></div>
		<div> <input type="submit" value="Update"> </div>
	</div>
</form>

<hr/>	
	<jsp:include page="footer.jsp"/>
</body>
</html>