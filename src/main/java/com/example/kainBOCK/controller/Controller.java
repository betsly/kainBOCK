package com.example.kainBOCK.controller;

import com.example.kainBOCK.bl.JWT;
import com.example.kainBOCK.db.DB_Access;
import com.example.kainBOCK.pojo.UserAccount;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Date;
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
        if(request.getParameter("registration") != null) {
            request.getRequestDispatcher("confirm").forward(request, response);
        }
        else if(request.getParameter("confirmRegistration") != null){
            String username = request.getParameter("username");
            String passwort = request.getParameter("password");
            String email = request.getParameter("email");
            String dateOfBirth = request.getParameter("dateOfBirth");
            String genderID = request.getParameter("genderID");
            String goal = request.getParameter("goal");
            try {
                DB_Access.getInstance().insertUser(new UserAccount(username, email, passwort, Integer.parseInt(genderID),goal, Date.valueOf(dateOfBirth)));
            } catch (SQLException ex) {
                System.out.println(ex.toString());
            }
        }
    }
}
