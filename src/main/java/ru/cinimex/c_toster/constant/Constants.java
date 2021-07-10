package ru.cinimex.c_toster.constant;

public class Constants {

    // Local:
    public static final String HOME_DIR = "C:\\Users\\Z9Neo\\Desktop\\template-service\\src\\main\\java\\foo\\";

    public static final String PACKAGE_NAME = "src\\main\\java\\foo\\";

    public static final String SOURCE_CODE = "package foo;\n" +
            "\n" +
            "import org.springframework.http.ResponseEntity;\n" +
            "import org.springframework.web.bind.annotation.RestController;\n" +
            "\n" +
            "@RestController\n" +
            "public class TemplateController891 extends AbstractController {\n" +
            "   \n" +
            "    private String path = \"/path8\";\n" +
            "\n" +
            "    @Override\n" +
            "    public ResponseEntity<?> controllerMethod() {\n" +
            "        return ResponseEntity.status(200).body(\"Hello Buba!1\");\n" +
            "    }\n" +
            "\n" +
            "    @Override\n" +
            "    public String getPath() {\n" +
            "        return path;\n" +
            "    }\n" +
            "}";
}
