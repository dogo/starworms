import java.awt.*;
import javax.swing.ImageIcon;

public class DesenhaTela {
    private String nomeArq;
   
    /**
     * Retorna um vetor de Rectangle contendo a barra de Vida dos players.
     */
    public Rectangle[] Barra() {       
        Rectangle barra1 = new Rectangle(60, 35, Main.player1.SuaVida(),20);
        Rectangle barra2 = new Rectangle(60, 35, Main.player2.SuaVida(),20);
        Rectangle[] barras = new Rectangle[2];
        barras[0] = barra1;
        barras[1] = barra2;
        
        return barras;         
    }    
    /**
     * Desenha Player1 na Tela.
     */
    public void Player1(Graphics2D g) {       
        g.setColor(Color.orange);
        g.drawString(Main.player1.Nome(),Main.player1.Position()[0],Main.player1.Position()[1]-2);
        if(Main.player1.Lado())
            nomeArq = "images/player1_esq.gif" ; 
        else
            nomeArq = "images/player1_dir.gif";
            
        ImageIcon icon = new ImageIcon ( getClass().getResource(nomeArq));
        int[] size = new int[2];
        size[0] = icon.getIconHeight();
        size[1] = icon.getIconWidth();
        g.drawImage(icon.getImage(),Main.player1.Position()[0],Main.player1.Position()[1],null);            
        Rectangle contorno = new Rectangle(Main.player1.Position()[0],Main.player1.Position()[1],size[0],size[1]);
        g.setColor(Color.orange);
        g.draw(contorno);
    }
    
    /**
     * Desenha Player2 na Tela.
     */
    public void Player2(Graphics2D g){       
        g.setColor(Color.orange);
        g.drawString(Main.player2.Nome(),Main.player2.Position()[0],Main.player2.Position()[1]-2);
        if(Main.player2.Lado())
            nomeArq = "images/player2_esq.gif";
        else
            nomeArq = "images/player2_dir.gif";
            
        ImageIcon icon = new ImageIcon( getClass().getResource(nomeArq));
        int[] size = new int[2];
        size[0] = icon.getIconHeight();
        size[1] = icon.getIconWidth();
        g.drawImage(icon.getImage(),Main.player2.Position()[0],Main.player2.Position()[1],null);            
        Rectangle contorno = new Rectangle(Main.player2.Position()[0],Main.player2.Position()[1],size[0],size[1]);
        g.setColor(Color.orange);
        g.draw(contorno);
    }
    
    /**
     * Desenha os Retangulos na Tela.
     */    
    public void Retangulos(Graphics2D g) {
        No Obst = Main.ListaRect.BuscaFirstRect();       
        if(Obst!=null) {
            while(Obst!=null){           
                Rectangle rect = Obst.Info();
                int[] cor = Obst.Cor();
                Color corAleatoria = new Color(cor[0],cor[1],cor[2]);
                g.setColor(corAleatoria);
                g.fill(rect);
                Obst = Main.ListaRect.BuscaRect();      
            }
        }
    }
    
    /**
     * Retorna um vetor de String com o player que ira jogar, seu nome e o nome do adversario.
     */
    public String[] PlayerVez() {
        String[] pvez = new String[3];
        if(Main.player1.Vez()==true){
            pvez[0] = "player1";
            pvez[1] = Main.player1.Nome();
            pvez[2] = Main.player2.Nome();
        }
        else  {
            pvez[0] = "player2";
            pvez[1] = Main.player2.Nome();
            pvez[2] = Main.player1.Nome();            
        }
        return pvez;
    }
}
