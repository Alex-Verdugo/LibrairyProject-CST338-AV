/**
 1.Title: ShelfTest
 2.Abstract: Tests shelf class
 3.Author: Alexander Verdugo
 4.Date: March 12th 2021
 */
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class ShelfTest {

    @Test
    void shelfConstructor() {
        Shelf testShelf = new Shelf();
        assertNotNull(testShelf);
    }

    @Test
    void getShelfNumber() {

        int testShelfNumber = 0;
        Shelf testShelf = new Shelf();
        testShelf.setShelfNumber(1);

        assertNotEquals(testShelfNumber,testShelf.getShelfNumber());

        testShelfNumber = testShelf.getShelfNumber();

        assertEquals(testShelfNumber,1);
    }

    @Test
    void setShelfNumber() {

        int testShelfNumber = 3;
        int oldShelfNumber;

        Shelf testShelf = new Shelf();

        oldShelfNumber = testShelf.getShelfNumber();

        testShelf.setShelfNumber(testShelfNumber);

        assertNotEquals(oldShelfNumber,testShelfNumber);

        assertEquals(testShelf.getShelfNumber(),testShelfNumber);
    }

    @Test
    void getSubject() {

        String testShelfSubject = "";
        Shelf testShelf = new Shelf();
        testShelf.setSubject("sci-fi");

        assertNotEquals(testShelfSubject,testShelf.getSubject());

        testShelfSubject = testShelf.getSubject();

        assertEquals(testShelfSubject,"sci-fi");

    }

    @Test
    void setSubject() {

        String testShelfSubject = "sci-fi";
        String oldShelfSubject;

        Shelf testShelf = new Shelf();

        oldShelfSubject = testShelf.getSubject();

        testShelf.setSubject(testShelfSubject);

        assertNotEquals(oldShelfSubject,testShelfSubject);

        assertEquals(testShelf.getSubject(),testShelfSubject);

    }

    @Test
    void getBooks() {

        Shelf testShelf1 = new Shelf();
        Shelf testShelf2 = new Shelf();
        testShelf1.setSubject("sci-fi");
        testShelf2.setSubject("sci-fi");
        Book testBook = new Book("98723-a","How to procrastinate on Shelf.java","sci-fi",9999,"Me", LocalDate.parse("1000-01-01"));

        testShelf1.addBook(testBook);
        assertNotEquals(testShelf1.getBooks(),testShelf2.getBooks());
        testShelf2.addBook(testBook);
        assertEquals(testShelf2.getBooks(), testShelf1.getBooks());
    }

    @Test
    void setBooks() {

        Shelf testShelf1 = new Shelf();
        Shelf testShelf2 = new Shelf();
        testShelf1.setSubject("sci-fi");
        testShelf2.setSubject("sci-fi");
        Book testBook = new Book("98723-a","How to procrastinate on Shelf.java","sci-fi",9999,"Me", LocalDate.parse("1000-01-01"));

        testShelf1.addBook(testBook);

        assertNotEquals(testShelf1.getBooks(),testShelf2.getBooks());

        testShelf2.setBooks(testShelf1.getBooks());

        assertEquals(testShelf2.getBooks(), testShelf1.getBooks());

    }

    @Test
    void testEquals() {

        Shelf testShelf1 = new Shelf();
        Shelf testShelf2 = new Shelf();
        testShelf1.setSubject("sci-fi");
        testShelf2.setSubject("sci-fi");

        testShelf2.setShelfNumber(1);

        assertFalse(testShelf1.equals(testShelf2));

        testShelf1.setShelfNumber(1);

        assertTrue(testShelf1.equals(testShelf2));

    }


    @Test
    void getBookCount() {

        Shelf testShelf1 = new Shelf();
        testShelf1.setSubject("sci-fi");
        Book testBook = new Book("98723-a","How to procrastinate on Shelf.java","sci-fi",9999,"Me", LocalDate.parse("1000-01-01"));
        Book testBook2 = new Book("yt4567","How to do ","fake",123,"Me", LocalDate.parse("1000-01-01"));

        Random rand = new Random();

        int randNum = rand.nextInt(100);

        for (int i = 0; i<randNum; i++){
            assertEquals(testShelf1.addBook(testBook),Code.SUCCESS);
        }

        assertEquals(testShelf1.getBookCount(testBook),randNum);

        testShelf1.removeBook(testBook);

        assertEquals(testShelf1.getBookCount(testBook),randNum-1);

        for (int i = 0; i<randNum-1; i++){
            assertEquals(testShelf1.removeBook(testBook),Code.SUCCESS);
        }

        assertEquals(testShelf1.getBookCount(testBook),0);

        assertEquals(testShelf1.getBookCount(testBook2),-1);



    }

    @Test
    void addBook() {

        Shelf testShelf1 = new Shelf();
        testShelf1.setSubject("sci-fi");
        Book testBook = new Book("98723-a","How to procrastinate on Shelf.java","sci-fi",9999,"Me", LocalDate.parse("1000-01-01"));

        assertEquals(testShelf1.addBook(testBook),Code.SUCCESS);

        assertEquals(testShelf1.getBookCount(testBook),1);

        assertEquals(testShelf1.addBook(testBook),Code.SUCCESS);

        assertEquals(testShelf1.getBookCount(testBook),2);

        Book testBook2 = new Book("yt4567","How to do ","fake",123,"Me", LocalDate.parse("1000-01-01"));

        assertEquals(testShelf1.addBook(testBook2),Code.SHELF_SUBJECT_MISMATCH_ERROR);

        assertEquals(testShelf1.getBookCount(testBook),2);

    }

    @Test
    void removeBook() {

        Shelf testShelf1 = new Shelf();
        testShelf1.setSubject("sci-fi");

        Book testBook2 = new Book("yt4567","How to do ","fake",123,"Me", LocalDate.parse("1000-01-01"));

        assertEquals(testShelf1.removeBook(testBook2),Code.BOOK_NOT_IN_INVENTORY_ERROR);

        Book testBook = new Book("98723-a","How to procrastinate on Shelf.java","sci-fi",9999,"Me", LocalDate.parse("1000-01-01"));

        assertEquals(testShelf1.addBook(testBook),Code.SUCCESS);

        assertEquals(testShelf1.getBookCount(testBook),1);

        assertEquals(testShelf1.removeBook(testBook),Code.SUCCESS);

        assertEquals(testShelf1.getBookCount(testBook),0);

        for (int i = 0; i<4; i++){
            assertEquals(testShelf1.addBook(testBook),Code.SUCCESS);
        }

       for(int i = 0; i<4; i++){
           assertEquals(testShelf1.removeBook(testBook),Code.SUCCESS);
       }

        assertEquals(testShelf1.getBookCount(testBook),0);

    }

    @Test
    void listBooks() {
        String testString1;
        String testString2;

        Shelf testShelf1 = new Shelf();
        Shelf testShelf2 = new Shelf();
        testShelf1.setSubject("sci-fi");
        testShelf2.setSubject("sci-fi");

        Book testBook = new Book("98723-a","How to procrastinate on Shelf.java","sci-fi",9999,"Me", LocalDate.parse("1000-01-01"));

        testShelf1.addBook(testBook);
        testShelf2.addBook(testBook);

        testString1 = testShelf1.listBooks();
        testString2 = testShelf2.listBooks();

        assertEquals(testString1,testString2);

    }
}