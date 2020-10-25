/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unab.edu.DAO;

import com.unab.edu.Conexion.ConexionBd;
import java.sql.Connection;

/**
 *
 * @author CruzCalles
 */
public class ClsAdministrador {
    
    ConexionBd conexion = new ConexionBd();
    Connection con = conexion.RetornarConexion();
    
    
}
