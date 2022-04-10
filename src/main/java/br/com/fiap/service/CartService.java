package br.com.fiap.service;

import br.com.fiap.dao.impl.CartDAOImpl;
import br.com.fiap.dto.CartDTO;
import br.com.fiap.entity.Cart;
import br.com.fiap.exception.CommitException;
import br.com.fiap.exception.IdNotFoundException;

import java.util.List;

public class CartService {

    private CartDAOImpl cartDAO;

    public CartService(CartDAOImpl cartDAO){
        this.cartDAO = cartDAO;
    }

    public List<Cart> findAll() {
        return cartDAO.findAll();
    }

    public Cart findById(Integer id) throws IdNotFoundException {
        return cartDAO.findById(id);
    }

    public void save(CartDTO cartDTO) throws CommitException {
        Cart cart = CartDTO.toEntity(cartDTO);
        cartDAO.save(cart);
    }

    public void update(CartDTO cartDTO) throws CommitException {
        Cart cart = CartDTO.toEntity(cartDTO);
        cartDAO.update(cart);
    }

    public void delete(CartDTO cartDTO) throws CommitException {
        Cart cart= CartDTO.toEntity(cartDTO);
        cartDAO.delete(cart);
    }
}
