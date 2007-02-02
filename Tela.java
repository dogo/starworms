/**
 * Worms Tela  
 * 
 * @author Diogo Autilio.
 * @version 0.3.1-alpha 
 * Created (07/11/2006)
 * last update : 29/02/07
 */


import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.io.*;
import javax.swing.BorderFactory;
import java.net.URL;

/**
 * Classe responsavel por criar interface do jogo, controlar os movimentos do teclado e a leitura da fase.
 */
public class Tela extends JFrame implements ActionListener {
    private boolean LeArq;
    private int velo= 0;
    private int ang= 1;
    private int tempo= 0; 
    private String[] vez;
    private String arq;
    private javax.swing.Timer time;
    private Movimentacao teclas = new Movimentacao();
    
    private BorderFactory borda;
    private JPanel jPanel1 = new JPanel();
    private JPanel painelDesenho = new JPanel();
    private JPanel painelCima = new JPanel();    
    private JPanel painelEsq = new JPanel();
    private JPanel painelDir = new JPanel();    
    private FlowLayout flowLayout1 = new FlowLayout();
    Graphics2D graphic;
    private JButton ButtonLoad = new JButton();
    private JButton ButtonSave = new JButton();
    private JButton ButtonQuit = new JButton();
    
    private JLabel LabelTempo = new JLabel();
    
    private JTextField TxtArq = new JTextField();    
    private JTextField TxtVelo_1 = new JTextField();
    private JTextField TxtAng_1 = new JTextField();
    private JTextField TxtVelo_2 = new JTextField();    
    private JTextField TxtAng_2 = new JTextField();
       
    private JLabel LabelArq = new JLabel();
    private JLabel LabelLife_1 = new JLabel(); 
    private JLabel LabelLife_2 = new JLabel();
    private JLabel LabelNome_1 = new JLabel(); 
    private JLabel LabelVida_1 = new JLabel();
    private JLabel LabelVelo_1 = new JLabel();
    private JLabel LabelAng_1 = new JLabel();
    private JLabel LabelVez_1 = new JLabel();
    
    private JLabel LabelNome_2 = new JLabel();
    private JLabel LabelVida_2 = new JLabel();    
    private JLabel LabelVelo_2 = new JLabel();    
    private JLabel LabelAng_2 = new JLabel();
    private JLabel LabelVez_2 = new JLabel();
    
    private BorderLayout borderLayout1 = new BorderLayout();
    
