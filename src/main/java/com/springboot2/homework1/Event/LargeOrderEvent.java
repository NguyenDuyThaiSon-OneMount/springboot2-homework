package com.springboot2.homework1.Event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class LargeOrderEvent extends ApplicationEvent {
    private String message;
    private String bookId;
    private int amount;
    public LargeOrderEvent(Object source, String message, String bookId, int amount) {
        super(source);
        this.message= message;
        this.bookId= bookId;
        this.amount= amount;
    }
}
