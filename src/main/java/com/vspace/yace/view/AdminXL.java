package com.vspace.yace.view;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse; 

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import com.vspace.yace.command.UserTimesheet;
import com.vspace.yace.domain.TimeEntry;
import com.vspace.yace.util.DateUtil;

public class AdminXL extends AbstractExcelView {

	@Override
	protected void buildExcelDocument(Map<String, Object> model,
			HSSFWorkbook wb, HttpServletRequest req, HttpServletResponse resp)
	throws Exception {

		System.out.println("REALLY?? ");

		HSSFSheet sheet;
		HSSFRow sheetRow;
		HSSFCell cell;

		// Get sheetName as currentWeek //TODO: Pass current week, when history functionality is available
		Date currWeek = DateUtil.getClarityWeekStartDate();
		String sheetName = new SimpleDateFormat("MMddyyyy").format(currWeek);

		// Create the sheet
		sheet = wb.createSheet(sheetName);
		sheet.setDefaultColumnWidth(12);

		// Headers
		getCell(sheet, 0, 0).setCellValue("Name");
		getCell(sheet, 0, 1).setCellValue("Project");
		getCell(sheet, 0, 2).setCellValue("Hours");
		getCell(sheet, 0, 3).setCellValue("Total Hours");
		getCell(sheet, 0, 4).setCellValue("Status");
		getCell(sheet, 0, 5).setCellValue("Comments");
		

		List<UserTimesheet> userTimesheets = (List<UserTimesheet>) model.get("userTimesheets");

		int row=1;
		for(UserTimesheet ut: userTimesheets) {
			int col = 0;

			// User name (0)
			cell = getCell(sheet, row, 0);
			cell.setCellValue(ut.getUser().getfName()+" " + ut.getUser().getfName());

			if(!ut.isDefaulter()) {
				
				row--;
				for(TimeEntry timeEntry: ut.getTimesheet().getTimeEntries()) {

					row++; // next row
					
					// Project Name (1)
					cell = getCell(sheet, row, 1);
					cell.setCellValue(timeEntry.getProjName());

					// Hours (2)
					cell = getCell(sheet, row, 2);
					cell.setCellValue(timeEntry.getTotalHrs());
				}

				// Total (3)
				cell = getCell(sheet, ++row, 3);
				cell.setCellValue(ut.getTimesheet().getTotalHrs());

				// Status(4)
				cell = getCell(sheet, row, 4);
				cell.setCellValue(ut.getTimesheet().getStatus());
			}
			else {
				// Comments (5)
				cell = getCell(sheet, row, 5);
				cell.setCellValue("Defaulter");
			}

		}
	}
}
