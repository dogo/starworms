import java.awt.*;
import java.io.*;

/**    
 * Classe respons�vel por ler arquivo onde est� a fase do jogo, chamar m�todo processaLinha para carreg�-la e depois chamar classe para arremessar a bola.
 */
public class Engine extends Main {
    private int alt_larg;
    private int tam_linha;
    private String[] linhas;
    private int retangulos;
    Rectangle[] ListaRetangulos;
    
    public Engine(){
        retangulos = 0;
        ListaRect = new ListaLigada();
        player1 = new Players();
        player2 = new Players();
    }   
    /**
     * Le o arquivo passado pelo usu�rio e chama o m�todo para carregar a fase.
     */
    public void LeArq(FileReader leitor) throws IOException  {
        BufferedReader buffer = new BufferedReader(leitor);
        Fase.LeArq(true);
        String linha = buffer.readLine();
            if(linha.equals("saved")){
            while(true) {               
                linha = buffer.readLine();
                if(linha==null)
                    break;
                GameSaved(linha);
            /**PAra Debug do GameSaved**/
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
            //System.out.println("Aki Começa o debug do ProcessaLinha !");
            //System.out.println(linha);
            linha = buffer.readLine();            
            } 
        }
            Obstaculos = new KDTree(ListaRect,retangulos);           
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
            Fase.novoSize(x,y);
        }
        else if(linhas[0].equals("playersize")) {
            int x = Integer.parseInt(linhas[1]);
            int y = Integer.parseInt(linhas[2]);
            player1.NovoSize(x,y);
            player2.NovoSize(x,y);
        }      
        else if(linhas[0].equals("hitpoints")) {
            int h = Integer.parseInt(linhas[1]);
            player1.Durabilidade(h);
            player2.Durabilidade(h);
        }  
        else if(linhas[0].equals("player1")) {
            int x = Integer.parseInt(linhas[1]);
            int y = Integer.parseInt(linhas[2]);
            player1.NovaPosition(x,y);
            String vez = linhas[3];
            if(vez.equals("true"))
                player1.MudaVez(true);                
            player1.Life(Integer.parseInt(linhas[4]));
            Fase.Tempo(Integer.parseInt(linhas[5]));
        }
        else if(linhas[0].equals("player2")) {
            int x = Integer.parseInt(linhas[1]);
            int y = Integer.parseInt(linhas[2]);
            player2.NovaPosition(x,y);
            String vez = linhas[3];
            if(vez.equals("true"))
                player2.MudaVez(true);                
            player2.Life(Integer.parseInt(linhas[4]));
            Fase.Tempo(Integer.parseInt(linhas[5]));
        } 
        else if(linhas[0].equals("turno")) {
            int t = Integer.parseInt(linhas[1]);
            player1.Tempo(t);
            player2.Tempo(t);
            
        }        
        else if(linhas[0].equals("projetil"))  {
            int m = Integer.parseInt(linhas[1]);
            alt_larg = Integer.parseInt(linhas[2]);
            player1.MassaProjetil(m);
            player1.Raio(alt_larg);
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
            ListaRect.Insere(obst,cor,m2);
            ListaRect.Length(retangulos);
            Obstaculo Obst = new Obstaculo();
            Obst.DesenhaRetangulo();
            retangulos= retangulos + 1;
        }        
    }

    /**     *Recebe cada linha do TXT da fase, e em seguida verifica qual � a instru��o dela, fazendo que a cada execu��o carregue um
     *      peda�o da Tela do Jogo.
     */
    public void processaLinha(String linha) {
        tam_linha = linha.length();
        linha = linha.replace("/","");
        linhas = new String[tam_linha];
        linhas = linha.split("\\s+");
        
        if(linhas[0].equals("tela")) {
            int x = Integer.parseInt(linhas[1]);
            int y = Integer.parseInt(linhas[2]);
            Fase.novoSize(x,y);
        }
        else if(linhas[0].equals("playersize")) {
            int x = Integer.parseInt(linhas[1]);
            int y = Integer.parseInt(linhas[2]);
            player1.NovoSize(x,y);
            player2.NovoSize(x,y);
        }        
        else if(linhas[0].equals("hitpoints")) {
            int h = Integer.parseInt(linhas[1]);
            player1.Durabilidade(h);
            player2.Durabilidade(h);
        }   
        else if(linhas[0].equals("player1")) {
            int x = Integer.parseInt(linhas[1]);
            int y = Integer.parseInt(linhas[2]);
            player1.NovaPosition(x,y);
            player1.MudaVez(true);
        }
        else if(linhas[0].equals("player2")) {
            int x = Integer.parseInt(linhas[1]);
            int y = Integer.parseInt(linhas[2]);
            player2.NovaPosition(x,y);
        }
        else if(linhas[0].equals("turno")){
            int t = Integer.parseInt(linhas[1]);
            player1.Tempo(t);
            player2.Tempo(t);
            Fase.Tempo(0);         
        }  
        else if(linhas[0].equals("projetil")) {
            int massa = Integer.parseInt(linhas[1]);
            alt_larg = Integer.parseInt(linhas[2]);
            player1.MassaProjetil(massa);
            player1.Raio(alt_larg);            
        }
        else if(linhas[0].equals("rect")) {
            int x = Integer.parseInt(linhas[1]);
            int y = Integer.parseInt(linhas[2]);
            int m2 = Integer.parseInt(linhas[3]);
            String hexcode = linhas[4];
            player1.MassaRect(m2);
            ConverteCor Color = new ConverteCor();
            cor = Color.Converte(hexcode);
            Rectangle obst = new Rectangle(x,y,alt_larg,alt_larg);
            ListaRect.Insere(obst,cor,m2);
            ListaRect.Length(retangulos);            
            Obstaculo Obst = new Obstaculo();
            Obst.DesenhaRetangulo();
            retangulos= retangulos +1;
        }
    }
}
