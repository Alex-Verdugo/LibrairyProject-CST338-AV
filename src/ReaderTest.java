/**
 1.Title: ReaderTest
 2.Abstract: Tests Java class "Reader"
 3.Author: Alexander Verdugo
 4.Date: February 23th 2021
 */

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ReaderTest {



    @Test
    void ConstructorTest() {

       Reader testReader = null;
       assertNull(testReader);
       testReader = new Reader(7861,"Alex Verdugo","123-456-7891");
       assertNotNull(testReader);

    }

    @Test
    void removeBook() {

        Reader reader1 = new Reader(1,"chucky cheese","987-098-1445");
        Book testBook = new Book("","","",0,"",null);

        assertEquals(reader1.removeBook(testBook),Code.READER_DOESNT_HAVE_BOOK_ERROR);

        reader1.addBook(testBook);

        assertEquals(reader1.removeBook(testBook),Code.SUCCESS);
    }

    @Test
    void hasBook() {

        Reader reader1 = new Reader(1,"chucky cheese","987-098-1445");

        Book testBook = new Book("","","",0,"",null);

        assertFalse(reader1.hasBook(testBook));

        reader1.addBook(testBook);

        assertTrue(reader1.hasBook(testBook));

    }

    @Test
    void addBook() {

        Reader reader1 = new Reader(1,"chucky cheese","987-098-1445");

        Book testBook = new Book("","","",0,"",null);

        assertEquals(reader1.addBook(testBook), Code.SUCCESS);

        assertNotEquals(reader1.addBook(testBook), Code.SUCCESS);

        assertEquals(reader1.addBook(testBook), Code.BOOK_ALREADY_CHECKED_OUT_ERROR);

    }

    @Test
    void getBookCount() {

        Reader reader1 = new Reader(1,"chucky cheese","987-098-1445");

        assertEquals(0,reader1.getBookCount());

        reader1.addBook(null);

        assertEquals(0,reader1.getBookCount());

        Book testBook = new Book("","","",0,"",null);

        reader1.addBook(testBook);
        assertEquals(1,reader1.getBookCount());

        reader1.removeBook(testBook);
        assertEquals(0,reader1.getBookCount());
    }

    @Test
    void getBooks() {

        Reader reader1 = new Reader(1,"tester","987-098-1445");

        Reader reader2 = new Reader(9,"tester2","781-321-1885");

        Book testBook = new Book("123","testing","readerbooks",4,"me",null);

        Book testBook2 = new Book("18765","testing2","readerbooks",997,"not me",null);

        reader1.addBook(testBook);
        reader1.addBook(testBook2);

        assertNotEquals(reader1.getBooks(),reader2.getBooks());

        reader2.addBook(testBook);
        reader2.addBook(testBook2);

        assertEquals(reader1.getBooks(),reader2.getBooks());
    }

    @Test
    void setBooks() {

        Reader reader1 = new Reader(1,"tester","987-098-1445");

        Reader reader2 = new Reader(9,"tester2","781-321-1885");

        Book testBook = new Book("123","testing","readerbooks",4,"me",null);

        Book testBook2 = new Book("18765","testing2","readerbooks",997,"not me",null);

        reader1.addBook(testBook);
        reader1.addBook(testBook2);

        assertNotEquals(reader1.getBooks(),reader2.getBooks());

        reader2.setBooks(reader1.getBooks());

        assertEquals(reader1.getBooks(),reader2.getBooks());

    }

    @Test
    void getCardNumber() {
        int testCardNumber = 0;

        Reader reader1 = new Reader(987,"tester","987-098-1445");

        assertNotEquals(testCardNumber,reader1.getCardNumber());

        testCardNumber = reader1.getCardNumber();

        assertEquals(testCardNumber,987);

    }

    @Test
    void setCardNumber() {

        int testCardNumber = 98;

        Reader reader1 = new Reader(987,"tester","987-098-1445");

        int oldCardNumber = reader1.getCardNumber();

        reader1.setCardNumber(testCardNumber);

        assertNotEquals(reader1.getCardNumber(),oldCardNumber);

        assertEquals(reader1.getCardNumber(),testCardNumber);
    }

    @Test
    void getName() {

        String testName = "Alex";

        Reader reader1 = new Reader(987,"tester","987-098-1445");

        assertNotEquals(testName,reader1.getName());

        testName = reader1.getName();

        assertEquals(testName,"tester");

    }

    @Test
    void setName() {

        String testName = "Alex";

        Reader reader1 = new Reader(987,"tester","987-098-1445");

        String oldName = reader1.getName();

        reader1.setName(testName);

        assertNotEquals(reader1.getName(),oldName);

        assertEquals(reader1.getName(),testName);

    }

    @Test
    void getPhone() {

        String testPhone = "567-8762-438";

        Reader reader1 = new Reader(987,"tester","987-098-1445");

        assertNotEquals(testPhone,reader1.getPhone());

        testPhone = reader1.getPhone();

        assertEquals(testPhone,"987-098-1445");

    }

    @Test
    void setPhone() {

        String testPhone = "876-234-9912";

        Reader reader1 = new Reader(987,"tester","987-098-1445");

        String oldPhone = reader1.getPhone();

        reader1.setPhone(testPhone);

        assertNotEquals(reader1.getPhone(),oldPhone);

        assertEquals(reader1.getPhone(),testPhone);

    }

}