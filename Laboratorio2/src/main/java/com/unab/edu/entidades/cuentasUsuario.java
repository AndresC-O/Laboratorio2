/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unab.edu.entidades;

import java.util.Date;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @author CruzCalles
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class cuentasUsuario extends usuario{
    protected int idCuentasUsuario;
    public double saldo;
    protected int transaccion;
}
