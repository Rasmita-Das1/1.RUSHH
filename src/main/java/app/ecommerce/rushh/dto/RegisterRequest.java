package app.ecommerce.rushh.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterRequest {
	
    private String name;
    private String email;
    private String password;
    
}
