package com.vspace.yace.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.vspace.yace.domain.TimeEntry;
import com.vspace.yace.domain.Timesheet;
import com.vspace.yace.domain.User;

@Repository
public class TimesheetDAO {

	private SimpleJdbcTemplate simpleJdbcTemplate;
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void init(DataSource dataSource) {

		this.simpleJdbcTemplate = new SimpleJdbcTemplate(dataSource);
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}	

	/*
	 * Saves Timesheet
	 */		
	public void saveTimesheet(User user, Timesheet timesheet,
			List<TimeEntry> timeEntryList) {


		/* This 
		/*final String insertSQL = "INSERT INTO TIMESHEET (USER_ID, WEEK_START_DT, STATUS, CLARITY_STATUS, LAST_MODIF_DT, " +
			 "MODIF_BY, DAY1_HRS, DAY2_HRS, DAY3_HRS, DAY4_HRS, DAY5_HRS, DAY6_HRS, DAY7_HRS, TOT_HRS) " +
			 "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		KeyHolder keyHolder = new GeneratedKeyHolder();
		this.jdbcTemplate.update(
			    new PreparedStatementCreator() {
			    	@Override
			    	public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
			            PreparedStatement ps =
			                connection.prepareStatement(insertSQL);
			            //ps.setInt(1, user.getId());


			            return ps;
			        }
			    },
			    keyHolder);*/

		String sql;
		// DELETE TIMESHEET if already exists
		if(!timesheet.isNew()) {
			
			// Delete existing timeentry and timesheet records
			sql = "DELETE FROM TIME_ENTRY WHERE TS_ID= ? ";
			this.simpleJdbcTemplate.update(sql, timesheet.getId());
			
			sql = "DELETE FROM TIMESHEET WHERE ID= ? ";
			this.simpleJdbcTemplate.update(sql, timesheet.getId());
		}

		// SAVE TIMESHEET
		sql = "INSERT INTO TIMESHEET (USER_ID, WEEK_START_DT, STATUS, CLARITY_STATUS, LAST_MODIF_DT, " +
		"MODIF_BY, DAY1_HRS, DAY2_HRS, DAY3_HRS, DAY4_HRS, DAY5_HRS, DAY6_HRS, DAY7_HRS, TOT_HRS) " +
		"VALUES (:user_id, :week_start_dt, :status, :clarity_status, :last_modif_dt, " + 
		":modif_by, :day1_hrs, :day2_hrs, :day3_hrs, :day4_hrs, :day5_hrs, :day6_hrs, :day7_hrs, :TOT_HRS)";

		System.out.println("TIMESHEET sql-->"+ sql);
		MapSqlParameterSource tsParamsMap = createTimesheetParameterSource(timesheet, user);
		System.out.println("tsParamsMap--->" + tsParamsMap.getValues());
		int rows = this.simpleJdbcTemplate.update(sql, tsParamsMap);

		// Get current timesheet id; TODO: Get it automatically by using preparedstatement as in commented code
		int currTimesheetId = simpleJdbcTemplate.queryForInt("select max(ID) FROM TIMESHEET");

		System.out.println("currTimesheetId--->"+ currTimesheetId);

		// SAVE TIMEENTRY
		for(TimeEntry timeEntry: timeEntryList) {
			sql = "INSERT INTO TIME_ENTRY (USER_ID, TS_ID, PROJECT_ID, PROJECT, PROJECT_DESC, " +
			"DAY1_HRS, DAY2_HRS, DAY3_HRS, DAY4_HRS, DAY5_HRS, DAY6_HRS, DAY7_HRS, TOT_HRS) " +
			"VALUES (:user_id, :ts_id, :project_id, :project, :project_desc, " + 
			":day1_hrs, :day2_hrs, :day3_hrs, :day4_hrs, :day5_hrs, :day6_hrs, :day7_hrs, :TOT_HRS)";

			System.out.println("TIMEENTRY sql-->"+ sql);	 
			MapSqlParameterSource teParamsMap = createTimeEntryParameterSource(timeEntry, timesheet, user, currTimesheetId);
			System.out.println("teParamsMap-->" +teParamsMap.getValues()); 
			rows = this.simpleJdbcTemplate.update(sql, teParamsMap);
		}
		
	}

