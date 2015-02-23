$(document).ready(function() {

	$('#table_timesheets').dataTable({
		"bPaginate": false,
		"bLengthChange": false,
		"bFilter": true,
		"bSort": false,
		"bInfo": false,
		"bAutoWidth": false });
	
	var oTable = $('#example').dataTable();
	var oSettings = oTable.fnSettings();

} );