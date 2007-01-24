import java.awt.*;
import java.io.*;

/**    
 * Classe responsavel por ler arquivo onde esta a fase do jogo, 
 * chamar metodo processaLinha para carrega-la e depois chamar classe para arremessar a bola. 
 */
public class Engine extends Main{
    private int alt_larg;
    private int tam_linha;
    private String[] linhas;
    private int retangulos;
    Rectangle[] ListaRetangulos;
    
    public Engine(){
        retangulos = 0;
        Main.ListaRect = new ListaLigada();
        Main.player1 = new Players();
        Main.player2 = new Players();
    }   
    /**
     * Le o arquivo passado pelo usuario e chama o metodo para carregar a fase.
     */
    public void LeArq(FileReader leitor) throws IOException  {
        BufferedReader buffer = new BufferedReader(leitor);
        Main.Fase.LeArq(true);
        String linha = buffer.readLine();
            if(linha.equals("saved")){
            while(true) {               
                linha = buffer.readLine();
                if(linha==null)
                    break;
                GameSaved(linha);
     /**Para Debug do GameSaved**/
            //System.out.println(linha);
            //System.out.println("Aki Acaba o debug do GameSaved !");
            }
        }
            else {
                while(true) {            
                    if(linha==null)
                        break;
                        processaLinha(linha);
     /**Para Debug**/
            //System.out.println("Aki Comeca o debug do ProcessaLinha !");
            //System.out.println(linha);
            linha = buffer.readLine();            
            } 
        }
            Main.Obstaculos = new KDTree(Main.ListaRect,retangulos);           
    }
    /**
     * Classe responsavel por salvar o jogo.
     */
     public void GameSaved(String linha) {
        tam_linha = linha.length();
        linhas = new String[tam_linha];
        linhas = linha.split("\\s+");
        
        if(linhas[0].equals("tela")) {
            int x = Integer.parseInt(linhas[1]);
            int y = Integer.parseInt(linhas[2]);
            Main.Fase.novoSize(x,y);
        }
        else if(linhas[0].equals("playersize")) {
            int x = Integer.parseInt(linhas[1]);
            int y = Integer.parseInt(linhas[2]);
            Main.player1.NovoSize(x,y);
            Main.player2.NovoSize(x,y);
        }      
        else if(linhas[0].equals("hitpoints")) {
            int h = Integer.parseInt(linhas[1]);
            Main.player1.Durabilidade(h);
            Main.player2.Durabilidade(h);
        }  
        else if(linhas[0].equals("player1")) {
            int x = Integer.parseInt(linhas[1]);
            int y = Integer.parseInt(linhas[2]);
            Main.player1.NovaPosition(x,y);
            String vez = linhas[3];
            if(vez.equals("true"))
            	Main.player1.MudaVez(true);                
            Main.player1.Life(Integer.parseInt(linhas[4]));
            Main.Fase.Tempo(Integer.parseInt(linhas[5]));
        }
        else if(linhas[0].equals("player2")) {
            int x = Integer.parseInt(linhas[1]);
            int y = Integer.parseInt(linhas[2]);
            Main.player2.NovaPosition(x,y);
            String vez = linhas[3];
            if(vez.equals("true"))
            	Main.player2.MudaVez(true);                
            Main.player2.Life(Integer.parseInt(linhas[4]));
            Main.Fase.Tempo(Integer.parseInt(linhas[5]));
        } 
        else if(linhas[0].equals("turno")) {
            int t = Integer.parseInt(linhas[1]);
            Main.player1.Tempo(t);
            Main.player2.Tempo(t);
            
        }        
        else if(linhas[0].equals("projetil"))  {
            int m = Integer.parseInt(linhas[1]);
            alt_larg = Integer.parseInt(linhas[2]);
            Main.player1.MassaProjetil(m);
            Main.player1.Raio(alt_larg);
            System.out.println(alt_larg);
        }           
         else if(linhas[0].equals("rect")) {
            int x = Integer.parseInt(linhas[1]);
            int y = Integer.parseInt(linhas[2]);
            int m2 = Integer.parseInt(linhas[3]);
            cor = new int[3];
            cor[0] = Integer.parseInt(linhas[4]);
            cor[1] = Integer.parseInt(linhas[5]);
            cor[2] = Integer.parseInt(linhas[6]);
            Rectangle obst = new Rectangle(x,y,alt_larg,alt_larg);
            Main.ListaRect.Insere(obst,cor,m2);
            Main.ListaRect.Length(retangulos);
            Obstaculo Obst = new Obstaculo();
            Obst.DesenhaRetangulo();
            retangulos= retangulos + 1;
        }        
    }

    /**     *Recebe cada linha do TXT da fase, e em seguida verifica qual eh a instrucao dela, fazendo que a cada execucao carregue um
     *      pedaco da Tela do Jogo.
     */
    public void processaLinha(String linha) {
        tam_linha = linha.length();
        linha = linha.replace("/","");
        linhas = new String[tam_linha];
        linhas = linha.split("\\s+");
        
        if(linhas[0].equals("tela")) {
            int x = Integer.parseInt(linhas[1]);
            int y = Integer.parseInt(linhas[2]);
            Main.Fase.novoSize(x,y);
        }
        else if(linhas[0].equals("playersize")) {
            int x = Integer.parseInt(linhas[1]);
            int y = Integer.parseInt(linhas[2]);
            Main.player1.NovoSize(x,y);
            Main.player2.NovoSize(x,y);
        }        
        else if(linhas[0].equals("hitpoints")) {
            int h = Integer.parseInt(linhas[1]);
            Main.player1.Durabilidade(h);
            Main.player2.Durabilidade(h);
        }   
        else if(linhas[0].equals("player1")) {
            int x = Integer.parseInt(linhas[1]);
            int y = Integer.parseInt(linhas[2]);
            Main.player1.NovaPosition(x,y);
            Main.player1.MudaVez(true);
        }
        else if(linhas[0].equals("player2")) {
            int x = Integer.parseInt(linhas[1]);
            int y = Integer.parseInt(linhas[2]);
            Main.player2.NovaPosition(x,y);
        }
        else if(linhas[0].equals("turno")){
            int t = Integer.parseInt(linhas[1]);
            Main.player1.Tempo(t);
            Main.player2.Tempo(t);
            Main.Fase.Tempo(0);         
        }  
        else if(linhas[0].equals("projetil")) {
            int massa = Integer.parseInt(linhas[1]);
            alt_larg = Integer.parseInt(linhas[2]);
            Main.player1.MassaProjetil(massa);
            Main.player1.Raio(alt_larg);            
        }
        else if(linhas[0].equals("rect")) {
            int x = Integer.parseInt(linhas[1]);
            int y = Integer.parseInt(linhas[2]);
            int m2 = Integer.parseInt(linhas[3]);
            String hexcode = linhas[4];
            Main.player1.MassaRect(m2);
            ConverteCor Color = new ConverteCor();
            cor = Color.Converte(hexcode);
            Rectangle obst = new Rectangle(x,y,alt_larg,alt_larg);
            Main.ListaRect.Insere(obst,cor,m2);
            Main.ListaRect.Length(retangulos);            
            Obstaculo Obst = new Obstaculo();
            Obst.DesenhaRetangulo();
            retangulos= retangulos +1;
        }
    }
}
