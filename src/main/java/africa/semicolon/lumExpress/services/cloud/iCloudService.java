package africa.semicolon.lumExpress.services.cloud;

import java.io.IOException;
import java.util.Map;

public interface iCloudService {
    String upload(byte[] imageBytes, Map<?,?> map) throws IOException;
}
