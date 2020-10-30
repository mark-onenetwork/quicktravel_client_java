package au.com.sealink.quicktravel.client.retrofit;

import org.junit.Assert;
import org.junit.Test;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;

public class BearerTokenInterceptorTest {
    @Test
    public void testNullToken() throws Exception {
        MockWebServer mockWebServer = new MockWebServer();
        mockWebServer.enqueue(new MockResponse().setBody("hello, world"));
        mockWebServer.start();

        TokenInterceptor interceptor = new TokenInterceptor();
        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        RequestBody body = RequestBody.create(MediaType.parse("application/json"), "{}");

        Request request = new Request.Builder().url(mockWebServer.url("/")).post(body).build();
        okHttpClient.newCall(request).execute();

        RecordedRequest recordedRequest = mockWebServer.takeRequest();
        Assert.assertEquals(null, recordedRequest.getHeader("Authorization"));

        mockWebServer.shutdown();
    }

    @Test
    public void testWithToken() throws Exception {
        MockWebServer mockWebServer = new MockWebServer();
        mockWebServer.enqueue(new MockResponse().setBody("hello, world"));
        mockWebServer.start();

        BearerTokenInterceptor interceptor = new BearerTokenInterceptor();
        interceptor.setToken("ABC-123");
        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        RequestBody body = RequestBody.create(MediaType.parse("application/json"), "{}");
        Request request = new Request.Builder().url(mockWebServer.url("/")).post(body).build();
        okHttpClient.newCall(request).execute();

        RecordedRequest recordedRequest = mockWebServer.takeRequest();
        Assert.assertEquals("Bearer ABC-123", recordedRequest.getHeader("Authorization"));

        mockWebServer.shutdown();
    }
}