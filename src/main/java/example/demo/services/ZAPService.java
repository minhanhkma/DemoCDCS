package example.demo.services;
import org.springframework.stereotype.Service;
import org.zaproxy.clientapi.core.ClientApi;
import org.zaproxy.clientapi.core.ClientApiException;
import org.zaproxy.clientapi.core.ApiResponse;
import org.zaproxy.clientapi.core.ApiResponseElement;


@Service
public class ZAPService {
    private static final String ZAP_ADDRESS = "localhost";
    private static final int ZAP_PORT = 8080;
    private static final String ZAP_API_KEY = "cegu8q7ehrmqg17l70pungpv6c";

    private final ClientApi api = new ClientApi(ZAP_ADDRESS, ZAP_PORT, ZAP_API_KEY);

    public String scanURL(String url) throws ClientApiException {
    
        ApiResponse response = api.spider.scan(url, null, null, null, null);
        String scanId = ((ApiResponseElement) response).getValue();

        int progress;
        do {
            progress = Integer.parseInt(((ApiResponseElement) api.spider.status(scanId)).getValue());
            try {
                Thread.sleep(1000); 
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while (progress < 100);

        String title = "OWASP ZAP Report";
        String template = "traditional-html";
        String theme = "Mặc định";
        String description = "Kết quả quét kiểm tra " + url;
        String contexts = null;
        String sites = null;
        String sections = null;
        String includedConfidences = null;
        String includedRisks = null;
        String reportFilename = null;
        String reportFilenamePattern = null;
        String reportDir = null;
        String display = null;

        ApiResponse reportResponse = api.reports.generate(
                title, template, theme, description, contexts, sites, sections,
                includedConfidences, includedRisks, reportFilename, reportFilenamePattern, reportDir, display
        );

        String report = ((ApiResponseElement) reportResponse).getValue();
        return report;
    }
}


