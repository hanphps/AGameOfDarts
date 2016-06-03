package Handlers;

import Classes.*;
import Classes.sub.Board;
import Classes.sub.DartLine;
import Classes.sub.PowerBar;
import static com.sun.java.accessibility.util.AWTEventMonitor.addWindowListener;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class GameHandler extends JPanel{

    protected int multiplyer;
    
    protected boolean inGame = true;
    protected boolean inMenu;
    protected boolean isDown = false;
    
    protected ArrayList<Player> players;
    protected ArrayList<Board> boards;
    
    protected PowerBar p;
    protected DartLine dl;
            
    protected Player currentPlayer;
    
    protected int distance;
    protected int power=0;
    protected int size;
    
    protected boolean saving;
    protected ScoreHandler sHandler;

    public GameHandler() {
        
        setFocusable(true);
        setBackground(Color.WHITE);
        
        players = new ArrayList<>();
        players.add(new Player());
        currentPlayer = players.get(0);
        
     
    }
    
    public GameHandler(String name) {
        
        setFocusable(true);
        setBackground(Color.WHITE);
        
    sHandler = new ScoreHandler();
        addMouseListener(new MultiHandler());
        addMouseMotionListener(new MultiHandler());
        addKeyListener(new MultiHandler());
        addWindowListener(new MultiHandler());
        
        
        players = new ArrayList<>();
        players.add(new Player(name));
        currentPlayer = players.get(0);
        
     
    }
    
    public void Menu(){
        
    }
    
    public void Gameplay(Graphics g){
        
        super.paintComponent(g);
        
        this.distance = (super.getHeight()-(super.getHeight()/10+super.getHeight()/8));
        this.size = (super.getWidth());
        
        initBoards();
        initPowerBar();
        initDartLine();
        
        for (Board b : boards){
            b.draw(g);
        }
        
        p.draw(g);
        dl.draw(g);
        
        currentPlayer.currentDart().setSize(1,super.getHeight()/20);
        
        if(!currentPlayer.currentDart().isThrown()){
            currentPlayer.currentDart().MoveTo(super.getWidth()/2-1,super.getHeight()-(super.getHeight()/10+super.getHeight()/8)-currentPlayer.currentDart().getHeight()/2);
            currentPlayer.currentDart().move(g);
            currentPlayer.currentDart().snaptoOrigin(super.getWidth()/4,(super.getWidth()/4)*3);
        }
        
        currentPlayer.currentDart().draw(g);
        
        g.drawRect((int)p.getX(),(int)p.getY(),(int)(p.getWidth())-((p.getWidth()*currentPlayer.currentDart().getPower()))/distance,(int)p.getHeight());
        g.fillRect((int)p.getX(),(int)p.getY(),(int)(p.getWidth())-((p.getWidth()*currentPlayer.currentDart().getPower()))/distance,(int)p.getHeight());
        
        
        g.setColor(Color.BLACK);
        g.drawString("Name: " + currentPlayer.getName(), super.getHeight()/30, super.getWidth()/20);
        g.drawString("Score: " + currentPlayer.getScore(), super.getHeight()/30, super.getWidth()/20+super.getWidth()/20);
        g.drawString("Power: " + (currentPlayer.currentDart().getPower()), super.getHeight()/30, super.getWidth()/20+super.getWidth()/20+super.getWidth()/20);
       
        
    }
    
    public int withinRange(int min,int max){
        return (int) Math.floor(Math.random() * (max - min + 1)) + min;
    }
    
    public void initBoards(){
        boards = new ArrayList<>();
        int radius = super.getHeight()/2;
        boards.add(new Board(super.getWidth()/2-(radius/2),20,radius,1));
        boards.add(new Board(super.getWidth()/2-(radius/4+(radius/8)),20+radius/8,(radius/2)+(radius/4),1));
        boards.add(new Board(super.getWidth()/2-(radius/4),20+radius/4,(radius/2),1));
        boards.add(new Board(super.getWidth()/2-(radius/8),20 + (radius/4 + radius/8),(radius/4),1));
        boards.add(new Board(super.getWidth()/2-(radius/20),20 + (radius/2 - radius/18),(radius/10),1));
    }
    
    public void initPowerBar(){
        p = new PowerBar(super.getWidth()/4,super.getHeight()-(super.getHeight()/10+super.getHeight()/10),super.getWidth()/2,super.getHeight()/10);
    }
    
    public void initDartLine(){
        dl = new DartLine(super.getWidth()/4,super.getHeight()-(super.getHeight()/10+super.getHeight()/8),super.getWidth()/2,super.getHeight()/100);
    }
       
    public void checkCollisions() {

        Rectangle r3 = new Rectangle(currentPlayer.currentDart().getX(),currentPlayer.currentDart().getY(),1,1);

        for (Board b : boards) {
            Rectangle r2 = b.getBounds();

            if (r3.intersects(r2)) {
                currentPlayer.AddToScore(b.getValue());
                System.out.println(b.getValue());
            }
        }
    }

    public void AddtoList(ArrayList list, Object o){
        list.add(o);
    }
    
    public void setMulti(int val){
        this.multiplyer = val;
    }
    
    public int getMulti(){
        return this.multiplyer;
    }
    
    public void setStatus(Object status, Object val){
        status = val;
    }
    
    public boolean inGame(){
        return this.inGame;
    }
    
    public void setGame(boolean val){
        this.inGame = val;
    }
    
    public boolean inMenu(){
        return this.inMenu;
    }
    
    public void setMenu(boolean val){
        this.inMenu = val;
    }
    
    public void paint(Graphics g){
        if(inGame){
            Gameplay(g);
        }
        
    }
    
    
    public void Save(){
        if(!saving){
            saving = true;
            sHandler.Add(currentPlayer);
            JOptionPane.showMessageDialog(null, "Game saved!");
        }
        saving = false;
    }
    
    public int newMulti(){
        switch(getMulti()){
            case 1:
                return 5;
            case 2:
                return 4;
            case 3:
                return 3;
            case 4:
                return 2;
            case 5:
                return 1;
            default:
                return 5;
        }
    }
    
    private class MultiHandler implements Runnable, MouseListener, MouseMotionListener, KeyListener, WindowListener {
        
        @Override
        public void mouseClicked(MouseEvent e) {}

        @Override
        public void mousePressed(MouseEvent e) {
            isDown = true;
            if(!currentPlayer.currentDart().isThrown()){
                Thread thread = new Thread(this);
                thread.start();
            }
            
        }
            

        @Override
        public void mouseReleased(MouseEvent e) {
            isDown = false;
            thread = null;
            repaint();
            int x = MouseInfo.getPointerInfo().getLocation().x;
            
            //int y = MouseInfo.getPointerInfo().getLocation().y;
            if(!isDown){
                if(!currentPlayer.currentDart().isThrown()){ 
                    currentPlayer.currentDart().setThrown(true);
                    
                    System.out.println(currentPlayer.currentDart().getPower());
                    System.out.println("x =" + x);
                    System.out.println("y =" + currentPlayer.currentDart().getPower());
                    currentPlayer.currentDart().MoveTo(x,currentPlayer.currentDart().getPower());
                    currentPlayer.currentDart().setPower(0);
                    
                    repaint();
                    
                    checkCollisions();
                    

                }
            }
            
        }

        @Override
        public void mouseEntered(MouseEvent e) {}

        @Override
        public void mouseExited(MouseEvent e) {}

        @Override
        public void mouseDragged(MouseEvent e) {
            repaint();
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            repaint();
        }

        @Override
        public void keyTyped(KeyEvent e) {}

        @Override
        public void keyPressed(KeyEvent e) {
            switch(e.getKeyCode()){
                case KeyEvent.VK_R:
                    currentPlayer.thrownDart();
                    repaint();
                    break;
                case KeyEvent.VK_S:
                    Save();
                    break;
                default:
                    repaint();
                    break;
            }
        }
        

        @Override
        public void keyReleased(KeyEvent e) {}

        @Override
        public void run() {
            try{
             
                power = 0;
                while(isDown){
                    currentPlayer.currentDart().setPower(power);
                    repaint();
                    power ++;
                    if(power >= distance){
                        power = 0;
                        
                    }
                    thread.sleep(newMulti());
                    
                    //System.out.println("Your multiplyer:"+Integer.toString());
                }
            }catch(Exception e){e.printStackTrace();}
        }
            private Thread thread;

        @Override
        public void windowOpened(WindowEvent e) {}

        @Override
        public void windowClosing(WindowEvent e) {
            Save();
        }

        @Override
        public void windowClosed(WindowEvent e) {
            Save();
        }

        @Override
        public void windowIconified(WindowEvent e) {}

        @Override
        public void windowDeiconified(WindowEvent we) {}

        @Override
        public void windowActivated(WindowEvent e) {}

        @Override
        public void windowDeactivated(WindowEvent e) {}
        
    }


    
}

