package sintval.api.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;


@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
	@NotBlank(message = "First Name is mandatory")
	private String FirstName;
	@NotBlank(message = "Last Name is mandatory")
	private String LastName;
	@NotBlank(message = "UID is mandatory")
	private String UID;
	@Column(columnDefinition = "varchar(255) default Null")
	private String OTP;
	@NotBlank(message = "Email is mandatory")
	private String Email;
	
	public String getOTP() {
		return OTP;
	}
	public void setOTP(String oTP) {
		OTP = oTP;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getUID() {
		return UID;
	}
	public void setUID(String uID) {
		UID = uID;
	}
	
	
	public User(long id, @NotBlank(message = "First Name is mandatory") String firstName,
			@NotBlank(message = "Last Name is mandatory") String lastName,
			@NotBlank(message = "UID is mandatory") String uID, String oTP,
			@NotBlank(message = "Email is mandatory") String email) {
		this.id = id;
		FirstName = firstName;
		LastName = lastName;
		UID = uID;
		OTP = oTP;
		Email = email;
	}
	public User() {}
	public String getFirstName() {
		return FirstName;
	}
	public void setFirstName(String firstName) {
		FirstName = firstName;
	}
	public String getLastName() {
		return LastName;
	}
	public void setLastName(String lastName) {
		LastName = lastName;
	}
	

}
