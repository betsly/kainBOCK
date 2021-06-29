package com.example.kainBOCK.controller;

import com.example.kainBOCK.bl.BMICalc;
import com.example.kainBOCK.bl.JWT;
import com.example.kainBOCK.bl.SendMail;
import com.example.kainBOCK.db.DB_Access;
import com.example.kainBOCK.pojo.Goal;
import com.example.kainBOCK.pojo.TimeStamp;
import com.example.kainBOCK.pojo.UserAccount;
import com.example.kainBOCK.pojo.bmi;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "Controller", value = "/Controller")
public class Controller extends HttpServlet {

    private static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private List<TimeStamp> event = new ArrayList<>();
    private List<Goal> goals = new ArrayList<>();
    private String jwtUser = "";

    /**
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
        if (request.getParameter("confirmLogin") != null) {
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
                    request.getSession().setAttribute("username", DB_Access.getInstance().getUsername(Integer.parseInt(JWT.decodeJWT(jwtUser).getId())));
                    request.getRequestDispatcher("homepage.jsp").forward(request, response);
                } catch (SQLException throwables) {
                    System.out.println(throwables.toString());
                }
            }
            request.getRequestDispatcher("WelcomePage.jsp").forward(request, response);
        }
        /**
         * Go back to homepage
         */
        else if (request.getParameter("btHome") != null) {
            request.getRequestDispatcher("homepage.jsp").forward(request, response);
        }
        /**
         * Forward to Videos
         */
        else if (request.getParameter("videoButton") != null) {
            try {
                // get List of videos from DB
                request.getSession().setAttribute("videos", DB_Access.getInstance().getVideos(DB_Access.getInstance().getGoalIdForUser(Integer.parseInt(JWT.decodeJWT(jwtUser).getId()))));
            } catch (SQLException throwables) {
                System.out.println(throwables.toString());
            }
            request.getRequestDispatcher("videos.jsp").forward(request, response);
        }
        /**
         * Delete Timestamp
         */
        else if (request.getParameter("btDelete") != null) {
            int id = Integer.parseInt(request.getParameter("btDelete"));
            try {
                // Delete TimeStamp in DB
                DB_Access.getInstance().deleteTimeStamp(id);
                // Get new events List
                request.setAttribute("events", DB_Access.getInstance().getTimeStampsForUser(Integer.parseInt(JWT.decodeJWT(jwtUser).getId())));
            } catch (SQLException throwables) {
                System.out.println(throwables.toString());
            }
            request.getRequestDispatcher("timeline.jsp").forward(request, response);
        }
        /**
         * Registration
         */
        else if (request.getParameter("confirmRegistration") != null) {
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
                request.getSession().setAttribute("username", username);
            } catch (SQLException ex) {
                System.out.println(ex.toString());
            }
            // send Confirmationmail
            String usernameMail = "kainbock.pos@gmail.com";
            String passwordMail = "kainbock.pos123";
            String senderAddress = "kainbock.pos@gmail.com";//someone@web.de
            String recipientsAddress = email; //somereceiver@web.de
            String subject = "Bestätigung der Anmeldung";
            String text = "Ihre Anmeldung bei KainBOCK wurde hiermit bestätigt.";
            // we are using gmail for SNMP
            String smtpHost = "smtp.gmail.com";

            new SendMail().sendMail(smtpHost, usernameMail, passwordMail, senderAddress, recipientsAddress, subject, text);

