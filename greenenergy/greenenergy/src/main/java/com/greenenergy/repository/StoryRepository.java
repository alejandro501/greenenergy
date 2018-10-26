package com.greenenergy.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.greenenergy.domain.Blogger;
import com.greenenergy.domain.Story;

@Repository
public class StoryRepository {
	private JdbcTemplate jdbc;

	@Autowired
	public StoryRepository(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}
	
	//With lambda expression
	public List<Story> findAll(){
		String sql = "SELECT * FROM story ORDER BY posted DESC";
		return jdbc.query(sql, (rs,i) -> new Story(
				rs.getString("title"),
				rs.getString("content"),
				rs.getDate("posted"),
				rs.getLong("blogger_id")
				));
	}
	
	//Without lambda expression
	public Story findByTitle(String title) {
		String sql = "SELECT * FROM story WHERE title = ? ORDER BY posted DESC";
		return jdbc.queryForObject(
				sql, 
				new Object[] {title},
				new RowMapper<Story>() {
					public Story mapRow(ResultSet rs, int rowNum) throws SQLException {
						Story story = new Story();
						story.setId(rs.getLong("id"));
						story.setTitle(rs.getString("title"));
						story.setContent(rs.getString("content"));
						story.setPosted(rs.getDate("posted"));
						story.setBlogger(new Blogger(rs.getLong("blogger_id"), "Alex", 25));
						return story;
					}
				}
								   );
	}
}
