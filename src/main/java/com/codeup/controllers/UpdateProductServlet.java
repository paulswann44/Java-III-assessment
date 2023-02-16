package com.codeup.controllers;

import com.codeup.dao.DaoFactory;
import com.codeup.models.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UpdateProductServlet", urlPatterns = {"/products/increment", "/products/decrement"})
public class UpdateProductServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long productId = Long.parseLong(request.getParameter("product_id"));

        Product product = DaoFactory.getProductsDao().findById(productId);

        String action = request.getServletPath().substring(request.getServletPath().lastIndexOf("/") + 1);
        if (action.equals("increment")) {
            product.setQuantity(product.getQuantity() + 1);
        } else if (action.equals("decrement")) {
            product.setQuantity(product.getQuantity() - 1);
        }

        DaoFactory.getProductsDao().update(product);

        response.sendRedirect("/products");
    }
}
