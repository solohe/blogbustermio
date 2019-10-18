/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.ausiasmarch.controller;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.ausiasmarch.bean.PostBean;
import net.ausiasmarch.bean.ResponseBean;
import net.ausiasmarch.factory.ConnectionFactory;
import net.ausiasmarch.connection.ConnectionInterface;
import net.ausiasmarch.dao.PostDao;
import net.ausiasmarch.service.PostService;
import net.ausiasmarch.setting.ConnectionSettings;

public class Json extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try (PrintWriter out = response.getWriter()) {
            String ob = request.getParameter("ob");
            String op = request.getParameter("op");           
            try {
                if (ob.equalsIgnoreCase("post")) {
                    PostService oPostService = new PostService(request);
                    if (op.equalsIgnoreCase("get")) {
                        out.print(oPostService.get());
                    }
                    if (op.equalsIgnoreCase("update")) {
                        out.print(oPostService.update());
                    }
                    if (op.equalsIgnoreCase("getall")) {
                        out.print(oPostService.getall());
                    }
                    if (op.equalsIgnoreCase("remove")) {
                        out.print(oPostService.remove());
                    }
                    if (op.equalsIgnoreCase("new")) {
                        out.print(oPostService.insert());
                    }
                    if (op.equalsIgnoreCase("getcount")) {
                        out.print(oPostService.getcount());
                    }
                }
            } catch (SQLException ex) {
                ResponseBean oResponseBean = new ResponseBean(500, ex.getMessage());
                Gson oGson = new Gson();
                out.print(oGson.toJson(oResponseBean));
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
