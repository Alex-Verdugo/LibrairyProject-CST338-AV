import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 1.Title:Library
 2.Abstract: Creates a Java class called "Library" that uses classes Book, Reader, and Shelf
 3.Author: Alexander Verdugo
 4.Date: March 25th 2021 // Bad idea to start the day before I am so ready for spring break
 */

//I am not sure why but I can only add shelves if there exists at least one book with the same subject

/** Actually after 3 hours of going crazy it turns out that my listBooks method in Shelf did not do anything if the shelf had no books*/
public class Library {

    public static  final int LENDING_LIMIT = 5;
    private String name;
    private static int libraryCard;
    private List<Reader> readers;
    private HashMap<String, Shelf> shelves;
    private HashMap<Book, Integer> books;


    public Library(String name) {
        this.name = name;
        this.books = new HashMap<>();
        this.shelves = new HashMap<>();
        readers = new ArrayList<>();


    }

    public Code init(String filename){

        int bookCount;
        int readerCount;
        int shelfCount;
        File f = new File(filename);

        Scanner scan = null;

        try {
            scan = new Scanner(f);
        } catch (FileNotFoundException e) {
            System.out.println("could not find the file " + e);
            return Code.FILE_NOT_FOUND_ERROR;
        }

        bookCount = convertInt(scan.nextLine(),Code.BOOK_COUNT_ERROR);


            if(bookCount<0){
                return errorCode(bookCount);
            }
            initBooks(bookCount,scan);
            listBooks();
        //System.out.println("displaying Books hashmap"+books.toString());
        shelfCount = convertInt(scan.nextLine(),Code.SHELF_COUNT_ERROR);

            if(shelfCount<0){
                return errorCode(shelfCount);
            }
            initShelves(shelfCount,scan);
        //System.out.println("listing shelves:");
            listShelves(true);

        readerCount = convertInt(scan.nextLine(),Code.READER_COUNT_ERROR);
        if(readerCount<0){
            return errorCode(readerCount);
        }
        initReader(readerCount,scan);

        listReaders();

        return Code.SUCCESS;
    }



    private Code initBooks(int bookCount, Scanner scan){

        System.out.println("parsing " + bookCount + " books");

        if(bookCount<1){
            return Code.LIBRARY_ERROR;
        }

        while (scan.hasNextLine() && bookCount>0){
            String line = scan.nextLine();
            String[] bookInfo = line.split(",");
            String isbn = bookInfo[0];
            String tittle = bookInfo[1];
            String subject = bookInfo[2];
            int pageCount = convertInt(bookInfo[3],Code.PAGE_COUNT_ERROR);
            String author = bookInfo[4];
            LocalDate date = convertDate(bookInfo[5],Code.DATE_CONVERSION_ERROR);

            Book bookToBeAdded = new Book(isbn,tittle,subject,pageCount,author,date);
            System.out.println("parsing book: "+ line);
            addBook(bookToBeAdded);
            bookCount--;
        }
        System.out.println("SUCCESS");
        return Code.SUCCESS;
    }

