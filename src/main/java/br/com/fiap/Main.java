package br.com.fiap;

import br.com.fiap.exception.CommitException;
import br.com.fiap.singleton.EntityManagerFactorySingleton;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class Main {
    public static void main(String[] args) throws CommitException {
        EntityManagerFactory entityManagerFactory = EntityManagerFactorySingleton.getInstance();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
    }
}
