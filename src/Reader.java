
/**
 1.Title:Reader
 2.Abstract: Creates a Java class called "Reader"  for later use in library project
 3.Author: Alexander Verdugo
 4.Date: February 23th 2021
 */
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
public class Reader {

    public static final int CARD_NUMBER_ = 0;
    public static final int NAME_ = 1;
    public static final int PHONE_ = 2;
    public static final int BOOK_COUNT_ = 3;
    public static final int BOOK_START_ = 4;

    private int cardNumber;
    private String name;
    private String phone;
    private List<Book> books;

//    public static void main(String[] args) {
//
//        Reader reader1 = new Reader(1,"chucky cheese","987-098-1445");
//        Book testBook1=new Book("1337","Headfirst Java","education",1337,"Grady Booch", LocalDate.parse("1000-01-01"));
//        Book testBook2=new Book("Doritos-MountainDew","How to be a MLG","Darude Sandstorm",420,"Shrek", LocalDate.parse("6969-01-01"));
//        reader1.addBook(testBook1);
//        reader1.addBook(testBook2);
//
//        System.out.println(reader1.toString());
//
//    }

    public Reader(int cardNumber, String name, String phone) {
        this.cardNumber = cardNumber;
        this.name = name;
        this.phone = phone;
        books = new ArrayList<>();
    }

    public Code removeBook(Book book) {
        if(hasBook(book)==false){
            return Code.READER_DOESNT_HAVE_BOOK_ERROR;
        }
        books.remove(book);
        return Code.SUCCESS;
    }

    public boolean hasBook(Book book) {
        if(book==null){
            return false;
        }
        return books.contains(book);
    }

    public Code addBook(Book book) {
        if(book==null){
            return Code.LIBRARY_OUT_OF_BOOKS_ERROR;
        }
        if(hasBook(book)){
            return Code.BOOK_ALREADY_CHECKED_OUT_ERROR;
        }
        books.add(book);
        return Code.SUCCESS;
    }

    public int getBookCount() {
        if(books==null){
            return 0;
        }
        return books.size();
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Reader reader = (Reader) o;

        if (getCardNumber() != reader.getCardNumber()) return false;
        if (getName() != null ? !getName().equals(reader.getName()) : reader.getName() != null) return false;
        return getPhone() != null ? getPhone().equals(reader.getPhone()) : reader.getPhone() == null;
    }

    @Override
    public int hashCode() {
        int result = getCardNumber();
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getPhone() != null ? getPhone().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return name + " (#" + cardNumber + ") " + "has checked out " + books;

    }

}
