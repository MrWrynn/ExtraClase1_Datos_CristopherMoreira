/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Observable;
import java.util.Observer;

/**Para las ventanas se utiliza un template de Swing */

/**
 * A continuación se crea la clase ventana y se declara como observador
 * lo que permite que el servidor (observable) lo actualice 
 * 
 */

public class Ventana2 extends javax.swing.JFrame implements Observer {
    
    public Ventana2() {
        initComponents();
        this.getRootPane().setDefaultButton(this.btnEnviar);
        Servidor s = new Servidor(8000);
        s.addObserver(this);
        Thread t = new Thread(s);
        t.start();
        
    }

    /**
     * Este método se llama desde el constructor para inicializar el formulario.
     * WARNING: NO modifique este código. El contenido de este
     * método siempre lo regenera el Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        txtTexto = new javax.swing.JTextArea();
        btnEnviar = new javax.swing.JButton();
        txtTextoEnviar = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Ventana 2");

        txtTexto.setColumns(20);
        txtTexto.setRows(5);
        jScrollPane1.setViewportView(txtTexto);

        btnEnviar.setText("Enviar");
        btnEnviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnviarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtTextoEnviar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnEnviar, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnEnviar, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                    .addComponent(txtTextoEnviar))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

   
    private void btnEnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnviarActionPerformed
        
        String mensaje = "2: " + this.txtTextoEnviar.getText();

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
        
        this.txtTexto.append(mensaje + "\n");
        
        Cliente c = new Cliente(7000, mensaje);
        Thread t = new Thread(c);
        t.start();

    }//GEN-LAST:event_btnEnviarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Ventana2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Ventana2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Ventana2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Ventana2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Crea y muestra la ventana */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Ventana2().setVisible(true);
            }
        });
    }

    // Declaración de variables - no modificar//GEN-BEGIN:variables
    private javax.swing.JButton btnEnviar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea txtTexto;
    private javax.swing.JTextField txtTextoEnviar;
    // Fin de la delcaración de variables//GEN-END:variables

    @Override
    public void update(Observable o, Object arg) {
        
        this.txtTexto.append((String) arg);
    }
    
}
