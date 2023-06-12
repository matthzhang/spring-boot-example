package com.amigoscode;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    // api url: http//localhost:8080/greet
    @GetMapping("/greet")
    public GreetResponse greet() {
        //returns json object: {"greet":"Hello"}
        GreetResponse response = new GreetResponse(
            "Hello",
            List.of("Java", "Golang", "Javascript"),
            new Person("Alex")
        );

        return response;
    }

    record Person(String name)
    {

    }

    record GreetResponse(
        String greet, 
        List<String> favProgrammingLanguages,
        Person person
    ) {

    }
}
