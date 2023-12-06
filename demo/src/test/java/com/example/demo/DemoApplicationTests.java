package com.example.demo;

import com.example.demo.service.QuoteService;
import com.example.demo.ws.obj.Quote;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = DemoApplication.class)
@SpringBootTest
class DemoApplicationTests {

	QuoteService quoteService;

	@Before
	public void before(){
		this.quoteService = new QuoteService();
	}

	@Test
	public void testGetQuote() {
		this.quoteService.addQuote(new Quote("ABC", "D"));
		assertEquals("ABC", this.quoteService.getQuote().getValue());
	}

	@Test
	public void testGetQuoteMultiple(){
		this.quoteService.addQuote(new Quote("ABC", "D"));
		this.quoteService.addQuote(new Quote("EFG", "H"));
		assertTrue("ABC".equals(this.quoteService.getQuote().getValue()) ||
							"EFG".equals(this.quoteService.getQuote().getValue()));

	}

}
