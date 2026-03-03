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

@Entity
@Data
@Table(name = "user_tbl")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
	@Column(unique=true)
    private String userId;
   

    private String name;
   
	private String email;
	
    private String password;
    
    private String role;
    
    @Column(updatable=false)
    @CreationTimestamp
    private Timestamp createdAt;
    
    @UpdateTimestamp
    private Timestamp updatedAt;
}
