package utils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.*;
import java.util.ArrayList;

public class Listener implements ServletContextListener,
        HttpSessionListener, HttpSessionAttributeListener {

    // Public constructor is required by servlet spec
    public Listener() {

    }

    private static ArrayList<String> sessions = new ArrayList<>();
    public static final String COUNTER = "session-counter";

    // -------------------------------------------------------
    // ServletContextListener implementation
    // -------------------------------------------------------
    public void contextInitialized(ServletContextEvent sce) {
      /* This method is called when the servlet context is
         initialized(when the Web application is deployed). 
         You can initialize servlet context related data here.
      */
    }

    public void contextDestroyed(ServletContextEvent sce) {
      /* This method is invoked when the Servlet Context 
         (the Web application) is undeployed or 
         Application Server shuts down.
      */
    }

    // -------------------------------------------------------
    // HttpSessionListener implementation
    // -------------------------------------------------------
    public void sessionCreated(HttpSessionEvent se) {
        /* Session is created. */
        HttpSession session = se.getSession();
        sessions.add(session.getId());
        session.setAttribute(Listener.COUNTER, this);
    }

    public void sessionDestroyed(HttpSessionEvent se) {
        /* Session is destroyed. */
        System.out.println("SessionCounter.sessionDestroyed");
        HttpSession session = se.getSession();
        sessions.remove(session.getId());
        session.setAttribute(Listener.COUNTER, this);
    }

    public int getCurrentNbUserSessions() {
        return sessions.size();
    }

    // -------------------------------------------------------
    // HttpSessionAttributeListener implementation
    // -------------------------------------------------------

    public void attributeAdded(HttpSessionBindingEvent sbe) {
      /* This method is called when an attribute 
         is added to a session.
      */
    }

    public void attributeRemoved(HttpSessionBindingEvent sbe) {
      /* This method is called when an attribute
         is removed from a session.
      */
    }

    public void attributeReplaced(HttpSessionBindingEvent sbe) {
      /* This method is invoked when an attibute
         is replaced in a session.
      */
    }
}
