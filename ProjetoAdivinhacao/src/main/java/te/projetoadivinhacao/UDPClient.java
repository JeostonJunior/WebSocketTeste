/**
 * 
 * Classe que cria um Cliente UDP que se comunica atrav�s de Sockets com um Servidor
 * 
 */

import java.io.* ;
import java.net.* ;


public class UDPClient
{
    public static void main (String args[]){
        
// Cria��o do socket do Cliente. (in�cio)
        DatagramSocket s = null;
        try{
            int serverPort=8000;
            s = new DatagramSocket();
// Cria��o do socket do Cliente. (final) 
            
// Conversa entre o Cliente e o Servidor. (in�cio)
            int i=0;
            for (int j=0; j<10; ++j) {
// Cliente envia um pedido de servi�o ao Servidor.                
                String m = "";
                m = "Pedido de Servico " + ++i;
                byte[] data = m.getBytes();
                DatagramPacket request = new DatagramPacket(data,
                             data.length,
                             InetAddress.getByName("127.0.0.1"),
                             serverPort);
                s.send(request);

// Cliente aguarda pela resposta ao seu pedido de servi�o.
                byte[] datax = new byte[40];
                DatagramPacket reply = new DatagramPacket(datax, datax.length);
                s.receive(reply);
                System.out.println("Recebida a " + new String(reply.getData()));                

                try {
                    Thread.sleep(2000);
                } catch (Exception e) {}
            }
// Conversa entre o Cliente e o Servidor. (final)            

// Tratamento de Exce��es. (in�cio)
        }catch (UnknownHostException e){
            System.out.println("Sock:" + e.getMessage());
        }catch (EOFException e){System.out.println("EOF:" + e.getMessage());
        }catch (IOException e){System.out.println("IO:" + e.getMessage());
        }finally {if(s!=null) s.close();}
// Tratamento de Exce��es. (final)
    }
}