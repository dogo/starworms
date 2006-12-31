import java.awt.*;

public class KDTree {
    private Rectangle info;
    
   public KDTree(ListaLigada ListaRect, int N) {
       // initialise instance variables
       Rectangle[] ListaRetangulos = new Rectangle[N];
       No Obst = ListaRect.BuscaFirstRect();
       int i=0;
       while(Obst!=null)
       {
           ListaRetangulos[i] = Obst.Info();
           Obst = ListaRect.BuscaRect();
           i++;
       }
       MergeSortRect Lista_Ordenada = new MergeSortRect();
       ListaRetangulos = Lista_Ordenada.sort(ListaRetangulos);
       NovoInfo(ListaRetangulos[N/2]);
   }
    
    public void NovoInfo(Rectangle rect)
    {
        info = rect;
//         System.out.println("Info = "+info);
    }
    
}
