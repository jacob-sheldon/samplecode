package com.codewithjacob;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
 *@title HttpServer
 *@description
 *@author LearnWithJacob
 *@version 1.0
 *@create 3/11/23 12:08 AM
 */
@WebServlet("/hello")
public class HttpServer extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private int cnt = 0;
    @Override
    protected void doGet(HttpServletRequest req,
                             HttpServletResponse resp) throws ServletException, IOException {
        synchronized (this) {
            cnt += 1;
            System.out.println("thread = " + Thread.currentThread() + " cnt = " + cnt);
        }
        resp.setContentType("text/plain");
        resp.getWriter().write("Hello, Java Servlet");
        resp.setStatus(HttpServletResponse.SC_OK);
    }
}
