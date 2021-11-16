package com.amila.simplebank.core.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;
import java.util.UUID;

@Component
@Slf4j
public class SimpleBankErrorConfig extends DefaultErrorAttributes {
    private String applicationName;

    @Value(value = "${spring.application.name}")
    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, ErrorAttributeOptions options) {
        String uniqueKey = UUID.randomUUID().toString();

        Map<String, Object> errorAttributes = super.getErrorAttributes(webRequest, options);

        Throwable error = this.getError(webRequest);
        String errorCode = String.format("%1$s - %2$s - %3$s", error.getMessage(), applicationName, uniqueKey);
        errorAttributes.put("Simple_Bank_Error_Code", errorCode);
        errorAttributes.remove("trace");

        log.error("errorAttributes:- {}", errorAttributes);
        log.error("Original Error:-", error);
        return errorAttributes;
    }
}
