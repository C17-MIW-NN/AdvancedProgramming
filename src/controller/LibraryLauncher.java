package controller;

import model.library.Author;
import model.library.Book;
import model.library.BookRegister;

import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Vincent Velthuizen
 * Purpose for the class
 */
public class LibraryLauncher {
    public static void main(String[] args) {

        Optional<Book> optionalBook;

        BookRegister bookRegister =
                new BookRegister.Builder()
                        .addAuthor(new Author("Knuth", "D.E."))
                        .addAuthor(new Author("Dijkstra", "E.W."))
                        .addBook(new Book(13215871,"A discipline of programming"),
                                new Author("Dijkstra", "E.W."))
                        .addBook(new Book(9780201633610L,"Design Patterns"),
                                new Author("Gamma", "E."), new Author("Helm", "R."),
                                new Author("Johnson", "R."), new Author("Vlissides", "J."))
                        .addBook(new Book(9780198519737L,"The Emperor's New Mind"),
                                new Author("Penrose", "R."))
                        .addBook(new Book(13999999,"Selected writings on computing; a personal perspective"),
                                new Author("Dijkstra", "E.W."))
                        .addBook(new Book(9780299999999L,"Consciousness and the Universe: Quantum Physics, Evolution, Brain & Mind"),
                                new Author("Penrose", "R."), new Author("Stapp", "H."),
                                new Author("Page", "D."), new Author("Nauenberg", "M."))
                        .addBook(new Book(9780201633610L,"Design Patterns"),
                                new Author("Gamma", "E."), new Author("Helm", "R."),
                                new Author("Johnson", "R."), new Author("Vlissides", "J."))
                        .addBook(new Book(9789039522219L,"Relationele Databases en SQL"),
                                new Author("Wiegerink", "L."), new Author("Bijpost", "J."),
                                new Author("Groot", "M. de"))
                        .addAuthor(new Author("Hoare", "C.A.R."))
                        .build();



        Set<Book> travelBooks =
                bookRegister.findBooks(Book::travel2000plus);
        Set<Book> ewdBooks =
                bookRegister.findBooks(Book::authoredByEWD);
        Set<Book> booksBefore2000 =
                bookRegister.findBooks(
                        b -> b.yearOfIssue < 2000
                );

        Set<Book> booksBefore2000a =
                bookRegister.findBooks(Book::issuedBefore2000);

        Set<Book> booksWithSpecialAuthor =
                bookRegister.findBooks(
                        b -> {
                            for (Author a: b.getAuthors()) {
                                if (b.yearOfIssue - a.getDateOfBirth().getYear() < 20) {
                                    return true;
                                }
                            }
                            return false;
                        }
                );

        Set<Book> ewdTravelBooks =
                bookRegister.findBooks(
                        ((Predicate<Book>)(Book::authoredByEWD))
                                .and(Book::travel2000plus) );
        Set<Book> otherBooks =
                bookRegister.findBooks(
                        ((Predicate<Book>)(Book::travel2000plus))
                                .negate() );

        Set<Book> travelBooksBefore2000 =
                bookRegister.getBooks().stream()
                        .filter(b -> b.yearOfIssue < 2000 &&
                                b.getTitle().toLowerCase().contains("travel") )
                        .collect(Collectors.toSet());


        Book tenm = Book.theEmperorsNewMind();
        tenm.printTOC();
        System.out.println();
        tenm.printNumberedTOC();

        Book book = Book.theEmperorsNewMind();

        book.getAuthors()
                .sort(new Author.ComparatorByInitials());

        book.getAuthors()
                .sort(Author::compareByInitials);

        book.getAuthors()
                .sort(Author::compareByInitials2);

        book.getAuthors()
                .sort( (a1, a2) -> {
                    int result = a1.getInitials().compareTo(a2.getInitials());
                    if (result == 0) result = a1.getLastName().compareTo(a2.getLastName());
                    if (result == 0) result = a1.getDateOfBirth().compareTo(a2.getDateOfBirth());
                    return result;
                });

        System.out.println("Number of books per author:\n" +
                bookRegister.getBooks().stream()
                        .flatMap( b -> b.getAuthors().stream() )
                        .collect( Collectors.groupingBy(
                                a -> a,
                                Collectors.counting() ) )
        );

        System.out.println("Number of books per author:\n" +
                bookRegister.getBooks().stream()
                        .flatMap( b -> b.getAuthors().stream() )
                        .collect( Collectors.toMap(
                                a -> a,
                                a -> 1,
                                Math::addExact ) )
        );

        System.out.println("Authors that wrote a book before 2000:\n" +
                bookRegister.getBooks().stream()
                        .filter(b -> b.yearOfIssue < 2000)
                        .flatMap( b -> b.getAuthors().stream() )
                        .collect( Collectors.toSet() )
        );

        Stream<Book> recentBooks =
                bookRegister.getBooks().stream()
                        .filter(b -> b.yearOfIssue < 2000);

        System.out.printf("Found %d pages in %d recent books\n",
                recentBooks.mapToInt(Book::getNumPages).sum(),
                0 // recentBooks.count()
        );

        bookRegister.getBooks().stream()
                .forEach(b -> System.out.println(b.getSortedAuthors(Author::compareTo)));
        bookRegister.getBooks().stream()
                .forEach(b -> System.out.println(b.getSortedAuthors(Author::compareByInitials)));
        bookRegister.getBooks().stream()
                .forEach(b -> System.out.println(b.getSortedAuthors(Author::compareByInitials2)));
        bookRegister.getBooks().stream()
                .forEach(b -> System.out.println(b.getSortedAuthors(
                        (a1, a2) -> {
                            int result = a1.getInitials().compareTo(a2.getInitials());
                            if (result == 0) result = a1.compareTo(a2);
                            return result;
                        })));

        System.out.println(
                bookRegister.getBooks().stream()
                        .filter(b -> b.getAuthors().size() >= 3)
                        .map(Book::getTitle)
                        .collect(Collectors.joining(
                                ",\n", "*** Book titles: ***\n", "\n*** - ***"))
        );

        System.out.println("Average number of authors: " +
                bookRegister.getBooks().stream()
                        .collect(Collectors.averagingDouble(
                                b -> b.getAuthors().size())
                        )
        );

        System.out.println("Sum of all pages in our books: " +
                bookRegister.getBooks().stream()
                        .mapToInt(Book::getNumPages)
                        .sum()
        );

        System.out.println("Max pages of any book: " +
                bookRegister.getBooks().stream()
                        .mapToInt(Book::getNumPages)
                        .max()
                        .orElse(0)
        );

        System.out.println("Do all books have an author ? " +
                bookRegister.getBooks().stream()
                        .allMatch(b -> b.getAuthors().size() > 0)
        );

        System.out.println("Book with the longest title is:\n" +
                bookRegister.getBooks().stream()
                        .reduce( (b1,b2) ->
                                (b1.getTitle().length() > b2.getTitle().length() ? b1 : b2))
                        .get()
        );
    }



    static Function<String,Double> convertSID(
            Function<String,Integer> convertSI,
            Function<Integer,Double> convertID) {
        return convertSI.andThen(convertID);
    }

    static Predicate<Double> andPredD(
            Predicate<Double> pred1,
            Predicate<Double> pred2) {
        return pred1.and(pred2);
    }

    static String buildHTML() {

        String htmlPage =
                new StringBuilder()
                        .append("<div>\n")
                        .append("<h1>This is the title</h1>\n")
                        .append("This chapter is about the stringbuilder\n")
                        .append("It also includes a secret number:")
                        .append("<strong>").append(42).append("</strong>")
                        .append("</div>")
                        .toString();

        return htmlPage + "\n";
    }

}
