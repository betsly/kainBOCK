package com.example.kainBOCK.controller;

import com.example.kainBOCK.bl.JWT;
import com.example.kainBOCK.db.DB_Access;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@WebServlet(name = "Controller", value = "/Controller")
public class Controller extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getParameter("login") != null) {
            request.getRequestDispatcher("Login").forward(request, response);
        }
        else if(request.getParameter("confirmLogin") != null){
            
        }
    }
}