    /**
     * Constroi toda a interface do Jogo.
     */
    public Tela() {
        super("StarWorms 0.1.7.6-alpha");
        this.setContentPane(jPanel1);
        
        LeArq = false;
        painelDesenho.setBackground(Color.white);
        painelDesenho.setForeground(Color.black);
        painelDesenho.setLayout(flowLayout1);        
        
        jPanel1.setBackground(SystemColor.menu);
        jPanel1.setLayout(borderLayout1);
        jPanel1.setFocusable(true);     
        
        painelEsq.setPreferredSize(new Dimension(160, 50));
        painelEsq.setLayout(null);
        
        painelDir.setPreferredSize(new Dimension(160, 50));
        painelDir.setLayout(null);        
        
        painelCima.setPreferredSize(new Dimension(700, 30));
        painelCima.setLayout(null);                
              
        LabelArq.setText("Digite o nome da Fase: ");
        LabelArq.setBounds(new Rectangle(20, 5, 150, 20));
        TxtArq.setBounds(new Rectangle(180, 5, 120, 20));
        ButtonSave.setText("Save");
        ButtonSave.setBounds(new Rectangle(425, 5, 100, 20));
        ButtonLoad.setText("Load it!");
        ButtonLoad.setBounds(new Rectangle(315, 5, 100, 20));
        ButtonQuit.setText("Quit");
        ButtonQuit.setBounds(new Rectangle(535, 5, 100, 20));

        LabelNome_1.setText("StormTrooper");
        LabelNome_1.setBounds(new Rectangle(5, 5, 90, 20));
        LabelLife_1.setText("Life:");
        LabelLife_1.setBounds(new Rectangle(5, 5, 80, 80));
        LabelVida_1.setText("");
        LabelVida_1.setBounds(new Rectangle(60, 35, 50, 20));
        LabelAng_1.setText("Angulo:");
        LabelAng_1.setBounds(new Rectangle(5, 65, 50, 20));
        TxtAng_1.setBounds(new Rectangle(80, 65, 70, 20));
        LabelVelo_1.setText("Velocidade:");
        LabelVelo_1.setBounds(new Rectangle(5, 95, 70, 20));        
        TxtVelo_1.setBounds(new Rectangle(80, 95, 70, 20));
        LabelVez_1.setBounds(new Rectangle(60,115,70,20));
        
        LabelNome_2.setText("Darth Vader");
        LabelNome_2.setBounds(new Rectangle(5, 5, 80, 20));
        LabelLife_2.setText("Life:");
        LabelLife_2.setBounds(new Rectangle(5, 5, 80, 80));
        LabelVida_2.setText("");
        LabelVida_2.setBounds(new Rectangle(60, 35, 50, 20));        
        LabelAng_2.setText("Angulo:");
        LabelAng_2.setBounds(new Rectangle(5, 65, 50, 20));
        TxtAng_2.setBounds(new Rectangle(80, 65, 70, 20));           
        LabelVelo_2.setText("Velocidade:");
        LabelVelo_2.setBounds(new Rectangle(5, 95, 70, 20));        
        TxtVelo_2.setBounds(new Rectangle(80, 95, 70, 20));
        LabelVez_2.setBounds(new Rectangle(60,115,70,20));
        
        painelEsq.add(LabelNome_1, null);       
        painelEsq.add(LabelAng_1, null);  
        painelEsq.add(LabelVida_1,null);
        painelEsq.add(LabelVelo_1, null);       
        painelEsq.add(TxtAng_1, null);
        painelEsq.add(TxtVelo_1, null);
        painelEsq.add(LabelVez_1, null);
        painelEsq.add(LabelLife_1,null); 
    

        painelDir.add(LabelNome_2, null);               
        painelDir.add(LabelVida_2,null);
        painelDir.add(LabelVelo_2, null);        
        painelDir.add(LabelAng_2, null);        
        painelDir.add(TxtAng_2, null);        
        painelDir.add(TxtVelo_2, null);        
        painelDir.add(LabelVez_2, null);
        painelDir.add(LabelLife_2,null);
        
        painelCima.add(LabelArq,null);
        painelCima.add(TxtArq,null); 
        painelCima.add(ButtonSave,null);
        painelCima.add(ButtonLoad,null);
        painelCima.add(ButtonQuit,null);
        painelCima.add(LabelTempo,null);
        
        jPanel1.add(painelDesenho, BorderLayout.CENTER);
        jPanel1.add(painelEsq, BorderLayout.WEST);
        jPanel1.add(painelDir, BorderLayout.EAST);        
        jPanel1.add(painelCima, BorderLayout.NORTH);  
        
        
        ButtonSave.addActionListener(new ActionListener() {            
            /**
             * Salva o estado do jogo num arquivo.
             */            
            public void actionPerformed(ActionEvent a){
                try {
                    FileOutputStream file = new FileOutputStream(TxtArq.getText());
                    BufferedOutputStream bfile = new BufferedOutputStream(file);
                    DataOutputStream dfile = new DataOutputStream(bfile);
                    Save salvar= new Save();
                    salvar.salvaFile(dfile);
                    jPanel1.requestFocus();
                }
                catch (IOException q) {
                    JOptionPane.showMessageDialog(null,"Erro ao tentar salvar a fase!","Erro",JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });        
        
        
        ButtonQuit.addActionListener(new ActionListener() {       
            /**
             * Da Quit.
             */            
            public void actionPerformed(ActionEvent a) {
                System.exit(0);             
            }
        });
        
        ButtonLoad.addActionListener(new ActionListener() {       
            /**
             * Le Arquivo passado pelo usuario apos clique no Botao Carrega.
             */            
            public void actionPerformed(ActionEvent a) {
            //Tenta abrir o arquivo para leitura                
                File arq = new File(TxtArq.getText());
                if (arq.exists()) { //Checa se o arquivo existe.
                    try{
                     FileReader leitor = new FileReader(TxtArq.getText());  
                     Engine i= new Engine();  
                     i.LeArq(leitor);  
                     leitor.close();  
                     startTime();  
                     jPanel1.requestFocus();
                    }
                catch (IOException q) {  
                    JOptionPane.showMessageDialog(null,"Erro ao tentar carregar a fase!","Erro",JOptionPane.INFORMATION_MESSAGE);  
                } 
                }
                else{
                String name = "levels/"+TxtArq.getText();  
                URL resource = getClass().getResource(name);  
                if (resource == null) {
                    JOptionPane.showMessageDialog(null,"Erro ao tentar carregar a fase!","Erro",JOptionPane.INFORMATION_MESSAGE);
                    //System.out.println(name + " not found");  DEBUG
                return;  
                }
                //String path = resource.getFile();  
                //System.out.println(path);  DEBUG
                try {  
                    Reader leitor = new InputStreamReader(resource.openStream());  
                    Engine i= new Engine();  
                    i.LeArq(leitor);  
                    leitor.close();
                    startTime();
                    jPanel1.requestFocus(); 
                    } 
                catch (Exception e) {  
                    e.printStackTrace();  
                }
            }}
        });
        
        TxtArq.addActionListener(new ActionListener(){   
        public void actionPerformed(ActionEvent e) {
        jPanel1.requestFocus();
        }
        }); 
        
        jPanel1.addKeyListener(new KeyAdapter() {
            /**
             * VK_UP: Aumenta o angulo do tiro.
             * VK_DOWN: Diminui o angulo do tiro.
             * VK_RIGHT: Move o player para direita.
             * VK_LEFT: Move o player para esquerda.
             * VK_SPACE: Aumenta a velocidade do tiro. 
             * VK_CONTROL: Faz o player pular.           
             */
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_RIGHT:
                            teclas.movePlayer("player_dir.gif",5,0);
                            break;
                    case KeyEvent.VK_LEFT:
                            teclas.movePlayer("player_esq.gif",-5,0);
                            break; 
                    case KeyEvent.VK_CONTROL:
                        	teclas.pulaPlayer();
                        	break;
                    case KeyEvent.VK_SPACE:
                            if(velo==250)
                                velo=245;
                            velo = velo + 5;
                            if(vez[0].equals("player1"))
                                TxtVelo_1.setText(velo+" M/s");                            
                            else
                                TxtVelo_2.setText(velo+" M/s");                            
                            break;
                    case KeyEvent.VK_UP:
                            if(ang==90)
                                ang=89;
                            ang = ang + 1;       
                            
                            if(vez[0].equals("player1"))
                                TxtAng_1.setText(ang+"°");
                            else
                                TxtAng_2.setText(ang+"°");
                            break;
                    case KeyEvent.VK_DOWN:
                            if(ang==1)
                                ang=2;
                            ang = ang - 1;
                            if(vez[0].equals("player1"))
                                TxtAng_1.setText(ang+"°");
                            else
                                TxtAng_2.setText(ang+"°");
                            break;                    
            }
        }                          
            public void keyReleased(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_ENTER:
                //Tenta abrir o arquivo para leitura                
                        File arq = new File(TxtArq.getText());
                        if (arq.exists()) { //Checa se o arquivo existe.
                            try{
                                FileReader leitor = new FileReader(TxtArq.getText());  
                                Engine i= new Engine();  
                                i.LeArq(leitor);  
                                leitor.close();  
                                startTime();  
                                jPanel1.requestFocus();
                            }
                            catch (IOException q) {  
                                JOptionPane.showMessageDialog(null,"Erro ao tentar carregar a fase!","Erro",JOptionPane.INFORMATION_MESSAGE);  
                            } 
                }    
                    else{             
                        String name = "levels/"+TxtArq.getText();  
                        URL resource = getClass().getResource(name);  
                        if (resource == null) {
                            JOptionPane.showMessageDialog(null,"Erro ao tentar carregar a fase!","Erro",JOptionPane.INFORMATION_MESSAGE);
                            //System.out.println(name + " not found");  DEBUG
                        return;  
                        }   
                        //String path = resource.getFile();  
                        //System.out.println(path);  DEBUG
                        try {  
                            Reader leitor = new InputStreamReader(resource.openStream());  
                            Engine i= new Engine();  
                            i.LeArq(leitor);  
                            leitor.close();
                            startTime();
                            jPanel1.requestFocus(); 
                        } 
                        catch (Exception k) {  
                            k.printStackTrace();  
                        }}
                        break;
               
                    case KeyEvent.VK_SPACE:
                            if(vez[0].equals("player1"))
                                TxtVelo_1.setText(velo+" M/s");
                            else
                                TxtVelo_2.setText(velo+" M/s");
                            Projetil atira = new Projetil();
                            atira.LancaProjetil(velo,ang);
                            TxtVelo_1.setText("");
                            TxtVelo_2.setText("");
                            TxtAng_1.setText("");
                            TxtAng_2.setText("");                            
                            velo = 0;
                            ang = 1;
                            tempo = teclas.Atirou();
                            break;
                }
            }
        });      
        this.novoSize(650,400);
        this.setVisible(true);
   }
       
