/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

/**
 *
 * @author Usuairo
 */
public class demoClienteEscritura {

    public static void main(String[] args) {
        Cliente cli = new Cliente();
        cli.setCedula(2);
        cli.setNombre("male");
        cli.setTipoVehiculo("camioneta");
        cli.setPlacaVehiculo("llll0000");
        cli.setColorVehiculo("blanco");
        cli.setPlaza("10");

        System.out.println(cli.guardarCliente());
    }
}
