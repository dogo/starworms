
public class Movimentacao {
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
        if(Main.player1.Vez()) {
            if(nomeArq.equals(Main.PLAYER_LEFT)){
            	Main.player1.MudaLado(true,false);
            }
            else {
            	Main.player1.MudaLado(false,true);
            }            
            pos = Main.player1.Position();
            int x = pos[0]+px;
            int y = pos[1];
            double t = 0.1;
            int OrigemY = y;
            double g=10;
            if(andar.BatidaFrente(x,y,Main.player1.Size()[0]+1,Main.player1.Size()[1]+1)==false) {
            	Main.player1.NovaPosition(x,y);                
            }
            Main.Fase.repinta();           
            while(andar.temChao(x,y,Main.player1.Size()[0]+1,Main.player1.Size()[1]+1)==false && andar.Paredao((int)x,(int)y,Main.player1.Size()[0],Main.player1.Size()[1])==false)
            {
            	Main.player1.NovaPosition(x,y);
            	Main.Fase.repinta();              
                y = (int)(OrigemY - (0-(g * (t*t))/2.0));
                t = t + 0.1;             
            }
            Main.Fase.repinta(); 
        }
        else {
            if(nomeArq.equals(Main.PLAYER_LEFT)) {
            	Main.player2.MudaLado(true,false);
            }
            else {
            	Main.player2.MudaLado(false,true);
            }            
            pos = Main.player2.Position();
            int x = pos[0]+px;
            int y = pos[1];
            double t = 0;
            int OrigemY = y;
            double g=10;
            if(andar.BatidaFrente(x,y,Main.player2.Size()[0]+1,Main.player2.Size()[1]+1)==false) {
            	Main.player2.NovaPosition(x,y);                
            }
            Main.Fase.repinta();           
            while(andar.temChao(x,y,Main.player2.Size()[0]+1,Main.player2.Size()[1]+1)==false && andar.Paredao((int)x,(int)y,Main.player2.Size()[0]+1,Main.player2.Size()[1]+1)==false)
            {
            	Main.player2.NovaPosition(x,y);
            	Main.Fase.repinta();              
                y = (int)(OrigemY - (0-(g * (t*t))/2.0));
                t = t + 0.1;             
            }
        }
    }
    public void pulaPlayer() {     
        if(Main.player1.Vez()) {
            pos = Main.player1.Position();
            
            int OrigemX = pos[0];
            int OrigemY = pos[1];
            
            Vo = Main.player1.Velocidade();
            ang = Main.player1.Angulo();
            
            if(Main.player1.Lado()) {
                ang = -1 * ang;
                Vo  = -1 * Vo;
            }
            
            Vox = Vo * Math.cos(Math.toRadians(ang));
            Voy = Vo * Math.sin(Math.toRadians(ang));

            double g=9.8;   // gravidade
            double t = 0.1;   // tempo
            double x,y;
            boolean batida=false;
            do{
                double deslocaY = Voy * t - (g * (t*t))/2.0;
                x = OrigemX + Vox * t;
                y = OrigemY - deslocaY;
                if(andar.BatidaFrente((int)x,(int)y,Main.player1.Size()[0]+1,Main.player1.Size()[1]+1)==true)
                {
                    batida=true;
                }
                else
                {
                	Main.Fase.repinta();
                	Main.Fase.espera(10);
                	Main.player1.NovaPosition((int)x,(int)y);
                    t = t + 0.1;
                }
            }while(batida==false);
            pos = Main.player1.Position();            
            OrigemX = pos[0];
            OrigemY = pos[1];
            t=0.1;
            x = OrigemX;
            y = OrigemY;
            while(andar.temChao((int)x,(int)y,Main.player1.Size()[0],Main.player1.Size()[1])==false && andar.Paredao((int)x,(int)y,Main.player1.Size()[0],Main.player1.Size()[1])==false)
            {
            	Main.player1.NovaPosition((int)x,(int)y);
            	Main.Fase.repinta();                
                Main.Fase.espera(10);
                y = (int)(OrigemY - (0-(g * (t*t))/2.0));
                t = t + 0.1;
            }
        }
        else
        {
            pos = Main.player2.Position();
            
            int OrigemX = pos[0];
            int OrigemY = pos[1];
            
            Vo = Main.player2.Velocidade();
            ang = Main.player2.Angulo();
            
            if(Main.player2.Lado())
            {
                ang = -1 * ang;
                Vo  = -1 * Vo;
            }
            
            Vox = Vo * Math.cos(Math.toRadians(ang));
            Voy = Vo * Math.sin(Math.toRadians(ang));

            double g=9.8;   // gravidade
            double t = 0.1;   // tempo
            double x,y;
            boolean batida=false;
            do{
                double deslocaY = Voy * t - (g * (t*t))/2.0;
                x = OrigemX + Vox * t;
                y = OrigemY - deslocaY;
                if(andar.BatidaFrente((int)x,(int)y,Main.player2.Size()[0],Main.player2.Size()[1])==true)
                {
                    batida=true;
                }
                else
                {
                	Main.Fase.repinta();
                	Main.Fase.espera(10);
                	Main.player2.NovaPosition((int)x,(int)y);
                    t = t + 0.1;
                }
            }while(batida==false);
            pos = Main.player2.Position();            
            OrigemX = pos[0];
            OrigemY = pos[1];
            t=0.1;
            x = OrigemX;
            y = OrigemY;
            while(andar.temChao((int)x,(int)y,Main.player2.Size()[0],Main.player2.Size()[1])==false && andar.Paredao((int)x,(int)y,Main.player2.Size()[0],Main.player2.Size()[1])==false)
            {
            	Main.player2.NovaPosition((int)x,(int)y);
            	Main.Fase.repinta();                
            	Main.Fase.espera(10);
                y = (int)(OrigemY - (0-(g * (t*t))/2.0));
                t = t + 0.1;
            }
        }
    }    
    
    /**
     * Retorna true se o tempo do Turno do player acabou.
     */
    public boolean AcabaTempo(int t) {
        if(Main.player1.Vez()==true) {
            if(t>=Main.player1.Tempo()) {
            	Main.player1.MudaVez(false);
            	Main.player2.MudaVez(true);
                return true;
            }
            else {
                return false;
            }
        }
        else {
            if(t>=Main.player2.Tempo()) {
            	Main.player2.MudaVez(false);
            	Main.player1.MudaVez(true);
                return true;
            }
            else {
                return false;
            }        
        }
    }
    
    /**
     * Retorna True se o jogo ja acabou, quando um player morre.
     */
    public boolean GameOver() {
        if(Main.player1.SuaVida()==0 || Main.player2.SuaVida()==0)
            return true;
        return false;
    }
    
    /**
     * Retorna o tempo atual do player na jogada.
     */
    public int Atirou() {
        return Main.player1.Tempo();
    }
    
    /**
     * Retorna uma String com player vencedor.
     */
    public String Winner() {
        if(Main.player1.SuaVida()==0)
            return "player2";
        else
            return "player1";
    }    
}
