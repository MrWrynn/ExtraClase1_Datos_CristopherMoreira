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
            /**Se crea el socket del servidor */
            servidor = new ServerSocket(puerto);
            System.out.println("Servidor iniciado");

            /**El while true es para que siempre esté escuchando peticiones */
            while (true) {

                /**Espera a que un cliente se conecte */
                sc = servidor.accept();

                System.out.println("Cliente conectado");
                in = new DataInputStream(sc.getInputStream());
               
                /**Lee el mensaje enviado */
                String mensaje = in.readUTF();
                
                System.out.println(mensaje);
                
                
                /**
                 * A continuación el codigo se encarga de preguntar si el mensaje enviado
                 * tiene el formato de un calculo (",valor,peso,porcentaje") para realizar
                 * el calculo, en caso de no tenerlo solo envía el mensaje.
                 */

                String numero = mensaje;
                if (numero.contains(",")) {
                    String[] parts = numero.split(",");
                    String usuario = parts[0]; 
                    String valor = parts[1]; 
                    String peso = parts[2];
                    String porcentaje = parts[3];
        


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
                
                /**el notifyObservers es el encargado de hacer llegar el mensaje enviado por la ventana emisora
                 * hasta la ventana receptora
                 */
                this.setChanged();
                this.notifyObservers(mensaje + "\n");
                this.clearChanged();
                
                /**Para cerrar el socket */
                sc.close();
                System.out.println("Cliente desconectado");

            }

        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}