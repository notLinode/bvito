package ru.penzgtu.bvito;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.penzgtu.bvito.items.ItemTag;
import ru.penzgtu.bvito.items.ItemTagRepository;

@Configuration
@SpringBootApplication
public class BvitoApplication {

    public static void main(String[] args) {
        SpringApplication.run(BvitoApplication.class, args);
    }

    @Bean
    public CommandLineRunner dataLoader(ItemTagRepository repo) {
        return args -> {
            repo.save(new ItemTag("Транспорт"));
            repo.save(new ItemTag("Услуга"));
            repo.save(new ItemTag("Травел"));
            repo.save(new ItemTag("Тэг1"));
        };
    }

}
