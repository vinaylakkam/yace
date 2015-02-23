package com.vspace.yace.testing;

import org.springframework.jdbc.core.JdbcTemplate;

import com.vspace.yace.domain.User;

public class A {
	
	private JdbcTemplate jdbcTemplate;
	
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}


	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public String sayHello(String user){
		return "Hello" + user;
	}
	
	public void addUser(User user) {
		String sql = " insert into USER (ID, CNAME) values("+ user.getId()+ ",'"+ user.getcName()+"')";
		System.out.println("sql--->"+sql);

		jdbcTemplate.execute(sql);
		
	}	
}
