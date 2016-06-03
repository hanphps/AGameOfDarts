package Handlers;

/* Name Score */

import java.io.*;
import Classes.Player;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class ScoreHandler {

    public File Scores = new File("scores.txt");
    public ArrayList<Classes.Player> Sort;  
    public ArrayList<Classes.Player> Players;  
    
    public ScoreHandler(){
        
        if(!Scores.exists()){
            try {
                Scores.createNewFile();
                System.out.println("Creating new file");
            } catch (Exception e) {
                 e.printStackTrace();
            }
        }
        
        getScores();
        
    }
    
    public void getScores() {
        
        //Sort = new ArrayList<Classes.Player>();
        Players = new ArrayList<Classes.Player>(); //flush arraylist each time
        
        try{

            Scanner data = new Scanner(Scores);
            String line;

            while(data.hasNextLine()){
                
                line = data.nextLine();
                System.out.println("Parsing line: " + line);
                
                String[] parts = line.split(" ");
                
                Players.add(new Player(parts[0],Integer.parseInt(parts[1])));
                
                Collections.sort(Players,Player.comparer);
            }

            data.close();
        
        } catch (Exception e){
            e.printStackTrace();
        }
       
    }
    
    public void SaveScores() {
        
        PrintWriter out = null;
        
        try{
            
            out = new PrintWriter(new FileOutputStream(Scores,false)); //flush the file each time
            for(int i = 0; i<Players.size();i++){
                String temp = Players.get(i).getName();
                temp = temp.replace(" " , "");
                out.println(temp+" "+Players.get(i).getScore());
                System.out.println("Saving: " + Players.get(i).getName()+" "+Players.get(i).getScore());
            }
            
        } catch(Exception e){
            e.printStackTrace();
        } finally {
            out.flush();
            out.close();
        }
        
    }
    
    public void Add(Player p){
        Players.add(p);
        System.out.println("Adding:" + p.getName());
        SaveScores();
        getScores();
    }
    
}
