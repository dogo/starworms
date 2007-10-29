/**
 * Worms Read File for Engine  
 * 
 * @author Diogo Autilio 
 * @version 0.1-alpha 
 * Created (03/09/2006)
 * last update : 15/11/06
 */
public class Main {
    /**    *Tela do Jogo.   */
    public static Tela Fase; 
    /**    *Armazena todos os obstaculos do jogo     */
    public static ListaLigada ListaRect;    
    /**    *Vetor que guarda as cores.   */        
    int[] cor;
    /**    *Representa o Player 1.   */           
    public static Players player1;
    /**    *Representa o Player 2.   */    
    public static Players player2;
    /**    *Armazena todos os obstaculos do jogo    */
    public static KDTree Obstaculos ;
    
    public static void main(String[] args){
        Fase = new Tela();
    }
   
}
