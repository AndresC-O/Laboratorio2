/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unab.edu.DAO;

import com.unab.edu.Conexion.ConexionBd;
import com.unab.edu.entidades.cuentasUsuario;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLXML;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author patty
 */
public class ClsCuentaUsuario {
    
    ConexionBd conexion = new ConexionBd();
    Connection con = conexion.RetornarConexion();
    
    public ArrayList<cuentasUsuario>MostrarCuentas(cuentasUsuario cuentas) {
    ArrayList<cuentasUsuario> List = new ArrayList<>();
    
    try {
            CallableStatement st = con.prepareCall("call SP_S_CUENTASUSUARIO (?)");
            st.setInt("PIdUsuario", cuentas.getIdUsuario());
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                cuentasUsuario es = new cuentasUsuario();
                es.setSaldo(rs.getInt("saldo"));
                es.setTransaccion(rs.getInt("transaccion"));
                es.setFecha(rs.getDate("fecha"));

                List.add(es);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error" + e);
        }
        return List;
    }
    
    public void AgregarCuentasUsuario(cuentasUsuario cuentas){
        
        try {
            
            CallableStatement consulta = con.prepareCall("call SP_I_CUENTASUSUARIO(?,?,?,?)");
            consulta.setDouble("PSaldo", cuentas.getSaldo());
            consulta.setInt("PidUsuario", cuentas.getIdUsuario());
            consulta.setInt("PTransaccion", cuentas.getTransaccion());
            consulta.setDate("PFecha", new java.sql.Date(cuentas.getFecha().getTime()));
            consulta.executeQuery();
            JOptionPane.showMessageDialog(null, "¡Inserción Exitosa!");
            con.close();
                    
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
}