    private Code initShelves(int shelfCount , Scanner scan){
        System.out.println("parsing " + shelfCount + " shelves");
        int shelfCount2 = shelfCount;
        if(shelfCount<1){
            return Code.SHELF_COUNT_ERROR;
        }
       // System.out.println("Shelf Count : " + shelfCount);
        while (scan.hasNextLine() && shelfCount>0) {
            String line = scan.nextLine();
            //System.out.println("Line read into initShelves :    " + line);
            String[] shelfInfo = line.split(",");
            int shelfNumber;
            String subject = shelfInfo[1];

            if (convertInt(shelfInfo[0], Code.SHELF_NUMBER_PARSE_ERROR) == -999) {
                return Code.SHELF_NUMBER_PARSE_ERROR;
            } else {
                shelfNumber = convertInt(shelfInfo[0], Code.SHELF_NUMBER_PARSE_ERROR);
            }
            System.out.println("Parsing Shelf : " + line);
            Shelf shelfToBeAdded = new Shelf();
            shelfToBeAdded.setSubject(subject);
            shelfToBeAdded.setShelfNumber(shelfNumber);
            addShelf(shelfToBeAdded);
            for (Book book : books.keySet()) {
               for (int i = 0; i <books.get(book); i++) {
                    if (book.getSubject().equals(shelfToBeAdded.getSubject())) {
                        addBookToShelf(book, shelfToBeAdded);
                      //  System.out.println("THE FUNCTION WAS CALLED HERE");
                        //System.out.println(shelfToBeAdded.toString());
                    }
               }


            }

            shelfCount--;

         }



        if (shelves.size()==shelfCount2){
            System.out.println("SUCCESS");
            return Code.SUCCESS;
        }

        System.out.println("Number of shelves doesn't match expected");
        return Code.SHELF_NUMBER_PARSE_ERROR;

    }


//The doc was a little vauge on this function
    private Code initReader(int readerCount, Scanner scan){

        if(readerCount<=0){
            return Code.READER_COUNT_ERROR;
        }

        while (scan.hasNextLine() && readerCount>0){
            String line = scan.nextLine();
           // System.out.println("Line read into initReader :    " + line);
            String[] readerInfo = line.split(",");
            int cardNumber = convertInt(readerInfo[0],Code.READER_CARD_NUMBER_ERROR);
            String name = readerInfo[1];
            String phone = readerInfo[2];
            LocalDate dueDate = convertDate(readerInfo[5],Code.DATE_CONVERSION_ERROR);


            Reader readerToBeAdded = new Reader(cardNumber,name,phone);
            addReader(readerToBeAdded);
          for(int i = 0;i<Reader.BOOK_COUNT_;i++){
              Book bookToAdd = getBookByISBN(readerInfo[4]);
              bookToAdd.setDueDate(dueDate);

              if (!readerToBeAdded.hasBook(bookToAdd)){
                  checkOutBook(readerToBeAdded,bookToAdd);
              }
          }
            readerCount--;
        }
            System.out.println("SUCCESS");
            return Code.SUCCESS;
    }



    public static int convertInt(String recordCountString, Code code){

        int num = 0;

        try {
            num = Integer.parseInt(recordCountString.trim());
        }
        catch (NumberFormatException nfe) {

            if(code == Code.PAGE_COUNT_ERROR){
                System.out.println("Value which caused the error: " + recordCountString + "\n" + Code.PAGE_COUNT_ERROR.getMessage());
                return Code.PAGE_COUNT_ERROR.getCode();
            }
           else if(code == Code.BOOK_COUNT_ERROR){
                System.out.println("Value which caused the error: " + recordCountString + "\n" + Code.BOOK_COUNT_ERROR.getMessage());
                return Code.BOOK_COUNT_ERROR.getCode();
            }
            else if(code == Code.DATE_CONVERSION_ERROR){
                System.out.println("Value which caused the error: " + recordCountString + "\n" + Code.DATE_CONVERSION_ERROR.getMessage());
                return Code.DATE_CONVERSION_ERROR.getCode();
            }
            else {
                System.out.println("Value which caused the error: " + recordCountString + "\n" +Code.UNKNOWN_ERROR.getMessage());
                return Code.UNKNOWN_ERROR.getCode();
            }

        }

        return num;
    }

    public Code returnBook(Reader reader,Book book){

        if(!reader.getBooks().contains(book)){
            System.out.println(reader.getName() + " doesn't have " + book.getTitle() + " checked out");
            return Code.READER_DOESNT_HAVE_BOOK_ERROR;
        }

        System.out.println(reader.getName() + " is returning " + book);
        Code resultCode = reader.removeBook(book);

        if(resultCode == Code.SUCCESS){
            return returnBook(book);
        }else {
            System.out.println("Could not return" + book);
        }
        return resultCode;
    }

    public Code addBook(Book newBook){

        if(books.containsKey(newBook)){
            books.put(newBook,books.get(newBook)+1);
            System.out.println(books.get(newBook)+" copies of " + newBook.toString() + " in the stacks");
        }else {
            books.put(newBook,1);
            System.out.println(newBook.toString()+" added to the stacks.");
        }
        if(shelves.containsKey(newBook.getSubject())){
            shelves.get(newBook.getSubject()).addBook(newBook);
            return Code.SUCCESS;
        }
        System.out.println("No shelf for " + newBook.getSubject()+ " books");
        return Code.SHELF_EXISTS_ERROR;
    }

