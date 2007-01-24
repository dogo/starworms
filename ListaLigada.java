import java.awt.*;
/**
 * Controla Lista Encadeada 
 */
public class ListaLigada {
    private No cabeca;
    private No fim;
    private No aux;
    private int lado;
    private int tamanho;
    
    /**    *Constroi uma Lista Ligada de No com cabe�a.   */
    public ListaLigada() {
        cabeca = new No();
        fim = aux = cabeca;
    }
    
    /**    *Insere um Retangulo e sua respectiva Cor na Lista Ligada.   */
    public void Insere(Rectangle rect,int[] cor, int massa) {
        No novo_rect = new No(rect,cor,massa);
        if(ListaVazia())
            cabeca.NovoProx(novo_rect);
        fim.NovoProx(novo_rect);
        fim = novo_rect;
    }

    /**    *Busca e Retorna um No da Lista Lista Ligada.   */    
    public No BuscaRect(){
        
        No aux2 = aux.Prox();
        if(aux2==null) {       
            aux = cabeca;
            return null;
        }
        aux = aux.Prox();
        return aux2;
    }
    /**    *Busca e Retorna o primeiro No da Lista Lista Ligada.   */    
    public No BuscaFirstRect() {
        No aux2 = cabeca.Prox();
        if(aux2==null) {       
            aux = cabeca;
            return aux;
        }
        aux = cabeca.Prox();
        return aux2;
    }

    /**    *Busca e Retorna o ultimo No da Lista Lista Ligada.   */        
    public No BuscaLastRect()  {
        return fim;
    }      

    /**    *Recebe um Retangulo e Remove o No da Lista Ligada que contem ele.   */        
    public void Remove(Rectangle r) {
        No aux2 = cabeca.Prox();
        aux = cabeca;
        while(aux2.Info()!=r) {
            if(aux2.Prox()==null) {       
                return;
            }   
            aux2 = aux2.Prox();
            aux = aux.Prox();              
        }
        if(aux2.Prox()==null)
            aux.NovoProx(null);
        else
            aux.NovoProx(aux2.Prox());
        aux = cabeca;
    }    
    
    /**    *Retorna True se Lista estiver Vazia e False caso contrario.   */ 
    public boolean ListaVazia() {
        if(cabeca.Prox()==null)
            return true;
        return false;
    }
    
    /**
     * Guarda o tamanho do Obstaculo.
     */
    public void Lado(int l) {
        lado=l;
    }
    
    /**
     * Retorna o tamanho do Obstaculo.
     */
    public int Lado() {
        return lado;
    }

    /**
     * Guarda o tamanho da Lista.
     */
    public void Length(int L) {
        tamanho=L;
    }    
    
    /**
     * Retorna o tamanho da Lista.
     */
    public int Length() {
        return tamanho;
    }       
}



