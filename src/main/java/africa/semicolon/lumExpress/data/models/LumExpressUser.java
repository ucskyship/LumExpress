package africa.semicolon.lumExpress.data.models;

import lombok.*;

@Setter
@Getter
public class LumExpressUser {
    private String firstname;
    private String lastName;
    private String email;
    private String password;
    private String phoneNumber;
    private String imageUrl;
}