    private Code addBookToShelf(Book book,Shelf shelf){

        //System.out.println("shelf Subject: "+shelf.getSubject() + " Book subject: " + book.getSubject());
            shelf.addBook(book);
        if(returnBook(book)==Code.SUCCESS){

            return Code.SUCCESS;
        }else if(book.getSubject() != shelf.getSubject()){
            return Code.SHELF_SUBJECT_MISMATCH_ERROR;
        }

        Code resultCode = shelf.addBook(book);
        System.out.println(resultCode);
        if (resultCode == Code.SUCCESS){
            //System.out.println(book.toString() + " added to shelf");
            return Code.SUCCESS;
        }
       /// System.out.println("Could not add "+ book + " to shelf");
        return resultCode;
    }




    public int listBooks(){

        int totalAmountOfBooks = 0;
        for(Book book : books.keySet()){
            totalAmountOfBooks += books.get(book);

            System.out.println(books.get(book)+" copies of " + book.toString());
        }
        return totalAmountOfBooks;
    }

    public Code checkOutBook(Reader reader, Book book){
       // System.out.println("IN CHECK OUT BOOK FUNCTION");
        System.out.println();
        System.out.println("Returning Reader : " + reader.toString());
        if(!readers.contains(reader)){
            System.out.println(reader.getName() + " does not have an account here");
            return Code.READER_NOT_IN_LIBRARY_ERROR;
        }
        if(reader.getBookCount()>LENDING_LIMIT){
            System.out.println(reader.getName()+" has reached the lending limit, (" + LENDING_LIMIT + ")");
            return Code.BOOK_LIMIT_REACHED_ERROR;
        }
        if(!books.containsKey(book)){
            System.out.println("ERROR: could not find "+ book);
            return Code.BOOK_NOT_IN_INVENTORY_ERROR;
        }
        if (!shelves.containsKey(book.getSubject())){
            System.out.println("no shelf for "+book.getSubject()+" books!");
            return Code.SHELF_EXISTS_ERROR;
        }
        if (shelves.get(book.getSubject()).getBookCount(book)<1){
            System.out.println("ERROR: no copies of "+book +" remain");
            return Code.BOOK_NOT_IN_INVENTORY_ERROR;
        }
        Code addResultCode = reader.addBook(book);
        if (addResultCode != Code.SUCCESS){
            //System.out.println("Couldn't checkout "+ book);
            return addResultCode;
        }
        Code removeResultCode = shelves.get(book.getSubject()).removeBook(book);
        if (removeResultCode.equals(Code.SUCCESS)){
            System.out.println(book+" checked out successfully");
        }
        return removeResultCode;
    }

    public Book getBookByISBN(String isbn){

        for (Book book : books.keySet()){

            if(book.getISBN().equals(isbn)){
                return book;
            }
        }
        System.out.println("ERROR: Could not find a book with isbn: "+ isbn);
        return null;
    }


    public Code returnBook(Book book){

        if(!shelves.containsKey(book.getSubject())){
            System.out.println("No Shelf for " + book);
            return Code.SHELF_EXISTS_ERROR;
        }else {
            shelves.get(book.getSubject()).addBook(book);
        }
        return Code.SUCCESS;
    }

   public Code listShelves(boolean showBooks){

        if(showBooks){
           // System.out.println("In list shelves !!!");
            for (Shelf shelf : shelves.values()){
               // System.out.println(shelf.getBooks().toString());
                //May need to add conditon for if shelf is empty
                String output = shelf.listBooks();
                System.out.println(output);
            }
        }else {
            for (Shelf shelf : shelves.values()){
                System.out.println(shelf.toString());
            }
        }
        return Code.SUCCESS;
    }

    public Shelf getShelf(Integer shelfNumber){

        for (Shelf shelf : shelves.values()){
            if(shelf.getShelfNumber()==shelfNumber){
                return shelf;
            }
        }
        System.out.println("No shelf number " + shelfNumber + " found");
        return null;
    }

    public Shelf getShelf(String subject){

        for (Shelf shelf : shelves.values()){
            if(shelf.getSubject().equals(subject)){
                return shelf;
            }
        }
        System.out.println("No shelf for " + subject + " books");
        return null;
    }

