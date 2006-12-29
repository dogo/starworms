/**
 * Worms Colisão  
 * 
 * @author Diogo Autilio.
 * @version 0.3.1-alpha 
 * Created (07/10/2006)
 * last update : 20/11/06
 */


import java.awt.*;
public class Colisao extends Main {
    /** Retorna True se o Projétil não atingiu o Retângulo, o Oponente e os Limites da Tela.   */ 
    public boolean Check(int x,int y,int AltProjetil,int distancia,Rectangle alvo, double VF, int Vo, int massa) {
        int TelaSizeX = Fase.telaX();
        int TelaSizeY = Fase.telaY();
        if(x > TelaSizeX || y > TelaSizeY || x < 0) {
            return false;
        }
        No Obst = ListaRect.BuscaFirstRect();
        int cont=0;
        while(Obst!=null) {
            cont++;
            if(Obst.Info().contains(x+distancia,y) || Obst.Info().contains(x+distancia,y+AltProjetil)) {
                double massas = massa+Obst.Massa();
                double projetil_dobro = 2*massa;
                double VR = projetil_dobro/massas*Math.abs(VF);
                double PorcVo = Math.abs(Vo) * 0.3;
                if(VR > PorcVo) {
                    ListaRect.Remove(Obst.Info());
                    return false;
                }
                return false;
            }
            Obst = ListaRect.BuscaRect();
        }
        if(alvo.contains(x+distancia,y) || alvo.contains(x+distancia,y+AltProjetil)){
            if(player1.Vez()) {
                player2.Dano();
            }
            else {
                player1.Dano();
           }
            return false;
        }
        return true;
    }
    /**
     * Retorna true se o player colidiu com algum obstáculo.
     */
    public boolean Paredao(int x, int y, int Alt, int Larg) {
        No Obst = ListaRect.BuscaFirstRect();
        while(Obst!=null)
        {
            if(Obst.Info().contains(x,y) || Obst.Info().contains(x+Larg,y) || Obst.Info().contains(x,y+Alt) || Obst.Info().contains(x+Larg,y+Alt))
            {
                return true;
            }
            Obst = ListaRect.BuscaRect();
        }
        int TelaSizeX = Fase.telaX();
        int TelaSizeY = Fase.telaY();        
        if(x+Larg >= TelaSizeX || y+Alt >= TelaSizeY || x < 0)
        {
            return true;
        }
        return false;
    }
    
    /**
     * Retorna True se o player bateu de frente com algum obstáculo.
     */
    public boolean BatidaFrente(int x, int y, int Alt, int Larg)    
    {
        int TelaSizeX = Fase.telaX();
        int TelaSizeY = Fase.telaY();  
        if(x + Larg > TelaSizeX || y > TelaSizeY || x < 0)
        {
            return true;
        }        
        No Obst = ListaRect.BuscaFirstRect();
        while(Obst!=null)
        {
            if(Obst.Info().contains(x,y) || Obst.Info().contains(x+Larg,y))
            {
                return true;
            }
            Obst = ListaRect.BuscaRect();
        }
        return false;
    }
    
    /**
     * Retorna true se o player chegou no chao da Tela.
     */
    public boolean temChao(int y)
    {
        if(y > (Fase.telaY()))
            return true;
        return false;
    }
    
    /**
     * Retorna True se o player está em cima de algum obstáculo.
     */
    public boolean temChao(int x, int y, int Alt, int Larg)    
    {
        if(y+Larg >= (Fase.telaY()))
            return true;
        No Obst = ListaRect.BuscaFirstRect();
        while(Obst!=null)
        {
            if(Obst.Info().contains(x+Larg,y+Alt) || Obst.Info().contains(x,y+Alt))
            {
                return true;
            }
            Obst = ListaRect.BuscaRect();
        }
        return false;            
    }      
}
