package ekam.example.api.tests;

import ekam.example.api.server.BookStoreServer;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.IOException;

public class BaseTests {

    @BeforeSuite(alwaysRun = true)
    public void GRPCSetUp() throws IOException, InterruptedException {
        BookStoreServer grpcServer = new BookStoreServer();
        grpcServer.start();
    }

    @AfterSuite(alwaysRun = true)
    public void GRPCTearDown() throws InterruptedException {
        BookStoreServer grpcServer = new BookStoreServer();
        grpcServer.stop();
    }
}
