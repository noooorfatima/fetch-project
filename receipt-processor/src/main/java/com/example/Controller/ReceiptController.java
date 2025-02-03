package com.example.receiptprocessor;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/receipts")
public class ReceiptController {

    @PostMapping("/process")
    public String processReceipt(@RequestBody String receipt) {
        // Process the receipt and calculate points
         System.out.println("Received receipt: " + receipt);
        return "{\"points\": 100}"; // Replace with actual logic
    }

    @GetMapping("/{id}/points")
    public String getPoints(@PathVariable String id) {
        // Retrieve points for a receipt by ID
        return "{\"points\": 100}"; // Replace with actual logic
    }
}