    public int listReaders(){
        int totalAmountOfReaders = 0;

        for(Reader reader : readers){
            totalAmountOfReaders ++;
                System.out.println(reader.toString());

        }
        return totalAmountOfReaders;
    }


    public int listReaders(boolean showBooks){
        int totalAmountOfReaders = 0;

        if (showBooks) {
            for(Reader reader : readers){
                totalAmountOfReaders ++;
                System.out.println(reader.getName()+" ("+ reader.getCardNumber()+") " + " has the following books:");
                System.out.println(reader.getBooks());
                return totalAmountOfReaders;
            }
        }

            return listReaders();
    }

    public Code addShelf(String shelfSubject){
        Shelf shelf = new Shelf();
        shelf.setSubject(shelfSubject);
        return addShelf(shelf);
    }

    public Reader getReaderByCard(int cardNumber){

        for(Reader reader : readers){
            if(reader.getCardNumber()==cardNumber){
                return reader;
            }
        }
        System.out.println("Could not find a reader with card #" + cardNumber);
        return null;
    }

    public Code addReader(Reader reader){

        if(readers.contains(reader)){
            System.out.println(reader.getName()+" already has an account!");
            return Code.READER_ALREADY_EXISTS_ERROR;
        }

        for(Reader loopReader : readers){
            if(loopReader.getCardNumber()==reader.getCardNumber()){
                    System.out.println(loopReader.getName()+ " and " + reader.getName()+ " have the same card number!");
                    return Code.READER_CARD_NUMBER_ERROR;
                }
            }

          readers.add(reader);
          System.out.println(reader.getName()+ " added to the library!");

          if(reader.getCardNumber()>libraryCard){
              libraryCard = reader.getCardNumber();
          }
            return Code.SUCCESS;
    }


    public Code removeReader(Reader reader){

        if(readers.contains(reader) && reader.getBookCount()>0){
            System.out.println(reader.getName() + " must return all books!");
            return Code.READER_STILL_HAS_BOOKS_ERROR;
        }
        if(!readers.contains(reader)){
            System.out.println(reader.getName());
            System.out.println("is not part of this Library");
            return Code.READER_NOT_IN_LIBRARY_ERROR;
        }

        readers.remove(reader);
        return Code.SUCCESS;
    }


    public Code addShelf(Shelf shelf){
        int nextShelfNumber = 0;

        if(shelves.containsValue(shelf)){
            System.out.println("ERROR: Shelf already exists " +shelf);
            return Code.SHELF_EXISTS_ERROR;
        }

        // finding largest shelf number and setting it to nextShelfNumber
        for(Shelf shelf1 : shelves.values()){
            if(shelf1.getShelfNumber()>nextShelfNumber){
                nextShelfNumber=shelf1.getShelfNumber();
            }
        }
        shelves.put(shelf.getSubject(),shelf);
        shelf.setShelfNumber(nextShelfNumber+1);

        for (Book book : books.keySet()){
            if(book.getSubject().equals(shelf.getSubject())){
                shelf.addBook(book);
            }
        }
        return Code.SUCCESS;
    }


    public static LocalDate convertDate(String date, Code errorCode) {

            if(date.equals("0000")){
                return LocalDate.of(1970,1,1);
            }

           String[] dateSplit = date.split("-");
           int year = Integer.parseInt(dateSplit[0]);
           int month = Integer.parseInt(dateSplit[1]);
           int day = Integer.parseInt(dateSplit[2]);

           if(dateSplit.length>3){
               System.out.println("ERROR: date conversion error, could not parse " + date);
               System.out.println("Using default date (01-jan-1970)");
               return LocalDate.of(1970,1,1);
           }
           else if(year<0||month<0||day<0){
               System.out.println("Error converting date: Year " + year);
               System.out.println("Error converting date: Month " + month);
               System.out.println("Error converting date: Day " + day);
               System.out.println("Using default date (01-jan-1970");
               return LocalDate.of(1970,1,1);
           }
        return LocalDate.of(year,month,day);
    }

    public static int getLibraryCardNumber(){
        return libraryCard+1;
    }

    private Code errorCode(int codeNumber){

       for (Code code : Code.values()){
           if(code.getCode() == codeNumber){
               return code;
           }
       }
       return Code.UNKNOWN_ERROR;
    }

}
