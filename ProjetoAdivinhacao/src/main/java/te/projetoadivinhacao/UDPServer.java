/**
 * 
 * Classe que cria um Servidor UDP atrav�s 
 * de Sockets, atendendo diversos Clientes.
 * 
 */

import java.io.* ;
import java.net.* ;

public class UDPServer
{
    public static void main(String args[]){

        DatagramSocket s = null;
        try{
            
// Cria��o do socket do Servidor, utilizando a porta serverPort.
            int serverPort=8000;
            s = new DatagramSocket(serverPort);
            System.out.println("Servidor Ativo");
// Loop principal de espera por pedidos de servi�os de Clientes.            
            while(true){

// Servidor aguarda algum pedido de servi�o.
               byte[] data = new byte [40] ;
               DatagramPacket request = new DatagramPacket(data, data.length);
               s.receive(request);
               System.out.println("Recebido o " + new String(request.getData()));

// Resposta ao pedido solicitado pelo Cliente.
               String m = "Resposta ao " + new String(request.getData());
               data = m.getBytes();
               DatagramPacket reply = new DatagramPacket(data,
                                                         data.length,
                                                         request.getAddress(),
                                                         request.getPort());
               s.send(reply);

            }

// Tratamento de Exce��es. (in�cio)
        }catch (EOFException e){System.out.println("EOF:" + e.getMessage());
        }catch (IOException e){System.out.println("IO:" + e.getMessage());
        }finally {if(s!=null) s.close();}
// Tratamento de Exce��es. (final)
    }
}
