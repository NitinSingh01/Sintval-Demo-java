package sintval.api.repository;

import java.sql.ResultSet; 
import java.sql.SQLException;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Repository;

import sintval.api.entity.User;



@Repository
public class ValidatorRepository {
	@Autowired
	JavaMailSender javaMailSender;
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public List<User> getUserById(User uid) {
		return jdbcTemplate.query("select * from UID where uid =\""+uid.getUID()+"\""+" And first_name =\""+uid.getFirstName()+"\""+" And last_name =\""+uid.getLastName()+"\""+";", new RowMapper<User>(){
		      public User mapRow(ResultSet rs, int arg1) throws SQLException {
		
		        User p = new User();
		        p.setFirstName(rs.getString("first_name"));
		        p.setLastName(rs.getString("last_name"));
		        p.setUID(rs.getString("uid"));
		        p.setOTP(rs.getString("otp"));
		        p.setEmail(rs.getString("email"));
		        return p;}}
);
	
	}
	public List<User> AllUser() {
		return jdbcTemplate.query("select * from UID;", new RowMapper<User>(){
		      public User mapRow(ResultSet rs, int arg1) throws SQLException {
		
		        User p = new User();
		        p.setFirstName(rs.getString("first_name"));
		        p.setLastName(rs.getString("last_name"));
		        p.setUID(rs.getString("uid"));
		        p.setOTP(rs.getString("otp"));
		        p.setEmail(rs.getString("email"));
		        return p;}}
);
	}
	public int insertuser(User uid) {	
	jdbcTemplate.execute("INSERT INTO UID(first_name,last_name,uid,email) values(\""+uid.getFirstName()+"\",\""+uid.getLastName()+"\",\""+uid.getUID()+"\",\""+uid.getEmail()+"\");");
	
	return 1;
}
	public String getEmail(String UID) {
		String sql = "Select email from UID where uid = ?";
		String email = (String) jdbcTemplate.queryForObject(
	            sql, new Object[] { UID }, String.class);
		return email;
	}
	public void updateOTP(String uid) {
		String c = generateOTP();
		jdbcTemplate.execute("Update UID set otp =\""+c+"\" where uid=\""+uid+"\";");
	}
	public int getOTP(String uid) {
		String sql = "Select otp from UID where uid = ?";
		String otp = (String) jdbcTemplate.queryForObject(
	            sql, new Object[] { uid }, String.class);
		return Integer.parseInt(otp);
	}
	public void sendEmail(String uid) {
		SimpleMailMessage mail = new SimpleMailMessage();
		String email = getEmail(uid);
		int otp = getOTP(uid);
		mail.setTo(email);
		mail.setSubject("Sintval Authentication Code");
		mail.setText("Hi! Your OTP is:" + otp);
		javaMailSender.send(mail);
	}
	public String generateOTP() 
	    { 
	        String numbers = "0123456789"; 
	        Random rndm_method = new Random(); 
	        char[] otp = new char[6]; 
	        for (int i = 0; i < 6; i++) 
	        {  
	            otp[i] = 
	             numbers.charAt(rndm_method.nextInt(numbers.length())); 
	        }
	        String str2 = String.valueOf(otp);
	        return str2; 
	
	}
	public int verifyOTP(User uid) {
		List<User> u = jdbcTemplate.query("select uid from UID where uid =\""+uid.getUID()+"\""+" And otp =\""+uid.getOTP()+"\""+";", new RowMapper<User>(){
		      public User mapRow(ResultSet rs, int arg1) throws SQLException {
		
		        User p = new User();
		        p.setUID(rs.getString("uid"));
		        
		        return p;}}
);
		System.out.println(u);
		if(u.isEmpty())
			{return 0;}
		else 
			return 1;
	}
}
