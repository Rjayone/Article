package by.bsuir.Article.controller;

import by.bsuir.Article.SessionBeanLocal;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class Controller extends HttpServlet {
    @EJB
    private SessionBeanLocal sessionBean = null;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String command = request.getParameter("command");
        if(command.equals("add")) {
            String author = request.getParameter("author");
            String text   = request.getParameter("text");
            sessionBean.addArticle(author, text);
            
            request.getRequestDispatcher("index.html").forward(request, response);
        }
        
        if(command.equals("delete")) {
            String id = request.getParameter("articleId");
            sessionBean.removeArticle(id);
        }
        
        if(command.equals("edit")) {
            String author = request.getParameter("author");
            String text   = request.getParameter("text");
            String id = request.getParameter("articleId");
            sessionBean.editArticle(id, author, text);
        }
    }
}
