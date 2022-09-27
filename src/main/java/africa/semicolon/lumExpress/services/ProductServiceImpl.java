package africa.semicolon.lumExpress.services;

import africa.semicolon.lumExpress.data.dtos.request.CreateProductRequest;
import africa.semicolon.lumExpress.data.dtos.request.UpdateProductRequest;
import africa.semicolon.lumExpress.data.dtos.response.CreateProductResponse;
import africa.semicolon.lumExpress.data.models.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements iProductService{

    @Override
    public CreateProductResponse createProduct(CreateProductRequest productRequest) {

        return null;
    }

    @Override
    public String updateProductDetails(UpdateProductRequest updateProductRequest) {
        return null;
    }

    @Override
    public Product getProductById(Long id) {
        return null;
    }

    @Override
    public List<Product> getAllProducts() {
        return null;
    }
}
