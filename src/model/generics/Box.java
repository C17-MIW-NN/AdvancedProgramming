package model.generics;

/**
 * @author Vincent Velthuizen
 * Use generics
 */
public class Box<T> {
    private T content;

    public void setContent(T content) {
        this.content = content;
        Box.print(content);
    }

    public T getContent() {
        return content;
    }

    public static <T> void print(T someContent) {
        System.out.println(someContent);
    }
}
