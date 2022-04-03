package br.com.fiap.singleton;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerFactorySingleton {
    private static EntityManagerFactory instance;

    private EntityManagerFactorySingleton(){}

    public static EntityManagerFactory getInstance(){
        return instance == null ? Persistence.createEntityManagerFactory("ORCL") : instance;
    }
}
