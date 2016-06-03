package Classes.sub;

import Classes.AbstractObject;
import Game.*;
import java.awt.Graphics;
import java.awt.Image;

public class Board extends AbstractObject {
    
    protected String name = "Board";
    
    protected int r = getWidth();
    protected int value;
    
    protected Image image;

    public Board(int x,int y,int w, int val) {
        super(x,y,w,w);
        super.name = this.name;
        this.value = val;
    }
    
    public Board(Image id, int x,int y,int w, int val) {
        super(x,y,w,w);
        image = id;
        super.name = this.name;
        this.value = val;
    }
    
    public int getValue(){
        return this.value;
    }
    
    @Override
    public void draw(Graphics g){
        g.drawOval(this.x,this.y,this.r,this.r);
    }
    
}
