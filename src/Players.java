/**
 * Worms Players Engine  
 * 
 * @author Diogo Autilio 
 * @version 0.1-alpha 
 * Created (03/09/2006)
 * last update : 15/11/06
 */

import java.awt.*;
import javax.swing.ImageIcon;

class Players { 

        private String nome;
        private boolean vez;
        private boolean player_dir;        
        private boolean player_esq;
        private int velocidade;
        private int angulo; 
        private int[] size;
        private int[] pos = new int[2];
        private int Life;
        private int tempo;        
        private int hitpoints;
        private int Dano;
        private int massa=40;
        private int m2=20;
        private int thunder;
        
        public Players() {
            massa=0;
            player_dir = false;
            player_esq = false;
            pos = new int[2];
            nome="";   
            vez = false;
            size = new int[2];
            Life = 50;
            tempo=0;
            hitpoints=0;
            Dano=0;
        }
       
        public void Erase() {
            Main.Fase.apagaString(nome,pos[0],pos[1]-2);
            Rectangle cont = new Rectangle(pos[0],pos[1],size[0],size[1]);
            Main.Fase.novoForegroundColour(Color.white);
            Main.Fase.pinta(cont);
            Main.Fase.desenha(cont);            
        }
            
        public void Draw(int pos_x, int pos_y) {
            pos[0] = pos_x;
            pos[1] = pos_y;
            String nomeArq="";
            int OvalY;
            int OvalX;
            if(player_esq==true) { /**Se o personagem esta na direita, ele inverte o lado da saida do tiro**/ 
                nomeArq= "player_dir.gif";
                OvalX = pos_x + size[0]+5;
                OvalY = pos_y + size[1];
            }
            else {
                OvalX = pos_x - 5;
                OvalY = pos_y + size[1];
                nomeArq= "player_esq.gif";
            }
            ImageIcon icon = new ImageIcon(nomeArq);
            Main.Fase.desenhaImage(icon.getImage(),pos[0],pos[1],size[0],size[1]);
            Rectangle contorno = new Rectangle(pos[0],pos[1],size[0],size[1]);
            Main.Fase.novoForegroundColour(Color.orange);
            Main.Fase.desenhaString(nome,pos[0],pos[1]-2);
            Main.Fase.desenha(contorno);
            Main.Fase.desenhaOval(OvalX,OvalY,30,30);
        }
        /**
         * Retorna a velocidade do player em seu pulo.
         */
        public int Velocidade() {
            return velocidade;
        }

        /**
         * Retorna o angulo do player em seu pulo.
         */
        public int Angulo() {
            return angulo;
        }
        
        /**
         * Altera a vez de jogada do Player.
         */
        public void MudaVez(boolean x) {
            vez = x;
        }
        
        /**
         * Retorna true se for a Vez do player e false caso contrario.
         */
        public boolean Vez() {
            return vez;
        }    
        /**
         * Retorna a posicao do player na tela.
         */        
        public int[] Position(){           
            return pos;
        }

        /**
         * Retorna o tamanho do player.
         */        
        public int[] Size(){           
            return size;
        }        
        
        /**
         * Retorna o nome do player.
         */
        public String Nome() {
            return nome;
        }        
        
        /**
         * Retorna o true se o player esta virado para a esquera e false se esta virado para a direita.
         */
        public boolean Lado(){
            return player_esq==true;
        }                
        
        /**
         * Retorna o lado do player.
         */
        public void MudaLado(boolean esq, boolean dir){
            player_dir = dir;
            player_esq = esq;            
        }  
        /**
         * Insere posicao do player
         */
        public void NovaPosition(int x,int y){
            pos[0] = x;
            pos[1] = y;
        }         
        /**
         * Insere tamanho do player
         */
        public void NovoSize(int x,int y){
            size[0] = x;
            size[1] = y;
        }    
        
        /**
         * Insere vida ao player
         */
        public void Life(int l) {
            Life = l;
        }  
        
        /**
         * Tira vida do player
         */
        public void Dano(){
            Life = Life - Dano;
        }
         /**
         * Insere a quantidade de tempo do player em cada turno
         */        
        public void Tempo(int t){
            tempo=t;
        }        
        /**
         * Retorna a quantidade de tempo do player
         */        
        public int Tempo() {
            return tempo;
        }
        /**
         * Insere a quantidade de hitpoints do player. Quantidade suportada de Tiros.
         */        
        public void Durabilidade(int h) {
            hitpoints = h;
            Dano = ((100/h)*Life)/100;
        }  
        /**
         * Retorna a quantidade de vida do player
         */
        public int SuaVida() {
            return Life;
        }
        /**
         * Retorna a quantidade de hitpoints do player. Quantidade suportada de Tiros.
         */        
        public int Hitpoints() {
            return hitpoints;
        }             
        /**
         * Insere a quantidade de massa do Projetil do player
         */        
        public void MassaProjetil(int m) {
            massa = m;
        }              
        /**
         * Retorna a quantidade de massa do Projetil do player
         */        
        public int MassaProjetil() {
            return massa;
        }     
        /**
         * Insere a quantidade de massa do Retangulo
         */        
        public void MassaRect(int mR) {
            m2 = mR;
        }
        /**
         * Retorna a quantidade de massa do Retangulo
         */        
        public int MassaRect() {
            return m2;
        }   
        /**
         * Insere a quantidade o raio do projetil
         */        
        public void Raio(int raio) {
            thunder = raio;
        }
        /**
         * Retorna a quantidade de massa do Retangulo
         */        
        public int Raio() {
            return thunder;
        }    
    }

