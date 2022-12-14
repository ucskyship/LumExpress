package africa.semicolon.lumExpress.services;

import africa.semicolon.lumExpress.data.dtos.request.CustomerRegistrationRequest;
import africa.semicolon.lumExpress.data.dtos.request.LoginRequest;
import africa.semicolon.lumExpress.data.dtos.request.UpdateCustomerDetails;
import africa.semicolon.lumExpress.data.dtos.response.CustomerRegistrationResponse;
import africa.semicolon.lumExpress.data.dtos.response.LoginResponse;

public interface iCustomerService {
    CustomerRegistrationResponse register(CustomerRegistrationRequest registerRequest);
    LoginResponse login(LoginRequest loginRequest);
    String completeProfile(UpdateCustomerDetails updateCustomerDetails);
}
