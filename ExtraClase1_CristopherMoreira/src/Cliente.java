import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Cliente implements Runnable { //No hace falta el observable, este solo

    private int puerto;
    private String mensaje;

    public Cliente(int puerto, String mensaje) {
        this.puerto = puerto;
        this.mensaje = mensaje;
    }

    @Override
    public void run() {
        //Host del servidor
        final String HOST = "192.168.1.5";
        //Puerto del servidor
        DataOutputStream out;

        try {
            //Creo el socket para conectarme con el cliente
            Socket sc = new Socket(HOST, puerto);

            out = new DataOutputStream(sc.getOutputStream());

            //Envio un mensaje, el servidor se encarga de procesar el cambio
            out.writeUTF(mensaje);

            sc.close();

        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}