    /**
     * Pinta na Tela todos as informacoes do jogo.
     */                    
    public void paint(Graphics g) {        

        super.paint(g);        
        DesenhaTela desenha = new DesenhaTela();
        Rectangle[] vida;
        if(LeArq==false)
            return;           
        vida = desenha.Barra();
        LabelVida_1.setBounds(vida[0]);
        LabelVida_2.setBounds(vida[1]);            
        graphic = (Graphics2D)painelDesenho.getGraphics(); 
        desenha.Retangulos(graphic);
        vida = desenha.Barra();
        vez = desenha.PlayerVez();
        if(vez[0].equals("player1")) {
            LabelVez_1.setText("Sua Vez");
            LabelVez_2.setText("");
            LabelNome_1.setText(vez[1]);
            LabelNome_2.setText(vez[2]);
        }
        else {
            LabelVez_2.setText("Sua Vez");
            LabelVez_1.setText("");      
            LabelNome_1.setText(vez[2]);
            LabelNome_2.setText(vez[1]);
        }
        LabelNome_1.setText("StormTrooper");
        LabelNome_2.setText("Darth Vader");
        LabelVez_1.setForeground(Color.red);
        LabelVez_2.setForeground(Color.red);        
        LabelTempo.setText("Tempo: "+tempo);
        LabelTempo.setForeground(Color.red);
        LabelTempo.setBounds(645,5,250,20);
        LabelVida_1.setBounds(vida[0]);
        LabelVida_2.setBounds(vida[1]);
        LabelVida_1.setOpaque(true);
        LabelVida_2.setOpaque(true);
        LabelVida_1.setBackground(Color.green);
        LabelVida_2.setBackground(Color.green);   
        LabelVida_1.setBorder(borda.createLineBorder(Color.black));
        LabelVida_2.setBorder(borda.createLineBorder(Color.black));
        desenha.Player1(graphic);
        desenha.Player2(graphic);
        }  
    /**
     * Chama metodo paint para redesenhar a tela do jogo.
     * Acho que eh aqui o problema das "piscadas", porque ele redesenha TUDO.
     */            
    public void repinta() {
        paint(this.getGraphics());
       if(teclas.GameOver()==true) {       
            LabelVez_1.setText("");
            LabelVez_2.setText("");            
            LabelTempo.setText("");            
            if(teclas.Winner().equals("player1")){
                JOptionPane.showMessageDialog(null,"StormTrooper Wins !","You Win",JOptionPane.INFORMATION_MESSAGE);
                System.exit(0); 
            }
            else{
                 JOptionPane.showMessageDialog(null,"Darth Vader Wins !","You Win",JOptionPane.INFORMATION_MESSAGE);
                 System.exit(0); 
            }      
    }
}
    /**
     * Desenha uma String.
     */            
    public void desenhaString(String text,int x,int y) {
        painelDesenho.getGraphics().drawString(text, x, y);   
    }

