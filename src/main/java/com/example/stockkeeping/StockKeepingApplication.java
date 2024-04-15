package com.example.stockkeeping;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StockKeepingApplication {

    public static void main(String[] args) {
        SpringApplication.run(StockKeepingApplication.class, args);
    }

//    @Bean
//    CommandLineRunner runner(StockRepository stockRepository){
//        return  args -> {
//            Stock newStock = stockRepository.save(new Stock(1L, "tb500", "Tooth brush", 20, 20, LocalDate.now()));
//
//        };
//
//        }

}
