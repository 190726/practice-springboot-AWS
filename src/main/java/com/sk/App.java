package com.sk;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@EnableJpaAuditing
@SpringBootApplication
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @RestController
    class HelloController {

        @GetMapping("/hello")
        public String hello() {
            return "hello";
        }

        @GetMapping("/hello/dto")
        public HelloResponseDto helloDto(@RequestParam("name") String name,
                                         @RequestParam("amount") int amount){
            return new HelloResponseDto(name, amount);
        }
    }

    @Getter
    @RequiredArgsConstructor
    static class HelloResponseDto{
        private final String name;
        private final int amount;
    }

}