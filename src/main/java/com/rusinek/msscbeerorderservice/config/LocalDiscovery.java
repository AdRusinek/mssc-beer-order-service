package com.rusinek.msscbeerorderservice.config;

import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Created by Adrian Rusinek on 10.04.2020
 **/
@Profile("local-discovery")
@Configuration
@EnableDiscoveryClient
public class LocalDiscovery {
}
