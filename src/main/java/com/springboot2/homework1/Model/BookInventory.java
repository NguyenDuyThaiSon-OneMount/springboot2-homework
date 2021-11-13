package com.springboot2.homework1.Model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BookInventory{
  private static int count=1;
  private int id; //số tự động tăng
  private String bookId;
  private int amount; //số sách còn lại trong kho 
  private LocalDateTime updateDate; //ngày, giờ cập nhật lại

  public BookInventory(String bookId, int amount){
      this.id= count;
      this.bookId= bookId;
      this.amount= amount;
      this.updateDate= LocalDateTime.now();
  }
}