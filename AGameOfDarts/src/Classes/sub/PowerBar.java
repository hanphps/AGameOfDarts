package Classes.sub;

import Classes.AbstractObject;
import Game.*;
import java.awt.Graphics;

public class PowerBar extends AbstractObject {
    
    protected String name = "PowerBar";
    
    protected int value;

    public PowerBar(int x,int y,int w, int h) {
        super(x,y,w,h);
        super.name = this.name;
    }
    
    public int getValue(){
        return this.value;
    }
    
    @Override
    public void draw(Graphics g){
        g.drawRect(this.x,this.y,this.w,this.h);
    }
    
}
