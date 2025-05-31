package com.onlinebookstore.controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.sql.*;

public class ConfirmPurchase extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                 System.out.println("Servlet was called!");
        String username = request.getParameter("username");
        String address = request.getParameter("address");
        String payment = request.getParameter("payment");
        String bookTitle = request.getParameter("book_title");
        int bookId = Integer.parseInt(request.getParameter("book_id"));
        System.out.println("Username received: " + username);
       

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/Books", "root", "pvpsit61");

            String sql = "INSERT INTO purchases (username, address, book_title, book_id) VALUES (?, ?, ?, ?)";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, username);
            pst.setString(2, address);
            pst.setString(3, bookTitle);
            pst.setInt(4, bookId);
            pst.executeUpdate();
            conn.close();

            response.sendRedirect("thankyou.jsp");

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error: " + e.getMessage());
        }
    }
}
