//http://stackoverflow.com/questions/26638620/java-add-rectangles-to-arraylist-and-then-draw-them
//http://zetcode.com/tutorials/javagamestutorial/collision/
//http://www.javaprogrammingforums.com/whats-wrong-my-code/18615-why-isnt-rectangle-moving.html

package Game;

import javax.swing.JFrame;
import Classes.*;
import Handlers.GameHandler;
import Handlers.StartMenu;
import java.awt.Frame;
import java.awt.TextField;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class DartsGame extends JFrame{
    
    public GameHandler b;

    public DartsGame(){
        add(new GameHandler());
        setExtendedState(Frame.MAXIMIZED_BOTH);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  
    }
    
    public DartsGame(String name){
        b = new GameHandler(name);
        add(b);
        setExtendedState(Frame.MAXIMIZED_BOTH);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

          
    }

}
