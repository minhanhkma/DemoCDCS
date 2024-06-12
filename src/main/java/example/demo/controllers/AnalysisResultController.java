package example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zaproxy.clientapi.core.ClientApiException;

import example.demo.DTO.LinkRequest;
import example.demo.entities.AnalysisResult;
import example.demo.services.AnalysisResultService;

@RestController
@RequestMapping("/api/analysis-results")
public class AnalysisResultController {
    @Autowired
    private AnalysisResultService analysisResultService;

    @PostMapping("/analyze-url")
    public ResponseEntity<?> analyzeUrl(@RequestBody LinkRequest linkRequest) {
        try {
            AnalysisResult result = analysisResultService.analyzeUrl(linkRequest.getUrl());
            return ResponseEntity.ok(result);
        } catch (ClientApiException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Lỗi khi phân tích: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Xảy ra lỗi: " + e.getMessage());
        }
    }

}




