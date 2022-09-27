package africa.semicolon.lumExpress.data.dtos.request;

import africa.semicolon.lumExpress.data.models.Category;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateProductRequest {
    private String productName;
    private BigDecimal price;
    private String productCategory;
    private MultipartFile imageUrl;
    private int quantity;
}
