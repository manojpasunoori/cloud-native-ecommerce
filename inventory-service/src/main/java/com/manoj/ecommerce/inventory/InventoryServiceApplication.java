package com.manoj.ecommerce.inventory;

import com.manoj.ecommerce.inventory.model.Inventory;
import com.manoj.ecommerce.inventory.repository.InventoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InventoryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryServiceApplication.class, args);
    }

    // This runs automatically when the server starts!
    @Bean
    public CommandLineRunner loadData(InventoryRepository inventoryRepository) {
        return args -> {
            Inventory iphone = new Inventory();
            iphone.setSkuCode("IPHONE_15");
            iphone.setQuantity(100);

            Inventory galaxy = new Inventory();
            galaxy.setSkuCode("GALAXY_S24");
            galaxy.setQuantity(50);

            inventoryRepository.save(iphone);
            inventoryRepository.save(galaxy);
        };
    }
}