	private MapSqlParameterSource createTimesheetParameterSource(Timesheet timesheet, User user) {

		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yy");
		return new MapSqlParameterSource()
		.addValue("user_id", user.getId())
		.addValue("week_start_dt", sdf.format(timesheet.getWeekStartDate()))
		.addValue("status", timesheet.getStatus())
		.addValue("clarity_status", timesheet.getClarityStatus())
		.addValue("last_modif_dt", sdf.format(timesheet.getLastModifDate()))
		.addValue("modif_by", user.getAssoId())
		.addValue("day1_hrs", timesheet.getDay1Hrs())
		.addValue("day2_hrs", timesheet.getDay2Hrs())
		.addValue("day3_hrs", timesheet.getDay3Hrs())
		.addValue("day4_hrs", timesheet.getDay4Hrs())
		.addValue("day5_hrs", timesheet.getDay5Hrs())
		.addValue("day6_hrs", timesheet.getDay6Hrs())
		.addValue("day7_hrs", timesheet.getDay7Hrs())
		.addValue("TOT_HRS", timesheet.getTotalHrs());
	}

	private MapSqlParameterSource createTimeEntryParameterSource(TimeEntry timeEntry, Timesheet timesheet, User user, int currTimesheetId) {

		return new MapSqlParameterSource()
		.addValue("user_id", user.getId())
		.addValue("ts_id", currTimesheetId)
		.addValue("project_id", timeEntry.getProjId())
		.addValue("project", timeEntry.getProjName())
		.addValue("project_desc", timeEntry.getProjDesc())
		.addValue("day1_hrs", timeEntry.getDay1Hrs())
		.addValue("day2_hrs", timeEntry.getDay2Hrs())
		.addValue("day3_hrs", timeEntry.getDay3Hrs())
		.addValue("day4_hrs", timeEntry.getDay4Hrs())
		.addValue("day5_hrs", timeEntry.getDay5Hrs())
		.addValue("day6_hrs", timeEntry.getDay6Hrs())
		.addValue("day7_hrs", timeEntry.getDay7Hrs())
		.addValue("TOT_HRS", timeEntry.getTotalHrs());
	}

	/**
	 * Get timesheets for the weekstartdate
	 * 
	 * @param weekStartDate
	 * @return
	 */
	public List<Timesheet> getTimesheets(Date weekStartDate) {

		// Format weekStartDate
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yy");
		String weekStartDateStr = sdf.format(weekStartDate);

		// SQL string
		String sql = "SELECT * FROM TIMESHEET WHERE WEEK_START_DT = ?";
		RowMapper<Timesheet> rm = new RowMapper<Timesheet>() {

			@Override
			public Timesheet mapRow(ResultSet rs, int rowNum) throws SQLException {

				Timesheet timesheet = new Timesheet();
				timesheet.setId(rs.getInt("id"));
				timesheet.setUserId(rs.getInt("USER_ID"));
				timesheet.setWeekStartDate(rs.getDate("WEEK_START_DT"));
				timesheet.setStatus(rs.getString("STATUS"));
				timesheet.setClarityStatus(rs.getString("CLARITY_STATUS"));
				timesheet.setLastModifDate(rs.getDate("LAST_MODIF_DT"));
				timesheet.setModifBy(rs.getInt("MODIF_BY"));
				timesheet.setDay1Hrs(rs.getDouble("DAY1_HRS"));
				timesheet.setDay2Hrs(rs.getDouble("DAY2_HRS"));
				timesheet.setDay3Hrs(rs.getDouble("DAY3_HRS"));
				timesheet.setDay4Hrs(rs.getDouble("DAY4_HRS"));
				timesheet.setDay5Hrs(rs.getDouble("DAY5_HRS"));
				timesheet.setDay6Hrs(rs.getDouble("DAY6_HRS"));
				timesheet.setDay7Hrs(rs.getDouble("DAY7_HRS"));
				timesheet.setTotalHrs(rs.getDouble("TOT_HRS"));

				return timesheet;
			}

		};

		// Execute and return query
		return this.simpleJdbcTemplate.query(sql, rm, weekStartDateStr);
	}

