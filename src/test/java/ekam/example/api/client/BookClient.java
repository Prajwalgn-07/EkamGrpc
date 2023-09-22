package ekam.example.api.client;

import com.testvagrant.ekam.api.retrofit.GrpcClient;
import ekam.example.api.exception.AuthorNotFoundException;
import ekam.example.api.exception.BookNotFoundException;
import io.grpc.ManagedChannel;

import java.net.URISyntaxException;

public class BookClient extends GrpcClient {
    ManagedChannel channel;

    com.endpoints.examples.bookstore.BookServiceGrpc.BookServiceBlockingStub bookServiceStub;

    public BookClient(String host) {
        try {
            channel = build(host);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e.getMessage());
        }
        bookServiceStub = com.endpoints.examples.bookstore.BookServiceGrpc.newBlockingStub(channel);
    }

    public com.endpoints.examples.bookstore.BookResponse getBookByISBN(Integer ISBN) throws BookNotFoundException {
        try {
            com.endpoints.examples.bookstore.GetBookRequest getBookRequest = com.endpoints.examples.bookstore.GetBookRequest.newBuilder().setIsbn(ISBN).build();
            return bookServiceStub.getBook(getBookRequest);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            throw new BookNotFoundException("Book not found");
        }
    }

    public com.endpoints.examples.bookstore.BookResponse getBookByAuthor(String authorName) throws AuthorNotFoundException {
        try {
            com.endpoints.examples.bookstore.BookAuthorRequest getBookRequest = com.endpoints.examples.bookstore.BookAuthorRequest.newBuilder().setAuthor(authorName).build();
            return bookServiceStub.getBooksViaAuthor(getBookRequest);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            throw new AuthorNotFoundException("Author details not found ");
        }
    }

}
