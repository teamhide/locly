package com.locly.locly.common.config.database

import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@Configuration
@EnableJpaRepositories(basePackages = ["com.locly.locly.user.adapter.out.persistence.jpa"])
class SpringDataJpaConfig
