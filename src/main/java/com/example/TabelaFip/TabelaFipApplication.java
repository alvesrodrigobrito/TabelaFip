package com.example.TabelaFip;

import com.example.TabelaFip.main.MainApp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TabelaFipApplication implements CommandLineRunner {
	@Autowired
	MainApp mainApp;

	public static void main(String[] args) {
		SpringApplication.run(TabelaFipApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		mainApp.displayMenu();
	}
}
