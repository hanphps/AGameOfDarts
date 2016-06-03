package Classes;



import Handlers.*;
import Classes.*;
import Classes.sub.Dart;
import java.util.ArrayList;
import java.util.Comparator;

public class Player {
    
    protected String name;
    protected int score;
    protected int high_score;
    protected int num_throws;
    
    protected boolean gameover;
    
    protected Dart currentDart;
    public ArrayList<Dart> darts;

    public Player(String name) {
        this.name = name;
        initDarts();
        currentDart = darts.get(0);
        currentDart.setCurrent(true);
    }
    
    public Player(String name,int score){
        this.name = name;
        this.score = score;
        
    }
    
    public Player() {
        this.name = "GenericPlayer";
        initDarts();
        currentDart = darts.get(0);
        currentDart.setCurrent(true);
    }
    
    public String getName(){
        return this.name;
    }
    
    public int getScore(){
        return this.score;
    }
    
    public Dart currentDart(){
        return this.currentDart;
    }
    
    public void initDarts(){
        darts = new ArrayList<>();
        darts.add(0,new Dart());
    }
    
    public void thrownDart(){
        if(currentDart.isThrown()){
            if(this.num_throws < 4){
                this.num_throws +=1;
                currentDart().setThrown(false);
            }else{
                this.gameover = true;
            }
        }
    }
    
    public boolean GameOver(){
        return this.gameover;
    }
    
    public void drawInventory(){
    }
    
    public void AddToScore(int val){
        this.score += val;
    }
    
    public static Comparator<Player> comparer = new Comparator<Player>() {

	public int compare(Player p1, Player p2) {

	   int s1 = p1.getScore();
	   int s2 = p2.getScore();

	   return s2-s1;
    }};
    
}
