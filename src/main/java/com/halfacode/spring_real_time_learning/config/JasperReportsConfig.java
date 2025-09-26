package com.halfacode.spring_real_time_learning.config;

import jakarta.annotation.PostConstruct;
import net.sf.jasperreports.engine.DefaultJasperReportsContext;
import net.sf.jasperreports.engine.JRPropertiesUtil;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JasperReportsConfig {

    @PostConstruct
    public void init() {
        // Register the fonts folder so JasperReports can find TTF fonts
        JRPropertiesUtil.getInstance(DefaultJasperReportsContext.getInstance())
                .setProperty("net.sf.jasperreports.extension.simple.font.families", "src/main/resources/fonts");
    }
}
