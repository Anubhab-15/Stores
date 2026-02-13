package in.shop.entity;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "category_tbl")
public class CategoryEntity {

	
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
        @Column(unique=true)
	    private String categoryid;
        @Column(unique=true)
	    private String name;
	    private String description;
	    private String bgcolor;
	    private String imgurl;
	    
	    @Column(updatable=false)
	    @CreationTimestamp
	    private Timestamp createdAt;
	    
	    @UpdateTimestamp
	    private Timestamp updatedAt;
	    
	    
	    

}
