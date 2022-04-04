package br.com.fiap.service;

import br.com.fiap.dao.impl.CartDAOImpl;

public class CartService {

    private CartDAOImpl cartDAO;

    public CartService(CartDAOImpl cartDAO){
        this.cartDAO = cartDAO;
    }
}
