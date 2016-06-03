package Classes;

import java.awt.Graphics;
import java.awt.Rectangle;

public class AbstractObject {
    
    protected String name;
    
    protected int x;
    protected int y;
    
    protected int w;
    protected int h;
    
    protected boolean visible;

    public AbstractObject(int x,int y,int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.visible = true;
    }
    
    public String getName(){
        return this.name;
    }
    
    public int getX(){
        return this.x;
    }
    
    public int getY(){
        return this.y;
    }
    
    public int getWidth(){
        return this.w;
    }
    
    public int getHeight(){
        return this.h;
    }
    
    public void MoveTo(int x, int y){
        this.x = x;
        this.y = y;
        
    } 
        
    public void setSize(int w,int h){
        this.w = w;
        this.h = h;
    }
    
    public Rectangle getBounds() {
        return new Rectangle(x,y,w,h);
    }

    public void draw(Graphics g){}
    
}
