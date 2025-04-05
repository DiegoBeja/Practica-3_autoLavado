package com.mycompany.practica3;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class Interfaz extends javax.swing.JFrame {

    public Interfaz() {
        initComponents();
        
        x = new AutoLavado();
        Thread hilo = new Thread(x);
        hilo.start();
        actualizarAcceso();
        
        Timer timer = new Timer(150, e -> actualizarAcceso());
        timer.start();
        
        horaContador = 8;
        minutos = 0;
        
        reloj();
        registroCarros = new StringBuilder();
    }
    
    public void mostrarInformacion(String texto){
        JDialog dialogo = new JDialog(this, "Registro carros", true);
        JTextArea textArea = new JTextArea(20, 40);
        textArea.setText(texto);
        textArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(textArea);
        dialogo.getContentPane().add(scrollPane);
        dialogo.pack();
        dialogo.setLocationRelativeTo(this); 
        dialogo.setVisible(true);
    }
    
    public void reloj(){  
        relojTimer = new Timer(1000, e -> {
            if(horaContador < 10){
                minutos++;
                if(minutos == 60){
                    minutos = 0;
                    horaContador++;
                }
                actualizarAcceso();
            } else{
                relojTimer.stop(); 
                System.out.println(x.getRegistro());
                mostrarInformacion(x.getRegistro());
            }
        });
        relojTimer.start();
    }
    
    public void actualizarAcceso(){
         SwingUtilities.invokeLater(() -> {
            if(x.getHoras() >= 9.75){
                horasRestantes.setVisible(true);
            } else{
                horasRestantes.setVisible(false);
            }
            
            String tiempo = String.format("%02d:%02d", horaContador, minutos);
            if(!hora.getText().equals(tiempo)){
                hora.setText(tiempo);
            }
            
            acceso0.setBackground(Color.WHITE);
            acceso1.setBackground(Color.WHITE);
            acceso2.setBackground(Color.WHITE);
            acceso3.setBackground(Color.WHITE);
            acceso4.setBackground(Color.WHITE);
            acceso5.setBackground(Color.WHITE);
            acceso6.setBackground(Color.WHITE);
            acceso7.setBackground(Color.WHITE);
            acceso8.setBackground(Color.WHITE);
            acceso9.setBackground(Color.WHITE);
            
            acceso0.setText("");
            acceso1.setText("");
            acceso2.setText("");
            acceso3.setText("");
            acceso4.setText("");
            acceso5.setText("");
            acceso6.setText("");
            acceso7.setText("");
            acceso8.setText("");
            acceso9.setText("");
            
            int size = x.getAcceso().getSize(); 
            int sizeLavado = x.getMaquinaLavado().getSize();
            int sizeSecado1 = x.getLineaAspirado1().getSize();
            int sizeSecado2 = x.getLineaAspirado2().getSize();
            int sizeSecado3 = x.getLineaAspirado3().getSize();
            int sizeSecado4 = x.getLineaAspirado4().getSize();
            int sizeExpress = x.getLineaSecadoExpress().getSize();
        
            for(int i = 0; i < size; i++){
                switch(i){
                    case 0: acceso0.setBackground(Color.GREEN); acceso0.setText(x.getAcceso().getVehiculo(0).getMarca()); break;
                    case 1: acceso1.setBackground(Color.GREEN); acceso1.setText(x.getAcceso().getVehiculo(1).getMarca()); break;
                    case 2: acceso2.setBackground(Color.GREEN); acceso2.setText(x.getAcceso().getVehiculo(2).getMarca()); break;
                    case 3: acceso3.setBackground(Color.GREEN); acceso3.setText(x.getAcceso().getVehiculo(3).getMarca()); break;
                    case 4: acceso4.setBackground(Color.GREEN); acceso4.setText(x.getAcceso().getVehiculo(4).getMarca()); break;
                    case 5: acceso5.setBackground(Color.GREEN); acceso5.setText(x.getAcceso().getVehiculo(5).getMarca()); break;
                    case 6: acceso6.setBackground(Color.GREEN); acceso6.setText(x.getAcceso().getVehiculo(6).getMarca()); break;
                    case 7: acceso7.setBackground(Color.GREEN); acceso7.setText(x.getAcceso().getVehiculo(7).getMarca()); break;
                    case 8: acceso8.setBackground(Color.GREEN); acceso8.setText(x.getAcceso().getVehiculo(8).getMarca()); break;
                    case 9: acceso9.setBackground(Color.GREEN); acceso9.setText(x.getAcceso().getVehiculo(9).getMarca()); break;
                }
            }
            
            lavado0.setBackground(Color.WHITE);
            lavado1.setBackground(Color.WHITE);
            lavado2.setBackground(Color.WHITE);
            
            lavado0.setText("");
            lavado1.setText("");
            lavado2.setText("");
            for(int j = 0; j < sizeLavado; j++){
                switch (j){
                    case 0: lavado0.setBackground(Color.GREEN); lavado0.setText(x.getMaquinaLavado().getVehiculo(0).getMarca()); break;
                    case 1: lavado1.setBackground(Color.GREEN); lavado1.setText(x.getMaquinaLavado().getVehiculo(1).getMarca()); break;
                    case 2: lavado2.setBackground(Color.GREEN); lavado2.setText(x.getMaquinaLavado().getVehiculo(2).getMarca()); break;
                }
            }
            
            secado10.setBackground(Color.WHITE);
            secado11.setBackground(Color.WHITE);
            secado12.setBackground(Color.WHITE);
            secado13.setBackground(Color.WHITE);
            
            secado10.setText("");
            secado11.setText("");
            secado12.setText("");
            secado13.setText("");
            for(int j = 0; j < sizeSecado1; j++){
                switch(j){
                    case 0: 
                        secado10.setBackground(Color.GREEN); 
                        secado10.setText(x.getLineaAspirado1().getVehiculo(0).getMarca());  
                        break;
                    case 1: 
                        secado11.setBackground(Color.GREEN); 
                        secado11.setText(x.getLineaAspirado1().getVehiculo(1).getMarca()); 
                        break;
                    case 2: 
                        secado12.setBackground(Color.GREEN); 
                        secado12.setText(x.getLineaAspirado1().getVehiculo(2).getMarca());
                        break;
                    case 3: 
                        secado13.setBackground(Color.GREEN); 
                        secado13.setText(x.getLineaAspirado1().getVehiculo(3).getMarca()); 
                        break;
                }
            }
            
            secado20.setBackground(Color.WHITE);
            secado21.setBackground(Color.WHITE);
            secado22.setBackground(Color.WHITE);
            secado23.setBackground(Color.WHITE);
            
            secado20.setText("");
            secado21.setText("");
            secado22.setText("");
            secado23.setText("");
            for(int j = 0; j < sizeSecado2; j++){
                switch(j){
                    case 0: 
                        secado20.setBackground(Color.GREEN); 
                        secado20.setText(x.getLineaAspirado2().getVehiculo(0).getMarca()); 
                        break;
                    case 1: 
                        secado21.setBackground(Color.GREEN); 
                        secado21.setText(x.getLineaAspirado2().getVehiculo(1).getMarca());
                        break;
                    case 2: 
                        secado22.setBackground(Color.GREEN); 
                        secado22.setText(x.getLineaAspirado2().getVehiculo(2).getMarca());
                        break;
                    case 3: 
                        secado23.setBackground(Color.GREEN); 
                        secado23.setText(x.getLineaAspirado2().getVehiculo(3).getMarca());
                        break;
                }
            }

            secado30.setBackground(Color.WHITE);
            secado31.setBackground(Color.WHITE);
            secado32.setBackground(Color.WHITE);
            secado33.setBackground(Color.WHITE);
            
            secado30.setText("");
            secado31.setText("");
            secado32.setText("");
            secado33.setText("");
            for(int j = 0; j < sizeSecado3; j++){
                switch(j){
                    case 0: 
                        secado30.setBackground(Color.GREEN); 
                        secado30.setText(x.getLineaAspirado3().getVehiculo(0).getMarca()); 
                        break;
                    case 1: 
                        secado31.setBackground(Color.GREEN); 
                        secado31.setText(x.getLineaAspirado3().getVehiculo(1).getMarca()); 
                        break;
                    case 2: 
                        secado32.setBackground(Color.GREEN); 
                        secado32.setText(x.getLineaAspirado3().getVehiculo(2).getMarca());
                        break;
                    case 3: 
                        secado33.setBackground(Color.GREEN); 
                        secado33.setText(x.getLineaAspirado3().getVehiculo(3).getMarca());
                        break;
                }
            }
            
            secado40.setBackground(Color.WHITE);
            secado41.setBackground(Color.WHITE);
            secado42.setBackground(Color.WHITE);
            secado43.setBackground(Color.WHITE);
            
            secado40.setText("");
            secado41.setText("");
            secado42.setText("");
            secado43.setText("");
            for(int j = 0; j < sizeSecado4; j++){
                switch(j){
                    case 0: 
                        secado40.setBackground(Color.GREEN); 
                        secado40.setText(x.getLineaAspirado4().getVehiculo(0).getMarca());
                        break;
                    case 1: 
                        secado41.setBackground(Color.GREEN); 
                        secado41.setText(x.getLineaAspirado4().getVehiculo(1).getMarca()); 
                        break;
                    case 2: 
                        secado42.setBackground(Color.GREEN); 
                        secado42.setText(x.getLineaAspirado4().getVehiculo(2).getMarca()); 
                        break;
                    case 3: 
                        secado43.setBackground(Color.GREEN); 
                        secado43.setText(x.getLineaAspirado4().getVehiculo(3).getMarca());
                        break;
                }
            }
                
            express0.setBackground(Color.WHITE);
            express1.setBackground(Color.WHITE);
            express2.setBackground(Color.WHITE);
            express3.setBackground(Color.WHITE);
            express4.setBackground(Color.WHITE);
            
            express0.setText("");
            express1.setText("");
            express2.setText("");
            express3.setText("");
            express4.setText("");
            for(int j = 0; j < sizeExpress; j++){
                switch(j){
                    case 0: 
                        express0.setBackground(Color.GREEN); 
                        express0.setText(x.getLineaSecadoExpress().getVehiculo(0).getMarca()); 
                        break;
                    case 1: 
                        express1.setBackground(Color.GREEN); 
                        express1.setText(x.getLineaSecadoExpress().getVehiculo(1).getMarca());
                        break;
                    case 2: 
                        express2.setBackground(Color.GREEN); 
                        express2.setText(x.getLineaSecadoExpress().getVehiculo(2).getMarca()); 
                        break;
                    case 3: 
                        express3.setBackground(Color.GREEN); 
                        express3.setText(x.getLineaSecadoExpress().getVehiculo(3).getMarca());
                        break;
                    case 4: 
                        express4.setBackground(Color.GREEN); 
                        express4.setText(x.getLineaSecadoExpress().getVehiculo(4).getMarca()); 
                        break;
                }
            }
        });
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        hora = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        horasRestantes = new javax.swing.JLabel();
        saltar = new javax.swing.JButton();
        adelantar = new javax.swing.JButton();
        acceso0 = new javax.swing.JLabel();
        acceso1 = new javax.swing.JLabel();
        acceso2 = new javax.swing.JLabel();
        acceso3 = new javax.swing.JLabel();
        ColaAcceso = new javax.swing.JLabel();
        acceso4 = new javax.swing.JLabel();
        acceso5 = new javax.swing.JLabel();
        acceso6 = new javax.swing.JLabel();
        acceso7 = new javax.swing.JLabel();
        acceso8 = new javax.swing.JLabel();
        acceso9 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        lavado0 = new javax.swing.JLabel();
        lavado1 = new javax.swing.JLabel();
        lavado2 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        secado10 = new javax.swing.JLabel();
        secado11 = new javax.swing.JLabel();
        secado12 = new javax.swing.JLabel();
        secado13 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        secado20 = new javax.swing.JLabel();
        secado21 = new javax.swing.JLabel();
        secado22 = new javax.swing.JLabel();
        secado23 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        secado30 = new javax.swing.JLabel();
        secado40 = new javax.swing.JLabel();
        secado31 = new javax.swing.JLabel();
        secado32 = new javax.swing.JLabel();
        secado33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        secado41 = new javax.swing.JLabel();
        secado42 = new javax.swing.JLabel();
        secado43 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel38 = new javax.swing.JLabel();
        express0 = new javax.swing.JLabel();
        express1 = new javax.swing.JLabel();
        express2 = new javax.swing.JLabel();
        express3 = new javax.swing.JLabel();
        express4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(153, 153, 153));
        jPanel2.setForeground(new java.awt.Color(153, 153, 153));
        jPanel2.setToolTipText("");

        hora.setText("8:00");
        hora.setFocusable(false);
        hora.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                horaActionPerformed(evt);
            }
        });

        jLabel1.setText("Hora:");

        horasRestantes.setText("Faltan 15 min para cerrar");

        saltar.setText("Saltar");
        saltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saltarActionPerformed(evt);
            }
        });

        adelantar.setText("Adelantar");
        adelantar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adelantarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(hora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(231, 231, 231)
                .addComponent(horasRestantes)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(saltar)
                .addGap(18, 18, 18)
                .addComponent(adelantar)
                .addGap(29, 29, 29))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(hora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(horasRestantes)
                    .addComponent(saltar)
                    .addComponent(adelantar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        acceso0.setBackground(new java.awt.Color(255, 255, 255));
        acceso0.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        acceso0.setOpaque(true);

        acceso1.setBackground(new java.awt.Color(255, 255, 255));
        acceso1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        acceso1.setOpaque(true);

        acceso2.setBackground(new java.awt.Color(255, 255, 255));
        acceso2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        acceso2.setOpaque(true);

        acceso3.setBackground(new java.awt.Color(255, 255, 255));
        acceso3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        acceso3.setOpaque(true);

        ColaAcceso.setText("Cola Acceso");

        acceso4.setBackground(new java.awt.Color(255, 255, 255));
        acceso4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        acceso4.setOpaque(true);

        acceso5.setBackground(new java.awt.Color(255, 255, 255));
        acceso5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        acceso5.setOpaque(true);

        acceso6.setBackground(new java.awt.Color(255, 255, 255));
        acceso6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        acceso6.setOpaque(true);

        acceso7.setBackground(new java.awt.Color(255, 255, 255));
        acceso7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        acceso7.setOpaque(true);

        acceso8.setBackground(new java.awt.Color(255, 255, 255));
        acceso8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        acceso8.setOpaque(true);

        acceso9.setBackground(new java.awt.Color(255, 255, 255));
        acceso9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        acceso9.setOpaque(true);

        jLabel14.setText("Cola Lavado");

        lavado0.setBackground(new java.awt.Color(255, 255, 255));
        lavado0.setText("jLabel3");
        lavado0.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        lavado0.setOpaque(true);

        lavado1.setBackground(new java.awt.Color(255, 255, 255));
        lavado1.setText("jLabel3");
        lavado1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        lavado1.setOpaque(true);

        lavado2.setBackground(new java.awt.Color(255, 255, 255));
        lavado2.setText("jLabel3");
        lavado2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        lavado2.setOpaque(true);

        jLabel18.setText("Cola Secado 1");

        secado10.setBackground(new java.awt.Color(255, 255, 255));
        secado10.setText("jLabel3");
        secado10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        secado10.setOpaque(true);

        secado11.setBackground(new java.awt.Color(255, 255, 255));
        secado11.setText("jLabel3");
        secado11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        secado11.setOpaque(true);

        secado12.setBackground(new java.awt.Color(255, 255, 255));
        secado12.setText("jLabel3");
        secado12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        secado12.setOpaque(true);

        secado13.setBackground(new java.awt.Color(255, 255, 255));
        secado13.setText("jLabel3");
        secado13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        secado13.setOpaque(true);

        jLabel23.setText("Cola Secado 2");

        secado20.setBackground(new java.awt.Color(255, 255, 255));
        secado20.setText("jLabel3");
        secado20.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        secado20.setOpaque(true);

        secado21.setBackground(new java.awt.Color(255, 255, 255));
        secado21.setText("jLabel3");
        secado21.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        secado21.setOpaque(true);

        secado22.setBackground(new java.awt.Color(255, 255, 255));
        secado22.setText("jLabel3");
        secado22.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        secado22.setOpaque(true);

        secado23.setBackground(new java.awt.Color(255, 255, 255));
        secado23.setText("jLabel3");
        secado23.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        secado23.setOpaque(true);

        jLabel28.setText("Cola Secado 3");

        secado30.setBackground(new java.awt.Color(255, 255, 255));
        secado30.setText("jLabel3");
        secado30.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        secado30.setOpaque(true);

        secado40.setBackground(new java.awt.Color(255, 255, 255));
        secado40.setText("jLabel3");
        secado40.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        secado40.setOpaque(true);

        secado31.setBackground(new java.awt.Color(255, 255, 255));
        secado31.setText("jLabel3");
        secado31.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        secado31.setOpaque(true);

        secado32.setBackground(new java.awt.Color(255, 255, 255));
        secado32.setText("jLabel3");
        secado32.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        secado32.setOpaque(true);

        secado33.setBackground(new java.awt.Color(255, 255, 255));
        secado33.setText("jLabel3");
        secado33.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        secado33.setOpaque(true);

        jLabel34.setText("Cola Secado 4");

        secado41.setBackground(new java.awt.Color(255, 255, 255));
        secado41.setText("jLabel3");
        secado41.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        secado41.setOpaque(true);

        secado42.setBackground(new java.awt.Color(255, 255, 255));
        secado42.setText("jLabel3");
        secado42.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        secado42.setOpaque(true);

        secado43.setBackground(new java.awt.Color(255, 255, 255));
        secado43.setText("jLabel3");
        secado43.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        secado43.setOpaque(true);

        jLabel38.setText("Cola Secado Express");

        express0.setBackground(new java.awt.Color(255, 255, 255));
        express0.setText("jLabel3");
        express0.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        express0.setOpaque(true);

        express1.setBackground(new java.awt.Color(255, 255, 255));
        express1.setText("jLabel3");
        express1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        express1.setOpaque(true);

        express2.setBackground(new java.awt.Color(255, 255, 255));
        express2.setText("jLabel3");
        express2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        express2.setOpaque(true);

        express3.setBackground(new java.awt.Color(255, 255, 255));
        express3.setText("jLabel3");
        express3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        express3.setOpaque(true);

        express4.setBackground(new java.awt.Color(255, 255, 255));
        express4.setText("jLabel3");
        express4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        express4.setOpaque(true);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel38)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(express0, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(express1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(express2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(express3, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(express4, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(33, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(233, Short.MAX_VALUE)
                .addComponent(jLabel38)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(express0, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(express1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(express2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(express3, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(express4, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(209, 209, 209))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(acceso0, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(acceso1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(acceso2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(acceso3, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(acceso4, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(acceso5, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(acceso6, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(acceso7, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(acceso8, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(acceso9, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(ColaAcceso))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(secado20, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(secado21, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(secado22, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(secado23, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel18)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel14)
                                    .addComponent(lavado0, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lavado1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lavado2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(secado10, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(secado11, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(secado12, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(secado13, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel23)
                            .addComponent(jLabel34)
                            .addComponent(jLabel28)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(secado30, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(secado31, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(secado32, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(secado33, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(secado40, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(secado41, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(secado42, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(secado43, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(ColaAcceso)
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(acceso0, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(acceso1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(acceso2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(acceso3, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(acceso4, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(acceso5, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(acceso6, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(acceso7, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(acceso8, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(acceso9, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addGap(5, 5, 5)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lavado0, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lavado1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lavado2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(secado10, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(secado11, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(secado12, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(secado13, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel23)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(secado20, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(secado21, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(secado22, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(secado23, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel28)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(secado30, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(secado31, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(secado32, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(secado33, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel34)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(secado40, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(secado41, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(secado42, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(secado43, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void horaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_horaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_horaActionPerformed

    private void adelantarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adelantarActionPerformed
        x.setMilis((float) 0.3);
        relojTimer.setDelay(300);
    }//GEN-LAST:event_adelantarActionPerformed

    private void saltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saltarActionPerformed
        x.setMilis((float) 0.001);
        relojTimer.setDelay(10);
    }//GEN-LAST:event_saltarActionPerformed

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
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Interfaz().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ColaAcceso;
    private javax.swing.JLabel acceso0;
    private javax.swing.JLabel acceso1;
    private javax.swing.JLabel acceso2;
    private javax.swing.JLabel acceso3;
    private javax.swing.JLabel acceso4;
    private javax.swing.JLabel acceso5;
    private javax.swing.JLabel acceso6;
    private javax.swing.JLabel acceso7;
    private javax.swing.JLabel acceso8;
    private javax.swing.JLabel acceso9;
    private javax.swing.JButton adelantar;
    private javax.swing.JLabel express0;
    private javax.swing.JLabel express1;
    private javax.swing.JLabel express2;
    private javax.swing.JLabel express3;
    private javax.swing.JLabel express4;
    private javax.swing.JTextField hora;
    private javax.swing.JLabel horasRestantes;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel lavado0;
    private javax.swing.JLabel lavado1;
    private javax.swing.JLabel lavado2;
    private javax.swing.JButton saltar;
    private javax.swing.JLabel secado10;
    private javax.swing.JLabel secado11;
    private javax.swing.JLabel secado12;
    private javax.swing.JLabel secado13;
    private javax.swing.JLabel secado20;
    private javax.swing.JLabel secado21;
    private javax.swing.JLabel secado22;
    private javax.swing.JLabel secado23;
    private javax.swing.JLabel secado30;
    private javax.swing.JLabel secado31;
    private javax.swing.JLabel secado32;
    private javax.swing.JLabel secado33;
    private javax.swing.JLabel secado40;
    private javax.swing.JLabel secado41;
    private javax.swing.JLabel secado42;
    private javax.swing.JLabel secado43;
    // End of variables declaration//GEN-END:variables
    private AutoLavado x;
    private int horaContador;
    private int minutos;
    private Timer relojTimer;
    private StringBuilder registroCarros;
    private JTextArea textArea;
}