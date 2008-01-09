/**
 * Worms Sound Class  
 * 
 * @author Diogo Autilio.
 * @version 0.3.1-alpha 
 * Created (28/04/2007)
 * last update : 09/01/08
 */

import java.applet.*;

public class Som extends Thread{
    private AudioClip som; 
    
    public void toca()
    {
        java.net.URL url = getClass().getResource("sounds/som1.wav");
        AudioClip som = Applet.newAudioClip(url);
        som.loop();
    }
   
    public void anda()
    {
        java.net.URL url = getClass().getResource("sounds/anda.wav");
        AudioClip som = Applet.newAudioClip(url);
        som.play();    
    }
    
    public void acerta()
    {
        java.net.URL url = getClass().getResource("sounds/ow.wav");
        AudioClip som = Applet.newAudioClip(url);
        som.play();    
    }
    
    public void pula()
    {
        java.net.URL url = getClass().getResource("sounds/jumpy.wav");
        AudioClip som = Applet.newAudioClip(url);
        som.play();    
    }
    
    public void explode()
    {
        java.net.URL url = getClass().getResource("sounds/exp.wav");
        AudioClip som = Applet.newAudioClip(url);
        som.play();    
    }    
    
    public void atira()
    {
        java.net.URL url = getClass().getResource("sounds/shot.wav");
        AudioClip som = Applet.newAudioClip(url);
        som.play();    
    }    
    
}
