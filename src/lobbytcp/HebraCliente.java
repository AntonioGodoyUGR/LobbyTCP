/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lobbytcp;

import java.net.Socket;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author roman
 */
public class HebraCliente extends Thread{
    
    Cliente cliente;
    
    public HebraCliente(Cliente cli){
        cliente = cli;
    }
    
    @Override
    public void run(){
        cliente.escuchaCliente();
        
    }
    
     
    
}
