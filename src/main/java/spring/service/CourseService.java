package spring.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import spring.dto.CourseDTO;

public class CourseService {

	public int addCourse(CourseDTO dto)
	{
		Connection con=ConnectionClass.getConnection();
		int result = 0;
		try {
			PreparedStatement ps = con.prepareStatement("INSERT INTO course (id, name) VALUES (?,?)");
			ps.setString(1,dto.getId());
			ps.setString(2, dto.getName());
			result = ps.executeUpdate();
			con.close();
		} catch (SQLException e) {
			System.out.println("Course insert: "+ e.getMessage());
		}
		return result;
	}
	
	
	public Map<String, String> getCourses()
	{
		Connection con=ConnectionClass.getConnection();
		Map<String, String> courses = new HashMap<>();
		try {
			PreparedStatement ps = con.prepareStatement("select * from course");
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				courses.put(rs.getString("id"), rs.getString("name"));
			}
			con.close();
		} catch (SQLException e) {
			System.out.println("Course insert: "+ e.getMessage());
		}
		return courses;
	}
	
}
