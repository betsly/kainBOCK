package com.example.kainBOCK.controller;

import com.example.kainBOCK.bl.JWT;
import com.example.kainBOCK.bl.SendMail;
import com.example.kainBOCK.db.DB_Access;
import com.example.kainBOCK.pojo.Goal;
import com.example.kainBOCK.pojo.UserAccount;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "Controller", value = "/Controller")
public class Controller extends HttpServlet {

    private static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private List<Goal> goals = new ArrayList<>();
    private String jwtUser = "";

    /**
     *
     * @param config
     * @throws ServletException
     */
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        try {
            // Ziele aus Datenbank laden
            goals.addAll(DB_Access.getInstance().getAllGoals());
        } catch (SQLException throwables) {
            System.out.println(throwables);
        }
        config.getServletContext().setAttribute("goals", goals);
    }

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("WelcomePage.jsp").forward(request, response);
    }

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /**
         * Login
         */
        if(request.getParameter("confirmLogin") != null) {
            String password = request.getParameter("pswLogin");
            String email = request.getParameter("emailLogin");
            int pwCompare = -1;
            try {
                // get password for input mail address
                pwCompare = DB_Access.getInstance().getPasswordByEmail(email);
            } catch (SQLException ex) {
                System.out.println(ex.toString());
            }
            // compare password of DB with the input
            if (pwCompare != -1 && password.hashCode() == pwCompare) {
                // create JWT for current user
                jwtUser = JWT.createJWT(email, email, "login-success", 1000000000);
                request.setAttribute("user", jwtUser);
            }
            request.getRequestDispatcher("homepage.jsp").forward(request,response);
        }
        /**
         * Registration
         */
        else if(request.getParameter("confirmRegistration") != null) {
            String username = request.getParameter("name");
            String password = request.getParameter("psw");
            String email = request.getParameter("email");
            String dateOfBirth = request.getParameter("age");
            int genderID = request.getParameter("gender").equals("male") ? 1 : 2;
            int goalID = Integer.parseInt(request.getParameter("goal"));
            try {
                DB_Access.getInstance().insertUser(new UserAccount(username, email, password, genderID, goalID, LocalDate.parse(dateOfBirth, DTF)));
            } catch (SQLException ex) {
                System.out.println(ex.toString());
            }
            // send Confirmationmail
            String usernameMail = "";
            String passwordMail = "";
            String senderAddress ="";//someone@web.de
            String recipientsAddress = ""; //somereceiver@web.de
            String subject = "Bestätigung der Anmeldung";
            String text = "Ihre Anmeldung bei KainBOCK wurde hiermit bestätigt";
            String smtpHost = "smtp.web.de";

            //new SendMail().sendMail(smtpHost, usernameMail, passwordMail, senderAddress, recipientsAddress, subject, text);

            request.getRequestDispatcher("WelcomePage.jsp").forward(request,response);
        }
        /**
         * forward to BMI page
         */
        else if(request.getParameter("BMI") != null) {
            request.getRequestDispatcher("bmi.jsp").forward(request, response);
        }
        /**
         * Calculate BMI and save values to DB
         */
        else if (request.getParameter("calcBMI") != null){

        }
    }
}
