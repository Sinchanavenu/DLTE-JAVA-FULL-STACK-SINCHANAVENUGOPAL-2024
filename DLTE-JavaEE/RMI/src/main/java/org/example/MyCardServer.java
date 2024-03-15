package org.example;

import basic.service.Entity.UserDetails;
import basic.service.middleware.DatabaseTarget;
import basic.service.services.UserDetailsServices;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.stream.Collectors;

public class MyCardServer extends UnicastRemoteObject implements MyCardFunctions {

    private static Context context;
    private Registry registry;

    private UserDetailsServices services;

    @Override
    public List<String> fetchPhone() throws RemoteException {
        List<UserDetails> userDetails = services.callFindAll().stream().filter(each->each.getUsername()>= ).collect(Collectors.toList());
        List<String> returned=new ArrayList<>();
        for(UserDetails userDetails1:userDetails){
            returned.add(userDetails1.getuserName());
        }
        return returned;
    }

    public MyCardServer() throws RemoteException {
        super();
        services=new UserDetailsServices(new DatabaseTarget());
        try {
            registry= LocateRegistry.createRegistry(3030);
            Hashtable properties=new Hashtable();
            properties.put(Context.INITIAL_CONTEXT_FACTORY,"com.sun.jndi.rmi.registry.RegistryContextFactory");
            properties.put(Context.PROVIDER_URL,"rmi://localhost:3030");
            context=new InitialContext(properties);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws NamingException, RemoteException {
        MyCardServer myCardServer=new MyCardServer();
        context.bind("java:/card-filter",myCardServer);
    }
    }
