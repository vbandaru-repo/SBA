<%@page import="com.iiht.evaluation.coronokit.model.ProductMaster"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Corona Kit-All Products(Admin)</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<a href="admin?action=logout"><button>LogOut</button></a>
<hr/>
<br/>
	<a href="admin?action=newproduct"><button>Add New Product</button></a>
	<br/>
	<%
		// fetch the shared data
		List<ProductMaster> products =  (List<ProductMaster>) request.getAttribute("products");
	%>
	<table border="1" width="100%">
		<thead>
			<th>NAME</th>
			<th>DESCRIPTION</th>
			<th>COST</th>
			<th>ACTION</th>
		</thead>
		<tbody>
			<% for(ProductMaster product : products) { %>
			<tr>
				<td><%=product.getProductName()%></td>
				<td><%=product.getProductDescription()%></td>
				<td><%=product.getCost()%></td>
				<!-- <td><a href="admin?action=editproduct&id=<%=product.getId()%>"><input type="button" value="Edit"></a></td> 
				<td><a href="admin?action=deleteproduct&id=<%=product.getId()%>"><input type="button" value="Delete"></a></td> -->
				<td style="width: 120px;"><a href="admin?action=editproduct&id=<%=product.getId()%>"><button>Edit</button></a>  <a href="admin?action=deleteproduct&id=<%=product.getId()%>"><button>Delete</button></a></td>
			</tr>
			<% } %>
		</tbody>
	</table>

<hr/>	
	<jsp:include page="footer.jsp"/>
</body>
</html>