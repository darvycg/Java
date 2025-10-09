package com.learning.room_clr;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Scanner;

@SpringBootApplication
public class RoomClrApplication {

	public static void main(String[] args) {
		SpringApplication.run(RoomClrApplication.class, args);
	}

    @Bean
    public CommandLineRunner run() {
        return args -> {
            Scanner s = new Scanner(System.in);
            System.out.print("Enter number to count up to: ");
            int number = s.nextInt();

            for(int i = 1; i <= number; i++) {
                String result = "";
                result += (i % 3) == 0 ? "Fizz" : "";
                result += (i % 5) == 0 ? "Buzz" : "";
                System.out.println(!result.isEmpty() ? result : i);
            }
        };
    }
}
