package ru.penzgtu.bvito;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

@Configuration
@SpringBootApplication
public class BvitoApplication {

    public static void main(String[] args) {
        SpringApplication.run(BvitoApplication.class, args);
    }

//    @Bean
//    public CommandLineRunner dataLoader(ItemTagRepository repo) {
//        return args -> {
//            repo.save(ItemTag.builder().name("Транспорт").build());
//            repo.save(ItemTag.builder().name("Услуга").build());
//            repo.save(ItemTag.builder().name("Травел").build());
//            repo.save(ItemTag.builder().name("Техника").build());
//        };
//    }

}
