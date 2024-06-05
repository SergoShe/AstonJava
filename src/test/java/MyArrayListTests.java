import com.github.SergoShe.Book;
import com.github.SergoShe.MyArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

public class MyArrayListTests {
    private MyArrayList<Book> list;

    @BeforeEach
    public void setUp() {
        list = new MyArrayList<>();
    }

    @Test
    public void testCreateEmptyList() {
        int size = list.size();
        assertEquals(0, size);
    }

    @Test
    public void testAddAtIndex() {
        Book book = new Book("Title", 2000);

        list.add(book);

        assertEquals(1, list.size());
        assertEquals(book, list.get(0));
    }

    @Test
    public void testToString(){
        list = new MyArrayList<>(1);
        Book book = new Book("Title",2000);

        list.add(book);
        String text = list.toString();

        assertEquals("MyArrayList{elementData=[Book{title='Title', year=2000}]}",text);

    }

    @Test
    public void testAddSomeElemAtIndex() {
        Book book1 = new Book("Title1", 2000);
        Book book2 = new Book("Title2", 2001);
        Book book3 = new Book("Title3", 2002);
        list.add(book1);
        list.add(book2);

        list.add(book3, 1);

        assertEquals(3, list.size());
        assertEquals(book1, list.get(0));
        assertEquals(book3, list.get(1));
        assertEquals(book2, list.get(2));
    }

    @Test
    public void testAddAll() {
        Book[] newBooks = {new Book("Title1", 2020),
                new Book("Title2", 2021),
                new Book("Title3", 2022)};

        list.addAll(newBooks);

        assertEquals(3, list.size());
        assertEquals(newBooks[0], list.get(0));
        assertEquals(newBooks[1], list.get(1));
        assertEquals(newBooks[2], list.get(2));
    }

    @Test
    public void testSet() {
        Book book1 = new Book("Title1", 2000);
        Book book2 = new Book("Title2", 2001);
        list.add(book1);

        list.set(book2, 0);

        assertEquals(1, list.size());
        assertEquals(book2, list.get(0));
    }

    @Test
    public void testGet() {
        Book book1 = new Book("Title1", 2000);
        Book book2 = new Book("Title2", 2001);
        list.add(book1);
        list.add(book2);

        Book bookElem = list.get(1);

        assertEquals(book2, bookElem);
    }

    @Test
    public void testRemove() {
        Book book1 = new Book("Title1", 2000);
        Book book2 = new Book("Title2", 2001);
        list.add(book1);
        list.add(book2);

        Book removedElem = list.remove(0);

        assertEquals(book1, removedElem);
        assertEquals(1, list.size());
        assertEquals(book2, list.get(0));
    }

    @Test
    public void testClear() {
        Book book1 = new Book("Title1", 2000);
        Book book2 = new Book("Title2", 2001);
        list.add(book1);
        list.add(book2);

        list.clear();

        assertEquals(0, list.size());
    }

    @Test
    public void testGetThrownOfIndexOutOfBoundException() {
        Book book = new Book("Title1", 2000);
        list.add(book);

        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(1));
    }

    @Test
    public void testGetThrownOfIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new MyArrayList<>(-1));
    }

    @Test
    public void testCreateListWithDefaultCapacity() {
        MyArrayList<Book> zeroCapacityList = new MyArrayList<>(0);
        assertEquals(0, zeroCapacityList.size());
    }

    @Test
    public void testCreateListWithCustomCapacity() {
        MyArrayList<Book> customCapacityList = new MyArrayList<>(5);
        assertEquals(0, customCapacityList.size());
    }

    @Test
    public void testCreateListWithCustomArrays() {
        Book[] newBooks = {new Book("Title1", 2020),
                new Book("Title2", 2021),
                new Book("Title3", 2022)};

        MyArrayList<Book> arrayList = new MyArrayList<>(newBooks);

        assertEquals(3, arrayList.size());
        assertEquals(newBooks[0], arrayList.get(0));
        assertEquals(newBooks[1], arrayList.get(1));
        assertEquals(newBooks[2], arrayList.get(2));
    }

    @Test
    public void testAddElemWhenFullCapacity() {
        IntStream.range(0, 10).forEach(i -> list.add(new Book("Title" + i, 2000 + i)));

        Book newBook = new Book("Title10", 2010);
        list.add(newBook);

        assertEquals(11, list.size());
        assertEquals(newBook, list.get(10));
    }

    @Test
    public void testAddElemWhenIndexMoreThanSizeList() {

        Book newBook = new Book("Title10", 2010);

        assertThrows(IndexOutOfBoundsException.class, () -> list.add(newBook, 1));
    }

    @Test
    public void testAddAllWithNullElem() {
        assertThrows(NullPointerException.class, () -> list.addAll(null));
    }

    @Test
    public void testAddAllAtIndexAndGetThrownException() {
        Book[] newBooks = {new Book("Title1", 2020),
                new Book("Title2", 2021),
                new Book("Title3", 2022)};

        assertThrows(IndexOutOfBoundsException.class, () -> list.addAll(newBooks, 2));
    }

    @Test
    public void testAddEmptyArraysWithAddAll() {
        Book[] emptyBooks = {};

        boolean result = list.addAll(emptyBooks);

        assertFalse(result);
        assertEquals(0, list.size());
    }

    @Test
    public void testRemoveForEmptyList() {
        assertThrows(NoSuchElementException.class, () -> list.remove(0));
    }

}
