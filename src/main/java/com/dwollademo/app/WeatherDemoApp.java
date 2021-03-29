package com.dwollademo.app;

import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.dwollademo.model.Root;

@SpringBootApplication
@ComponentScan("com.dwollademo")
public class WeatherDemoApp {

	
	  private static final Logger LOGGER =
	  LogManager.getLogger(WeatherDemoApp.class);
	  
	  
	  @Value("${weather.apiUrl}") private String apiUrl;
	  
	  @Value("${weather.apiKey}") private String apiKey;
	  
	  @Value("${weather.units}") private String units;
	 
	 

	public static void main(String[] args) {

		SpringApplication.run(WeatherDemoApp.class, args);
	}

	
	
	  @Bean public CommandLineRunner run(RestTemplate restTemplate) throws
	  Exception { return args -> { try { System.out.println("Where are you?");
	  Scanner sc = new Scanner(System.in); String city = sc.nextLine(); sc.close();
	  UriComponents uriComponents = UriComponentsBuilder .newInstance()
	  .scheme("http") .host(apiUrl) .path("")
	  .query("q={keyword}&appid={appid}&units={unit}")
	  .buildAndExpand(city,apiKey,units); String uri=uriComponents.toString();
	  LOGGER.debug("URI formed is:"+uri);
	  
	  Root root = restTemplate.getForObject(uri, Root.class);
	  System.out.println(city + " weather:\n" + root.getMain().getTemp() +
	  " degrees Fahrenheit "); } catch(Exception e) {
	  LOGGER.info("Exception occured:"+e.getMessage()); }
	  
	  
	  
	  }; }
	 
	 

}
