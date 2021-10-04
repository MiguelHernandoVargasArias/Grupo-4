/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import persistencia.ConexionBD;

/**
 *
 * @author Usuairo
 */
public class Cliente {

    private int cedula;
    private String nombre;
    private String tipoVehiculo;
    private String placaVehiculo;
    private String colorVehiculo;
    private String plaza;

    public Cliente() {
    }

    public Cliente(int cedula) {
        this.cedula = cedula;
    }

    public int getCedula() {
        return cedula;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipoVehiculo() {
        return tipoVehiculo;
    }

    public void setTipoVehiculo(String tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
    }

    public String getPlacaVehiculo() {
        return placaVehiculo;
    }

    public void setPlacaVehiculo(String placaVehiculo) {
        this.placaVehiculo = placaVehiculo;
    }

    public String getColorVehiculo() {
        return colorVehiculo;
    }

    public void setColorVehiculo(String colorVehiculo) {
        this.colorVehiculo = colorVehiculo;
    }

    public String getPlaza() {
        return plaza;
    }

    public void setPlaza(String plaza) {
        this.plaza = plaza;
    }

    @Override
    public String toString() {
        return "Cliente{" + "cedula=" + cedula
                + ", nombre=" + nombre + ", tipoVehiculo=" + tipoVehiculo
                + ", placaVehiculo=" + placaVehiculo + ", colorVehiculo="
                + colorVehiculo + ", plaza=" + plaza + '}';
    }

    public Cliente getCliente() {
        //creando la lista que va retornar el método
        ConexionBD conexion = new ConexionBD();
        String sql = "select * from cliente where cedula=" + this.cedula + "";
        try {
            ResultSet rs = conexion.consultarBD(sql);
            if (rs.next()) {//recorrer el conjunto de resultados
                this.cedula = (rs.getInt("cedula"));
                this.nombre = (rs.getString("nombre"));
                this.tipoVehiculo = (rs.getString("tipoVehiculo"));
                this.placaVehiculo = (rs.getString("placaVehiculo"));
                this.colorVehiculo = (rs.getString("colorVehiculo"));
                this.plaza = (rs.getString("plaza"));

            }
        } catch (SQLException ex) {
            System.out.println("Error" + ex.getMessage());
        } finally {
            conexion.cerrarConexion();
        }
        return this;//retornando la lista
    }

    public List<Cliente> listarCliente() {
        //creando la lista que va retornar el método
        List<Cliente> listaCliente = new ArrayList<>();
        ConexionBD conexion = new ConexionBD();
        String sql = "select * from cliente";
        try {
            ResultSet rs = conexion.consultarBD(sql);
            Cliente cli;//objeto a mapear
            while (rs.next()) {//recorrer el conjunto de resultados
                cli = new Cliente();//creo un onjeto
                cli.setCedula(rs.getInt("cedula"));
                cli.setNombre(rs.getString("nombre"));
                cli.setTipoVehiculo(rs.getString("tipoVehiculo"));
                cli.setPlacaVehiculo(rs.getString("placaVehiculo"));
                cli.setColorVehiculo(rs.getString("colorVehiculo"));
                cli.setPlaza(rs.getString("plaza"));

                listaCliente.add(cli);
            }
        } catch (SQLException ex) {
            System.out.println("Error" + ex.getMessage());
        } finally {
            conexion.cerrarConexion();
        }
        return listaCliente;//retornando la lista
    }

    public boolean guardarCliente() {
        ConexionBD conexion = new ConexionBD();
        String sql = "insert into cliente (cedula,nombre,tipoVehiculo,placaVehiculo,colorVehiculo,plaza)\n"
                + "values(" + this.cedula + ",'" + this.nombre + "','" + this.tipoVehiculo + "','" + this.placaVehiculo + "','" + this.colorVehiculo
                + "','" + this.plaza + "');";
       
        if (conexion.setAutoCommitBD(false)) {
            if (conexion.insertarBD(sql)) {
                conexion.commitBD();
                conexion.cerrarConexion();
                return true;
            } else {
                conexion.rollbackBD();
                conexion.cerrarConexion();
                return false;
            }
        } else {
            conexion.cerrarConexion();
            return false;
        }
    }

    public boolean actualizarCliente() {
        ConexionBD conexion = new ConexionBD();
        String sql = "update cliente set cedula='" + this.cedula + "',nombre ='" + this.nombre + "',tipoVehiculo ='" + this.tipoVehiculo
                + "',placaVehiculo='" + this.placaVehiculo +"',colorVehiculo='" + this.colorVehiculo + "',plaza ='" + this.plaza + "';";
        System.out.println("update cliente set cedula='" + this.cedula + "',nombre ='" + this.nombre + "',tipoVehiculo ='" + this.tipoVehiculo
                + "',placaVehiculo='" + this.placaVehiculo +"',colorVehiculo='" + this.colorVehiculo + "',plaza ='" + this.plaza + "';");
        if (conexion.setAutoCommitBD(false)) {
            if (conexion.actualizarBD(sql)) {
                conexion.commitBD();
                conexion.cerrarConexion();
                return true;
            } else {
                conexion.rollbackBD();
                conexion.cerrarConexion();
                return false;
            }
        } else {
            conexion.cerrarConexion();
            return false;
        }
    }

    public boolean eliminarCliente() {
        ConexionBD conexion = new ConexionBD();
        String sql = "delete from cliente where cedula=" + this.cedula + ";";
        if (conexion.setAutoCommitBD(false)) {
            if (conexion.actualizarBD(sql)) {
                conexion.commitBD();
                conexion.cerrarConexion();
                return true;
            } else {
                conexion.rollbackBD();
                conexion.cerrarConexion();
                return false;
            }
        } else {
            conexion.cerrarConexion();
            return false;
        }
    }

}
