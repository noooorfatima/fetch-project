package com.example.Controller;
import com.example.Models.ReceiptModel;
import com.example.Services.ReceiptService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/receipts")
public class ReceiptController {

    private final ReceiptService receiptService;

    public ReceiptController(ReceiptService receiptService) {
        this.receiptService = receiptService;
    }

    @PostMapping("/process")
        public ResponseEntity<Map<String, String>> processReceipt(@RequestBody ReceiptModel receipt) {
            boolean isValid = receiptService.isValidReceipt(receipt);
            if (!isValid) {
                Map<String, String> response = new HashMap<>();
                response.put("message", "The receipt is invalid");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }

            String uuid = receiptService.processReceipt(receipt);
            Map<String, String> response = new HashMap<>();
            response.put("id", uuid);

            return ResponseEntity.ok(response);

    }

    @GetMapping("/{id}/points")
    public ResponseEntity<?> getPoints(@PathVariable String id) {
        int points = receiptService.getPoints(id);

        if (points ==-1){
            Map<String, String> response = new HashMap<>();
            response.put("message", "No receipt found for ID: " + id);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        Map<String, Integer> response = new HashMap<>();
        response.put("total", points);

        return ResponseEntity.ok(response);

    }

}