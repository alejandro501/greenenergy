package com.greenenergy.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class BloggerRepository {
	private JdbcTemplate jdbc;
	
	@Autowired
	public BloggerRepository(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

}
