package com.example.Services;


import com.example.Models.ReceiptModel;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class ReceiptService {
    //map to store the receipt ids with the receipts (data won't survive application restart)
    Map<String, ReceiptModel> receipts = new HashMap<>();

    public String processReceipt(ReceiptModel receipt){
        UUID uuid = UUID.randomUUID();
        receipts.put(uuid.toString(), receipt);
        return uuid.toString();

    }
    public int getPoints(String receiptId){
        ReceiptModel receipt = receipts.get(receiptId);
        if (receipt == null){
            return -1;
        }
        return calculatePoints(receipt);
    }

    private int calculatePoints(ReceiptModel receipt){
        int points = 0;

        // One point for every alphanumeric character in the retailer name
        points += receipt.getRetailer().replaceAll("[^a-zA-Z0-9]", "").length();

        // 50 points if the total is a round dollar amount
        if (receipt.getTotal() == Math.floor(receipt.getTotal())) {
            points += 50;
        }

        // 25 points if the total is a multiple of 0.25
        if (receipt.getTotal() % 0.25 == 0) {
            points += 25;
        }

        // 5 points for every two items on the receipt
        points += (receipt.getItems().size() / 2) * 5;

        //6 points if the day in the purchase date is odd
        LocalDate purchaseDate = LocalDate.parse(receipt.getPurchaseDate());
        if (purchaseDate.getDayOfMonth() % 2 != 0) {
            points += 6;
        }

        //If the trimmed length of the item description is a multiple of 3, multiply the price by 0.2 and round up to the nearest integer.
        // The result is the number of points earned
        for (ReceiptModel.Item item : receipt.getItems()) {
            String trimmedDescription = item.getShortDescription().trim();
            if (trimmedDescription.length() % 3 == 0) {
                double price = item.getPrice();
                int itemPoints = (int) Math.ceil(price * 0.2);
                points += itemPoints;
            }
        }
        //10 points if the time of purchase is after 2:00pm and before 4:00pm
        LocalTime purchaseTime = LocalTime.parse(receipt.getPurchaseTime());
        LocalTime startTime = LocalTime.of(14, 0); // 2:00 PM
        LocalTime endTime = LocalTime.of(16, 0);   // 4:00 PM
        if (purchaseTime.isAfter(startTime) && purchaseTime.isBefore(endTime)) {
            points += 10;
        }

        return points;
    }

    public boolean isValidReceipt(ReceiptModel receipt) {
        //check for null or empty values in the receipt for all fields
        if (receipt.getRetailer() == null || receipt.getRetailer().trim().isEmpty()) {
            return false;
        }
        if (receipt.getPurchaseDate() == null || !receipt.getPurchaseDate().matches("\\d{4}-\\d{2}-\\d{2}")) {
            return false;
        }
        if (receipt.getPurchaseTime() == null || !receipt.getPurchaseTime().matches("\\d{2}:\\d{2}")) {
            return false;
        }
        if (receipt.getItems() == null || receipt.getItems().isEmpty()) {
            return false;
        }
        for (ReceiptModel.Item item : receipt.getItems()) {
            if (item.getShortDescription() == null || item.getShortDescription().trim().isEmpty()) {
                return false;
            }
            if (item.getPrice() <= 0) {
                return false;
            }
        }
        if (receipt.getTotal() <= 0) {
            return false;
        }
        return true;
    }

}
