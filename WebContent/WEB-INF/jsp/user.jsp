<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<%@ include file="/WEB-INF/jsp/header.jsp" %>


<link rel="stylesheet" type="text/css" href="<spring:url value="/static/styles/user.css" htmlEscape="true" />" />

<h2><c:if test="${user.new}">New </c:if>User</h2>
<h3><c:if test="${!user.new}">User is saved.</c:if>  Goto <a href="/yace" style="color:blue">YACE</a></h3> <h2></h2>

<form:form modelAttribute="user" method="POST">
 
 	<form:hidden path="id"/>
 	
	<table>
		<tr>
			<th>
				Associate ID: <form:errors path="assoId" cssClass="errors"/>
				<br/>
				<form:input path="assoId" size="10" maxlength="6"/>
			</th>
		</tr>
		<tr>
			<th>
				First name: <form:errors path="fName" cssClass="errors"/>
				<br/>
				<form:input path="fName" size="20" maxlength="50"/>
			</th>
		</tr>
		<tr>
			<th>
				Last Name: <form:errors path="lName" cssClass="errors"/>
				<br/>
				<form:input path="lName" size="20" maxlength="50"/>
			</th>
		</tr>
		<tr>
			<th>
				Cognizant EMail: <form:errors path="eMail" cssClass="errors"/>
				<br/>
				<form:input path="eMail" size="20" maxlength="36"/> @cognizant.com
			</th>
		</tr>
		<tr>
			<th>
				System IP: <form:errors path="systemIP" cssClass="errors" />
				<br/>
				<%-- <form:input path="systemIP" size="20" maxlength="20">
					<jsp:attribute name="readOnly">false</jsp:attribute>
				</form:input> --%>
				<form:input path="systemIP" size="20" maxlength="20" readonly="true"/>
			</th>
		</tr>
		<tr>
			<td>
				<c:choose>
					<c:when test="${user.new}">
						<p class="submit"><input type="submit" value="Add User" /></p>
					</c:when>
					<c:otherwise>
						<p class="submit"><input type="submit" value="Save User"/></p>
					</c:otherwise>
				</c:choose>
			</td>
		</tr>
	</table>
</form:form>

<%@ include file="/WEB-INF/jsp/footer.jsp" %>