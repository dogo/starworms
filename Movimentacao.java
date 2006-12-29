
public class Movimentacao extends Main {
    private int[] pos;
    private int Vo;
    private int ang;
    private double Vox;
    private double Voy;
    
    private Colisao andar = new Colisao();
    
    /**
     * Move o player pela Tela. 
     */
    public void movePlayer(String nomeArq, int px, int py) {
        if(player1.Vez()) {
            if(nomeArq.equals("player_esq.gif")){
                player1.MudaLado(true,false);
            }
            else {
                player1.MudaLado(false,true);
            }            
            pos = player1.Position();
            int x = pos[0]+px;
            int y = pos[1];
            double t = 0.1;
            int OrigemY = y;
            double g=10;
            if(andar.BatidaFrente(x,y,player1.Size()[0]+1,player1.Size()[1]+1)==false) {
                player1.NovaPosition(x,y);                
            }
            Fase.repinta();           
            while(andar.temChao(x,y,player1.Size()[0]+1,player1.Size()[1]+1)==false && andar.Paredao((int)x,(int)y,player1.Size()[0],player1.Size()[1])==false)
            {
                player1.NovaPosition(x,y);
                Fase.repinta();              
                y = (int)(OrigemY - (0-(g * (t*t))/2.0));
                t = t + 0.1;             
            }
            Fase.repinta(); 
        }
        else {
            if(nomeArq.equals("player_esq.gif")) {
                player2.MudaLado(true,false);
            }
            else {
                player2.MudaLado(false,true);
            }            
            pos = player2.Position();
            int x = pos[0]+px;
            int y = pos[1];
            double t = 0;
            int OrigemY = y;
            double g=10;
            if(andar.BatidaFrente(x,y,player2.Size()[0]+1,player2.Size()[1]+1)==false) {
                player2.NovaPosition(x,y);                
            }
            Fase.repinta();           
            while(andar.temChao(x,y,player2.Size()[0]+1,player2.Size()[1]+1)==false && andar.Paredao((int)x,(int)y,player2.Size()[0]+1,player2.Size()[1]+1)==false)
            {
                player2.NovaPosition(x,y);
                Fase.repinta();              
                y = (int)(OrigemY - (0-(g * (t*t))/2.0));
                t = t + 0.1;             
            }
        }
    }
    public void pulaPlayer() {     
        if(player1.Vez()) {
            pos = player1.Position();
            
            int OrigemX = pos[0];
            int OrigemY = pos[1];
            
            Vo = player1.Velocidade();
            ang = player1.Angulo();
            
            if(player1.Lado()) {
                ang = -1 * ang;
                Vo  = -1 * Vo;
            }
            
            Vox = Vo * Math.cos(Math.toRadians(ang));
            Voy = Vo * Math.sin(Math.toRadians(ang));

            double g=10;   // gravidade
            double t = 0.1;   // tempo
            double x,y;
            boolean batida=false;
            do{
                double deslocaY = Voy * t - (g * (t*t))/2.0;
                x = OrigemX + Vox * t;
                y = OrigemY - deslocaY;
                if(andar.BatidaFrente((int)x,(int)y,player1.Size()[0]+1,player1.Size()[1]+1)==true)
                {
                    batida=true;
                }
                else
                {
                    Fase.repinta();
                    Fase.espera(10);
                    player1.NovaPosition((int)x,(int)y);
                    t = t + 0.1;
                }
            }while(batida==false);
            pos = player1.Position();            
            OrigemX = pos[0];
            OrigemY = pos[1];
            t=0.1;
            x = OrigemX;
            y = OrigemY;
            while(andar.temChao((int)x,(int)y,player1.Size()[0],player1.Size()[1])==false && andar.Paredao((int)x,(int)y,player1.Size()[0],player1.Size()[1])==false)
            {
                player1.NovaPosition((int)x,(int)y);
                Fase.repinta();                
                Fase.espera(10);
                y = (int)(OrigemY - (0-(g * (t*t))/2.0));
                t = t + 0.1;
            }
        }
        else
        {
            pos = player2.Position();
            
            int OrigemX = pos[0];
            int OrigemY = pos[1];
            
            Vo = player2.Velocidade();
            ang = player2.Angulo();
            
            if(player2.Lado())
            {
                ang = -1 * ang;
                Vo  = -1 * Vo;
            }
            
            Vox = Vo * Math.cos(Math.toRadians(ang));
            Voy = Vo * Math.sin(Math.toRadians(ang));

            double g=10;   // gravidade
            double t = 0.1;   // tempo
            double x,y;
            boolean batida=false;
            do{
                double deslocaY = Voy * t - (g * (t*t))/2.0;
                x = OrigemX + Vox * t;
                y = OrigemY - deslocaY;
                if(andar.BatidaFrente((int)x,(int)y,player2.Size()[0],player2.Size()[1])==true)
                {
                    batida=true;
                }
                else
                {
                    Fase.repinta();
                    Fase.espera(10);
                    player2.NovaPosition((int)x,(int)y);
                    t = t + 0.1;
                }
            }while(batida==false);
            pos = player2.Position();            
            OrigemX = pos[0];
            OrigemY = pos[1];
            t=0.1;
            x = OrigemX;
            y = OrigemY;
            while(andar.temChao((int)x,(int)y,player2.Size()[0],player2.Size()[1])==false && andar.Paredao((int)x,(int)y,player2.Size()[0],player2.Size()[1])==false)
            {
                player2.NovaPosition((int)x,(int)y);
                Fase.repinta();                
                Fase.espera(10);
                y = (int)(OrigemY - (0-(g * (t*t))/2.0));
                t = t + 0.1;
            }
        }
    }    
    
    /**
     * Retorna true se o tempo do Turno do player acabou.
     */
    public boolean AcabaTempo(int t) {
        if(player1.Vez()==true) {
            if(t>=player1.Tempo()) {
                player1.MudaVez(false);
                player2.MudaVez(true);
                return true;
            }
            else {
                return false;
            }
        }
        else {
            if(t>=player2.Tempo()) {
                player2.MudaVez(false);
                player1.MudaVez(true);
                return true;
            }
            else {
                return false;
            }        
        }
    }
    
    /**
     * Retorna True se o jogo já acabou, quando um player morre.
     */
    public boolean GameOver() {
        if(player1.SuaVida()==0 || player2.SuaVida()==0)
            return true;
        return false;
    }
    
    /**
     * Retorna o tempo atual do player na jogada.
     */
    public int Atirou() {
        return player1.Tempo();
    }
    
    /**
     * Retorna uma String com player vencedor.
     */
    public String Winner() {
        if(player1.SuaVida()==0)
            return "player2";
        else
            return "player1";
    }    
}
