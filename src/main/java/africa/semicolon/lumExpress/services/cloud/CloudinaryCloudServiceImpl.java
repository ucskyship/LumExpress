package africa.semicolon.lumExpress.services.cloud;

import africa.semicolon.lumExpress.data.dtos.response.ImageUrl;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.Map;

@Service
@AllArgsConstructor
public class CloudinaryCloudServiceImpl implements iCloudService{
//    @Value("${cloudinary.cloud.name}")
//    private final String CLOUD_NAME;
//    @Value("${cloudinary.api.key}")
//    private final String API_KEY;
//    @Value("${cloudinary.api.secret}")
//    private final String API_SECRET;

    private final Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
            "cloud_name", "dhjipwjkh",
            "api_key", "657444945756682",
            "api_secret", "E_t9bhPEEU1E7RTm4JPB4hK7Mag",
            "secure", true
    ));

    @Override
    public String uploadImage(byte[] imageBytes, Map<?, ?> map) throws IOException {
        var uploadResponse = cloudinary.uploader().upload(imageBytes, ObjectUtils.emptyMap());

        return uploadResponse.get("url").toString();
    }
}
