package com.Royal.SCM.Controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import dto.EmailResponse;
import dto.IntentRequest;


public class EmailAIController {

    @Value("${openai.api.key}")
private String OPENAI_API_KEY;


    @PostMapping("/generate")
    public ResponseEntity<EmailResponse> generateEmail(@RequestBody IntentRequest request) {
        String prompt = "Write a professional email to " + request.getRecipientName()
                      + " with the intent: " + request.getIntent();

        String aiGenerated = callOpenAi(prompt);
        return ResponseEntity.ok(new EmailResponse(aiGenerated));
    }

    private String callOpenAi(String prompt) {
        String endpoint = "https://api.openai.com/v1/completions";

        RestTemplate rest = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(OPENAI_API_KEY);
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, Object> body = new HashMap<>();
        body.put("model", "text-davinci-003"); // You can also try gpt-3.5-turbo via /chat/completions endpoint
        body.put("prompt", prompt);
        body.put("max_tokens", 200);
        body.put("temperature", 0.7);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);

        try {
            ResponseEntity<Map> response = rest.postForEntity(endpoint, entity, Map.class);

            List<Map<String, Object>> choices = (List<Map<String, Object>>) response.getBody().get("choices");
            String result = (String) choices.get(0).get("text");
            return result != null ? result.trim() : "No response from AI";
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed to generate email: " + e.getMessage();
        }
    }
}
