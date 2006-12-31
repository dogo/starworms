/**
 * Worms Obstaculos  
 * 
 * @author Diogo Autilio 
 * @version 0.1-alpha 
 * Created (08/10/2006)
 * last update : 15/11/06
 */
import java.awt.*;

class Obstaculo {
   
    /**    *Guarda o Retângulo e a Cor deste que será Desenhado.   */       
    No Obst;
    /**    *Guarda o Retângulo a ser Desenha na Tela.   */       
    Rectangle rect;
    /**    *Vetor que guarda a cor dos Players em RGB.   */       
    int[] cor;
    
    /**
     * Desenha Retangulo com sua respectiva Cor.
    */    
    public void DesenhaRetangulo() {
        Obst = Main.ListaRect.BuscaLastRect();
        rect = Obst.Info();
        cor = Obst.Cor();
        Color corAleatoria = new Color(cor[0],cor[1],cor[2]);
        Main.Fase.novoForegroundColour(corAleatoria);
        Main.Fase.pinta(rect);
    }
    

}



