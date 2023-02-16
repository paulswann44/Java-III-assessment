package com.codeup.controllers;

import com.codeup.dao.DaoFactory;
import com.codeup.models.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ProductQuantityServlet", urlPatterns = "/products/quantity")
public class ProductQuantityServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long productId = Long.parseLong(request.getParameter("product_id"));
        int change = Integer.parseInt(request.getParameter("change"));

        Product product = DaoFactory.getProductsDao().findById(productId);
        if (product == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        int newQuantity = product.getQuantity() + change;
        if (newQuantity < 0) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Cannot have negative quantity.");
            return;
        }

        product.setQuantity(newQuantity);
        DaoFactory.getProductsDao().update(product);

        response.sendRedirect("/products");
    }
}
