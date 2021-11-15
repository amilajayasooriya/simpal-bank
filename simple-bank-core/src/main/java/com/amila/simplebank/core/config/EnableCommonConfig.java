package com.amila.simplebank.core.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = {"classpath:simple-bank-core-config.properties"})
public class EnableCommonConfig{

}
