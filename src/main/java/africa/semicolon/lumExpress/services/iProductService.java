package africa.semicolon.lumExpress.services;

import africa.semicolon.lumExpress.data.dtos.request.AddProductRequest;
import africa.semicolon.lumExpress.data.dtos.request.UpdateProductRequest;
import africa.semicolon.lumExpress.data.dtos.response.AddProductResponse;
import africa.semicolon.lumExpress.data.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.IOException;
import java.util.List;

public interface iProductService {
    AddProductResponse addProduct(AddProductRequest productRequest) throws IOException;
    String updateProductDetails(UpdateProductRequest updateProductRequest);
    Product getProductById(Long id);
    Page<Product> getAllProducts();
    String deleteProduct(Long id);
}
