# fetch-project

Instructions:
1. Clone the Repository
   ```
   git clone https://github.com/your-username/receipt-processor.git
   cd receipt-processor
   ```
2. Build the Docker Image
   ```
   docker build -t receipt-processor .
   ```
3. Run the Docker Container
   ```
    docker run -p 8080:8080 receipt-processor
   ```

4. Test the API:
   Use Postman or any HTTP client to test the API:
   
   POST: http://localhost:8080/receipts/process

   GET:  http://localhost:8080/receipts/{id}/points

   

