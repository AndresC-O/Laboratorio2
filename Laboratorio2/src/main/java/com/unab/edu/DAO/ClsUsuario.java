package com.unab.edu.DAO;

import com.unab.edu.Conexion.ConexionBd;
import com.unab.edu.entidades.usuario;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ClsUsuario {

    ConexionBd conexion = new ConexionBd();
    Connection con = conexion.RetornarConexion();

    public boolean LoguinUsuario(String Usuario, String Password, int Combo) {

        ArrayList<usuario> ListadoUsuario = new ArrayList<>();
        ArrayList<usuario> ListaPass = new ArrayList<>();

        try {
            CallableStatement Statement = con.prepareCall("call SP_S_LOGIN(?,?,?)");
            Statement.setString("PUsuario", Usuario);
            Statement.setString("PPass", Password);
            Statement.setInt("PTipoUsuario", Combo);
            ResultSet rs = Statement.executeQuery();
            while (rs.next()) {
                usuario user = new usuario();
                user.setUsuario(rs.getString("Usuario"));
                user.setPass(rs.getString("PassWord"));
                user.setIdTipoUsuario(rs.getInt("tipoUsuario"));
                ListadoUsuario.add(user);
            }
            String usuarioBD = null;
            String passwordBD = null;
            for (var iterar : ListadoUsuario) {

                usuarioBD = iterar.getUsuario();
                passwordBD = iterar.getPass();
            }

            CallableStatement Statement2 = con.prepareCall("call SP_S_CRIPUSUARIO(?)");
            Statement2.setString("PPass", Password);
            ResultSet rs2 = Statement2.executeQuery();
            while (rs2.next()) {
                usuario user = new usuario();
                user.setPass(rs2.getNString("crip"));
                ListaPass.add(user);
            }
            String passcrip = null;
            for (var iterar : ListaPass) {

                passcrip = iterar.getPass();
                Password = passcrip;
            }
            if (usuarioBD != null && passwordBD != null) {
                if (usuarioBD.equals(Usuario) && passwordBD.equals(Password)) {
                    return true;
                }
            }

            con.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Hubo un error en: " + e);
        }
        return false;
    }

    public ArrayList<usuario> ListadoUSUARIOS() {
        ArrayList<usuario> Lista = new ArrayList<>();

        try {

            CallableStatement consulta = con.prepareCall("call SP_S_SOLOUSUARIOS()");
            ResultSet rs = consulta.executeQuery();
            while (rs.next()) {
                usuario user = new usuario();
                user.setIdUsuario(rs.getInt("idUsuario"));
                user.setUsuario(rs.getString("Usuario"));
                user.setPass(rs.getString("PassWord"));
                user.setIdTipoUsuario(rs.getInt("tipoUsuario"));
                Lista.add(user);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error en: \n\n\n\n" + e);
        }

        return Lista;
    }

    public int ObtenerID(String Usuario, String Password, int TipoUsuario) {

        int ID = 0;

        try {
            CallableStatement Statement = con.prepareCall("call SP_S_LOGIN(?,?,?)");
            Statement.setString("PUsuario", Usuario);
            Statement.setString("PPass", Password);
            Statement.setInt("PTipoUsuario", TipoUsuario);
            ResultSet rs = Statement.executeQuery();
            while (rs.next()) {

                ID = rs.getInt("idUsuario");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error en: \n\n\n\n" + e);
        }

        return ID;
    }
}
