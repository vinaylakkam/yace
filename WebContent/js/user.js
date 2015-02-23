
var clarityJSON;
$(document).ready(function() {
	
	var isNewTimesheet = $('#timesheet #new').val() == 'true';
	
	if(isNewTimesheet){
		// Enable new form
		$('#div_newCForm').show();
		$('#div_existingCForm').hide();
	}
	else {
		// Enable existing form
		$('#div_existingCForm').show();
		$('#div_newCForm').hide();
	}
	

	/* Modify existing timesheet */
	$("#but_modify").click(function() {

		// Hide existing form
		$('#div_existingCForm').hide();

		// Enable new form
		$('#div_newCForm').show();

	});

	/* Done timesheet */
	$("#but_done").click(function() {

		// Get JSON object with data
		getClarityJSON();

		// Validate clarity data
		if(! validate()) {
			return false;
		}

		// Hide form
		$('#div_newCForm').hide();

		// Show saved data in table
		$('#div_savableData').show();

		// Debug info
		$('#div_jsonData').show();
		$('#txt_jsonData').attr("value", JSON.stringify(clarityJSON, null, '\t'));

		// Display clarity data in table.
		displaySavableData();
			
	});

	/* Submit timesheet */
	$("#but_submit").click(function() {

		alert('submit:\n' + JSON.stringify(clarityJSON, null, '\t'));
		
		$.postJSON("save", clarityJSON, function(data) {
			
			alert("Resonse from server: " + data);
			if(data == 'saved'){
				// Hide clarity form
				$('#div_savableData').hide();
				$('#div_jsonData').hide();
				alert('Data saved. Thanks!');
			}
			else {
				alert('Something went wrong; Please contact admin');
			}
		});
	});

});


/* Validates clarity data*/
validate = function(clarityJSON) {
	return true;
}

/* Displayes savable data in a table */
displaySavableData = function() {

	var table = $('#table_savableData');

	// Time entries
	var tds ="";
	for(i=0; i< clarityJSON.timeEntry.length; i++){
		tds += '<tr>';
		tds += '<td>'+ clarityJSON.timeEntry[i].projId;+'</td>';
		tds += '<td>'+ clarityJSON.timeEntry[i].projName;+'</td>';
		tds += '<td>'+ clarityJSON.timeEntry[i].day1Hrs;+'</td>';
		tds += '<td>'+ clarityJSON.timeEntry[i].day2Hrs;+'</td>';
		tds += '<td>'+ clarityJSON.timeEntry[i].day3Hrs;+'</td>';
		tds += '<td>'+ clarityJSON.timeEntry[i].day4Hrs;+'</td>';
		tds += '<td>'+ clarityJSON.timeEntry[i].day5Hrs;+'</td>';
		tds += '<td>'+ clarityJSON.timeEntry[i].day6Hrs;+'</td>';
		tds += '<td>'+ clarityJSON.timeEntry[i].day7Hrs;+'</td>';
		tds += '<td>'+ clarityJSON.timeEntry[i].totalHrs;+'</td>';
		tds += '<td> </td>';
		tds += '</tr>';
	}

	// Totals
	tds += '<tr>';
	tds += '<td colspan="2"> <b>Total</b> </td>';
	tds += '<td>'+ clarityJSON.timesheet.day1Hrs+'</td>';
	tds += '<td>'+ clarityJSON.timesheet.day2Hrs;+'</td>';
	tds += '<td>'+ clarityJSON.timesheet.day3Hrs;+'</td>';
	tds += '<td>'+ clarityJSON.timesheet.day4Hrs;+'</td>';
	tds += '<td>'+ clarityJSON.timesheet.day5Hrs;+'</td>';
	tds += '<td>'+ clarityJSON.timesheet.day6Hrs;+'</td>';
	tds += '<td>'+ clarityJSON.timesheet.day7Hrs;+'</td>';
	tds += '<td>'+ clarityJSON.timesheet.totalHrs;+'</td>';
	tds += '<td>'+ clarityJSON.timesheet.status;+'</td>';
	tds += '</tr>';	 

	$('tbody', table).append(tds);	 
}

