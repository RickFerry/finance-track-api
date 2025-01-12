package com.api.config.property;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ConfigurationProperties("api")
public class ApiProperty {
    private String originPermitida = "http://localhost:8000";
    private final Seguranca seguranca = new Seguranca();

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Seguranca {
        private boolean enableHttps;
    }
}
