package foo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TemplateController891 extends AbstractController {
   
    private String path = "/path8";

    @Override
    public ResponseEntity<?> controllerMethod() {
        return ResponseEntity.status(200).body("{\"msg\": \"Hello Buba!1\"}");
    }

    @Override
    public String getPath() {
        return path;
    }
}