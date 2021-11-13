package com.springboot2.homework1.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book implements Comparable<Book>{
  private String id;
  private String title;
  private String author;


  @Override
  public int compareTo(Book o) {
    return this.title.compareTo(o.getTitle());
  }
}