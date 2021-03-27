package com.itsubedibesh.wallmart.controllers.configuration.Audit;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing()
public class JpaAuditConfig { }