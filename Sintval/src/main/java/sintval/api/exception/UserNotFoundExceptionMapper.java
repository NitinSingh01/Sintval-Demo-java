package sintval.api.exception;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import sintval.api.error.ErrorMessage;

@Provider
public class UserNotFoundExceptionMapper implements ExceptionMapper<UserNotFoundException> {
	
	
	@Override
	public Response toResponse(UserNotFoundException ex) {
		ErrorMessage errorMessage= new ErrorMessage(ex.getMessage(), 404, "User parameter are not matched.");
		return Response.status(Status.NOT_FOUND)
				.type(MediaType.APPLICATION_JSON)
			    .entity(errorMessage)
			    .build();
	
	}

}
