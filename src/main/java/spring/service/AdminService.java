package spring.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import spring.dto.UserDTO;
import spring.model.AdminBean;

public class AdminService {
	
	public boolean checkEmail(String email) {
		Connection con = ConnectionClass.getConnection();
		boolean result = false;
		try {
			PreparedStatement ps = con.prepareStatement("select * from admin where email=?");
			ps.setString(1, email);
			result = ps.execute();
			con.close();
		} catch (SQLException e) {
			System.out.println("Get Admin 1 : "+ e.getMessage());
		}
		return result;
	}
	
	public UserDTO getUser(UserDTO adminDto) {
		Connection con = ConnectionClass.getConnection();
		UserDTO admin = null;
		try {
			PreparedStatement ps = con.prepareStatement("select * from admin where email=? and password=?");
			ps.setString(1, adminDto.getEmail());
			ps.setString(2, adminDto.getPassword());
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				admin = new UserDTO();
				admin.setEmail(rs.getString("email"));
				admin.setPassword(rs.getString("password"));
				admin.setName(rs.getString("name"));
			}
			con.close();
		} catch (SQLException e) {
			System.out.println("Get Admin 2 : "+ e.getMessage());
		}
		return admin;
	}

	public int addUser(UserDTO adminDto) {
		Connection con = ConnectionClass.getConnection();
		int result = 0;
		try {
			PreparedStatement ps = con.prepareStatement("INSERT INTO admin (email, password, name) VALUES (?, ?, ?)");
			ps.setString(1, adminDto.getEmail());
			ps.setString(2, adminDto.getPassword());
			ps.setString(3, adminDto.getName());
			result = ps.executeUpdate();
			con.close();
		} catch (SQLException e) {
			System.out.println("Admin insert: "+ e.getMessage());
		}
		
		return result;
	}

	
	
}
