package in.shop.binding;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {

	  private String userId;
	  
	       private String name;
	   
			private String email;
			
		    private String password;
		    
		    private String role;
		    
		    private Timestamp createdAt;
		    
		    private Timestamp updatedAt;
}
