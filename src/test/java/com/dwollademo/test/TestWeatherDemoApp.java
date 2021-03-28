/**
 * 
 */
package com.dwollademo.test;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import com.dwollademo.app.WeatherDemoApp;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = WeatherDemoApp.class)
public class TestWeatherDemoApp {

	@Test
	public void testWeatherAPI() {
		UriComponents uriComponents = UriComponentsBuilder.newInstance().scheme("http")
				.host("api.openweathermap.org/data/2.5/weather").path("").query("q={keyword}&appid={appid}")
				.buildAndExpand("chicago", "931410533e0ed5b59d8b952a24abf7b9");

		assertEquals("http://api.openweathermap.org/data/2.5/weather?q=chicago&appid=931410533e0ed5b59d8b952a24abf7b9",
				uriComponents.toUriString());

	}
}
