package com.example.loginext;

import com.example.loginext.dtos.BookingRequest;
import com.example.loginext.dtos.BookingResponse;
import com.example.loginext.dtos.DriverDetailsResponse;
import com.example.loginext.dtos.TripCompletionRequest;
import com.example.loginext.entity.driver.AvailabilityStatus;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

@SpringBootTest(classes = LogiNextApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@RunWith(SpringJUnit4ClassRunner.class)
@EnableAutoConfiguration
class LogiNextApplicationTests extends TestHelper {

    public static final String HTTP_LOCALHOST_8080_BOOK_BOOKCAB = "http://localhost:8080/book/bookcab/";
    public static final String HTTP_LOCALHOST_8080_BOOK_COMPLETETRIP = "http://localhost:8080/book/completetrip";

    RestTemplate restTemplate = new RestTemplate();

    @Test
    @Order(1)
    @Sql(scripts = {"/sql/db-insert.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    // @Sql(scripts = "/scripts/entity_test_clear.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void testBookingSuccess() throws URISyntaxException {
        URI uri = new URI(HTTP_LOCALHOST_8080_BOOK_BOOKCAB);
        invokeAndAssert(uri, new BookingRequest("Charvak", 27.2046, 77.4977), "STEPHEN");
        invokeAndAssert(uri, new BookingRequest("Chintan", 19.2808, 72.854), "PAWAN");
    }

    @Test
    @Order(2)
    public void fetchDriverDetails() throws Exception {
        final String uri = "http://localhost:8080/book/fetchdriverdetails?page=0&size=10";
        DriverDetailsResponse result = restTemplate.getForObject(uri, DriverDetailsResponse.class);
        Assert.assertEquals(5, result.getTotalElements());
        Assert.assertEquals(2, result.getDriverDetails().stream().filter(t -> t.getAvailabilityStatus() == AvailabilityStatus.BUSY).count());
    }

    @Test
    @Order(3)
    public void testMarkCompleteSuccess() throws URISyntaxException {
        URI uri = new URI(HTTP_LOCALHOST_8080_BOOK_COMPLETETRIP);
        invokeAndAssertForComplete(uri, new TripCompletionRequest("STEPHEN"), "Trip Completed");
    }

    @Test()
    @Order(4)
    public void testMarkCompleteFailure() throws URISyntaxException {
        URI uri = new URI(HTTP_LOCALHOST_8080_BOOK_COMPLETETRIP);
        Assertions.assertThrows(RuntimeException.class, () -> invokeAndAssertForFailure(uri, new TripCompletionRequest("RAKESH"), "Trip Completed"));
    }

    @Test
    @Order(5)
    @Sql(scripts = {"/sql/db-insert.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    public void testBookingSuccessDuplicate() throws URISyntaxException, InterruptedException, ExecutionException {
        BookingRequest charvak = new BookingRequest("Charvak", 27.2046, 77.4977);
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        java.util.List<Future<BookingResponse>> futures = executorService.invokeAll(Arrays.asList(new MultiRunner(charvak), new MultiRunner(charvak)));
        long stephen = futures.stream().filter(t ->  getDriverId(t)).count();
        Assert.assertEquals(stephen, 1);
    }

}

@Data
@NoArgsConstructor
class MultiRunner implements Callable<BookingResponse> {

    private BookingRequest bookingRequest;

    public MultiRunner(BookingRequest bookingRequest) {
        this.bookingRequest = bookingRequest;
    }

    @Override
    public BookingResponse call() throws Exception {
        ResponseEntity<BookingResponse> result = new RestTemplate().postForEntity("http://localhost:8080/book/bookcab/", new HttpEntity<>(bookingRequest), BookingResponse.class);
        return result.getBody();
    }
}
