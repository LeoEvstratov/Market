package com.evstratov.market;

import lombok.extern.log4j.Log4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MarketApplication {

    public static void main(String[] args) {
        SpringApplication.run(MarketApplication.class, args);
    }
//    some ideas to improve this project
//todo manager profile (all orders with user addresses and phones and status change selector)
//todo	user profile (my orders)
//todo	search with multiple parameters
// todo   address (kladr??) and phone validation in order
//    todo mass import/export products from to excel/txt/etc...
//    todo order statistics
}
