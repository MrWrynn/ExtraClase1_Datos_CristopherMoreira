import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Servidor extends Observable implements Runnable {

    private int puerto;

    public Servidor(int puerto) {
        this.puerto = puerto;
    }

    @Override
    public void run() {

        ServerSocket servidor = null;
        Socket sc = null;
        DataInputStream in;

        try {
            //Creamos el socket del servidor
            servidor = new ServerSocket(puerto);
            System.out.println("Servidor iniciado");

            //Siempre estara escuchando peticiones
            while (true) {

                //Espero a que un cliente se conecte
                sc = servidor.accept();

                System.out.println("Cliente conectado");
                in = new DataInputStream(sc.getInputStream());
               
                //Leo el mensaje que me envia
                String mensaje = in.readUTF();
                
                System.out.println(mensaje);
                
                

                String numero = mensaje;
                if (numero.contains(",")) {
                    String[] parts = numero.split(",");
                    String usuario = parts[0]; // 123
                    String valor = parts[1]; // 654321
                    String peso = parts[2]; // 654321
                    String porcentaje = parts[3]; // 654321
        


                    int valorN = Integer.parseInt(valor);
                    int pesoN = Integer.parseInt(peso);
                    int porcentajeN = Integer.parseInt(porcentaje);
                    String prueba = new String("1: ");
                    String prueba1 = new String("2: ");
                    System.out.println(mensaje);
                    if (usuario.equals(prueba)  || usuario.equals(prueba1)) {
                        Double monto = (valorN*porcentajeN)/100 + (pesoN*0.15);
                        System.out.println(monto);
                        
                        String s=String.valueOf(monto);
                        mensaje=usuario + s;
                    }
                }
                
                



                this.setChanged();
                this.notifyObservers(mensaje + "\n");
                this.clearChanged();
                
                //Cierro el socket
                sc.close();
                System.out.println("Cliente desconectado");

            }

        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}