    /**
     * Desenha um shape na Tela.
     */            
    public void desenha(Shape shape) {
        graphic = (Graphics2D)painelDesenho.getGraphics(); 
        graphic.draw(shape);
    }           
    /**
     * Seta um novo tamanho para a Tela.
     */            
    public void novoSize(int x, int y) {
        int tam_paineis_lado=painelEsq.getWidth() + painelDir.getWidth()+8;
        int tam_paineis_cima=painelCima.getHeight()+27;
        if(x<450)
            x=450;
        if(y<200)
            y=200;
        this.setSize(x+tam_paineis_lado,y+tam_paineis_cima);
        this.setVisible(true);
        this.setResizable( false);
    }
    
    /**
     * Preenche a parte interna de um shape dado.
     */
    public void pinta(Shape shape){        
        graphic = (Graphics2D)painelDesenho.getGraphics(); 
        graphic.fill(shape);
    }   
    
    /**
     * Apaga Toda Tela.
     */
    public void apaga() {
        graphic = (Graphics2D)painelDesenho.getGraphics();
        Color original = graphic.getColor();
        graphic.setColor(this.getBackground());
        Dimension size = getSize();
        graphic.fill(new Rectangle(0, 0, size.width, size.height));
        graphic.setColor(original);
    }  
    /**
     * Apaga um shape dado.
     */
    public void apaga(Shape shape) {
        graphic = (Graphics2D)painelDesenho.getGraphics();
        Color original = graphic.getColor();
        graphic.setColor(this.getBackground());
        graphic.fill(shape);              // apaga pintando com a cor de fundo
        graphic.setColor(original);
    }
    /**
     * Desenha uma Imagem na Tela.
     */
    public boolean desenhaImage(Image image, int x, int y) {
        boolean result = painelDesenho.getGraphics().drawImage(image, x, y, null);
        return result;
    }

