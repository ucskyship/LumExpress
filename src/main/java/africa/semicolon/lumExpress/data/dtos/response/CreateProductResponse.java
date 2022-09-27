package africa.semicolon.lumExpress.data.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateProductResponse {
    private Long productId;
    private String message;
    private int code;
}
