/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.util.List;

/**
 *
 * @author Usuairo
 */
public class DemoClienteLectura {

    public static void main(String[] args) {
        Cliente cli = new Cliente();
        List<Cliente> lista = cli.listarCliente();
        for (Cliente cl : lista) {
            System.out.println(cl);
        }
        System.out.println("");
    }
}
