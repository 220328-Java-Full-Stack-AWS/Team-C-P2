package com.revature.TeamCP2.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class ConnectionManager {

    private static ConnectionManager connectionManager;
    private SessionFactory sf;
    private Session session;
    private List<Class> annotatedEntities;


    private ConnectionManager(){
        annotatedEntities = new LinkedList<>();
    }

    public static ConnectionManager getConnection(){
        if(connectionManager == null){
            connectionManager = new ConnectionManager();
        }
        return connectionManager;
    }

    public void initializeDatasource()  {
        Configuration config = new Configuration();

        for(Class c : annotatedEntities){
            config.addAnnotatedClass(c);
        }

        sf = config.buildSessionFactory();
        session = sf.openSession();
        System.out.println("Database connection initialized");
    }

    public ConnectionManager addAnnotatedClass(Class c){
        annotatedEntities.add(c);
        return this;
    }

    public SessionFactory getSf() {
        return sf;
    }

    public Session getSession() {
        return session;
    }
}
