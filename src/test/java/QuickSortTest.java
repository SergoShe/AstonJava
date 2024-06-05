import com.github.SergoShe.Book;
import com.github.SergoShe.MyArrayList;
import com.github.SergoShe.People;
import com.github.SergoShe.QuickSort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

public class QuickSortTest {

    private MyArrayList<Book> bookList;
    private MyArrayList<People> peopleList;

    @BeforeEach
    void setUp() {
        bookList = new MyArrayList<>();
        bookList.add(new Book("book",1998));
        bookList.add(new Book("aaaaa",1953));
        bookList.add(new Book("dfh",1936));
        bookList.add(new Book("dfhd",2013));
        bookList.add(new Book("title",1847));
        bookList.add(new Book("name",3058));
        bookList.add(new Book("book1",2014));

        peopleList = new MyArrayList<>();
        peopleList.add(new People("Sergo", 27));
        peopleList.add(new People("Andrew", 26));
        peopleList.add(new People("Elena", 35));
        peopleList.add(new People("Yarik", 32));
        peopleList.add(new People("Tanya", 30));
    }

    @Test()
    public void testQuickSortBookByYear(){
        MyArrayList<Book> expectedList = new MyArrayList<>();
        expectedList.add(new Book("title",1847));
        expectedList.add(new Book("dfh",1936));
        expectedList.add(new Book("aaaaa",1953));
        expectedList.add(new Book("book",1998));
        expectedList.add(new Book("dfhd",2013));
        expectedList.add(new Book("book1",2014));
        expectedList.add(new Book("name",3058));

        Comparator<Book> comparator = Comparator.comparing(Book::getYear);
        QuickSort.quickSort(bookList,comparator);

        assertEquals(expectedList,bookList);
    }

    @Test
    public void testQuickSortPeopleByAge(){
        MyArrayList<People> expectedList = new MyArrayList<>();
        expectedList.add(new People("Andrew", 26));
        expectedList.add(new People("Sergo", 27));
        expectedList.add(new People("Tanya", 30));
        expectedList.add(new People("Yarik", 32));
        expectedList.add(new People("Elena", 35));

        QuickSort.quickSort(peopleList);

        assertEquals(expectedList,peopleList);
    }

    @Test
    void testNullBookList() {
        // Given
        MyArrayList<Book> nullList = null;

        // When / Then
        assertThrows(NullPointerException.class, () -> QuickSort.quickSort(nullList, Comparator.comparing(Book::getYear)));
    }
}
