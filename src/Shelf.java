import java.time.LocalDate;
import java.util.HashMap;

/**
 1.Title: Shelf
 2.Abstract: Creates Shelf class for library project
 3.Author: Alexander Verdugo
 4.Date: March 9th 2021
 */

public class Shelf {

    public static final int SHELF_NUMBER_ = 0;
    public static final int SUBJECT_ = 1;

    private int shelfNumber;
    private String subject;
    private HashMap<Book,Integer> books;

//    public static void main(String[] args) {
//        Shelf test = new Shelf();
//        test.setSubject("sci-fi");
//        test.setShelfNumber(1);
//        Book testBook = new Book("1337","Headfirst Java","sci-fi",1337,"Grady Booch", LocalDate.parse("1000-01-01"));
//        test.addBook(testBook);
//        test.addBook(testBook);
//        System.out.println(test.listBooks());
//    }

    public int getShelfNumber() {
        return shelfNumber;
    }

    public void setShelfNumber(int shelfNumber) {
        this.shelfNumber = shelfNumber;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public HashMap<Book, Integer> getBooks() {
        return books;
    }

    public void setBooks(HashMap<Book, Integer> books) {
        this.books = books;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Shelf shelf = (Shelf) o;

        if (getShelfNumber() != shelf.getShelfNumber()) return false;
        return getSubject() != null ? getSubject().equals(shelf.getSubject()) : shelf.getSubject() == null;
    }

    @Override
    public int hashCode() {
        int result = getShelfNumber();
        result = 31 * result + (getSubject() != null ? getSubject().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return shelfNumber +
                " : " + subject;
    }

    public int getBookCount(Book book){

        if(books.containsKey(book)){
            return books.get(book);
        }

        return -1;
    }

    public Code addBook(Book book){

       // System.out.println("Book Subject (Shelf) " + book.getSubject()+ " Shelf Subject: " + subject);

        if(books.containsKey(book)){
            books.put(book,getBookCount(book)+1);
            System.out.println(book.toString()+" added to shelf " + this.toString());
            return Code.SUCCESS;
        }

        else if (book.getSubject().equals(subject)){
            books.put(book,1);
            System.out.println(book.toString()+" added to shelf " + this.toString());
            return Code.SUCCESS;
        }

         System.out.println("MistMatch Error");
         return Code.SHELF_SUBJECT_MISMATCH_ERROR;
    }

    public Code removeBook(Book book){
        if(books.containsKey(book)==false){
            System.out.println(book.getTitle()+" is not on shelf "+subject);
            return Code.BOOK_NOT_IN_INVENTORY_ERROR;
        }
        else if (books.get(book)==0){
            System.out.println(" No copies of "+book.getTitle()+" remain on shelf "+subject);
            return Code.BOOK_NOT_IN_INVENTORY_ERROR;
        }

        books.put(book,getBookCount(book)-1);
        System.out.print(book.getTitle()+" successfully removed from shelf "+subject);
        return Code.SUCCESS;

    }

    public Shelf() {
      this.books = new HashMap<>();
      this.subject = new String();
      this.shelfNumber = 0;
    }

    public String listBooks(){
        String output = "";
        String start = "";

        if(books.isEmpty()){
            return System.lineSeparator() + "0 books on shelf: "+shelfNumber + " :" + subject;
        }

       for (Book book : books.keySet()){

           if (books.get(book)!= 1){
               start =  System.lineSeparator() + getBookCount(book) + " books on shelf: " +shelfNumber + " :" + subject;
           }
            else {
               start =  System.lineSeparator() + getBookCount(book) + " book on shelf: " +shelfNumber + " :" + subject;
           }
           output = System.lineSeparator() + book.toString() + " " + getBookCount(book);
       }

        return start+output;
    }


}
