package africa.semicolon.lumExpress.data.dtos.response;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddProductResponse {
    private Long productId;
    private String message;
    private int code;
}
