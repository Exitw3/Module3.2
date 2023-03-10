package controller;

import dao.ICustomerDAO;
import dao.IProductDAO;
import dao.IUserDAO;
import dao.impl.CustomerDAO;
import dao.impl.ProductDAO;
import dao.impl.UserDAO;
import model.Product;
import utils.ValidateUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@WebServlet(name = "ProductServlet", urlPatterns = "/product")
public class ProductServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final IUserDAO userDAO = UserDAO.getInstance();
    private final IProductDAO productDAO = ProductDAO.getInstance();
    private final ICustomerDAO customerDAO = CustomerDAO.getInstance();

    public void init() {
        if (this.getServletContext().getAttribute("listProduct") == null) {
            this.getServletContext().setAttribute("listProduct", productDAO.selectAllProduct());
        } else {
            updateListProduct();
        }
        if (this.getServletContext().getAttribute("listUser") == null) {
            this.getServletContext().setAttribute("listUser", userDAO.selectAllUser());
        }

        if (this.getServletContext().getAttribute("listCustomer") == null) {
            this.getServletContext().setAttribute("listCustomer", customerDAO.selectAllCustomer());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                createProduct(request, response);
                break;
            case "edit":
                editProduct(request, response);
                break;
            case "search":
                searchProduct(request, response);
                break;
        }
    }

    private void editProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name, description, image;
        int price;
        int quantity;
        List<String> errors = new ArrayList<>();
        String id = request.getParameter("id");
        Product oldProduct = productDAO.selectProduct(id);
        try {
            name = request.getParameter("productName");
            if (!oldProduct.getName().equals(name)) {
                if (productDAO.checkNameExits(name)) {
                    errors.add("S???n ph???m ????" + " ' " + name + " ' " + "t???n t???i trong h??? th???ng");
                }
            }
            if (name.trim().equals("")) errors.add("T??n s???n ph???m kh??ng ???????c ????? tr???ng");
            price = Integer.parseInt((request.getParameter("price")));
            quantity = Integer.parseInt(request.getParameter("quantity"));
            description = request.getParameter("description");
            image = request.getParameter("image");
            if (!ValidateUtils.isImageValid(image)) errors.add("???????ng d???n ???nh kh??ng ????ng");
            if (errors.isEmpty()) {
                Product newProduct = new Product(id, name, price, quantity, description, image);
                newProduct.setCreatedTime(oldProduct.getCreatedTime());
                newProduct.setUpdatedTime(LocalDateTime.now());
                productDAO.updateProduct(newProduct);
                updateListProduct();
                request.setAttribute("message", "Ch???nh s???a s???n ph???m" + " ' " + name + " ' " + "th??nh c??ng!");
                request.setAttribute("product", newProduct);

            }
        } catch (NumberFormatException numberFormatException) {
            errors.add("?????nh d???ng c???a gi?? ho???c s??? l?????ng kh??ng h???p l???");
        } finally {
            if (request.getAttribute("product") == null)
                request.setAttribute("product", oldProduct);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/dashboard/product/edit.jsp");
            request.setAttribute("errors", errors);
            requestDispatcher.forward(request, response);
        }
    }

    private void searchProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String searchStr = request.getParameter("inputSearch");
        List<Product> productListSearch = productDAO.searchProduct(searchStr);
        request.setAttribute("productListSearch", productListSearch);
        request.setAttribute("searchStr", searchStr);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/dashboard/product/search.jsp");
        requestDispatcher.forward(request, response);
    }

    private void createProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<String> errors = new ArrayList<>();
        try {
            String id = "SP" + System.currentTimeMillis() / 1000;
            String productName = request.getParameter("productName");
            String description = request.getParameter("description");
            int price = Integer.parseInt((request.getParameter("price")));
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            String image = request.getParameter("image");

            Product newProduct = new Product();
            newProduct.setId(id);
            newProduct.setCreatedTime(LocalDateTime.now());
            newProduct.setDescription(description);
            newProduct.setName(productName);
            newProduct.setImage(image);
            if (!ValidateUtils.isImageValid(image)) errors.add("???????ng d???n ???nh kh??ng ????ng");
            newProduct.setPrice(price);
            newProduct.setQuantity(quantity);
            ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
            Validator validator = validatorFactory.getValidator();
            Set<ConstraintViolation<Product>> constraintViolations;
            constraintViolations = validator.validate(newProduct);

            if (!constraintViolations.isEmpty()) {
                for (ConstraintViolation<Product> constraintViolation : constraintViolations) {
                    errors.add(constraintViolation.getMessage());
                }
                request.setAttribute("product", newProduct);
            } else {
                if (productDAO.checkNameExits(productName)) {
                    request.setAttribute("product", newProduct);
                    errors.add("S???n ph???m ???? t???n t???i");
                } else {
                    productDAO.insertProduct(newProduct);
                    updateListProduct();
                    request.setAttribute("message", "Th??m s???n ph???m" + " ' " + productName + " ' " + "th??nh c??ng!");
                }
            }
        } catch (NumberFormatException numberFormatException) {
            errors.add("?????nh d???ng c???a gi?? ho???c s??? l?????ng kh??ng h???p l???");
        } finally {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/dashboard/product/create.jsp");
            request.setAttribute("errors", errors);
            requestDispatcher.forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException  {
//        HttpSession session = request.getSession();
//        if(session.getAttribute("account")==null){
//            response.sendRedirect("/login?type=user");
//            return;
//        }
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        try {
            switch (action) {
                case "create":
                    showCreateForm(request, response);
                    break;
                case "edit":
                    showEditForm(request, response);
                    break;
                case "delete":
                    deleteProduct(request, response);
                    break;
                default:
                    listProduct(request, response);
                    break;
            }
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        RequestDispatcher requestDispatcher;
        Product product = productDAO.selectProduct(id);
        if (product == null) {
            requestDispatcher = request.getRequestDispatcher("/WEB-INF/dashboard/product/list.jsp");
            request.setAttribute("error", "S???n ph???m kh??ng t???n t???i");
            requestDispatcher.forward(request, response);
        } else {
            request.setAttribute("product", product);
            requestDispatcher = request.getRequestDispatcher("/WEB-INF/dashboard/product/edit.jsp");
            requestDispatcher.forward(request, response);
        }
    }

    private void deleteProduct(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        String id = request.getParameter("id");
        Product product = productDAO.selectProduct(id);
        if (product == null) {
            request.setAttribute("error", "S???n ph???m" + " ' " + id + " ' " + "kh??ng t???n t???i");
        } else {
            productDAO.deleteProduct(id);
            request.setAttribute("message", "X??a th??nh c??ng!");
        }
        updateListProduct();
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/dashboard/product/list.jsp");
        requestDispatcher.forward(request, response);
    }

    private void showCreateForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/dashboard/product/create.jsp");
        dispatcher.forward(request, response);
    }

    private void listProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/dashboard/product/list.jsp");
        dispatcher.forward(request, response);
    }

    private void updateListProduct() {
        this.getServletContext().removeAttribute("listProduct");
        this.getServletContext().setAttribute("listProduct", productDAO.selectAllProduct());
    }
}