package com.codeup.controllers;

import com.codeup.dao.DaoFactory;
import com.codeup.models.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/products/create")
public class CreateProductServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/create.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String priceStr = req.getParameter("price");
        String quantityStr = req.getParameter("quantity");

        // Validate input values
        if (name == null || name.trim().isEmpty()) {
            req.setAttribute("error", "Name is required");
            req.getRequestDispatcher("/WEB-INF/create.jsp").forward(req, resp);
            return;
        }

        if (priceStr == null || priceStr.trim().isEmpty()) {
            req.setAttribute("error", "Price is required");
            req.getRequestDispatcher("/WEB-INF/create.jsp").forward(req, resp);
            return;
        }

        if (quantityStr == null || quantityStr.trim().isEmpty()) {
            req.setAttribute("error", "Quantity is required");
            req.getRequestDispatcher("/WEB-INF/create.jsp").forward(req, resp);
            return;
        }

        double price;
        int quantity;

        try {
            price = Double.parseDouble(priceStr);
        } catch (NumberFormatException e) {
            req.setAttribute("error", "Invalid price value");
            req.getRequestDispatcher("/WEB-INF/create.jsp").forward(req, resp);
            return;
        }

        try {
            quantity = Integer.parseInt(quantityStr);
        } catch (NumberFormatException e) {
            req.setAttribute("error", "Invalid quantity value");
            req.getRequestDispatcher("/WEB-INF/create.jsp").forward(req, resp);
            return;
        }

        Product product = new Product(name, price, quantity);
        DaoFactory.getProductsDao().create(product);

        resp.sendRedirect("/products");
    }
}
