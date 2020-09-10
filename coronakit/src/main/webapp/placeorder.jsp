<%@page import="java.util.HashMap"%>
<%@page import="com.iiht.evaluation.coronokit.model.UserProfile"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Corona Kit-Place Order(user)</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<hr/>
<h2 style="color:blue">Confirm Details and Place Order</h2>
<%
HashMap<String, HashMap<String,String>> pdetails = (HashMap<String, HashMap<String,String>>)session.getAttribute("pdetails");
UserProfile userProfile = (UserProfile)request.getAttribute("userprofile");
%>
<table border="1">

<tr>

<th>Product Name</th>

<th>Price/Unit</th>

<th>Quantity</th>

<th>Amount</th>

</tr>

<%
String name;
String cost;
String quantity;
double amount;
double totalAmount = 0;
for(String pid: pdetails.keySet()){
	name = pdetails.get(pid).get("name");
	cost = pdetails.get(pid).get("cost");
	quantity = pdetails.get(pid).get("quantity");
	amount = Double.parseDouble(cost)* Double.parseDouble(quantity);
	totalAmount = totalAmount+amount;
%>

<tr>

<td><%=name%></td>

<td><%=cost%></td>

<td><%=quantity%></td>

<td><%=amount%></td>

</tr>

<%} %>

</table>

<label><b>Total Amount : </b></label> 
<text><%=totalAmount%></text>
<br>
<br>
<label><b> Name: </b></label>
<text><%=userProfile.getUserName() %></text>
<br>
<label><b>Email: </b></label>
<text><%=userProfile.getEmail() %></text>
<br>
<label><b>Contact: </b></label>
<text><%=userProfile.getContactNumber()%></text>
<br>
<label><b>Delivery Address: </b></label>
<address><%=userProfile.getDeliveryAddress() %></address>
<br>
<a href="user?action=addnewitem"><button>View Products</button></a>
<a href="user?action=ordersummary"><button>Confirm Order</button></a>
<hr/>	
	<jsp:include page="footer.jsp"/>
</body>
</html>