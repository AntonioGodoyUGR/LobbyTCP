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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author roman
 */
public class HebraServicio extends Thread{
    
    Socket socket;
    BufferedReader lector;
    PrintWriter escritor;
    public HebraServicio(Socket sock) throws IOException{
        socket = sock;
        BufferedReader lector = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter escritor = new PrintWriter(socket.getOutputStream(),true);
    }
    
    
    void servicioEscucha(){
        String[] mensaje ={""};
        do{
            try {
                mensaje = lector.readLine().split("-");
            } catch (IOException ex) {
                Logger.getLogger(HebraServicio.class.getName()).log(Level.SEVERE, null, ex);
            }
            switch(mensaje[0]){
                case OpcionesSeleccion.USER:
                    escritor.println("conectado con exito");
                    
                    break;
            }
            
        }while(true);
    }
    
    @Override
    public void run(){
        servicioEscucha();
        
    }
}
