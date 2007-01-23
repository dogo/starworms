/**
 * 
 */
public class ConverteCor {

    /**    *Guarda a quantidade de Cor Vermelha do Retangulo.   */       
    int r;
    /**    *Guarda a quantidade de Cor Verde do Retangulo.   */  
    int g;
    /**    *Guarda a quantidade de Cor Azul do Retangulo.   */  
    int b;
    /**    *Vetor que guarda a cor dos Players em RGB.   */      
    int[] rgb = new int[3];
    
    /**    *Converte uma String com uma cor em Hexadecial para RGB.   */  
    public int[] Converte(String hexcode) {

        try // tenta converter os valores embutidos na string
        {
            // Converteremos para short (podemos ter valores maiores que 127) e 
            // depois para double, na faixa 0-1.
            short tempR = Short.parseShort(hexcode.substring(1,3),16); // RR
            r = tempR;
            short tempG = Short.parseShort(hexcode.substring(3,5),16); // GG
            g = tempG;
            short tempB = Short.parseShort(hexcode.substring(5,7),16); // BB
            b = tempB;
        }
         // Caso tenha sido impossivel converter de hexadecimal para short, 
        catch (NumberFormatException nfe)  {
            r = g = b = 0;
        }
        rgb[0] = r;
        rgb[1] = g;
        rgb[2] = b;
        return rgb;
    }
}
