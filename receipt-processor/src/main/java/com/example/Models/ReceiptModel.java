package com.example.Models;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import lombok.*;
import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReceiptModel {
    private String retailer;
    private String purchaseDate;
    private String purchaseTime;
    private List<Item> items;
    private double total;

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Item {
        private String shortDescription;
        private double price;

        // Getters and Setters
    }
}
