package com.example.kainBOCK.controller;

import com.example.kainBOCK.bl.BMICalc;
import com.example.kainBOCK.bl.JWT;
import com.example.kainBOCK.bl.SendMail;
import com.example.kainBOCK.db.DB_Access;
import com.example.kainBOCK.pojo.Goal;
import com.example.kainBOCK.pojo.UserAccount;
import com.example.kainBOCK.pojo.bmi;

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
            // load Goals from DB
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
                try {
                    jwtUser = JWT.createJWT(DB_Access.getInstance().getUserIDByEmail(email), email, "login-success", 1000000000);
                } catch (SQLException throwables) {
                    System.out.println(throwables.toString());
                }
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
                //insert User into DB
                DB_Access.getInstance().insertUser(new UserAccount(username, email, password, genderID, goalID, LocalDate.parse(dateOfBirth, DTF)));
                //login the user after registration
                jwtUser = JWT.createJWT(DB_Access.getInstance().getUserIDByEmail(email), email, "login-success", 1000000000);
                request.setAttribute("username", username);
            } catch (SQLException ex) {
                System.out.println(ex.toString());
            }
            // send Confirmationmail
            String usernameMail = "kainbock.pos@gmail.com";
            String passwordMail = "kainbock.pos123";
            String senderAddress ="kainbock.pos@gmail.com";//someone@web.de
            String recipientsAddress = email; //somereceiver@web.de
            String subject = "Bestätigung der Anmeldung";
            String text = "Ihre Anmeldung bei KainBOCK wurde hiermit bestätigt.";
            // we are using gmail for SNMP
            String smtpHost = "smtp.gmail.com";

            new SendMail().sendMail(smtpHost, usernameMail, passwordMail, senderAddress, recipientsAddress, subject, text);

            request.getRequestDispatcher("homepage.jsp").forward(request,response);
        }
        /**
         * forward to BMI page
         */
        else if(request.getParameter("bmiButton") != null) {
            request.getRequestDispatcher("bmi.jsp").forward(request, response);
        }
        /**
         * Calculate BMI and save values to DB
         */
        else if (request.getParameter("calcBMI") != null){
            double weight = Double.parseDouble(request.getParameter("weight"));
            int height = Integer.parseInt(request.getParameter("height"));
            double value = BMICalc.getBMI(height, weight);
            try {
                DB_Access.getInstance().insertBMI(new bmi(LocalDate.now(), value, height, weight,
                        Integer.parseInt(JWT.decodeJWT(jwtUser).getId())));
            } catch (SQLException throwables) {
                System.out.println(throwables.toString());
            }
            request.setAttribute("bmi", value);
            request.setAttribute("weight", weight);
            request.setAttribute("height", height);
            request.getRequestDispatcher("bmiAnzeigen.jsp").forward(request, response);
        }
    }
}
