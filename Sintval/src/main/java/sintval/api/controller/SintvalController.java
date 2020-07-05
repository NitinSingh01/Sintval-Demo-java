package sintval.api.controller;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import sintval.api.entity.User;
import sintval.api.exception.InvalidInputParameterException;
import sintval.api.exception.UserNotFoundException;
import sintval.api.repository.ValidatorRepository;




@RestController
public class SintvalController {
	@Autowired
	ValidatorRepository repository; 
	
	
	@RequestMapping(value = "/external/validator/uid/v1/validate", method = RequestMethod.POST)
	public String getDetails(@Valid @RequestBody User uid){ 
		if(uid.getFirstName()=="" | uid.getLastName()=="" | uid.getUID()=="")
		{throw new InvalidInputParameterException("Invalid Input");}
		
		List<User> a = repository.getUserById(uid);
		if(a.isEmpty())
		{throw new UserNotFoundException("User id '" + uid.getUID() + "' does no exist");}
		repository.updateOTP(uid.getUID());
		repository.sendEmail(uid.getUID());
		return "OTP is sent to your registered email id. Please authenticate by entering received OTP.";
	}	
	
	
	@RequestMapping(value = "/external/validator/uid/v1/verify/otp", method = RequestMethod.POST)
	public String otpVerification(@RequestBody User uid)
	{	
		int a = repository.verifyOTP(uid);
		System.out.println(a);
		if(a==1)
			return "Validation successful";
		else
			return "Authentication failed. Please try again.";
	}
	
	
	@RequestMapping(value= "/external/validator/uid/v1/users", method = RequestMethod.POST )
	public String Insert_User(@Valid @RequestBody User uid){
		int a=repository.insertuser(uid);
		if(a==1)
		{return "Record Inserted";}
		else
		{return "Server Down";}
	}
	
	
	@RequestMapping(value= "/external/validator/uid/v1/users", method = RequestMethod.GET )
	public List<User> findUser(){
		return repository.AllUser();
	
	}

	
}


