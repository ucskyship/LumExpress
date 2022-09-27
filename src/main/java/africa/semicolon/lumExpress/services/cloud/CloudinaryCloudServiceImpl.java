package africa.semicolon.lumExpress.services.cloud;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.Map;

@Service
public class CloudinaryCloudServiceImpl implements iCloudService{
    private final Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
            "cloud_name", "dhjipwjkh",
            "api_key", "657444945756682",
            "api_secret", "E_t9bhPEEU1E7RTm4JPB4hK7Mag",
            "secure", true
    ));

    @Override
    public String upload(byte[] imageBytes, Map<?, ?> map) throws IOException {
        var uploadResponse = cloudinary.uploader().upload(imageBytes, ObjectUtils.emptyMap());
        return (String) uploadResponse.get("url");
    }
}
