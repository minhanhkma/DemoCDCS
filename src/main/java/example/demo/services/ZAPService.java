package example.demo.services;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.zaproxy.clientapi.core.ClientApi;
import org.zaproxy.clientapi.core.ClientApiException;
import org.zaproxy.clientapi.core.ApiResponse;
import org.zaproxy.clientapi.core.ApiResponseElement;

@Service
public class ZAPService {
    private static final Logger logger = LoggerFactory.getLogger(ZAPService.class);

    private static final String ZAP_ADDRESS = "localhost";
    private static final int ZAP_PORT = 8080;
    private static final String ZAP_API_KEY = "cegu8q7ehrmqg17l70pungpv6c";// API trên ZAP

    private final ClientApi api = new ClientApi(ZAP_ADDRESS, ZAP_PORT, ZAP_API_KEY);

    public String scanURL(String url) {
        try {
            logger.info("Bắt đầu quét cho link: " + url);

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

            logger.info("Hoàn thành quét cho link: " + url);

            String title = "OWASP ZAP Report";
            String template = "traditional-html";
            String theme = "default";
            String description = "Kết quả quét kiểm tra" + url;

            ApiResponse reportResponse = api.reports.generate(
                    title, template, theme, description, null, null, null,
                    null, null, null, null, null, null
            );

            String report = ((ApiResponseElement) reportResponse).getValue();
            logger.info("Báo cáo của link: " + url);
            return report;
        } catch (ClientApiException e) {
            logger.error("Lỗi khi phân tích", e);
            return "Lỗi khi phân tích";
        }
    }
}


