package africa.semicolon.lumExpress.services;

import africa.semicolon.lumExpress.data.dtos.request.CreateProductRequest;
import africa.semicolon.lumExpress.data.dtos.request.UpdateProductRequest;
import africa.semicolon.lumExpress.data.dtos.response.CreateProductResponse;
import africa.semicolon.lumExpress.data.models.Product;

import java.util.List;

public interface iProductService {
    CreateProductResponse createProduct(CreateProductRequest productRequest);
    String updateProductDetails(UpdateProductRequest updateProductRequest);
    Product getProductById(Long id);
    List<Product> getAllProducts();
}
