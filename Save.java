
/**    
 * Le o arquivo onde est� a fase do jogo.
 */
import java.awt.*;
import java.io.*;

public class Save extends Main {
    public void salvaFile(DataOutputStream file) throws IOException { //Salva o jogo com o nome de um arquivo passado pelo usu�rio.
        file.writeBytes("saved\n");
        file.writeBytes("tela "+Fase.telaX()+" "+Fase.telaY()+"\n");
        file.writeBytes("playersize "+player1.Size()[0]+" "+player1.Size()[1]+"\n");
        file.writeBytes("hitpoints "+player1.Hitpoints()+"\n");    
        file.writeBytes("player1 "+player1.Position()[0]+" "+player1.Position()[1]+" "+player1.Vez()+" "+player1.SuaVida()+" "+Fase.Tempo()+"\n");
        file.writeBytes("player2 "+player2.Position()[0]+" "+player2.Position()[1]+" "+player2.Vez()+" "+player2.SuaVida()+" "+Fase.Tempo()+"\n");        
        file.writeBytes("turno "+player1.Tempo()+"\n");   
        file.writeBytes("projetil "+player1.MassaProjetil()+" "+player1.Raio()+"\n");
        file.writeBytes("lado "+ListaRect.Lado()+"\n");   
        
        No Obst = ListaRect.BuscaFirstRect();
        
        while(Obst!=null) {
            Rectangle rect = Obst.Info();
            int[] cor = Obst.Cor();
            file.writeBytes("rect "+rect.x+" "+rect.y+" "+Obst.Massa()+" "+cor[0]+" "+cor[1]+" "+cor[2]+"\n"); 
            Obst = ListaRect.BuscaRect();
        }        
        file.close();
    }
}
