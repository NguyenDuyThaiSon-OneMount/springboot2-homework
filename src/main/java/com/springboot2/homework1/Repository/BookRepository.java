package com.springboot2.homework1.Repository;

import com.github.javafaker.Faker;
import com.springboot2.homework1.Event.LargeOrderEvent;
import com.springboot2.homework1.Model.Book;
import com.springboot2.homework1.Model.BookInventory;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

@Repository
@EnableScheduling
public class BookRepository implements ApplicationListener<LargeOrderEvent> {

    List<Book> bookList;
    List<BookInventory> bookInventoryList;
    ConcurrentHashMap<String, List<BookInventory>> bookInventoryAggregate;

    Faker faker;

    public BookRepository(){
        bookList= new ArrayList<>();
        bookInventoryList= new ArrayList<>();
        bookInventoryAggregate= new ConcurrentHashMap<>();
    }

    public void generateBook() {
        Random random= new Random();
        faker= new Faker();
        int beforeGenerateCount= bookList.size();
        for (int i=beforeGenerateCount+1; i<=beforeGenerateCount+1000; ++i){
            bookList.add(
                    new Book(
                            Integer.toString(i),
                            faker.book().title(),
                            faker.book().author()
                    )
            );

            BookInventory bookInventory= new BookInventory(
                    Integer.toString(i),
                    random.nextInt(101));

            bookInventoryList.add(bookInventory);

            bookInventoryAggregate.put(Integer.toString(i), new ArrayList<>());
            bookInventoryAggregate.get(Integer.toString(i)).add(bookInventory);
        }
    }

    public List<Book> getBooksByAlphabeticOrder() {
        Collections.sort(this.bookList);
        return this.bookList;
    }

    public List<Book> findBookByKeyword(String keyword) {
        List<Book> bookByKeywordList = new ArrayList<>();

//        Book book= bookList.stream()
//                .filter(b -> title.equals(b.getTitle()))
//                .findFirst();

        for(Book book: bookList){
            if(book.getTitle().contains(keyword)) bookByKeywordList.add(book);
        }

        return bookByKeywordList;
    }

    public List<Book> getUnavailableBooks() {
        List<Book> unavailableBookList= new ArrayList<>();

        for(String key: bookInventoryAggregate.keySet()){
            List<BookInventory> bookInventoryList = bookInventoryAggregate.get(key);
            BookInventory bookInventory= bookInventoryList.get(bookInventoryList.size()-1); // latest
            if(bookInventory.getAmount()==0){
                for (Book book: bookList){
                    if(book.getId().equals(bookInventory.getBookId())){
                        unavailableBookList.add(book);
                    }
                }
            }
        }
        return unavailableBookList;
    }

    public String buyBooks(String bookId, int amount) {

        List<BookInventory> bookInventoryList= bookInventoryAggregate.get(bookId);
        if (bookInventoryList==null || bookInventoryList.size()==0){
            return "Book not found!";
        }
        BookInventory bookInventory=
                bookInventoryList
                        .get(bookInventoryList.size()-1);

        if(bookInventory.getAmount()< amount){
            return "Your order is too big. There are only " + bookInventory.getAmount()
                    + " books left in stock. You specified " + amount;
        }
        else{
            BookInventory bookInventoryNew= new BookInventory(
                    bookInventory.getBookId(),
                    bookInventory.getAmount()-amount);

            bookInventoryList.add(bookInventoryNew);
            bookInventoryAggregate.get(bookId).add(bookInventoryNew);
            return "Bought " + amount + " books with bookId " + bookInventory.getBookId() + " successfully";
        }
    }

    public String restockBook(String bookId, int amount) {
        List<BookInventory> bookInventoryList= bookInventoryAggregate.get(bookId);
        if (bookInventoryList==null || bookInventoryList.size()==0){
            return "Book not found!";
        }
        BookInventory bookInventory=
                bookInventoryList
                        .get(bookInventoryList.size()-1);

        BookInventory bookInventoryNew= new BookInventory(
                bookInventory.getBookId(),
                bookInventory.getAmount()+amount);

        bookInventoryList.add(bookInventoryNew);
        bookInventoryAggregate.get(bookId).add(bookInventoryNew);

//                bookInventory.setAmount(bookInventory.getAmount() + amount);
        return "Done";
    }

    @Scheduled(fixedRate= 60000)
    public void scheduledBookRestock(){

        for(String key: bookInventoryAggregate.keySet()){

            List<BookInventory> bookInventoryList= bookInventoryAggregate.get(key);
            BookInventory bookInventory=
                    bookInventoryList
                            .get(bookInventoryList.size()-1);

            if(bookInventory.getAmount()==1){
                BookInventory bookInventoryNew= new BookInventory(
                        bookInventory.getBookId(),
                        bookInventory.getAmount()+5);

                bookInventoryList.add(bookInventoryNew);
                bookInventoryAggregate.get(key).add(bookInventoryNew);
            }
        }

        System.out.println("Books restocked");
    }

    @Override
    public void onApplicationEvent(LargeOrderEvent event) {
        System.out.println(event.getMessage());
        List<BookInventory> bookInventoryList= bookInventoryAggregate.get(event.getBookId());
        if (bookInventoryList==null || bookInventoryList.size()==0){
            System.out.println("Book not found");
            return;
        }
        BookInventory bookInventory=
                bookInventoryList
                        .get(bookInventoryList.size()-1);

        BookInventory bookInventoryNew= new BookInventory(
                bookInventory.getBookId(),
                bookInventory.getAmount()+ event.getAmount());

        bookInventoryList.add(bookInventoryNew);
        bookInventoryAggregate.get(event.getBookId()).add(bookInventoryNew);

//                bookInventory.setAmount(bookInventory.getAmount() + amount);
    }
}
