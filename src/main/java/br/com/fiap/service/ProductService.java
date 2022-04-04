package br.com.fiap.service;

import br.com.fiap.dao.impl.ProductDAOImpl;

public class ProductService {

    private ProductDAOImpl productDAO;

    public ProductService(ProductDAOImpl productDAO){
        this.productDAO = productDAO;
    }

}
