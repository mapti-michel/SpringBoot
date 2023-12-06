package com.example.demo.service;

import com.example.demo.ws.obj.Quote;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class QuoteService {
    private List<Quote> quotes = new ArrayList<Quote>();

    public QuoteService(){
        this.quotes.add(new Quote("Mensagem 1", "Teste1"));
        this.quotes.add(new Quote("Mensagem 2", "Teste2"));
        this.quotes.add(new Quote("Mensagem 3", "Teste3"));
        this.quotes.add(new Quote("Mensagem 4", "Teste4"));
        this.quotes.add(new Quote("Mensagem 5", "Teste5"));
        this.quotes.add(new Quote("Mensagem 6", "Teste6"));
    }

    public Quote getQuote(){
        Random rn = new Random();
        int select = rn.nextInt(this.quotes.size());
        return this.quotes.get(select);
    }

    public void addQuote(Quote quote){
        this.quotes.add(quote);
    }

}
