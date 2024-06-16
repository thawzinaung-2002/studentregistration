package spring.service;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

import spring.dto.StudentDTO;

public class StudentService {
	
	public int insertUser(StudentDTO user)
	{
		Connection con = ConnectionClass.getConnection();
		int result = 0;
		try {
			PreparedStatement ps = con.prepareStatement("INSERT INTO student (name, dob, gender, phone, education, course, photo) VALUES (?, ?, ?, ?, ?, ?, ?)");
			ps.setString(1, user.getName());
			ps.setString(2, user.getDob());
			ps.setString(3, user.getGender());
			ps.setString(4, user.getPhone());
			ps.setString(5, user.getEducation());
			ps.setString(6, user.getCourses().toString());
	        byte[] imageData = Base64.getDecoder().decode(user.getBase64Image());
	        Blob imageBlob = con.createBlob();
	        imageBlob.setBytes(1, imageData);
			ps.setBlob(7, imageBlob);
			
			result = ps.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("Insert Student : "+ e.getMessage());
		}
		
		return result;
	}

	public List<StudentDTO> getAllStudents() {
		
		Connection con = ConnectionClass.getConnection();
		List<StudentDTO> students = new ArrayList<>();
		try {
			PreparedStatement ps = con.prepareStatement("select * from student");
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				StudentDTO student = new StudentDTO();
				student.setName(rs.getString("name"));
				student.setId(rs.getInt("id"));
				student.setGender(rs.getString("gender"));
				student.setDob(rs.getString("dob"));
				student.setEducation(rs.getString("education"));
				student.setPhone(rs.getString("phone"));
				String str = rs.getString("course");
				String[] str_arr = str.split(",");
				ArrayList<String> ls = new ArrayList<>();
				for(String s: str_arr)
				{
					ls.add(s);
				}
				student.setCourses(ls);
				Blob imageBlob = rs.getBlob("photo");
				byte[] imageData = imageBlob.getBytes(1, (int)imageBlob.length());
				String base64Image = Base64.getEncoder().encodeToString(imageData);
				student.setBase64Image(base64Image);
				students.add(student);
			}
		} catch (SQLException e) {
			System.out.println("Get All students : "+ e.getMessage());
		}
		
		return students;
	}

	public StudentDTO getUser(int id) {
		
		Connection con = ConnectionClass.getConnection();
		StudentDTO student = null;
		try {
			PreparedStatement ps = con.prepareStatement("select * from student where id=?");
			ps.setInt(1, id);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				student = new StudentDTO();
				student.setName(rs.getString("name"));
				student.setId(rs.getInt("id"));
				student.setGender(rs.getString("gender"));
				student.setDob(rs.getString("dob"));
				student.setEducation(rs.getString("education"));
				String str = rs.getString("course");
				String[] str_arr = str.split(",");
				ArrayList<String> ls = new ArrayList<>();
				for(String s: str_arr)
				{
					ls.add(s);
				}
				student.setCourses(ls);
				student.setPhone(rs.getString("phone"));
				Blob imageBlob = rs.getBlob("photo");
				byte[] imageData = imageBlob.getBytes(1, (int)imageBlob.length());
				String base64Image = Base64.getEncoder().encodeToString(imageData);
				student.setBase64Image(base64Image);
			}
			con.close();
		} catch (SQLException e) {
			System.out.println("Get All students : "+ e.getMessage());
		}
		
		return student;
		
	}

	public int update(StudentDTO user) {
		
		Connection con = ConnectionClass.getConnection();
		int result = 0;
		try {
			PreparedStatement ps = con.prepareStatement("update student set name=?, dob=?, gender=?, phone=?, education=?, course=?, photo=? where id=?");
			ps.setString(1, user.getName());
			ps.setString(2, user.getDob());
			ps.setString(3, user.getGender());
			ps.setString(4, user.getPhone());
			ps.setString(5, user.getEducation());
			ps.setString(6, user.getCourses().toString());
	        byte[] imageData = Base64.getDecoder().decode(user.getBase64Image());
	        Blob imageBlob = con.createBlob();
	        imageBlob.setBytes(1, imageData);
			ps.setBlob(7, imageBlob);
			ps.setInt(8, user.getId());
			
			result = ps.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("Update Student : "+ e.getMessage());
		}
		
		return result;
		
	}

	public int deleteById(int id) {
		Connection con = ConnectionClass.getConnection();
		int result = 0;
		try {
			PreparedStatement ps = con.prepareStatement("DELETE FROM student WHERE id =?");
			ps.setInt(1, id);
			result = ps.executeUpdate();
			con.close();
		} catch (SQLException e) {
			System.out.println("Admin insert: "+ e.getMessage());
		}
		
		return result;
		
	}

}
