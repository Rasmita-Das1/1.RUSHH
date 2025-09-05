package app.ecommerce.rushh.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginRequest {
	
    private String email;
    private String password;
    
}
