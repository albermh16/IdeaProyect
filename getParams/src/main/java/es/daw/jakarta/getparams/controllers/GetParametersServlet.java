package es.daw.jakarta.getparams.controllers;

import java.io.*;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

//@WebServlet(name = "get-params", value = "/get-params")
@WebServlet("/get-params") //mapping
public class GetParametersServlet extends HttpServlet {

    private static final int LIMIT = 100;



    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        System.out.println("*************** estoy en get!!!");

        //***************RECOGIDA DE DATOS (REQUEST) ********************

        String saludo =  request.getParameter("saludo");
        String nombre = request.getParameter("nombre");

        // Controlar si llega nulo
        String codigo = request.getParameter("codigo");

        String message;
        //**************LOGICA*******************


        //Devolver mensaje indicando que el codigo no puede ser nulo
        if(codigo == null){
            message = "El codigo no puede ser nulo";
        }else{
            try{
                int code  = Integer.parseInt(codigo);
                //Controlar la excepcion NumberFormatException
                //message = "El codigo es numerico: " + code;
                // Si el codigo es mayor que 100 devolver: has superado el limite establecido

                message = code > LIMIT ? "Has superado el limite establecido" :  "El saludo enviado es: " +  saludo + " " + nombre + " con codigo " + codigo;

            }catch (NumberFormatException e){
                message = "El codigo no se puede convertir a entero";
            }

        }

        //***************RESPONSE*******************
        response.setContentType("text/html");

        try(PrintWriter out = response.getWriter()){
            out.println("<html><body>");
            out.println("<h1>ParamsGetServerlet</h1>");
            //out.println("<h1> El saludo enviado es: " + saludo + " " + nombre + " con codigo " + codigo + "</h1>");
            out.println("</h2>" + message + "</h2>");
            out.println("</body></html>");
        }


    }


}