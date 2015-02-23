package com.vspace.yace.dao;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.stereotype.Repository;

import com.vspace.yace.domain.User;

@Repository
public class UserDAO /*extends JdbcDaoSupport*/{


	/*	@Autowired
	JdbcTemplate jdbcTemplate;
	 */
	private SimpleJdbcTemplate simpleJdbcTemplate;
	private SimpleJdbcInsert insertUser;

	@Autowired
	public void init(DataSource dataSource) {
		
		this.simpleJdbcTemplate = new SimpleJdbcTemplate(dataSource);
		
		this.insertUser = new SimpleJdbcInsert(dataSource)
							.withTableName("USER");
							/*.usingGeneratedKeyColumns("ID");*/
		
	}

	// TODO: Is there any difference of giving @Autowired on field injection and setter injection


	/**
	 * Loads the {@link User} with the supplied <code>client IP</code>;
	 * Returns a new {@link User}, if not already registered with the system.
	 * 
	 * @param clientIP
	 * @return
	 */
	public User loadUser(String clientIP) throws DataAccessException {

		User user;
		try {
			// Load user if already exists
			String sql ="select ID, ASSO_ID, FNAME, LNAME, EMAIL, SYSTEM_IP, ROLE from USER where SYSTEM_IP = ?";
			RowMapper<User> mapper = new RowMapper<User>(){
				@Override
				public User mapRow(ResultSet rs, int rowNum)
				throws SQLException {
					
					User user = new User();
					user.setId(rs.getInt("ID"));
					user.setAssoId(rs.getInt("ASSO_ID")); 
					user.setfName(rs.getString("FNAME"));
					user.setlName(rs.getString("LNAME"));
					user.seteMail(rs.getString("EMAIL"));
					user.setRole(rs.getString("ROLE"));
					user.setSystemIP(rs.getString("SYSTEM_IP"));
					
					return user;
				}
			};
			user = simpleJdbcTemplate.queryForObject(sql, mapper, clientIP);
		}
		catch (EmptyResultDataAccessException ex) {
			System.out.println("User doesn't exist");
			// User doesn't exist
			user = new User();
			user.setSystemIP(clientIP);
		}
		
		return user; 
	}

	public void saveUser(User user) throws DataAccessException {

		System.out.println("In saveUser(): isNew()-->" + user.isNew());
		
		if(user.isNew()){
			// Insert new user
			String sql = "INSERT INTO user (asso_id, fname, lname, email, system_ip) " +
						 "VALUES(:assoId, :fName, :lName, :eMail, :systemIP)";
			this.simpleJdbcTemplate.update(sql,createUserParameterSource(user));
			
			user.setId(0);
			System.out.println("*******USER ADDED: \n asso id:"+user.getAssoId());
		}
		else {
			// Update existing user
			this.simpleJdbcTemplate.update(
					"UPDATE user SET asso_id=:assoId, fname=:fName, lname=:lName, " +
					"email=:eMail, system_ip=:systemIP " +
					"WHERE system_ip=:systemIP",
					createUserParameterSource(user));
			System.out.println("*******USER MODIFIED: \n asso id:"+user.getAssoId());
		}
	}

	/**
	 * Creates a {@link MapSqlParameterSource} based on data values from the
	 * supplied {@link User} instance.
	 */
	private MapSqlParameterSource createUserParameterSource(User user) {
		
		return new MapSqlParameterSource()
			.addValue("assoId", user.getAssoId())
			.addValue("fName", user.getfName())
			.addValue("lName", user.getlName())
			.addValue("eMail", user.geteMail()+"@cognizant.com")
			.addValue("systemIP", user.getSystemIP());
	}

	public List<User> getAllUsers() {
		
		String sql = "SELECT * FROM USER";
		RowMapper<User> rm = new RowMapper<User>() {

			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User user = new User();
				user.setId(rs.getInt("ID"));
				user.setAssoId(rs.getInt("ASSO_ID"));
				user.setfName(rs.getString("FNAME"));
				user.setlName(rs.getString("LNAME"));
				user.seteMail(rs.getString("EMAIL"));
				
				return user;
			}
			
		};
		// Execute and return query
		return this.simpleJdbcTemplate.query(sql, rm);
	}
	
	
}
