package ru.cinimex.c_toster.controller;

import foo.AbstractController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static ru.cinimex.c_toster.constant.Constants.PACKAGE_NAME;
import static ru.cinimex.c_toster.constant.Constants.SOURCE_CODE;

@RestController
public class FooController {

    @Autowired
    ProcessBuilder builder;

    @Autowired
    private RequestMappingHandlerMapping handlerMapping;

    @GetMapping
    public void hello() throws IOException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        //todo - this!
        String className = "TemplateController891";

        // create a source file
        File sourceFile = new File(PACKAGE_NAME + className + ".java");

        // Delete TemplateController.java if exist
        Files.deleteIfExists(sourceFile.toPath());

        // write the source code into the source file to .java
        Writer writer = new FileWriter(sourceFile);
        //todo - this!
        writer.write(SOURCE_CODE);
        writer.close();

        // compile the source file to .class
//		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
//		compiler.run(null, null, null, "-classpath", "C:/Users/Z9Neo/Desktop/template-service/target/template-service-0.0.1-SNAPSHOT.jar", "C:\\Users\\Z9Neo\\Desktop\\template-service\\src\\main\\java\\foo\\" + className + ".java");

//        List<String> cmds1 = Arrays.asList("bash", "-c", "./mvnw clean package");
//        List<String> cmds2 = Arrays.asList("bash", "-c", "nohup java -jar target/template-service-0.0.1-SNAPSHOT.jar &");

        List<String> cmds1 = Arrays.asList("cmd.exe", "/C", "javac", "-cp", "\"C:/Users/Z9Neo/.m2/repository/org/springframework/spring-web/5.3.4/spring-web-5.3.4.jar\"", "AbstractController.java");
        List<String> cmds2 = Arrays.asList("cmd.exe", "/C", "javac", "-cp", "../;\"C:/Users/Z9Neo/.m2/repository/org/springframework/spring-web/5.3.4/spring-web-5.3.4.jar\"", "TemplateController891.java");

        List<List<String>> commands = Arrays.asList(cmds1, cmds2);

        List<String> results = commands.stream().map(e -> this.runConsoleCommand(e)).collect(Collectors.toList());


        // load the compiled class
        File file = new File(PACKAGE_NAME);
        Class aClass = null;

        try {
            // Convert File to a URL
            URL url = file.toURI().toURL();
            URL[] urls = new URL[]{url};

            // Create a new class loader with the directory
            ClassLoader cl = new URLClassLoader(urls);

            // Load in the class; MyClass.class should be located in
            aClass = cl.loadClass("foo." + className);

        } catch (MalformedURLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        // Create a controller object
        Object controller = aClass.newInstance();
        AbstractController bean = (AbstractController) controller;

        // Register new mapping for controller
        registerMapping(bean);
    }

    private String runConsoleCommand(List<String> params) {
        Process process;
        int processReturnCode = 0;

        try {
            builder.command(params);
            process = builder.start();

            process.waitFor(2, TimeUnit.MINUTES);
            processReturnCode = process.exitValue();
            process.destroy();

            if (process.isAlive()) {
                process.destroyForcibly();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Process finished with exit code " + processReturnCode;
    }

    private void registerMapping(AbstractController bean) throws NoSuchMethodException {
        // Add new mapping from newly created controller
        RequestMappingInfo requestMappingInfo = RequestMappingInfo
                .paths(bean.getPath())
                .methods(RequestMethod.GET)
                .produces(MediaType.APPLICATION_JSON_VALUE)
                .build();

        handlerMapping.
                registerMapping(requestMappingInfo, bean, AbstractController.class.getDeclaredMethod("controllerMethod"));
    }
}
