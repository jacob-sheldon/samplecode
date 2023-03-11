package com.codewithjacob;

import org.junit.jupiter.api.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.mockito.Mockito.*;

/*
 *@title TestHttpServer
 *@description
 *@author LearnWithJacob
 *@version 1.0
 *@create 3/11/23 10:07 AM
 */
public class TestHttpServer {
    @Test
    public void testDoGET() throws Exception {
        int numberOfThread = 10;
        Thread[] threads = new Thread[numberOfThread];
        HttpServer httpServer = new HttpServer();
        for (int i = 0; i < numberOfThread; i++) {
            threads[i] = new Thread(() -> {
                HttpServletRequest mockReq = mock(HttpServletRequest.class);
                HttpServletResponse mockResp = mock(HttpServletResponse.class);
                when(mockReq.getMethod()).thenReturn("GET");
                try {
                    when(mockResp.getWriter()).thenReturn(new PrintWriter(new StringWriter()));
                    httpServer.service(mockReq, mockResp);

                    verify(mockResp).setStatus(HttpServletResponse.SC_OK);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
            threads[i].start();
        }
        for (Thread thread: threads) {
            thread.join();
        }
    }
}
