package com.springboot2.homework1.Controller;

import com.github.javafaker.Faker;
import com.springboot2.homework1.Annotation.Benchmark;
import com.springboot2.homework1.Event.LargeOrderEvent;
import com.springboot2.homework1.Model.Book;
import com.springboot2.homework1.Model.BookInventory;
import com.springboot2.homework1.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@EnableScheduling
public class BookController {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @PostMapping("/generate")
    @Benchmark
    public synchronized String generateBook(){
        bookRepository.generateBook();
        return "Complete!";
    }

    @GetMapping("/getBooksByABC")
    @Benchmark
    public List<Book> getBooksByAlphabeticOrder(){
        return bookRepository.getBooksByAlphabeticOrder();
    }

    @GetMapping("/findBookByKeyword")
    @Benchmark
    public List<Book> findBookByKeyword(@RequestParam(required = true) String keyword){
        return bookRepository.findBookByKeyword(keyword);
    }

    @GetMapping("/getUnavailableBooks")
    @Benchmark
    public List<Book> getUnavailableBooks(){

        return bookRepository.getUnavailableBooks();
    }

    @PutMapping("/buyBooks")
    @Benchmark
    public String buyBooks(@RequestParam(required = true) String bookId,
                           @RequestParam(required = false) Integer amount){
        if(amount==null) amount=1;
        if(amount>100){
            publishLargeOrderEvent(bookId, amount);
            return "Your order is too large. We will restock " + amount
                    + " books with id " + bookId + " shortly. In fact the restocking is already done " +
                    "as we are conversing." +
                    " You may attempt to buy the same book again with an amount smaller than 100. It will succeed!";
        }
        else return bookRepository.buyBooks(bookId, amount);
    }

    public void publishLargeOrderEvent(String bookId, int amount) {
        LargeOrderEvent largeOrderEvent = new LargeOrderEvent(this,
                "Large order event for bookId "+ bookId + " with amount of "+ amount,
                bookId, amount);
        applicationEventPublisher.publishEvent(largeOrderEvent);
    }

}
