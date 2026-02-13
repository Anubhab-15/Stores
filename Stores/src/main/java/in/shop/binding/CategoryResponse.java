package in.shop.binding;

import java.sql.Timestamp;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoryResponse {

     	 private String categoryid;
	    private String name;
	    private String description;
	    private String bgcolor;
        private Timestamp createdAt;
	    
        private String imgurl;
	    
	    private Timestamp updatedAt;
	    
	    
}
