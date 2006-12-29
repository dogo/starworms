/**
 * Worms Projetil  
 * 
 * @author Diogo Autilio baseado no codigo de Fabio Lubacheski.
 * @version 0.1-alpha 
 * Created (04/10/2006)
 * last update : 20/11/06
 */

import java.awt.*;
import javax.swing.ImageIcon; 

public class Projetil extends Main {
    
    /**    *Controla o Lançamento do Projétil do Player a partir de sua velocidade e o ângulo.   */ 
    public void LancaProjetil(int Vo, int ang) {
        Colisao Colide = new Colisao();
        ImageIcon projetil = new ImageIcon("projetil_1.gif");    
        int AltProjetil = projetil.getIconHeight()+1;
        int LargProjetil = projetil.getIconWidth()+1;
        int[] cord;
        int[] size;
        int distancia = LargProjetil;
        int OrigemX; // Origem do projétil sai da frente do player a Esquerda.
        int OrigemY;
        Rectangle alvo;
        if(player1.Vez()==true) {
            cord = player1.Position();
            size = player1.Size();
            OrigemX=cord[0]+size[0]; // Origem do projétil sai da frente do player Direita.
            OrigemY=cord[1];
            if(player1.Lado()==true) {
                OrigemX=cord[0]-distancia;
                Vo = -1 * Vo;
                ang = -1 * ang;
                distancia = 0;
            }
            alvo = new Rectangle(player2.Position()[0],player2.Position()[1],player2.Size()[0],player2.Size()[1]);
        }
        else {
            cord = player2.Position();
            size = player2.Size();
            OrigemX=cord[0]+size[0]; // Origem do projétil sai da frente do player Direita.
            OrigemY=cord[1];
            if(player2.Lado()==true) {
                OrigemX=cord[0]-distancia;
                Vo = -1 * Vo;
                ang = -1 * ang;
                distancia = 0;
            }
            alvo = new Rectangle(player1.Position()[0],player1.Position()[1],player1.Size()[0],player1.Size()[1]);
        }
        double g=10;   
        double t = 0.1;   
        double vf;
//        double vP;
        double Vox = Vo * Math.cos(Math.toRadians(ang));
        double Voy = Vo * Math.sin(Math.toRadians(ang));
        double x=OrigemX,y=OrigemY;     // posição na tela        
        int atrito = 8;
//        int m2 = player1.MassaRect();
        int massa = player1.MassaProjetil();
        do{
            /** 
             * Acha 'x' e o 'vx' 
             * Acha 'y' e o 'vy'
             */
            Vox = ((-atrito * Vox) /massa )* t + Vox;
            x = x + ((-(atrito*Vox)/massa)*t + Vox)*t;
                         
            Voy = ((-g)-((atrito*Voy)*(-Voy/Math.abs(Voy))/massa))*t + Voy;
            y = y - ((-g +((atrito*Voy)*(-Voy/Math.abs(Voy))/massa))*t + Voy)*t;          

            vf = Math.sqrt(Math.pow(Vox,2) + Math.pow(Voy,2));
//            vP = ((massa - m2)/(massa + m2)*vf);
            Fase.desenhaImage(projetil.getImage(),(int)x,(int)y);            
            t = t + 0.1;
            /**Apaga os prejeteis que ficaram para tras, ja foi calculado não deixando "rastro" do projetil**/ 
            Fase.repinta();
            Fase.espera(10);
        }
        while(Colide.Check((int)x,(int)y,AltProjetil,distancia,alvo,vf,Vo,massa));
    }
        

}
