<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.iiht.evaluation.coronokit.model.UserProfile"%>
<%@ page import="java.time.LocalDateTime"%>
<%@ page import="java.time.LocalDate"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Corona Kit-Order Summary(user)</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<hr/>

<h2 style="color:blue">Order Summary</h2>
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
<br>
<label><b>Total Amount : </b></label> 
<text style="color:blue"><b><%=totalAmount%></b></text>
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
<br>
<label><b>Order Placed At: </b></label>
<text style="color:green"><i><%=LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME) %></i></text>
<br>
<label><b>Estimated Delivery Date: </b></label>
<text style="color:green"><i><%=LocalDate.now().plusDays(7).format(DateTimeFormatter.ISO_LOCAL_DATE)%></i></text>
<br>
<hr/>	
	<jsp:include page="footer.jsp"/>
</body>
</html>