    /**
     * Desenha uma Imagem na Tela.
     */
    public boolean desenhaImage(Image image, int x, int y, int z, int k) {
        boolean result = painelDesenho.getGraphics().drawImage(image, x, y, z, k, null);
        return result;
    }    
    /**
     * Apaga uma String da Tela.
     */
    public void apagaString(String text, int x, int y) {
        graphic = (Graphics2D)painelDesenho.getGraphics();
        Color original = graphic.getColor();
        graphic.setColor(this.getBackground());
        graphic.drawString(text, x, y);   
        graphic.setColor(original);
        repaint();
    }
    /**
     * Seta a Cor dos Objetos da Tela.
     */   
    public void novoForegroundColour(Color newColour) {
        this.setForeground(newColour);
    }    
    
    /**
     * Retorna a cor atual dos Objetos da Tela
     */
    public Color pegaForegroundColour(){
            return this.getForeground();
    }

    /**
     * Seta a cor de Fundo da Tela.
     */
    public void novoBackgroundColour(Color newColour){
        this.setBackground(newColour);
    }

    /**
     * Desenha um shape oval.
     */    
    public void desenhaOval(int x, int y, int width, int heigth) {
        graphic = (Graphics2D)painelDesenho.getGraphics();
        graphic.drawOval(x,y,width,heigth);
    }
    /**
     * Retorna a cor atual de fundo da Tela.
     */
    public Color pegaBackgroundColour(){
        return this.getBackground();
    }
    
    /**
     * Retorna o tamanho da Tela de Jogo
     */
    public Dimension pegaSize(){
        return getSize();
    }

    /**
     * Retorna o tamanho no eixo X da Tela de Desenho.
     */
    public int telaX() {
        return painelDesenho.getWidth();
    }
    
    /**
     * Retorna o tamanho no eixo Y da Tela de Desenho.
     */
    public int telaY() {
        return painelDesenho.getHeight();
    }    
    
    /**
     * Espera por um numero espec�fico de milisegundos antes de finalizar.
     */
    public void espera(int milliseconds){
        try {
            Thread.sleep(milliseconds);
        } 
        catch (Exception e) {
            // ignoring exception at the moment
        }
    }
    
    /**
     * Informa que um arquivo correto foi lido.
     */
    public void LeArq(boolean b){
        LeArq = b;
    }
    
    /**
     * Informa o tempo do player na jogada.
     */
    public void Tempo(int t) {
        tempo = t;
    }    

    /**
     * Retorna o tempo do player na jogada.
     */
    public int Tempo(){
        return tempo;
    }        
        
    /**
     * Controla o Tempo do Jogo.
     */
    public void actionPerformed(ActionEvent e) {
        tempo = tempo + 1;
        if(teclas.AcabaTempo(tempo)==true) {
            tempo = 0;
            velo = 0;
            ang = 1;
        }
        repaint();
    }    
    
    /**
     * Inicia o Timer do Jogo.
     */   
    public void startTime() {
        if (time == null) {
            time = new javax.swing.Timer(1000, this);
            time.setInitialDelay(0);
            time.start();
        }
        else if (!time.isRunning()) {
            time.restart();
        }
    }
}
