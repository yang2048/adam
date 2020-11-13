package com.yyovo.adam.admin;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Slf4j
@ComponentScan(basePackages = {"com.yyovo.adam.**"})
@SpringBootApplication
public class AdamAdminApplication {

	public static void main(String[] args) throws UnknownHostException {
		ConfigurableApplicationContext application = SpringApplication.run(AdamAdminApplication.class, args);
		Environment env = application.getEnvironment();
		String host= InetAddress.getLocalHost().getHostAddress();
		String port=env.getProperty("server.port");
		log.info("\n----------------------------------------------------------\n\t" +
						"Application '{}' is running! Access URLs:\n\t" +
						"Local: \t\thttp://localhost:{}\n\t" +
						"External: \thttp://{}:{}\n\t"+
						"Doc: \thttp://{}:{}/doc.html\n\t"+
					"----------------------------------------------------------",
				env.getProperty("spring.application.name"),
				env.getProperty("server.port"),
				host,port,
				host,port);
	}

}
