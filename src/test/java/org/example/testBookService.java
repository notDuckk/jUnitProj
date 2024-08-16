package org.example;

import static org.mockito.Mockito.*;
import org.junit.Before;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.junit.jupiter.api.Assertions.*;


import java.util.List;

public class testBookService {

    @Mock
    BookService bookService = new BookService();
    Book book1 = new Book("title","author","genre",5.00);

    @BeforeAll
    static void beforeAll(){
        System.out.println("Before all book service tests");
    }

    @AfterAll
    static void afterAll(){
        System.out.println("After all book service tests");
    }

    @Test
    public void testBookSearchPositive(){
        bookService.addBook(book1);
        List<Book> result = bookService.searchBook("title");
        assertEquals(result.get(0).getTitle(),book1.getTitle());
    }

    @Test
    public void testBookSearchNegative(){
        bookService.addBook(book1);
        List<Book> result = bookService.searchBook("hello");
        assertTrue(result.isEmpty());

    }

    @Test
    public void testBookSearchEdge(){
        bookService.addBook(book1);
        List<Book> result = bookService.searchBook("");

        assertFalse(result.isEmpty(),"should return all books");
    }

    @Test
    public void testPurchaseBookSuccess(){
        bookService.addBook(book1);
        User user = new User("test","pass","test@test.com");

        boolean result = bookService.purchaseBook(user,book1);

        assertTrue(result, "purchase should go through if book exists");
    }

    @Test
    public void testPurchaseBookfail(){
        Book book = new Book("title","author","genre",5.00);
        User user = new User("test","pass","test@test.com");
        boolean result = bookService.purchaseBook(user,book);
        assertFalse(result, "purchase should not go through if book doesnt exist");
    }


    @Test
    public void testPurchaseBookEdge(){
        //creating a book without a title
        Book book = new Book("","","",0.00);
        //adding the book to the book service
        bookService.addBook(book);
        //creating a user to purchase the book
        User user = new User("test","pass","test@test.com");

        //if the books title is an empty string assert false because you cannot purchase a book without a title
        if (book.getTitle().isEmpty()){
            assertFalse(false, "cannot purchase a book without a tittle");
        }else{
            //else create a variable that returns true if the user and book exist and has a title
            boolean result = bookService.purchaseBook(user,book);
            assertTrue(result, "the purchase was succesful");
        }
    }

}
