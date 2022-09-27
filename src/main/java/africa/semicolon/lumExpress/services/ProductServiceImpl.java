package africa.semicolon.lumExpress.services;

import africa.semicolon.lumExpress.data.dtos.request.CreateProductRequest;
import africa.semicolon.lumExpress.data.dtos.request.UpdateProductRequest;
import africa.semicolon.lumExpress.data.dtos.response.CreateProductResponse;
import africa.semicolon.lumExpress.data.models.Category;
import africa.semicolon.lumExpress.data.models.Product;
import africa.semicolon.lumExpress.data.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements iProductService{
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper = new ModelMapper();
    @Override
    public CreateProductResponse createProduct(CreateProductRequest productRequest) {
        var product = modelMapper.map(productRequest, Product.class);
        product.getCategory().add(Category.valueOf(productRequest.getProductCategory()));
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
