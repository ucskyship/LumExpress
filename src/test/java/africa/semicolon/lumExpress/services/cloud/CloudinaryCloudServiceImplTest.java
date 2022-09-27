package africa.semicolon.lumExpress.services.cloud;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


@SpringBootTest
class CloudinaryCloudServiceImplTest {
    @Autowired
    private iCloudService cloudService;
    private MultipartFile file;

    @BeforeEach
    void setUp() throws IOException {
        Path path = Paths.get("/home/ucskyship/Documents/SEMICOLON/lumExpress/lumExpress/src/test/images/peak.jpeg");
        file =new MockMultipartFile("peak", Files.readAllBytes(path));
    }

    @Test
    @DisplayName("cloudinary upload test")
    void upload() {
        try {
            var response = cloudService.upload(file.getBytes(), null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}