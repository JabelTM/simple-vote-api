package com.example.simplevoteapi.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

@Configuration
public class ThreadPoolTasksSchedulerConfig {

    @Bean
    public ThreadPoolTaskScheduler getTaskScheduler() {
        return new ThreadPoolTaskScheduler();
    }

}
