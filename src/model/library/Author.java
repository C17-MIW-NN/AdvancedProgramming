package model.library;

import java.util.Comparator;
import java.util.Date;

public class Author implements Comparable<Author> {
    private String lastName;
    private String initials;
    private Date dateOfBirth;

    public String firstName;

    public Author(String lastName, String initials) {
        this.lastName = lastName;
        this.initials = initials;
        this.dateOfBirth = new Date(1980,1,1);
    }

    @Override
    public String toString() {
        return this.initials + " " + this.lastName;
    }

    @Override
    public boolean equals(Object o) {
        return this.lastName.equals(((Author)o).lastName) &&
                this.initials.equals(((Author)o).initials) &&
                this.dateOfBirth.equals(((Author)o).dateOfBirth);
    }
    @Override
    public int hashCode() {
        return this.lastName.hashCode() + this.initials.hashCode() + this.dateOfBirth.hashCode();
    }
    @Override
    public int compareTo(Author o) {
        int result = this.lastName.compareTo(o.lastName);
        if (result == 0) result = this.initials.compareTo(o.initials);
        if (result == 0) result = this.dateOfBirth.compareTo(o.dateOfBirth);
        return result;
    }

    public int compareByInitials(Author o) {
        int result = this.initials.compareTo(o.initials);
        if (result == 0) result = this.lastName.compareTo(o.lastName);
        if (result == 0) result = this.dateOfBirth.compareTo(o.dateOfBirth);
        return result;
    }

    public static int compareByInitials2(Author a1, Author a2) {
        int result = a1.initials.compareTo(a2.initials);
        if (result == 0) result = a1.lastName.compareTo(a2.lastName);
        if (result == 0) result = a1.dateOfBirth.compareTo(a2.dateOfBirth);
        return result;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getInitials() {
        return initials;
    }

    public void setInitials(String initials) {
        this.initials = initials;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public static class ComparatorByInitials implements Comparator<Author> {
        public int compare(Author a1, Author a2) {
            int result = a1.initials.compareTo(a2.initials);
            if (result == 0) result = a1.lastName.compareTo(a2.lastName);
            if (result == 0) result = a1.dateOfBirth.compareTo(a2.dateOfBirth);
            return result;
        }
    }
}
