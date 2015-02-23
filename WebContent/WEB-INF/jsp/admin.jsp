<%@ include file="/WEB-INF/jsp/includes.jsp"%>
<%@ include file="/WEB-INF/jsp/header.jsp"%>

<link rel="stylesheet" type="text/css" href="<spring:url value="/static/styles/demo_page.css" htmlEscape="true" />" />
<link rel="stylesheet" type="text/css" href="<spring:url value="/static/styles/demo_table.css" htmlEscape="true" />" />
<link rel="stylesheet" type="text/css" href="<spring:url value="/static/styles/demo_table_jui.css" htmlEscape="true" />" />

<link rel="stylesheet" type="text/css" href="<spring:url value="/static/styles/admin.css" htmlEscape="true" />" />
		
<script type="text/javascript" src="<spring:url value="/static/js/admin.js" htmlEscape="true" />"></script>
<script type="text/javascript" src="<spring:url value="/static/js/common/jquery.dataTables.js" htmlEscape="true" />"></script>



<div id="div_buttons">
	<input type="button" value="Show Defaulters" onclick="alert('Displays only defaulters list.' + '\n\n Under construction')"/>  
	<input type="button" value="Remind Defaulters" onclick="alert('Sends reminder mail to defaulters.' +'\n\n Under construction')"/> 
	<input type="button" value="Export To Excel" onclick="alert('Exports the displayed data to excel.' +'\n\n Under construction')"/>
	<input type="button" value="Show Forecast" onclick="alert('Opens forecast of this months in separate window.' +'\n\n Under construction')"/>
</div> 

<table  cellpadding="0" cellspacing="0" border="0" class="display"  id="table_timesheets">
	<thead>
		<tr>
			<th>Name</th>
			<c:forEach var="date" items="${currWeekDates}">
				<th>${date}</th>
			</c:forEach>
			<th>Total hours</th>
			<th>Status</th>
			<th>Remind?</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="uts" items="${userTimesheets}">
			
			<c:if test="${uts.defaulter eq true}">
				<tr class="gradeX" >
			</c:if>
			<c:if test="${uts.defaulter != true}">
				<tr class="gradeA" >
			</c:if>
				<td>${uts.user.fName} ${uts.user.lName}</td>
				<td>${uts.timesheet.day1Hrs}</td>
				<td>${uts.timesheet.day2Hrs}</td>
				<td>${uts.timesheet.day3Hrs}</td>
				<td>${uts.timesheet.day4Hrs}</td>
				<td>${uts.timesheet.day5Hrs}</td>
				<td>${uts.timesheet.day6Hrs}</td>
				<td>${uts.timesheet.day7Hrs}</td>
				<td>${uts.timesheet.totalHrs}</td> 
				<td>${uts.timesheet.status}</td>
				<td>
					<c:if test="${(uts.timesheet.status != 'Submitted') && (uts.timesheet.status != 'Approved') && (uts.timesheet.status != 'Excused')  && (uts.timesheet.status != 'Posted')}">
						 <%-- Refer http://www.ianr.unl.edu/internet/mailto.html--%>
						 <c:set var="subject">Reminder: Please submit clarity timesheet</c:set>
						<c:set var="body">Please submit the clarity timesheet and enter details in <c:url value="https://www.google.com">YACE</c:url></c:set>
						<a name="reminderMail" href="mailto:${uts.user.eMail}?subject=${subject}&body=${body}">Send eMail</a>
					</c:if>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>



<a href="#">Top</a>


<%@ include file="/WEB-INF/jsp/footer.jsp"%>