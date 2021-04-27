package com.example.kainBOCK.controller;

import com.example.kainBOCK.bl.JWT;
import com.example.kainBOCK.db.DB_Access;
import com.example.kainBOCK.pojo.UserAccount;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@WebServlet(name = "Controller", value = "/Controller")
public class Controller extends HttpServlet {

    private static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private String jwtUser = "";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getParameter("confirmLogin") != null) {
            String password = request.getParameter("pswLogin");
            String email = request.getParameter("emailLogin");
            int pwCompare = -1;
            try {
                pwCompare = DB_Access.getInstance().getPasswordByEmail(email);
            } catch (SQLException ex) {
                System.out.println(ex.toString());
            }
            if (pwCompare != -1 && password.hashCode() == pwCompare) {
                jwtUser = JWT.createJWT(email, email, "login-success", 1000000000);
                request.setAttribute("user", jwtUser);
            }
        }
        else if(request.getParameter("confirmRegistration") != null){
            String username = request.getParameter("name");
            String password = request.getParameter("psw");
            String email = request.getParameter("email");
            String dateOfBirth = request.getParameter("age");
            int genderID = request.getParameter("gender") == "male" ? 1 : 2;
            String goal = request.getParameter("goal");
            try {
                DB_Access.getInstance().insertUser(new UserAccount(username, email, password, genderID,goal, LocalDate.parse(dateOfBirth, DTF)));
            } catch (SQLException ex) {
                System.out.println(ex.toString());
            }
        }
    }
}
