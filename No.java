import java.awt.*;
/**
 * 
 */
public class No{

    private Rectangle info;
    private int[] rgb = {0,0,0};
    private No prox;
    private int massa;
    
    // construtores
    /**    *Constrói um No.   */ 
    public No()
    {
        info = new Rectangle();
        prox = null;
        massa=0;
//         rgb = null;
    }
    /**    *Constrói um No com um Retângulo e uma Cor.   */     
    public No (Rectangle x, int[] cor, int m){
        info = x;
        rgb = cor;
        massa = m;
        prox = null;
    }		

    // metodos de acesso	
    /**    *Retorna o Retângulo do No.   */ 
    public Rectangle Info(){
        return info;
    }		
    /**    *Retorna a Cor do No.   */     
    public int[] Cor(){
        return rgb;
    }		   
    /**    *Retorna a Massa do No.   */     
    public int Massa(){
        return massa;
    }		    
    /**    *Retorna o No seguinte.   */     
    public No Prox(){
        return prox;
    }		
    // metodo modificador	
    /**    *Reescre o Retângulo do No.   */ 
    public void NovoInfo(Rectangle x)
    {
        info = x;
    }
    /**    *Reescreve o No seguinte de um No.   */ 
    public void NovoProx(No x)
    {
        prox = x;
    }
    /**    *Reescreve a Cor do No.   */ 
    public void NovaCor(int[] cor)
    {
        rgb = cor;
    }    
    /**    *Reescreve a Massa do No.   */ 
    public void NovaMassa(int m)
    {
        massa = m;
    }        
}