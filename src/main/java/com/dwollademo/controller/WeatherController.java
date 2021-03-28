package com.dwollademo.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.dwollademo.model.Main;
import com.dwollademo.model.Root;

@RestController
public class WeatherController {

	@Value("${weather.apiUrl}") private String apiUrl;
	  
	  @Value("${weather.apiKey}") private String apiKey;
	  
	  @Value("${weather.units}") private String units;
	  
	  @Autowired
	  RestTemplate restTemplate;
	  
	  private static final Logger LOGGER =
			  LogManager.getLogger(WeatherController.class);
	  String tempToday="";
	@RequestMapping("/getTempCity/{city}")
	public String getTempByCity(@PathVariable("city") String city)
	{
		try
		{
		UriComponents uriComponents = UriComponentsBuilder
				.newInstance()
				.scheme("http")
				.host(apiUrl)
			    .path("")
			    .query("q={keyword}&appid={appid}&units={unit}")
			    .buildAndExpand(city,apiKey,units);
		String uri=uriComponents.toString();
	
		LOGGER.debug("URI formed is:", uri);
		
		Root root = restTemplate.getForObject(uri, Root.class);
		
		Main main=root.getMain();
		
		Double temp=main.getTemp();
		
		tempToday= city + " weather:\n" + temp + " degrees Fahrenheit ";
		
		LOGGER.info(city + " weather:\n" + temp + " degrees Fahrenheit ");
		}
		catch(Exception e)
		{
			LOGGER.error("Exception occured:"+e.getMessage());
		}
		
		
		return tempToday;
		
	}
}
