package example.demo.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zaproxy.clientapi.core.ClientApiException;

import example.demo.entities.AnalysisResult;
import example.demo.entities.Link;
import example.demo.repositories.AnalysisResultRepository;
import example.demo.repositories.LinkRepository;

import java.time.LocalDateTime;

@Service
public class AnalysisResultService {
    @Autowired
    private AnalysisResultRepository analysisResultRepository;

    @Autowired
    private LinkRepository linkRepository;

    @Autowired
    private ZAPService zapService;

    public AnalysisResult analyzeUrl(String url) throws ClientApiException {
        Link link = linkRepository.findByUrl(url).orElseGet(() -> {
            Link newLink = new Link();
            newLink.setUrl(url);
            newLink.setCreatedAt(LocalDateTime.now());
            return linkRepository.save(newLink);
        });

        String scanResults = zapService.scanURL(link.getUrl());
        double safetyScore = calculateSafetyScore(scanResults);

        AnalysisResult result = new AnalysisResult();
        result.setLink(link);
        result.setSafetyScore(safetyScore);
        result.setScanDate(LocalDateTime.now());
        result.setResultDetails(scanResults);
        return analysisResultRepository.save(result);
    }

    private double calculateSafetyScore(String scanResults) {
        // Logic tính toán điểm an toàn từ kết quả scan
        return 50.0; // Giả sử điểm là 50, cách tính điểm sẽ bổ sung sau
    }
}

