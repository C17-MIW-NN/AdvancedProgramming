package model.library;

import model.library.Author;

import java.util.*;

public class Book implements Comparable<Book> {
    public long isbn;
    public String title;
    public int yearOfIssue;
    private int numPages;
    public List<Author> authors;
    public List<Section> chapters;

    public Book() {
        this.authors = new ArrayList<>();
        this.chapters = new ArrayList<>();
        this.yearOfIssue = 1990 + (int)(15 * Math.random());
        this.numPages = 100 + (int)(900 * Math.random());
    }
    public Book(long isbn) {
        this();
        this.isbn = isbn;
    }
    public Book(long isbn, String title) {
        this(isbn);
        this.title = title;
    }

    public void printTOC() {
        System.out.println("*** " + this.getTitle() + " ***");

        this.getAuthors().forEach(System.out::println);

        this.chapters.forEach(section -> section.printAllTitles(""));
    }

    public void printNumberedTOC() {
        System.out.println("*** " + this.getTitle() + " ***");

        this.getAuthors().forEach(System.out::println);

        for (int i = 0; i < this.chapters.size(); i++) {
            this.chapters.get(i).printAllNumberedTitles(String.valueOf(i+1));
        }
    }

    public static Book theEmperorsNewMind() {
        Book tenm = new Book(9780198519737L, "The Emperor's New Mind");
        tenm.getAuthors().add(new Author("Penrose", "R."));

        Section chapter = new Section("Can a computer have a mind?", 3);
        chapter.subSections.add(new Section("The Turing Test", 5, 280));
        chapter.subSections.add(new Section("Artificial Intelligence", 11, 144));
        chapter.subSections.add(new Section("An AI approach to pleasure and pain", 14, 117));
        tenm.chapters.add(chapter);

        chapter = new Section("Algorithms and Turing machines", 30);
        chapter.subSections.add(new Section("How to outdo an Algorithm", 64, 97));
        chapter.subSections.add(new Section("Church's lambda calculus", 66, 367));
        tenm.chapters.add(chapter);

        chapter = new Section("Real brains and model brains", 374);
        chapter.subSections.add(new Section("Where is the seat of consciousness", 381, 141));
        tenm.chapters.add(chapter);

        System.out.println(tenm.getSortedAuthors(Author::compareByInitials));

        return tenm;

    }

    @Override
    public int compareTo(Book o) {
        return this.isbn < o.isbn ? -1 : this.isbn > o.isbn ? 1 : 0;
    }

    public int compareByFirstAuthor(Book o) {
        return this.getAuthors().get(0).compareTo(o.getAuthors().get(0));
    }

    public static int compareByFirstAuthor2(Book b1, Book b2) {
        return b1.getAuthors().get(0).compareTo(b2.getAuthors().get(0));
    }

    public List<Author> getSortedAuthors(Comparator<Author> comparator) {
        List<Author> authorsList = new ArrayList<>(this.getAuthors());
        authorsList.sort(comparator);
        return authorsList;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public int getNumPages() {
        return numPages;
    }

    @Override
    public String toString() {
        return String.format("\"%s\" by %s%s, ISBN=%d",
                this.title,
                this.authors.get(0),
                (this.authors.size() > 1 ? " et al." : ""),
                this.isbn);
    }

    public static boolean travel2000plus(Book book) {
        return book.yearOfIssue >= 2000 &&
                book.getTitle().toLowerCase().contains("travel");
    }
    public boolean authoredByEWD() {
        return this.getAuthors().contains(new Author("Dijkstra", "E.W."));
    }

    public static boolean issuedBefore2000(Book book) {
        return book.yearOfIssue < 2000;
    }
}
