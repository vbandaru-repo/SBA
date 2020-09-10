<%@page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Corona Kit-My Kit(user)</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<hr/>
<h2 style="color:blue">Order Details</h2>
<%
HashMap<String, HashMap<String,String>> pdetails = (HashMap<String, HashMap<String,String>>)session.getAttribute("pdetails");
%>
<table border="1">

<tr>

<th>Product Name</th>

<th>Price/Unit</th>

<th>Quantity</th>

<th>Action</th>

</tr>

<%
for(String pid: pdetails.keySet()){
%>

<tr>

<td><%=pdetails.get(pid).get("name")%></td>

<td><%=pdetails.get(pid).get("cost")%></td>

<td><%=pdetails.get(pid).get("quantity")%></td>

<td><a href="user?action=deleteitem&pid=<%=pid%>"><button>Delete</button></a><td>

</tr>

<%} %>

</table>

<br>
<a href="user?action=addnewitem"><button>View Products</button></a>
<hr/>	
	<jsp:include page="footer.jsp"/>
</body>
</html>