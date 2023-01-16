package com.example.customermanager.controller;

import com.example.customermanager.model.Category;
import com.example.customermanager.model.Product;
import com.example.customermanager.service.ICategoryService;
import com.example.customermanager.service.IProductService;
import com.example.customermanager.service.jdbc.CategoryServiceJDBC;
import com.example.customermanager.service.jdbc.ProductServiceJDBC;
import com.example.customermanager.utils.ValidateUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = {"/customers", ""})
public class ProductServlet extends HttpServlet {

    private IProductService iProductService;
    private ICategoryService iCategoryService;

    @Override
    public void init() throws ServletException {
        iProductService = new ProductServiceJDBC();
        iCategoryService = new CategoryServiceJDBC();
        List<Category> countryList = iCategoryService.getAllCategory();
        if (getServletContext().getAttribute("Categorys") == null) {
            getServletContext().setAttribute("Categorys", countryList);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }

        switch (action) {
            case "create":
                showFormCreateProduct(req, resp);
                break;
            case "edit":
                showEditProduct(req, resp);
                break;
            case "remove":
                showDeleteProduct(req, resp);
                break;
            default:
                showListProduct(req, resp);
                break;
        }
    }

    private void showListProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String kw = "";
        if (req.getParameter("kw") != null) {
            kw = req.getParameter("kw");
        }
        List<Product> productsSearch = iProductService.getAllCustomersByKw(kw);
        req.setAttribute("kw", kw);
        req.setAttribute("products", productsSearch);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/admin/customer/customers.jsp");
        requestDispatcher.forward(req, resp);
    }

    private void showDeleteProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = Long.parseLong(req.getParameter("id"));
        Product customer = iProductService.findCustomerById(id);
        req.setAttribute("customer", customer);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/admin/customer/delete.jsp");
        requestDispatcher.forward(req, resp);
    }

    private void showEditProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = Long.parseLong(req.getParameter("id"));
        Product product = iProductService.findCustomerById(id);
        req.setAttribute("product", product);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/admin/customer/edit.jsp");
        requestDispatcher.forward(req, resp);
    }

    private void showFormCreateProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/admin/customer/create.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher;
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }

        switch (action) {
            case "create":
                insertProduct(req, resp);
                break;
            case "edit": {
                editProduct(req, resp);
                break;
            }
            case "remove": {
                removeProduct(req, resp);
                break;
            }

        }
    }

    private void removeProduct(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        long id = Long.parseLong(req.getParameter("id"));
        iProductService.deleteCustomer(id);
        List<Product> products = iProductService.getAllCustomers();
        req.setAttribute("message", "Remove Success");
        req.setAttribute("products", products);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/admin/customer/customers.jsp");
        requestDispatcher.forward(req, resp);
    }

    private void updateListProduct() {
        this.getServletContext().removeAttribute("products");
        this.getServletContext().setAttribute("products", iProductService.getAllCustomers());
    }


    private void editProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<String> errors = new ArrayList<>();
        Product product = new Product();
        long id = Long.parseLong(req.getParameter("id"));
        product.setId(id);
        String name = req.getParameter("name");
        product.setName(name);
        String price = (req.getParameter("price"));
        product.setPrice(price);
        String amount = (req.getParameter("amount"));
        product.setAmount(amount);
        String color = req.getParameter("color");
        product.setColor(color);
        String description = req.getParameter("description");
        product.setDescription(description);
        int idCategory = Integer.parseInt(req.getParameter("idCategory"));
        product.setiDcategory(idCategory);

        RequestDispatcher requestDispatcher = null;
        if (errors.isEmpty()) {
            iProductService.updateCustomer(product);
            req.setAttribute("message", "Edit Success");
            List<Product> products = iProductService.getAllCustomers();
            req.setAttribute("products", products);
            requestDispatcher = req.getRequestDispatcher("/WEB-INF/admin/customer/customers.jsp");
            requestDispatcher.forward(req, resp);
        } else {
            req.setAttribute("errors", errors);
            req.setAttribute("customer", product);
            requestDispatcher = req.getRequestDispatcher("/WEB-INF/admin/customer/edit.jsp");
            requestDispatcher.forward(req, resp);

        }
    }

    private void insertProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<String> errors = new ArrayList<>();
        Product product = new Product();
        validateFullNameView(errors, req, product);
        validatePrice(errors, req, product);
        validateAmount(errors, req, product);
        String color = req.getParameter("color");
        product.setColor(color);
        String description = req.getParameter("description");
        product.setDescription(description);
        int idCategory = Integer.parseInt(req.getParameter("idCategory"));
        product.setiDcategory(idCategory);

        if (errors.isEmpty()) {
            product.setId(iProductService.findCustomerByIdMax() + 1);
            iProductService.addCustomer(product);
            req.setAttribute("message", "Add product Success");
        } else {
            req.setAttribute("errors", errors);
            req.setAttribute("product", product);
        }
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/admin/customer/create.jsp");
        requestDispatcher.forward(req, resp);
    }

    private void validateFullNameView(List<String> errors, HttpServletRequest req, Product product) {
        String name = req.getParameter("name");
        product.setName(name);
        if (name.equals("")) {
            errors.add("Fullname is not empty");
        } else {
            if (ValidateUtils.isFullNameValid(name) == false) {
                errors.add("Fullname not valid. Start with Upcase, least 4 character");
            }
        }
    }



    private void validatePrice(List<String> errors, HttpServletRequest req, Product product) {
        String price = req.getParameter("price");
        product.setPrice(price);
        if (ValidateUtils.isNumber(price) == false) {
            errors.add("Price: Input only number");
        }
    }

    private void validateAmount(List<String> errors, HttpServletRequest req, Product product) {
        String amount = req.getParameter("amount");
        product.setAmount(amount);
        if (ValidateUtils.isNumber(amount) == false) {
            errors.add("Amount: Input only number");
        }
    }
}
