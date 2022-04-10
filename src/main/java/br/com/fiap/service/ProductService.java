package br.com.fiap.service;

import br.com.fiap.dao.impl.ProductDAOImpl;
import br.com.fiap.dto.ProductDTO;
import br.com.fiap.entity.Product;
import br.com.fiap.exception.CommitException;
import br.com.fiap.exception.IdNotFoundException;

import java.util.List;

public class ProductService {

    private ProductDAOImpl productDAO;

    public ProductService(ProductDAOImpl productDAO) {
        this.productDAO = productDAO;
    }

    public List<Product> findAll() {
        return productDAO.findAll();
    }

    public Product findById(Integer id) throws IdNotFoundException {
        return productDAO.findById(id);
    }

    public void save(ProductDTO productDTO) throws CommitException {
        Product product = ProductDTO.toEntity(productDTO);
        productDAO.save(product);
    }

    public void update(ProductDTO productDTO) throws CommitException {
        Product product = ProductDTO.toEntity(productDTO);
        productDAO.update(product);
    }

    public void delete(ProductDTO productDTO) throws CommitException {
        Product product = ProductDTO.toEntity(productDTO);
        productDAO.delete(product);
    }
}
