package com.unab.edu.DAO;

import com.unab.edu.Conexion.ConexionBd;
import com.unab.edu.entidades.tipoUsuario;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ClsTipoUsuario {
    
    ConexionBd conexion = new ConexionBd();
    Connection con = conexion.RetornarConexion();
    
    public ArrayList<tipoUsuario> MostrarTiposUsuario() {
        ArrayList<tipoUsuario> tipoUsuarios = new ArrayList<>();

        try {
            CallableStatement Statement = con.prepareCall("call SP_S_TIPOUSUARIO()");
            ResultSet resultadoConsulta = Statement.executeQuery();
            while (resultadoConsulta.next()) {
                tipoUsuario usuario = new tipoUsuario();
                usuario.setIdTipoUsuario(resultadoConsulta.getInt("Id"));
                usuario.setTipoUser(resultadoConsulta.getString("TipoUser"));
                
                tipoUsuarios.add(usuario);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return tipoUsuarios;
    }
}
