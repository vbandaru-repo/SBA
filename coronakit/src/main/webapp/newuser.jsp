<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Corona Kit-New User(user)</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<hr/>

<h3 style="color:blue">New User Details</h3>



<form action="user?action=insertuser" method ="post">

		<table>

		<tr>

			<td><label for="cname">Enter  Name : </label> </td><td><input type="text" id="cname" name="cname"></td>

		</tr>

		<tr>

			<td><label for="cemail">Enter Email : </label></td><td><input type="text" id="cemail" name="cemail"></td> 

		</tr>

		<tr>

			<td><label for="pnum">Enter Mobile : </label></td> <td><input type="text" id="pnum" name="pnum"></td>

		</tr>
		
		<tr>

			<td><label for="doorno">Enter Door No : </label></td> <td><input type="text" id="doorno" name="doorno"></td>

		</tr>
		
		<tr>

			<td><label for="addressline1">Address Line1 : </label></td> <td><input type="text" id="addressline1" name="addressline1"></td>

		</tr>
		
		<tr>

			<td><label for="addressline1">Address Line2 : </label></td> <td><input type="text" id="addressline2" name="addressline2"></td>

		</tr>
	
		<tr>

			<td><label for="addressline1">Land Mark : </label></td> <td><input type="text" id="lmark" name="lmark"></td>

		</tr>
		
		<tr>

			<td><label for="addressline1">City : </label></td> <td><input type="text" id="city" name="city"></td>

		</tr>
		
		<tr>

			<td><label for="addressline1">Zip Code : </label></td> <td><input type="text" id="zcode" name="zcode"></td>

		</tr>
		
		<tr>

			<td><label for="addressline1">State : </label></td> <td><input type="text" id="state" name="state"></td>

		</tr>

		<tr>

			<td><label for="addressline1">Country : </label></td> <td><input type="text" id="country" name="country"></td>

		</tr>
		
		</table>

		<br/>

		<div>

			<input type="submit" value="View Products">

		</div>



</form>

<hr/>	
	<jsp:include page="footer.jsp"/>
</body>
</html>