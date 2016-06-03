package Classes.sub;

import Classes.AbstractObject;
import Game.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Point;

public class Dart extends AbstractObject {
    
    protected String name = "Dart";
    
    protected int org_max;
    protected int org_min;
    protected int power;

    protected boolean thrown = false;
    protected boolean current = false;
    
    protected Image image;
    
    public Dart(){
        super(0,0,0,0);
        this.org_max = 0;
        this.org_min = 0;
        super.name = this.name;
    }

    public Dart(int x,int y,int w, int h) {
        super(x,y,w,h);
        this.org_max = this.x;
        this.org_min = this.x;
        super.name = this.name;
    }
    
    public Dart(int x,int y,int w, int h,int min, int max) {
        super(x,y,w,h);
        this.org_max = min;
        this.org_min = max;
        super.name = this.name;
    }
    
    public Dart(Image id,int x,int y,int w, int h,int min, int max) {
        super(x,y,w,h);
        this.org_max = min;
        this.org_min = max;
        super.name = this.name;
    }
    
    public boolean isThrown(){
        return this.thrown;
    }
    
    public boolean isCurrent(){
        return this.current;
    }
    
    public int getPower(){
        return this.power;
    }
    
    public void setPower(int val){
        this.power = val;
    }
    
    public void setThrown(boolean val){
        this.thrown = val;
    }
    
    public void setCurrent(boolean val){
        this.current = val;
    }
    
    public void snaptoOrigin(int min,int max){
        
        this.org_min = min;
        this.org_max = max;
        
        if(!isThrown()){
            if(this.x > this.org_max){
                this.x = this.org_max;
            } else if(this.x < this.org_min){
                this.x = this.org_min;
            }
        }
    }
    
    public int powFunc(int num){
        int min = -(num+10);
        int max = num+10;
        return min + (int)(Math.random() * ((max - min) + 1));
    }

    @Override
    public void draw(Graphics g){
        Graphics2D g2 = (Graphics2D)g;
        g2.drawRect(this.x,this.y,this.w,this.h);
    }
    
    public void move(Graphics g){
        if(!isThrown()){
            MoveTo(MouseInfo.getPointerInfo().getLocation().x,this.y);
        }
    }
    
}