            request.getRequestDispatcher("homepage.jsp").forward(request, response);
        }
        /**
         * forward to BMI page
         */
        else if (request.getParameter("bmiButton") != null) {
            request.getRequestDispatcher("bmi.jsp").forward(request, response);
        }
        /**
         * Calculate BMI and save values to DB
         */
        else if (request.getParameter("calcBMI") != null) {
            double weight = Double.parseDouble(request.getParameter("weight"));
            int height = Integer.parseInt(request.getParameter("height"));
            double value = BMICalc.getBMI(height, weight);
            int age = -1;
            System.out.println(weight + " -- " + height + " -- " + value);
            try {
                DB_Access.getInstance().insertBMI(new bmi(LocalDateTime.now(), value, height, weight,
                        Integer.parseInt(JWT.decodeJWT(jwtUser).getId())));
                DB_Access.getInstance().createTimeStamp(Integer.parseInt(JWT.decodeJWT(jwtUser).getId()), String.format("Your BMI is %.2f", value), LocalDateTime.now());
                age = DB_Access.getInstance().getAgeOfUser(JWT.decodeJWT(jwtUser).getIssuer());
            } catch (SQLException throwables) {
                System.out.println(throwables.toString());
            }
            request.getSession().setAttribute("ageOfUser", age);
            System.out.println("Age Of User: " + age);
            request.setAttribute("bmiValue", Math.round(value * 100.0) / 100.0);
            request.getRequestDispatcher("bmiAnzeigen.jsp").forward(request, response);
        }
        /**
         * Logout
         */
        else if (request.getParameter("logout") != null) {
            request.getRequestDispatcher("WelcomePage.jsp").forward(request, response);
        } else if (request.getParameter("timeline") != null) {
            try {
                request.setAttribute("events", DB_Access.getInstance().getTimeStampsForUser(Integer.parseInt(JWT.decodeJWT(jwtUser).getId())));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            request.getRequestDispatcher("timeline.jsp").forward(request, response);
        }
        /**
         * add Timestamp to Timeline
         */
        else if (request.getParameter("addTimeline") != null) {
            String description = request.getParameter("description");
            try {
                DB_Access.getInstance().createTimeStamp(Integer.parseInt(JWT.decodeJWT(jwtUser).getId()), description, LocalDateTime.now());
                request.setAttribute("events", DB_Access.getInstance().getTimeStampsForUser(Integer.parseInt(JWT.decodeJWT(jwtUser).getId())));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            request.getRequestDispatcher("timeline.jsp").forward(request, response);
        }
        /**
         * forward to the editProfile jsp
         */
        else if (request.getParameter("changeProfile") != null) {
            request.getRequestDispatcher("editProfile.jsp").forward(request, response);
        }
        /**
         * check if password is correct
         * ---- if it is correct change password to new one
         * ---- else return error and go back to editProfile JSP
         */
        else if (request.getParameter("changePassword") != null) {
            try {
                String password = request.getParameter("pswLogin");
                String email = JWT.decodeJWT(jwtUser).getIssuer();
                int pwCompare = -1;
                // get password for input mail address
                pwCompare = DB_Access.getInstance().getPasswordByEmail(email);
                // compare password of DB with the input
                if (pwCompare != -1 && password.hashCode() == pwCompare) {
                    DB_Access.getInstance().changePassword(Integer.parseInt(JWT.decodeJWT(jwtUser).getId()),
                            request.getParameter("newPassword"));
                    request.getRequestDispatcher("homepage.jsp").forward(request, response);
                }
                // wrong input
                else {
                    request.setAttribute("errorChangePW", "Das eingegebene Passwort ist falsch ...");
                    request.getRequestDispatcher("editProfile.jsp");
                }
            } catch (SQLException throwables) {
                System.out.println(throwables.toString());
                request.setAttribute("errorChangePW", "Fehler in der Datenbank: " + throwables.toString());
                request.getRequestDispatcher("editProfile.jsp");
            }
        }
        /**
         * change Goal in DB
         */
        else if (request.getParameter("changeGoal") != null){
            try {
                // changeGoal(userID, goalID)
                DB_Access.getInstance().changeGoal(Integer.parseInt(JWT.decodeJWT(jwtUser).getId()),
                        Integer.parseInt(request.getParameter("goal")));
            } catch (SQLException throwables) {
                System.out.println(throwables.toString());
            }
            request.getRequestDispatcher("homepage.jsp").forward(request, response);
        }
        /**
         * forward to show profile
         */
        else if(request.getParameter("showProfile") != null){
            try {
                request.setAttribute("user", DB_Access.getInstance().getUserInformation(Integer.parseInt(JWT.decodeJWT(jwtUser).getId())));
                request.getRequestDispatcher("yourProfile.jsp").forward(request, response);
            } catch (SQLException throwables) {
                System.out.println(throwables);
                request.getRequestDispatcher("homepage.jsp").forward(request, response);
            }
        }
    }
}
