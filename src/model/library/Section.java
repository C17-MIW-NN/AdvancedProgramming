package model.library;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Section {
    public String title;
    public int numLines;
    public int pageNum;
    public List<Section> subSections;

    public Section() {
        this.subSections = new ArrayList<>();
        this.numLines = 0;
    }
    public Section(String title) {
        this();
        this.title = title;
    }
    public Section(String title, int pageNum) {
        this(title);
        this.pageNum = pageNum;
    }
    public Section(String title, int pageNum, int numLines) {
        this(title, pageNum);
        this.numLines = numLines;
    }

    void printAllTitles(String prefix) {
        System.out.printf("%s%s   %d\n", prefix, this.title, this.pageNum);
        this.subSections
                .forEach(s -> s.printAllTitles("  " + prefix) );
    }

    void printAllNumberedTitles0(String prefix) {
        int subSectionNumber = 0;
        System.out.printf("%s %s   %d\n", prefix, this.title, this.pageNum);
        this.subSections
                .forEach(s -> {
                    //subSectionNumber++;
                    s.printAllNumberedTitles("  " + prefix + "." + subSectionNumber);
                } );
    }

    void printAllNumberedTitles1(String prefix) {
        AtomicInteger subSectionNumber = new AtomicInteger(0);
        System.out.printf("%s %s   %d\n", prefix, this.title, this.pageNum);
        this.subSections
                .forEach(s -> {
                    int num = subSectionNumber.incrementAndGet();
                    s.printAllNumberedTitles("  " + prefix + "." + num);
                } );
    }

    void printAllNumberedTitles(String prefix) {
        System.out.printf("%s %s   %d\n", prefix, this.title, this.pageNum);
        for (int i = 0; i < this.subSections.size(); i++) {
            this.subSections.get(i).printAllNumberedTitles("  " + prefix + "." + (i+1));
        }
    }

    public int getTotalLines() {
        var ref = new Object() {
            int totalLines = 0;
        };
        this.subSections.forEach(s -> { ref.totalLines += s.numLines; });
        return ref.totalLines;
    }
}


