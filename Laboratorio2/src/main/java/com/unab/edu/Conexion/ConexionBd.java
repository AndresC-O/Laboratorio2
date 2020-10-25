/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unab.edu.Conexion;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author patty
 */
public class ConexionBd {
        
    private Connection coonec;
    
    public ConexionBd()
    {
    try{
        coonec = DriverManager.getConnection("jdbc:mysql://localhost/appbanco","root","root");
        System.out.println("CONECTADO A LA BD");
        
    }catch (Exception e)
    {
        System.out.println("ERROR DE CONEXION A LA BD "+ e);
    }
    }
    public Connection RetornarConexion(){   
        return coonec;   
    }
    
}
