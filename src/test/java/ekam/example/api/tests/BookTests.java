package ekam.example.api.tests;

import com.endpoints.examples.bookstore.BookResponse;
import ekam.example.api.client.BookClient;
import ekam.example.api.exception.AuthorNotFoundException;
import ekam.example.api.exception.BookNotFoundException;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BookTests extends BaseTests {
    private BookClient bookClient;

    public BookTests() {
        bookClient = new BookClient("http://localhost:50055");
    }

    @Test
    public void getBookTests() throws BookNotFoundException {
        BookResponse bookResponse = bookClient.getBookByISBN(1);
        Assert.assertEquals(bookResponse.getResponseCode(), "200");
    }

    @Test
    public void getBookViaAuthor() throws AuthorNotFoundException {
        BookResponse bookResponse = bookClient.getBookByAuthor("Bob");
        Assert.assertEquals(bookResponse.getResponseCode(), "200");
    }
}
