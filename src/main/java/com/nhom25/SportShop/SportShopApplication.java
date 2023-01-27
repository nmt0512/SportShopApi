package com.nhom25.SportShop;

import com.github.alexdlaird.ngrok.NgrokClient;
import com.github.alexdlaird.ngrok.protocol.CreateTunnel;
import com.github.alexdlaird.ngrok.protocol.Tunnel;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.nhom25.SportShop.entity")
public class SportShopApplication {
	public static void main(String[] args) {
		SpringApplication.run(SportShopApplication.class, args);
//		final NgrokClient ngrokClient = new NgrokClient.Builder().build();
//		final CreateTunnel createTunnel = new CreateTunnel.Builder()
//				.withAddr(8080)
//				.build();
//		final Tunnel tunnel = ngrokClient.connect(createTunnel);
	}

}
