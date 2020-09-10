<%@page import="com.iiht.evaluation.coronokit.model.ProductMaster"%>
<%@page import="com.iiht.evaluation.coronokit.model.CoronaKit"%>
<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Corona Kit-All Products(user)</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<hr/>
<h2 style="color:blue"> Product List </h2>
<%
List<ProductMaster> productsList= (List<ProductMaster>)request.getAttribute("productlist");
HashMap<String, HashMap<String,String>> pdetails = (HashMap<String, HashMap<String,String>>)session.getAttribute("pdetails");
%>

<form action="user?action=addnewitem" method="post">


<table border="1" width="100%">

<tr>

<th>Product Name</th>

<th>Product Description</th>

<th>Price/Unit</th>

<th>Quantity</th>

<th>Action</th>

</tr>

<%
String quantity;
for(ProductMaster product:productsList){
quantity = "0";
%>

<tr>
<td><%=product.getProductName()%></td>
<td><%=product.getProductDescription()%></td>
<td><%=product.getCost()%></td>
<%
if(pdetails!=null && !pdetails.isEmpty()) {
	if(pdetails.containsKey(String.valueOf(product.getId())))
	quantity = pdetails.get(String.valueOf(product.getId())).get("quantity");
}
%>
<td><input style="width: 50px;" type="text" id="qty" name="qty_<%=product.getId()%>" value="<%=quantity%>"/></td>
<td><input type="submit" value="Add to Kit"/ name="btn_<%=product.getId()%>"></td>
</tr>
<%} %>

</table>
<br>
<a href="user?action=placeorder"><input type="button" value="Place Order"></a>     <a href="user?action=showkit"><input type="button" value="View Order"></a>     

</form>


<hr/>
	<jsp:include page="footer.jsp"/>
</body>
</html>