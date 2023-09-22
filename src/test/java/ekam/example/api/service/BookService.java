package ekam.example.api.service;

import com.endpoints.examples.bookstore.BookAuthorRequest;
import com.endpoints.examples.bookstore.BookResponse;
import com.endpoints.examples.bookstore.BookServiceGrpc;
import com.endpoints.examples.bookstore.GetBookRequest;
import ekam.example.api.server.BookStoreServer;
import io.grpc.stub.StreamObserver;

import java.util.logging.Logger;

public class BookService {
    private static final Logger logger = Logger.getLogger(BookStoreServer.class.getName());

    static public class BookStoreServiceImpl extends BookServiceGrpc.BookServiceImplBase {

        @Override
        public void getBook(GetBookRequest request, StreamObserver<BookResponse> responseObserver) {

            BookResponse.Builder response = BookResponse.newBuilder();

            int isbn = request.getIsbn();

            if (isbn == 1) {
                response.setResponseCode("200").setMessage("Success").build();
            } else {
                response.setResponseCode("200").setMessage("Failed").build();
            }

            responseObserver.onNext(response.build());

            responseObserver.onCompleted();
        }

        @Override
        public void getBooksViaAuthor(BookAuthorRequest request, StreamObserver<BookResponse> responseObserver) {
            BookResponse.Builder response = BookResponse.newBuilder();

            String author = request.getAuthor();

            System.out.println("Author Request " + author);
            logger.info("Author name " + author);

            if (author.equals("Bob")) {
                response.setResponseCode("200").setMessage("Success").build();
            } else
                response.setResponseCode("200").setMessage("Failed").build();

            responseObserver.onNext(response.build());

            responseObserver.onCompleted();
        }
    }
}
