package model.library;

import java.util.*;
import java.util.function.*;

public class BookRegister {
    private Set<Book> books;
    private Set<Author> authors;

    private BookRegister() {
        this.books = new TreeSet<>();
        this.authors = new HashSet<>();
    }

    public static class Builder {
        private BookRegister register = new BookRegister();

        private Author addOrGetAuthor(Author author) {
            for (Author a: this.register.authors) {
                if (a.equals(author)) {
                    return a;
                }
            }
            this.register.authors.add(author);
            return author;
        }

        public Builder addAuthor(Author author) {
            this.addOrGetAuthor(author);
            return this;
        }

        public Builder addBook(Book book, Author ...authors) {
            book.getAuthors().clear();
            for (Author a: authors) {
                book.getAuthors().add(this.addOrGetAuthor(a));
            }
            this.register.books.add(book);
            return this;
        }

        public BookRegister build() {
            return this.register;
        }
    }

    public Set<Book> findBooks(Predicate<Book> filter) {
        Set<Book> result = new HashSet<>();
        for (Book b : this.books) {
            if (filter.test(b)) {
                result.add(b);
            }
        }
        return result;
    }

    public Set<Book> findBooksByAuthor(Author author) {
        return this.findBooks( b -> b.getAuthors().contains(author) );
    }

    public Map<Author,Integer> countBooksByAuthor() {
        Map<Author,Integer> bookCount = new TreeMap<>();

        for (Book b : this.books) {
            for (Author a: b.getAuthors()) {



            }
        }
        return bookCount;
    }


    public Map<Author,Integer> countBooksByAuthor2() {
        Map<Author,Integer> bookCount = new TreeMap<>();
        for (Book b : this.books) {
            for (Author a: b.getAuthors()) {

                bookCount.put(a,
                        1 + bookCount.getOrDefault(a,0)
                );


                bookCount.merge(a,
                        1,
                        Math::addExact
                );

                bookCount.merge(a,
                        1,
                        (x,y) -> x+y
                );

            }
        }
        return bookCount;
    }


    public Set<Book> getBooks() {
        return books;
    }

    public Set<Author> getAuthors() {
        return authors;
    }
}