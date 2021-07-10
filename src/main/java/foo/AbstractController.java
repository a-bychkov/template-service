package foo;

import org.springframework.http.ResponseEntity;

public abstract class AbstractController {

    public abstract ResponseEntity<?> controllerMethod();

    public abstract String getPath();
}
