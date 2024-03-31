package com.store.dominguez;


import com.store.dominguez.mock.MockDataConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;


@SpringBootApplication
public class DominguezApplication implements CommandLineRunner {

    private final MockDataConfig mockDataConfig;

    public DominguezApplication(MockDataConfig mockDataConfig) {
        this.mockDataConfig = mockDataConfig;
    }

    public static void main(String[] args) {
        SpringApplication.run(DominguezApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        mockDataConfig.run(args);
    }

}
