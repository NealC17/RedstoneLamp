import processing.core.PApplet;

import javax.swing.*;

public class Tile {
    private int x,y;
    public static final int SIDE = Integer.parseInt(JOptionPane.showInputDialog("Enter width of a tile")), UP = 0, DOWN = 1, LEFT = 2, RIGHT = 3;

    private boolean isOn;
    private Tile[] neighbors;
    private Tile parent;


    public Tile(int x, int y){
        this.x = x;
        this.y=y;

        neighbors = new Tile[4];

    }

    public boolean isOn(){return isOn;}

    public Tile[] getNeighbors() {
        return neighbors;
    }

    public void setNeighbors(Tile[] neighbors) {
        this.neighbors = neighbors;
    }



    public void draw(PApplet window){

        window.fill(255);
        if(isOn){
            window.fill(0,0,0);
            window.rect( x, y, SIDE, SIDE);
            window.fill(255);
        } else {
            window.rect( x, y, SIDE, SIDE);
        }



    }

    public String toString(){
        return "[x = " + x + "], [y = ]" + y + "]";
    }

    public boolean incorporates(int x, int y) {
        return this.x < x && this.y<y && x<this.x+SIDE && y<this.y+SIDE;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setParent(Tile parent) {
        this.parent=parent;
    }

    public Tile getParent() {
        return parent;
    }


    public void changePolarity() {
        isOn = !isOn;
    }

    public void changeNeighborsPolarity(){
        for(Tile t: neighbors){
            if(t!=null) t.changePolarity();
        }
    }

    public int numHypoSwitch() {
        int numOn =0;
        for(Tile t: neighbors){
            if(t!=null && t.isOn){
                numOn++;
            }
        }

        int numGotSwitched = neighbors.length-numOn;
        return numGotSwitched;


    }

    public int numHypoAdd() {
        int numOff =0;
        for(Tile t: neighbors){
            if(t!=null && !t.isOn){
                numOff++;
            }
        }

        int numGotAdded = neighbors.length-numOff;
        return numGotAdded;


    }
}
