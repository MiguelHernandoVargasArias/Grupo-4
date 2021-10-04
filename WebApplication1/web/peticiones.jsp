<%-- 
    Document   : Archivo de peticiones
    Created on : dd/mm/yyyy, hh:mm: AM/PM
    Author     : nombre autor
--%>

<%@page import="java.sql.SQLException"%>
<%@page import="logica.Cliente"%>
<%@page import="java.util.logging.Logger"%>
<%@page import="java.util.logging.Level"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="java.util.Arrays"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="application/json;charset=iso-8859-1" language="java" pageEncoding="iso-8859-1" session="true"%>

<%    // Iniciando respuesta JSON.
    String respuesta = "{";

    //Lista de procesos o tareas a realizar 
    List<String> tareas = Arrays.asList(new String[]{
        "guardar",
        "eliminar",
        "actualizar",
        "consultarUno",
        "listar"
    });

    String proceso = "" + request.getParameter("proceso");

    // Validación de parámetros utilizados en todos los procesos.
    if (tareas.contains(proceso)) {
        respuesta += "\"ok\": true,";
        // ------------------------------------------------------------------------------------- //
        // -----------------------------------INICIO PROCESOS----------------------------------- //
        // ------------------------------------------------------------------------------------- //
        if (proceso.equals("guardar")) {
            try {
                int cedula = Integer.parseInt(request.getParameter("cedula"));
                String nombre = request.getParameter("nombre");
                String tipoVehiculo = request.getParameter("tipoVehiculo");
                String placaVehiculo = request.getParameter("placaVehiculo");
                String colorVehiculo = request.getParameter("colorVehiculo");
                String plaza = request.getParameter("plaza");

                Cliente cli = new Cliente();
                cli.setCedula(cedula);
                cli.setNombre(nombre);
                cli.setTipoVehiculo(tipoVehiculo);
                cli.setPlacaVehiculo(placaVehiculo);
                cli.setColorVehiculo(colorVehiculo);
                cli.setPlaza(plaza);

                if (cli.guardarCliente()) {
                    respuesta += "\"" + proceso + "\": true";
                } else {
                    respuesta += "\"" + proceso + "\": false";
                }
            } catch (Exception e) {
                respuesta += "\"" + proceso + "\": false";
            }

        } else if (proceso.equals("eliminar")) {
            try {
                int cedula = Integer.parseInt(request.getParameter("cedula"));
                Cliente cli = new Cliente();
                cli.setCedula(cedula);
                if (cli.eliminarCliente()) {
                    respuesta += "\"" + proceso + "\": true";
                } else {
                    respuesta += "\"" + proceso + "\": false";
                }
            } catch (Exception e) {
                respuesta += "\"" + proceso + "\": false";
            }

        } else if (proceso.equals("listar")) {
            //Solicitud de parámetros enviados desde el frontned
            //, uso de request.getParameter("nombre parametro")
            //creación de objeto y llamado al metodo listar
            try {
                Cliente cli = new Cliente();
                List<Cliente> lista = cli.listarCliente();
                respuesta += "\"" + proceso + "\": true,\"Cliente\":" + new Gson().toJson(lista);
            } catch (Exception ex) {
                respuesta += "\"" + proceso + "\": true,\"Cliente\":[]";
                Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (proceso.equals("consultarUno")) {
            //Solicitud de parámetros enviados desde el frontned
            //, uso de request.getParameter("nombre parametro")
            //creación de objeto y llamado al metodo listar
            try {
                int cedula = Integer.parseInt(request.getParameter("cedula"));
                Cliente cli = new Cliente(cedula).getCliente();
                respuesta += "\"" + proceso + "\": true,\"Cliente\":" + new Gson().toJson(cli);
            } catch (Exception ex) {
                respuesta += "\"" + proceso + "\": true,\"Cliente\":{}";
                Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (proceso.equals("actualizar")) {
            try {
                int cedula = Integer.parseInt(request.getParameter("cedula"));
                String nombre = request.getParameter("nombre");
                String tipoVehiculo = request.getParameter("tipoVehiculo");
                String placaVehiculo = request.getParameter("placaVehiculo");
                String colorVehiculo = request.getParameter("colorVehiculo");
                String plaza = request.getParameter("plaza");

                Cliente cli = new Cliente();
                cli.setCedula(cedula);
                cli.setNombre(nombre);
                cli.setTipoVehiculo(tipoVehiculo);
                cli.setPlacaVehiculo(placaVehiculo);
                cli.setColorVehiculo(colorVehiculo);
                cli.setPlaza(plaza);

                if (cli.actualizarCliente()) {
                    respuesta += "\"" + proceso + "\": true";
                } else {
                    respuesta += "\"" + proceso + "\": false";
                }
            } catch (Exception e) {
                respuesta += "\"" + proceso + "\": false";
            }
        }
        // ------------------------------------------------------------------------------------- //
        // -----------------------------------FIN PROCESOS-------------------------------------- //
        // ------------------------------------------------------------------------------------- //
        // Proceso desconocido.
    } else {
        respuesta += "\"ok\": false,";
        respuesta += "\"error\": \"INVALID\",";
        respuesta += "\"errorMsg\": \"Lo sentimos, los datos que ha enviado,"
                + " son inválidos. Corrijalos y vuelva a intentar por favor.\"";
    }
    // Responder como objeto JSON codificación ISO 8859-1.
    respuesta += "}";
    response.setContentType("application/json;charset=iso-8859-1");
    out.print(respuesta);
%>

