import java.awt.*;
import javax.swing.ImageIcon;

public class DesenhaTela extends Main {
    private String nomeArq;
   
    /**
     * Retorna um vetor de Rectangle contendo a barra de Vida dos players.
     */
    public Rectangle[] Barra() {       
        Rectangle barra1 = new Rectangle(60, 35, player1.SuaVida(),20);
        Rectangle barra2 = new Rectangle(60, 35, player2.SuaVida(),20);
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
        g.drawString(player1.Nome(),player1.Position()[0],player1.Position()[1]-2);
        if(player1.Lado())
            nomeArq = "player1_esq.gif";
        else
            nomeArq = "player1_dir.gif";
            
        ImageIcon icon = new ImageIcon(nomeArq);
        int[] size = new int[2];
        size[0] = icon.getIconHeight();
        size[1] = icon.getIconWidth();
        g.drawImage(icon.getImage(),player1.Position()[0],player1.Position()[1],null);            
        Rectangle contorno = new Rectangle(player1.Position()[0],player1.Position()[1],size[0],size[1]);
        g.setColor(Color.orange);
        g.draw(contorno);
    }
    
    /**
     * Desenha Player2 na Tela.
     */
    public void Player2(Graphics2D g){       
        g.setColor(Color.orange);
        g.drawString(player2.Nome(),player2.Position()[0],player2.Position()[1]-2);
        if(player2.Lado())
            nomeArq = "player2_esq.gif";
        else
            nomeArq = "player2_dir.gif";
            
        ImageIcon icon = new ImageIcon(nomeArq);
        int[] size = new int[2];
        size[0] = icon.getIconHeight();
        size[1] = icon.getIconWidth();
        g.drawImage(icon.getImage(),player2.Position()[0],player2.Position()[1],null);            
        Rectangle contorno = new Rectangle(player2.Position()[0],player2.Position()[1],size[0],size[1]);
        g.setColor(Color.orange);
        g.draw(contorno);
    }
    
    /**
     * Desenha os Ret�ngulos na Tela.
     */    
    public void Retangulos(Graphics2D g) {
        No Obst = ListaRect.BuscaFirstRect();       
        if(Obst!=null) {
            while(Obst!=null){           
                Rectangle rect = Obst.Info();
                int[] cor = Obst.Cor();
                Color corAleatoria = new Color(cor[0],cor[1],cor[2]);
                g.setColor(corAleatoria);
                g.fill(rect);
                Obst = ListaRect.BuscaRect();      
            }
        }
    }
    
    /**
     * Retorna um vetor de String com o player que ir� jogar, seu nome e o nome do advers�rio.
     */
    public String[] PlayerVez() {
        String[] pvez = new String[3];
        if(player1.Vez()==true){
            pvez[0] = "player1";
            pvez[1] = player1.Nome();
            pvez[2] = player2.Nome();
        }
        else  {
            pvez[0] = "player2";
            pvez[1] = player2.Nome();
            pvez[2] = player1.Nome();            
        }
        return pvez;
    }
}
