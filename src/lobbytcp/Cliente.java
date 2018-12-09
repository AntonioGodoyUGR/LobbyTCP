/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lobbytcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author roman
 */

public class Cliente {
    
    OpcionesEstado estado;
    boolean conectado=false;
    String nombre;
    
    //----------------------------------//
    
    static BufferedReader lector;
    static PrintWriter escritor;
    
    static String host = "localhost";
    static int port = 8989;
    static Socket socketCliente;
    
    
    public Cliente(String nom){
        estado=OpcionesEstado.NOLOG;
        conectado = true;
        nombre = nom;     
    }
    
    public void comunicacionCliente(){
   
        String[] opSelecion ={""};
        String mimensaje= "";
        
        while(conectado){
            Scanner scanner = new Scanner(System.in);
            opSelecion = scanner.nextLine().split("-");
                    
            switch(estado){
                case NOLOG:
                    System.out.println("no estas conectado");
                    switch(opSelecion[0]){
                        case OpcionesSeleccion.GOODBYE:
                            conectado=false;
                            break;
                        case OpcionesSeleccion.USER:
                            mimensaje = OpcionesSeleccion.USER + "-" + opSelecion[1];
                            escritor.println(mimensaje);
                            break;
                        case OpcionesSeleccion.POSSIBILITIES:
                            mimensaje = OpcionesSeleccion.POSSIBILITIES;
                            escritor.println(mimensaje);
                            break;
                        default:
                            System.out.println("esa opcion no esta disponible");
                            break;
                    }    
                    break;
                case LOG:
                    System.out.println("Estas conectado, puedes ver mi pollon");
                    switch(opSelecion[0]){
                        case OpcionesSeleccion.GOODBYE:
                            conectado=false;
                            break;
                        case OpcionesSeleccion.SELECTMODE:
                            mimensaje= OpcionesSeleccion.SELECTMODE + "-" + opSelecion[1];
                            escritor.println(mimensaje);
                            break;
                        case OpcionesSeleccion.POSSIBILITIES:
                            mimensaje = OpcionesSeleccion.POSSIBILITIES;
                            escritor.println(mimensaje);
                            break;
                        default:
                            System.out.println("esa opcion no esta disponible");
                            break;
                    }    
                    break;
                case MODO:
                    System.out.println("Selecciona tu personaje");
                    switch(opSelecion[0]){
                        case OpcionesSeleccion.GOODBYE:
                            conectado=false;
                            break;    
                        case OpcionesSeleccion.SELECTCHARACTER:
                            mimensaje = OpcionesSeleccion.SELECTCHARACTER + "-" + opSelecion[1];
                            escritor.println(mimensaje);
                            break;
                        case OpcionesSeleccion.POSSIBILITIES:
                            mimensaje = OpcionesSeleccion.POSSIBILITIES;
                            escritor.println(mimensaje);
                            break;
                        default:
                            System.out.println("esa opcion no esta disponible, no inventes");
                            break;
                    }  
                    break;
                case MODO2:
                    System.out.println("Selecciona una linea y un personaje");
                    switch(opSelecion[0]){
                        case OpcionesSeleccion.GOODBYE:
                            conectado=false;
                            break;
                        case OpcionesSeleccion.SELECTCHARACTERANDLINE:
                            mimensaje = OpcionesSeleccion.SELECTCHARACTERANDLINE + "-" + opSelecion[1];
                            escritor.println(mimensaje);
                            break;
                        case OpcionesSeleccion.POSSIBILITIES:
                            mimensaje = OpcionesSeleccion.POSSIBILITIES;
                            escritor.println(mimensaje);
                            break;
                        default:
                            System.out.println("esa opcion no esta disponible");
                            break;
                    }  
                    break;
                /*case PERSONAJES:
                    System.out.println("espera al resto de jugadores huevon");
                    switch(opSelecion[0]){
                        case OpcionesSeleccion.GOODBYE:
                            conectado=false;
                        break;    
                        default:
                            System.out.println("esa opcion no esta disponible");
                            break;
                    }  
                    break;
                case LINEAPERSONAJES:
                    System.out.println("espera al resto de jugadores");
                    switch(opSelecion[0]){
                        case OpcionesSeleccion.GOODBYE:
                            conectado=false;
                        break; 
                        default:
                            System.out.println("esa opcion no esta disponible");
                            break;
                    }  
                    break;*/
                case SALA:
                    System.out.println("Habla!");
                    switch(opSelecion[0]){
                        case OpcionesSeleccion.GOODBYE:
                            conectado=false;
                            break;
                        case OpcionesSeleccion.SENDMESSAGE:
                            mimensaje = OpcionesSeleccion.SENDMESSAGE + "-" + opSelecion[1];
                            escritor.println(mimensaje);
                            break;
                        case OpcionesSeleccion.SELECTCHARACTER:
                            mimensaje = OpcionesSeleccion.CHANGECHARACTER + "-" + opSelecion[1];
                            escritor.println(mimensaje);
                            break;
                        case OpcionesSeleccion.POSSIBILITIES:
                            mimensaje = OpcionesSeleccion.POSSIBILITIES;
                            escritor.println(mimensaje);
                            break;
                        default:
                            System.out.println("esa opcion no esta disponible");
                            break;
                    }  
                    break;
                default:
                    System.out.println("eres subnormal, pues ya ves!");  
                    break;
            }
            
        }
    }

    public void escuchaCliente(){
        String[] mensaje ={""};
        
        while(conectado){
            try {
                mensaje = lector.readLine().split("-");
            } catch (IOException ex) {
                Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(mensaje[0] != null){
                switch(mensaje[0]){
                    case OpcionesSeleccion.SENDMESSAGE:
                        System.out.println(mensaje[1]);
                        break;
                    case OpcionesSeleccion.POSSIBILITIES:
                        System.out.println("Estas son tus opciones disponibles ahora mismo: \n");
                        System.out.println(mensaje[1]);
                        break;
                    case OpcionesSeleccion.CONFIRMATION:
                        switch(estado){
                            case NOLOG:
                                estado=OpcionesEstado.LOG;
                                break;
                            case LOG:
                                estado=OpcionesEstado.MODO;
                            case MODO:
                                estado=OpcionesEstado.PERSONAJES;
                            case MODO2:
                                estado=OpcionesEstado.LINEAPERSONAJES;
                        }
                        System.out.println(mensaje[1]);
                }
            }    
        }
    }
    
    public OpcionesEstado getEstado() {
        return estado;
    }

    public void setEstado(OpcionesEstado estado) {
        this.estado = estado;
    }

    public boolean getConectado() {
        return conectado;
    }

    public void setConectado(boolean conectado) {
        this.conectado = conectado;
    }
    
    
    public static void main(String argv[]){
        
        Cliente cliente = new Cliente(argv[1]);
        
        try {
            cliente.socketCliente = new Socket(host, port);
            cliente.lector = new BufferedReader(new InputStreamReader(cliente.socketCliente.getInputStream()));
            cliente.escritor = new PrintWriter(cliente.socketCliente.getOutputStream(),true);
            
            HebraCliente hebra=new HebraCliente(cliente);
            HebraClienteEscribe hebraEscritora = new HebraClienteEscribe(cliente);
            hebra.start();
            hebraEscritora.start();
            while(cliente.getConectado()){
               
            }
            cliente.socketCliente.close();
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    } 
}
