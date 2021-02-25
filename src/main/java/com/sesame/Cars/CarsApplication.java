package com.sesame.Cars;

import java.io.IOException;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;


@SpringBootApplication
public class CarsApplication extends SpringBootServletInitializer /*implements CommandLineRunner*/
	{
	/*@Autowired
	private JavaMailSender javaMailSender;
	void sendEmail() {
	SimpleMailMessage msg = new SimpleMailMessage();
	msg.setTo("hosnibaili1987@gmail.com");
	msg.setSubject("Testing from Spring Boot");
	msg.setText("Hello World \n Spring Boot Email");
	javaMailSender.send(msg);
	}
	@Override
	public void run(String... args) throws MessagingException, IOException {
	System.out.println("Sending Email...");
	sendEmail();
	System.out.println("Done");
	}*/

	public static void main(String[] args) {
		
		SpringApplication.run(CarsApplication.class, args);
	}

}