/* Updates JSON object with data from textarea*/
getClarityJSON = function() {

	var prjktArray = new Array();
	clarityJSON = {
			timesheet: {},
			timeEntry:[],
			user:{}
	};

	// Get textarea content
	var content = document.getElementById('txt_cData').value;

	// timesheet id (If existing timesheet)
	clarityJSON.timesheet.id = $('#timesheet #id').val();
	
	// Get User Name
	var nameStrtIndex = content.indexOf('Resource Name ');
	var text = content.substring(nameStrtIndex+14);
	var endLineIndex = text.indexOf('\n');
	var resourceName = text.substring(0, endLineIndex);

	// Get TimeSheet Status
	var statusStrtIndex =  content.indexOf('Timesheet Status ');
	var statusText = content.substring(statusStrtIndex+17);
	endLineIndex = statusText.indexOf('\n');
	var timeSheetStatus =  trim(statusText.substring(0,endLineIndex));

	clarityJSON.timesheet.status = timeSheetStatus;
	clarityJSON.timesheet.clarityStatus = timeSheetStatus;

	// Get WeekStartDate	
	var dateIndex = content.indexOf('Project ID Project Description');
	var datesArray = content.substring(dateIndex).split('\n');
	var weekStrtDate = datesArray[1] +"/11"; //TODO: Remove hardcoding of year	
	clarityJSON.timesheet.weekStartDate = weekStrtDate; 


	// Get Last Modified Date 
	dateIndex = content.indexOf('Last Modified ');
	var dateText = content.substring(dateIndex+14);
	endLineIndex = statusText.indexOf(' ');
	var lastModifiedDate = dateText.substring(0,endLineIndex);
	clarityJSON.timesheet.lastModifDate = trim(lastModifiedDate);

	// TODO: Find WeekStartDate

	//Get Project Level Details
	var prjktCount = 0;
	text = content.substring(content.indexOf('PRJ'));
	prjktArray = text.split('PRJ');

	for(i=1;i<prjktArray.length;i++){
		var hoursPerDayArray = new Array();
		var PrjktHoursArray = new Array();
		var dayObj = {};

		var prjktText = prjktArray[i];
		endLineIndex =  prjktText.indexOf('     ');
		var hoursText = prjktText.substring(endLineIndex);
		endLineIndex =  hoursText.indexOf('\n');
		var prjktHoursText = hoursText.substring(0,endLineIndex);
		//alert(prjktHoursText)
		var nxtCharIndex = findNextAlphabetIndex(prjktHoursText);
		var prjktHoursString = prjktHoursText.substring(0,nxtCharIndex);
		prjktHoursString = trim(prjktHoursString);

		PrjktHoursArray = prjktHoursString.split('  ');

		// get No. of hours per Project for current week
		var HoursPerProject = PrjktHoursArray[0];

		// find no. of dots
		var dotsCount = HoursPerProject.split(".").length - 1;

		if(dotsCount > 1){
			//Get Project Code
			var prjktCodeEndIndex = prjktText.indexOf(' ');
			var prjktCode = "PRJ"+prjktText.substring(0,prjktCodeEndIndex);

			// Get project Desc
			endLineIndex =  prjktText.indexOf('     ');
			var prjktDesc = prjktText.substring(prjktCode.length-2,endLineIndex);
			//alert("prjktDesc :"+prjktDesc)

			hoursPerDayArray = HoursPerProject.split(' ');
			//alert('hoursPerDayArray '+hoursPerDayArray.length)

			var totHoursperProject = hoursPerDayArray[hoursPerDayArray.length-1];
			clarityJSON.timeEntry[prjktCount] = new Object();
			clarityJSON.timeEntry[prjktCount].projId = prjktCode;
			clarityJSON.timeEntry[prjktCount].projName = prjktDesc;
			clarityJSON.timeEntry[prjktCount].projDesc = prjktDesc;

			// Populate hours per day values
			clarityJSON.timeEntry[prjktCount].day1Hrs = 0.00;
			clarityJSON.timeEntry[prjktCount].day2Hrs = 0.00;
			var day =3;
			while(day<=hoursPerDayArray.length+2-1){

				var dayName = "day"+day+"Hrs";
				clarityJSON.timeEntry[prjktCount][dayName] = hoursPerDayArray[(day-2)-1];
				day++;
			}

			//clarityJSON.timeEntry[prjktCount].days= dayObj;
			clarityJSON.timeEntry[prjktCount].totalHrs = hoursPerDayArray[hoursPerDayArray.length-1];
			prjktCount++;
		}
	}

	//clarityJSON.user.userId = resourceName; // Clarity name; TODO: TBD

	// Code for calculating total hours per week
	var HoursPerWeekStr = content.substring(content.lastIndexOf('Total')+6);
	endLineIndex =  HoursPerWeekStr.indexOf('\n');
	var HoursPerWeekArray = trim(HoursPerWeekStr.substring(0,endLineIndex)).split(' ');
	var totalWeeklyHours= HoursPerWeekArray[HoursPerWeekArray.length-1];
	var totDay2DayHoursIndex = 2;
	var totDay2DayHoursObj = {};

	clarityJSON.timesheet.day1Hrs = 0.00;
	clarityJSON.timesheet.day2Hrs = 0.00;

	var totHoursIndex = 3;
	while(totDay2DayHoursIndex<HoursPerWeekArray.length-1){
		var day = "day"+totHoursIndex+"Hrs";
		clarityJSON.timesheet[day] = HoursPerWeekArray[totDay2DayHoursIndex];
		totHoursIndex++;
		totDay2DayHoursIndex++;
	}
	clarityJSON.timesheet.totalHrs = totalWeeklyHours;

	// Fill user data
	clarityJSON.user.id = $("#id").val();
	clarityJSON.user.assoId = $("#assoId").val();
	clarityJSON.user.systemIP = $("#systemIP").val();
	clarityJSON.user.role = $("#role").val();

	var jsonObj = JSON.stringify(clarityJSON, null, '\t');
	
	//alert(jsonObj);

	//return clarityJSON; 
}

