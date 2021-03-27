/**
 1.Title: Book
 2.Abstract: Creates a Java class called "Book"  for later use in library project
 3.Author: Alexander Verdugo
 4.Date: February 13th 2021
 */

import java.time.LocalDate;


public class Book {

//    public static void main(String[] args){
//      Book myBook = new Book("1337","Headfirst Java","education",1337,"Grady Booch", LocalDate.parse("1000-01-01"));
//      System.out.println(myBook.toString());
//    }

    public static final int ISBN_ = 0;
    public static final int TITTLE_ = 1;
    public static final int SUBJECT_ = 2;
    public static final int PAGE_COUNT_ = 3;
    public static final int AUTHOR_ = 4;
    public static final int DUE_DATE_ = 5;

    private String ISBN;
    private String Title;
    private String Subject;
    private int PageCount;
    private String Author;
    private LocalDate DueDate;

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getSubject() {
        return Subject;
    }

    public void setSubject(String subject) {
        Subject = subject;
    }

    public int getPageCount() {
        return PageCount;
    }

    public void setPageCount(int pageCount) {
        PageCount = pageCount;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public LocalDate getDueDate() {
        return DueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        DueDate = dueDate;
    }

    public Book(String ISBN, String title, String subject, int pageCount, String author, LocalDate dueDate) {
        this.ISBN = ISBN;
        Title = title;
        Subject = subject;
        PageCount = pageCount;
        Author = author;
        DueDate = dueDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        if (getPageCount() != book.getPageCount()) return false;
        if (getISBN() != null ? !getISBN().equals(book.getISBN()) : book.getISBN() != null) return false;
        if (getTitle() != null ? !getTitle().equals(book.getTitle()) : book.getTitle() != null) return false;
        if (getSubject() != null ? !getSubject().equals(book.getSubject()) : book.getSubject() != null) return false;
        return getAuthor() != null ? getAuthor().equals(book.getAuthor()) : book.getAuthor() == null;
    }

    @Override
    public int hashCode() {
        int result = getISBN() != null ? getISBN().hashCode() : 0;
        result = 31 * result + (getTitle() != null ? getTitle().hashCode() : 0);
        result = 31 * result + (getSubject() != null ? getSubject().hashCode() : 0);
        result = 31 * result + getPageCount();
        result = 31 * result + (getAuthor() != null ? getAuthor().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return Title + " by "+ Author + " ISBN:" + ISBN;
    }
}



