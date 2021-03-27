/**
 1.Title: BookTest
 2.Abstract: Tests Java class "Book"
 3.Author: Alexander Verdugo
 4.Date: February 18th 2021
 */
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class BookTest {

//    Book myBook = null;
//    String test = null;
//    @BeforeEach
//    void setUp() {
//       myBook=new Book("1337","Headfirst Java","education",1337,"Grady Booch", LocalDate.parse("1000-01-01"));
//    }
//
//    @AfterEach
//    void tearDown() {
//        myBook=null;
//        test = null;
//    }

//    @Test
//    void getISBN() {
//        assertNull(test);
//        test=myBook.getISBN();
//        assertEquals(test,"1337");
//    }

    @Test
    void ConstructorTest() {
        Book testBook = null;
        assertNull(testBook);
        /**Not sure how to create dummy local date parameter*/
        testBook = new Book("","","",0,"",LocalDate.parse("1000-01-01"));
        assertNotNull(testBook);
    }

    @Test
    void FieldGetterTest() {

         String testISBN ="1337";
         String testTitle ="Headfirst Java";
         String testSubject ="education";
         int testPageCount =1337;
         String testAuthor ="Grady Booch";
         LocalDate testDueDate =LocalDate.parse("1000-01-01");
         //System.out.println(testDueDate);
         Book testBook=new Book(testISBN,testTitle,testSubject,testPageCount,testAuthor,testDueDate);

         testISBN=testBook.getISBN();
         assertEquals(testISBN,"1337");

         testTitle=testBook.getTitle();
         assertEquals(testTitle,"Headfirst Java");

         testSubject=testBook.getSubject();
         assertEquals(testSubject,"education");

         testPageCount=testBook.getPageCount();
         assertEquals(testPageCount,1337);

         testAuthor=testBook.getAuthor();
         assertEquals(testAuthor,"Grady Booch");

         testDueDate=testBook.getDueDate();
         assertEquals(testDueDate,LocalDate.parse("1000-01-01"));

    }

    @Test
    void EqualityTest() {
        Book testBook1=new Book("1337","Headfirst Java","education",1337,"Grady Booch", LocalDate.parse("1000-01-01"));

        Book testBook2=new Book("Doritos-MountainDew","How to be a MLG","Darude Sandstorm",420,"Shrek", LocalDate.parse("6969-01-01"));

        assertNotEquals(testBook1,testBook2);

        Book testBook3=new Book("Doritos-MountainDew","How to be a MLG","Darude Sandstorm",420,"Shrek", LocalDate.parse("6969-01-01"));

        assertEquals(testBook2,testBook3);
    }

    @Test
    void SetterTest() {


        String testISBN ="1337";
        String testTitle ="Headfirst Java";
        String testSubject ="education";
        int testPageCount =1337;
        String testAuthor ="Grady Booch";
        LocalDate testDueDate = LocalDate.parse("1000-01-01");

        Book testBook = new Book(testISBN,testTitle,testSubject,testPageCount,testAuthor,testDueDate);

        /**Creating new values*/
        String test2ISBN ="42-w-87";
        String test2Title ="Hitchhikers Guide To the Galaxy";
        String test2Subject ="sci-fi";
        int test2PageCount =42;
        String test2Author ="Douglas Adams";
        LocalDate test2DueDate =LocalDate.parse("5421-11-12");

        /**setting parameters to new values*/
        testBook.setISBN(test2ISBN);
        testBook.setTitle(test2Title);
        testBook.setSubject(test2Subject);
        testBook.setPageCount(test2PageCount);
        testBook.setAuthor(test2Author);
        testBook.setDueDate(test2DueDate);

        /**checking that new values aren't equal to old values*/
        assertNotEquals(testISBN,test2ISBN);
        assertNotEquals(testTitle,test2Title);
        assertNotEquals(testSubject,test2Subject);
        assertNotEquals(testPageCount,test2PageCount);
        assertNotEquals(testAuthor,test2Author);
        assertNotEquals(testDueDate,test2DueDate);

        /**checking that new parameters have been set correctly*/
        assertEquals(testBook.getISBN(),test2ISBN);
        assertEquals(testBook.getTitle(),test2Title);
        assertEquals(testBook.getSubject(),test2Subject);
        assertEquals(testBook.getPageCount(),test2PageCount);
        assertEquals(testBook.getAuthor(),test2Author);
        assertEquals(testBook.getDueDate(),test2DueDate);

    }


}