function findNextAlphabetIndex(content){
	var regExp = /^[A-Za-z]$/;
	for(var i = 0; i < content.length; i++){
		if (content.charAt(i).match(regExp)) {
			return i;
		}
	}
}

function trim(str) {
	return str.replace(/^\s+|\s+$/g,"");
}

/*clarityJSON1 = {

	timesheet:{
		"weekStartDate":"2011-07-22",
		"status":"Submitted",
		"clarityStatus": "Submitted",
		"lastModifDate": "2011-07-26",
		"modifBy":268916,
		"day1Hrs":8.00,
		"day2Hrs":8,
		"day3Hrs":8.00,
		"day4Hrs":08.00,
		"day5Hrs":08.00,
		"day6Hrs":08.00,
		"day7Hrs":08.00,
		"totalHrs":40.00
	},
	timeEntry:[
           {
        	   "projId": "xxx",
        	   "projName":"xxx",
        	   "projDesc":"xxx",
        	   "day1Hrs":8.00,
        	   "day2Hrs":8.00,
        	   "day3Hrs":8.00,
        	   "day4Hrs":4.00,
        	   "day5Hrs":4.00,
        	   "day6Hrs":4.00,
        	   "day7Hrs":4.00,
        	   "totalHrs":24.00
           },
           {
        	   "projId": "xxx",
        	   "projName":"xxx",
        	   "projDesc":"xxx",
        	   "day1Hrs":0.00,
        	   "day2Hrs":0.00,
        	   "day3Hrs":0.00,
        	   "day4Hrs":4.00,
        	   "day5Hrs":4.00,
        	   "day6Hrs":4.00,
        	   "day7Hrs":4.00,
        	   "totalHrs":16.00			
           }
     ],
	user:{
		"id":131,
		"assoId":256723,
		"systemIP":"xxx",
		"role":"xxx"
	}
}*/
