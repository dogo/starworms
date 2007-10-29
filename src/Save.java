/**    
 * Le o arquivo onde esta a fase do jogo.
 */
import java.awt.*;
import java.io.*;

public class Save {
    public void salvaFile(DataOutputStream file) throws IOException { //Salva o jogo com o nome de um arquivo passado pelo usuario.
        file.writeBytes("saved\n");
        file.writeBytes("tela "+Main.Fase.telaX()+" "+Main.Fase.telaY()+"\n");
        file.writeBytes("playersize "+Main.player1.Size()[0]+" "+Main.player1.Size()[1]+"\n");
        file.writeBytes("hitpoints "+Main.player1.Hitpoints()+"\n");    
        file.writeBytes("player1 "+Main.player1.Position()[0]+" "+Main.player1.Position()[1]+" "+Main.player1.Vez()+" "+Main.player1.SuaVida()+" "+Main.Fase.Tempo()+"\n");
        file.writeBytes("player2 "+Main.player2.Position()[0]+" "+Main.player2.Position()[1]+" "+Main.player2.Vez()+" "+Main.player2.SuaVida()+" "+Main.Fase.Tempo()+"\n");        
        file.writeBytes("turno "+Main.player1.Tempo()+"\n");   
        file.writeBytes("projetil "+Main.player1.MassaProjetil()+" "+Main.player1.Raio()+"\n");
        file.writeBytes("lado "+Main.ListaRect.Lado()+"\n");   
        
        No Obst = Main.ListaRect.BuscaFirstRect();
        
        while(Obst!=null) {
            Rectangle rect = Obst.Info();
            int[] cor = Obst.Cor();
            file.writeBytes("rect "+rect.x+" "+rect.y+" "+Obst.Massa()+" "+cor[0]+" "+cor[1]+" "+cor[2]+"\n"); 
            Obst = Main.ListaRect.BuscaRect();
        }        
        file.close();
    }
}
