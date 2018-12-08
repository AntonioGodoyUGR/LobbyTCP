/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lobbytcp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author roman
 */
public class LobbyTCP {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       int puerto = 8989;
       
       
       
       ServerSocket socketServidor;
       try{
           socketServidor = new ServerSocket(puerto);
           do{
               Socket socketCliente = socketServidor.accept();
               
               HebraServicio hebra = new HebraServicio(socketCliente);
               hebra.start();
               
               
           }while(true);       
       }catch (IOException e) {
            System.err.println("Error al escuchar en el puerto "+puerto);
       }
    }
    
}
