package africa.semicolon.lumExpress.services;

import africa.semicolon.lumExpress.data.models.Cart;
import africa.semicolon.lumExpress.data.repositories.CartRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CartServiceImpl implements iCartService{
    private final CartRepository cartRepository;
}
