package filters;

import config.Constants;
import org.junit.After;
import org.junit.Test;
import play.Application;
import play.Environment;
import play.inject.guice.GuiceApplicationBuilder;
import play.mvc.Http;
import play.mvc.Result;
import play.test.WithApplication;
import services.MunroCSVFile;
import utils.TestAppender;

import java.io.File;
import java.util.concurrent.CompletableFuture;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static play.mvc.Results.ok;
import static play.test.Helpers.GET;

public class MunroFilterTest extends WithApplication {

    @Override
    protected Application provideApplication() {
        return new GuiceApplicationBuilder().build();
    }

    @Test
    public void testFilterPassesRequestThroughIfCSVFileLoadedSuccessfully()
            throws Exception {
        Environment env = app.injector().instanceOf(Environment.class);

        MunroCSVFile munroCSVFile = new MunroCSVFile(env);

        MunroFilter filter = new MunroFilter(mat, munroCSVFile);

        Http.RequestBuilder request = new Http.RequestBuilder()
                .method(GET)
                .header(Http.HeaderNames.HOST, "localhost:19001")
                .uri("/");

        Result result = filter.apply(req -> {
            return CompletableFuture.completedFuture(ok());
        }, request.build()).toCompletableFuture().get();

        assertEquals(200, result.status());
        assertTrue(TestAppender.events.isEmpty());
    }

    @Test
    public void testFilterReturns500IfCSVFileFailedToLoad()
            throws Exception {
        Environment env = mock(Environment.class);

        when(env.getFile(any(String.class))).thenReturn(new File(""));

        MunroCSVFile munroCSVFile = new MunroCSVFile(env);

        MunroFilter filter = new MunroFilter(mat, munroCSVFile);

        Http.RequestBuilder request = new Http.RequestBuilder()
                .method(GET)
                .header(Http.HeaderNames.HOST, "localhost:19001")
                .uri("/");

        Result result = filter.apply(req -> {
            return CompletableFuture.completedFuture(ok());
        }, request.build()).toCompletableFuture().get();

        assertEquals(500, result.status());
        assertFalse(TestAppender.events.isEmpty());
        assertEquals(2, TestAppender.events.size());
        assertEquals(TestAppender.events.get(1).getMessage(), Constants.ErrorMessages.MUNRO_DATA_NOT_LOADED);;
    }

    @Test
    public void testFilterReturns500IfCSVFileFailedToParse()
            throws Exception {
        Environment env = mock(Environment.class);

        when(env.getFile(any(String.class))).thenReturn(new File(getClass().getResource("/invalid.csv").getPath()));

        MunroCSVFile munroCSVFile = new MunroCSVFile(env);

        MunroFilter filter = new MunroFilter(mat, munroCSVFile);

        Http.RequestBuilder request = new Http.RequestBuilder()
                .method(GET)
                .header(Http.HeaderNames.HOST, "localhost:19001")
                .uri("/");

        Result result = filter.apply(req -> {
            return CompletableFuture.completedFuture(ok());
        }, request.build()).toCompletableFuture().get();

        assertEquals(500, result.status());
        assertFalse(TestAppender.events.isEmpty());
        assertEquals(2, TestAppender.events.size());
        assertEquals(TestAppender.events.get(1).getMessage(), Constants.ErrorMessages.MUNRO_DATA_NOT_LOADED);
    }

    @After
    public void clearEvents() {
        TestAppender.events.clear();
    }
}
