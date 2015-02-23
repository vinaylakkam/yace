<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<%@ include file="/WEB-INF/jsp/header.jsp" %>

<link rel="stylesheet" type="text/css" href="<spring:url value="/static/styles/home.css" htmlEscape="true" />"/>

<script type="text/javascript" src="<spring:url value="/static/js/home.js" htmlEscape="true" />"></script>

	
<!--<h2 align="center" class="errors"> Arrah.. You caught us ; Please do not use</h2>
-->
<form:form modelAttribute="user">
	<form:hidden path="id"/>
	<form:hidden path="assoId"/>
	<form:hidden path="cName"/>
	<form:hidden path="fName"/>
	<form:hidden path="lName"/>
	<form:hidden path="eMail"/>
	<form:hidden path="role"/>
	<form:hidden path="systemIP"/>
</form:form>

<form:form modelAttribute="timesheet">
	<form:hidden path="id" id="timesheetId"/>
	<form:hidden path="new"/>
</form:form>



<div id="welcome">
	Hello <strong>${user.fName} ${user.lName}</strong>
</div>

<!--<img align="center" src="static/images/under_construction.jpg" alt="Under construction" title="Under construction"></img><h4 class="errors"> Please do not use</h4>

-->


<!--  New Clarity entry -->
<div id="div_newCForm">
	<p>Please enter your clarity clip board data</p>
	<textarea id="txt_cData" cols="70" rows="10" ></textarea>
	
	<p>
		<input id="but_done" type="button" value="DONE"/>
	</p>
</div>

<div id="div_success" style="display:none">
	<h3>Data saved successfully!!</h3>
</div>
<div id="div_failed" style="display:none">
	<h3>Data saving failed. Please contact admin</h3>
</div>


<!--  Existing Clarity entry -->
<div id="div_existingCForm">
	<p>
		You have already entered clarity timesheet for this week as below.
	</p>
	<table class="cDataTable">
		<thead>
			<tr>
				<th>Project ID</th>
				<th style="width:20%">Project, Description</th>
				<c:forEach var="date" items="${currWeekDates}">
					<th>${date}</th>
				</c:forEach>
				<th>Total hours</th>
				<th><b>Status</b></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="timeEntry" items="${timesheet.timeEntries}">
				<tr>
					<td>${timeEntry.projId}</td>
					<td>${timeEntry.projName}</td>
					<td>${timeEntry.day1Hrs}</td>
					<td>${timeEntry.day2Hrs}</td>
					<td>${timeEntry.day3Hrs}</td>
					<td>${timeEntry.day4Hrs}</td>
					<td>${timeEntry.day5Hrs}</td>
					<td>${timeEntry.day6Hrs}</td>
					<td>${timeEntry.day7Hrs}</td>
					<td>${timeEntry.totalHrs}</td>
					<td></td>
				</tr>
			</c:forEach>
			<tr>
				<td colspan="2">Total</td>
				<td>${timesheet.day1Hrs}</td>
				<td>${timesheet.day2Hrs}</td>
				<td>${timesheet.day3Hrs}</td>
				<td>${timesheet.day4Hrs}</td>
				<td>${timesheet.day5Hrs}</td>
				<td>${timesheet.day6Hrs}</td>
				<td>${timesheet.day7Hrs}</td>
				<td>${timesheet.totalHrs}</td>
				<td>${timesheet.status}</td>
			</tr>	
		</tbody>
	</table>
	<p>
		<input id="but_modify" type="button" value="MODIFY"/>
	</p>				
</div>


<!--  Existing Clarity entry -->
<div id="div_savableData" style="display:none">

	<p><h4>Data will be saved as below: </h4></p>

	<table id="table_savableData" class="cDataTable">
		<thead>
			<tr>
				<th>Project ID</th>
				<th style="width:20%">Project, Description</th>
				<c:forEach var="date" items="${currWeekDates}">
					<th>${date}</th>
				</c:forEach>
				<th>Total hours</th>
				<th><b>Status</b></th>
			</tr>
		</thead>
		<tbody id="tbody_savableData"/>
	</table>
	
	<p>
		<input id="but_submit" type="button" value="SUBMIT"/>
	</p>	
</div>

<!--  Debug -->
<div id="div_jsonData" style="display:none">
	<h4>Debug:Json: </h4>
	<textarea id="txt_jsonData" cols="70" rows="10" ></textarea>
</div>
<!-- 
Search[ Advanced ]                    
 
 
    
  Personal 
    
  General 
  Organizer 
  Timesheets 
  Account Settings 
    
    
  Portfolio Management 
    
  Projects 
    
    
  Resource Management 
    
  Resources 
  Resource Finder 
  Resource Requisitions 
    
    
  Portlets (Custom) 
    
  Exchange Rate Converter 
  Cost Center Explorer 
  Global Recovery Calendar 
    
 
  Timesheet  
 
 
 Processing your request... 
 
Stop 
 
Cancel [Printable Version] 
 
   
Time Period: 8/13/11 - 8/19/118/6/11 - 8/12/117/30/11 - 8/5/11 7/23/11 - 7/29/11 7/16/11 - 7/22/117/9/11 - 7/15/117/2/11 - 7/8/116/25/11 - 7/1/11   
 
Resource Name Sowjanya Ganji 
Timesheet Status Approved 
 Approved by Kristin L Vonwald 
Last Modified 7/21/11 10:08 AM 
 
 

 Project ID Project Description Sat
7/16
 Sun
7/17
 Mon
7/18
 Tue
7/19
 Wed
7/20
 Thu
7/21
 Fri
7/22
 Total ETC Charge Code Parent Phase Baseline Role Task ID Input Type Code Short Name 
Tasks 
  PRJ00000CG CSBST IA&CTT G&A Arora Vendor-Other Non-Paid               0.00  124.00  NonCapital Other Non-Paid (Contractor Personal time) General & Administrative 0.00 Programmer GA0702 Offshore/Offsite   
  PRJ0000YMU ZYNC Center 3.0- NGI Zync Test Execution Phase     8.00 8.00 8.00 8.00 8.00 40.00  0.00  Capital Test Test 0.00 Programmer 6586HS Offshore/Offsite   
      Total 0.00 0.00 8.00 8.00 8.00 8.00 8.00 40.00                   

  [Configure] 
Cancel [Printable Version] 
Work Effort = Hours   
 
 
 
 
 
 
 Copyright � 2008 CA. All rights reserved. [ About ]  
 

 
 
 	
	
 -->
<%@ include file="/WEB-INF/jsp/footer.jsp" %>