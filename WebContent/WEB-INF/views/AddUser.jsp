<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>New User</title>
</head>
<body>

<h3> New user</h3>
<form action="add" method="post" >
	<div>
		<table>
			<tr>
				<th>Associate ID</th>
				<th>First name</th>
				<th>Last name</th>
				<th>Cognizant Email</th>
				<th>System IP (ex. 10.241.94.183)</th>
			</tr>
			<tr>
				<td> <input type="text" name="inp_assoc_id" value=""/></td>
				<td> <input type="text" name="inp_first_name" value=""/></td>
				<td> <input type="text" name="inp_last_name" value=""/></td>
				<td> <input type="text" name="inp_email" value=""/>@cognizant.com</td>
				<td> <input type="text" name="inp_system_ip" value=""/></td> 
			</tr>
		</table>
	</div>
	<input type="submit" value="Save"/>
</form>
</body>
</html>