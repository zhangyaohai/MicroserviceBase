package com.daizhen.runner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.daizhen.beans.Quote;

@Component
public class MyStartupRunner1 implements CommandLineRunner {

	private static final Logger log = LoggerFactory.getLogger(MyStartupRunner1.class);

	@Override
	public void run(String... args) throws Exception {
		RestTemplate restTemplate = new RestTemplate();
		Quote quote = restTemplate.getForObject("http://gturnquist-quoters.cfapps.io/api/random", Quote.class);
		log.info(quote.toString());
		log.info("万恶的和谐程序即将开始！");
	}

}
