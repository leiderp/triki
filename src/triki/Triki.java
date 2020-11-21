
package triki;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;


public class Triki extends JFrame implements ActionListener,MouseListener {
   JButton iniciar;
   JButton tablero [][];
   String jug1,jug2;
   int turno = -1;
   JLabel mensaje;
   Color colorB;
   JLabel nuevo;
   
   public Triki(){
    this.setLayout(null);
    mensaje = new JLabel("Bienvenidos");
    mensaje.setBounds(180, 40, 200, 30);
    this.add(mensaje);
    iniciar = new JButton("Iniciar Juego");
    iniciar.setBounds(175, 350, 150, 30);
    iniciar.addActionListener(this);
    this.add(iniciar);
    tablero = new JButton[3][3];
       for (int i = 0; i < tablero.length; i++) {
           for (int j = 0; j < tablero.length; j++) {
               tablero[i][j]= new JButton();
               tablero[i][j].setBounds((i+1)*80+50, (j+1)*80, 80, 80);
               this.add(tablero[i][j]);
               tablero[i][j].addActionListener(this);
               tablero[i][j].addMouseListener((MouseListener) this);
           }
       }
       colorB = tablero[0][0].getBackground();
   }
    public static void main(String[] args) {
        Triki ventana = new Triki();
        ventana.setDefaultCloseOperation(3);
        ventana.setSize(500,550);
        ventana.setLocationRelativeTo(null);
        ventana.setResizable(false);
        ventana.setTitle("Triki Triki");
       ventana.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
         if (evento.getSource()==iniciar) {
            turno =0;//Para indicar que es el turno del jugador 1
            JOptionPane.showMessageDialog(this,"Iniciando el Juego");
            jug1 = JOptionPane.showInputDialog(this,"Ingrese el nombre del Jugador 1");
            jug2 = JOptionPane.showInputDialog(this,"Ingrese el nombre del Jugador 2");
            mensaje.setText("Turno del Jugador: "+jug1);
            iniciar.setText("Reiniciar");
            limpiar();
        }else{
             JButton boton = (JButton)evento.getSource();
             if (turno==0) {
                 if (boton.getText().equals("")) {
                     boton.setBackground(Color.BLUE);
                     boton.setText("X");
                     boton.setFont(new Font ("Italic", Font.BOLD, 30));
                     boton.setEnabled(false);
                     boton.setForeground(Color.ORANGE);
                     verificar();
                     turno = 1;
                    mensaje.setText("Turno del Jugador: "+jug2);
                 }
             }else if(turno==1){
                    boton.setBackground(Color.GREEN);
                    boton.setText("O");
                     boton.setFont(new Font ("Italic", Font.BOLD, 30));
                     boton.setEnabled(false);
                     boton.setForeground(Color.ORANGE);
                     verificar();
                    turno=0;
                    mensaje.setText("Turno del Jugador: "+jug1);
             }
         }
    }
    
    public void limpiar(){
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero.length; j++) {
                tablero[i][j].setText("");
                tablero[i][j].setBackground(colorB);
                tablero[i][j].setEnabled(true);
            }
            
        }
    }
    
    public void verificar(){
        if (comparar("X")) {
            bloquear();
            JOptionPane.showMessageDialog(this, "Felicidades Jugador: "+jug1+" Ganaste!!", "GANADOR!!", JOptionPane.INFORMATION_MESSAGE, new ImageIcon(getClass().getResource("/images/ganador.jpg")));
        }else if(comparar("O")){
        bloquear();
        JOptionPane.showMessageDialog(this, "Felicidades Jugador: "+jug2+" Ganaste!!", "GANADOR!!", JOptionPane.INFORMATION_MESSAGE, new ImageIcon(getClass().getResource("/images/ganador.jpg")));
        }
    }
    
    public boolean comparar(String simbolo){
        for (int i = 0; i < tablero.length; i++) {
            if(tablero[0][i].getText().equals(simbolo) && tablero[1][i].getText().equals(simbolo) && tablero[2][i].getText().equals(simbolo)){
                return true;
            }
            if(tablero[i][0].getText().equals(simbolo)&& tablero[i][1].getText().equals(simbolo)&&tablero[i][2].getText().equals(simbolo)){
                return true;
            } 
        }
        if(tablero[0][0].getText().equals(simbolo)&& tablero[1][1].getText().equals(simbolo)&&tablero[2][2].getText().equals(simbolo)){
                return true;
            }
         if(tablero[0][2].getText().equals(simbolo)&& tablero[1][1].getText().equals(simbolo)&&tablero[2][0].getText().equals(simbolo)){
                return true;
            }
            return false;
    }
    
    public void bloquear(){
         for (int i = 0; i < tablero.length; i++) {
                for (int j = 0; j < tablero.length; j++) {
                    tablero[i][j].setEnabled(false);
                }
            }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
         //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mousePressed(MouseEvent e) {
         //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent evento) {
        JButton buton = (JButton)evento.getSource();
        buton.setBackground(Color.ORANGE);
         //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent evento) {
         JButton buton = (JButton)evento.getSource();
        buton.setBackground(colorB);
        pintar(); //To change body of generated methods, choose Tools | Templates.
    }
     public void pintar(){
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero.length; j++) {
                if(tablero[i][j].getText().equals("X")){
                    tablero[i][j].setBackground(Color.BLUE);
                }else if(tablero[i][j].getText().equals("O")){
                    tablero[i][j].setBackground(Color.GREEN);
                }
            }
        }
     }
}
