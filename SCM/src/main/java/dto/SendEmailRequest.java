package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data  // generates getters/setters, toString(), equals(), hashCode()
@NoArgsConstructor // required by Spring when mapping JSON to Java object
@AllArgsConstructor
public class SendEmailRequest {

    
    private String to;
    private String subject;
    private String body;
}
