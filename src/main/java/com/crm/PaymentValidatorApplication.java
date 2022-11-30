package com.crm;

import com.crm.bindings.StreamBindings;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;

@SpringBootApplication
@EnableBinding(StreamBindings.class)
public class PaymentValidatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(PaymentValidatorApplication.class, args);
	}

}
