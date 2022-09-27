package africa.semicolon.lumExpress.data.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class CreateProductRequest {
    private String productName;
    private BigDecimal price;
    private String category;
    private MultipartFile imageUrl;
    private int quantity;
}
