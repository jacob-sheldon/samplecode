package com.codewithjacob;/*
 *@title $NAME
 *@description
 *@author LearnWithJacob
 *@version 1.0
 *@create $DATE $TIME
 */

import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws LifecycleException {
        System.out.println("Hello Java Servlet!");
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8080);
        tomcat.getConnector();

        Context ctx = tomcat.addContext("", null);

        Tomcat.addServlet(ctx, "hello", new HelloServlet());
        ctx.addServletMappingDecoded("/hello", "hello");

        tomcat.start();
        tomcat.getServer().await();
    }

    public static class HelloServlet extends HttpServlet {
        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            resp.getWriter().println("hello world!");
        }
    }
}