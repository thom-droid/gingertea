package com.thom.gingertea.config.properties;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "webclient")
@Component
@Getter
@Setter
public class WebClientProperties {

    private int retry;
    private Connection connection;
    private External external;

    public interface ExternalProperties {
        String getBaseUrl();
    }

    @Data
    public static class External {
        private Supplier supplier;
    }

    @Data
    public static class Supplier implements ExternalProperties {
        private String baseUrl;
    }

    @Data
    public static class Connection {
        private int timeout;
    }
}