	/**
	 * Get timesheet for the weekstartdate of the user
	 * 
	 * @param weekStartDate
	 * @return
	 */
	public Timesheet getTimesheet(Date weekStartDate, Integer userId) {

		// Format weekStartDate
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yy");
		String weekStartDateStr = sdf.format(weekStartDate);
		
		Timesheet timesheet; 
		try {
			// SQL string
			String sql = "SELECT * FROM TIMESHEET WHERE WEEK_START_DT = ? AND USER_ID = ?";
			RowMapper<Timesheet> rm = new RowMapper<Timesheet>() {

				@Override
				public Timesheet mapRow(ResultSet rs, int rowNum) throws SQLException {

					Timesheet timesheet = new Timesheet();
					timesheet.setId(rs.getInt("id"));
					timesheet.setUserId(rs.getInt("USER_ID"));
					timesheet.setWeekStartDate(rs.getDate("WEEK_START_DT"));
					timesheet.setStatus(rs.getString("STATUS"));
					timesheet.setClarityStatus(rs.getString("CLARITY_STATUS"));
					timesheet.setLastModifDate(rs.getDate("LAST_MODIF_DT"));
					timesheet.setModifBy(rs.getInt("MODIF_BY"));
					timesheet.setDay1Hrs(rs.getDouble("DAY1_HRS"));
					timesheet.setDay2Hrs(rs.getDouble("DAY2_HRS"));
					timesheet.setDay3Hrs(rs.getDouble("DAY3_HRS"));
					timesheet.setDay4Hrs(rs.getDouble("DAY4_HRS"));
					timesheet.setDay5Hrs(rs.getDouble("DAY5_HRS"));
					timesheet.setDay6Hrs(rs.getDouble("DAY6_HRS"));
					timesheet.setDay7Hrs(rs.getDouble("DAY7_HRS"));
					timesheet.setTotalHrs(rs.getDouble("TOT_HRS"));

					return timesheet;
				}

			};

			System.out.println("SQL-->" + sql);
			// Execute and return query
			timesheet = this.simpleJdbcTemplate.queryForObject(sql, rm, weekStartDateStr, userId);
		}
		catch (EmptyResultDataAccessException ex) {
			System.out.println("User doesn't exist");
			// User doesn't exist
			timesheet = new Timesheet();
		}		
		
		return timesheet;
	}

	/**
	 * Get timeentries of a timesheet 
	 * 
	 * @param weekStartDate
	 * @return
	 */
	public List<TimeEntry> getTimeEntries(Integer timesheetId) {

		// SQL string
		String sql = "SELECT * FROM TIME_ENTRY WHERE TS_ID = ?";
		RowMapper<TimeEntry> rm = new RowMapper<TimeEntry>() {

			@Override
			public TimeEntry mapRow(ResultSet rs, int rowNum) throws SQLException {

				TimeEntry timeEntry = new TimeEntry();
				timeEntry.setId(rs.getInt("id"));
				timeEntry.setUserId(rs.getInt("USER_ID"));
				timeEntry.setTsId(rs.getInt("TS_ID"));
				timeEntry.setProjId(rs.getString("PROJECT_ID"));
				timeEntry.setProjName(rs.getString("PROJECT"));
				timeEntry.setProjDesc(rs.getString("PROJECT_DESC"));
				timeEntry.setDay1Hrs(rs.getDouble("DAY1_HRS"));
				timeEntry.setDay2Hrs(rs.getDouble("DAY2_HRS"));
				timeEntry.setDay3Hrs(rs.getDouble("DAY3_HRS"));
				timeEntry.setDay4Hrs(rs.getDouble("DAY4_HRS"));
				timeEntry.setDay5Hrs(rs.getDouble("DAY5_HRS"));
				timeEntry.setDay6Hrs(rs.getDouble("DAY6_HRS"));
				timeEntry.setDay7Hrs(rs.getDouble("DAY7_HRS"));
				timeEntry.setTotalHrs(rs.getDouble("TOT_HRS"));

				return timeEntry;
			}

		};

		System.out.println("SQL-->" + sql);
		// Execute and return query
		return this.simpleJdbcTemplate.query(sql, rm, timesheetId);
	}



	/**
	 * Get Timesheet with timeentries of a user for week start date 
	 * @param currWeekStartDate
	 * @param userId
	 * @return
	 */
	public Timesheet getTimesheetWithTimeEntries(Date weekStartDate, Integer userId) {

		// Get timesheet
		Timesheet timesheet = this.getTimesheet(weekStartDate, userId);

		if(! timesheet.isNew()) {
			// Get timeEntries
			List<TimeEntry> timeEntries = this.getTimeEntries(timesheet.getId());
	
			// Add timeEntries to timesheet
			timesheet.setTimeEntries(timeEntries);
		}

		return timesheet;
	}	

}
