//chloe mo, 2013

import java.awt.Color;
import java.awt.Graphics;

public class Cell {
	private int myX, myY; // x,y position on grid
	public static int turnCount;
	private boolean myAlive; // alive (true) or dead (false)
	private int myNeighbors; // count of neighbors with respect to x,y
	private boolean myAliveNextTurn; // Used for state in next iteration
	private Color myColor; // Based on alive/dead rules
	private Color tempColor;
	private final Color DEFAULT_ALIVE = Color.MAGENTA;
	private final Color DEFAULT_DEAD = Color.GRAY;

	public Cell(int x, int y) {
		this(x, y, false, Color.GRAY);
	}

	public Cell(int row, int col, boolean alive, Color color) {
		myAlive = alive;
		myColor = color;
		myX = col;
		myY = row;
	}

	public boolean getAlive() {
		return myAlive;
	}

	public int getX() {
		return myX;
	}

	public int getY() {
		return myY;
	}

	public Color getColor() {
		return myColor;
	}

    public void setAlive(boolean alive) {
    	//changes depending on column and turn
    	int trueColor = (myY + turnCount)%8;
    	
    	//switch statements for different colors
    	switch (trueColor) {
    	case 0: tempColor = Color.MAGENTA;
    		break;
    	case 1: tempColor = Color.RED;
    		break;
    	case 2: tempColor = Color.ORANGE;
    		break;
    	case 3: tempColor = Color.YELLOW;
    		break;
    	case 4: tempColor = Color.GREEN;
    		break;
    	case 5: tempColor = Color.CYAN;
    		break;
    	case 6: tempColor = Color.BLUE;
    		break;
    	case 7: tempColor = Color.PINK;
    		break;
    	default: tempColor = Color.MAGENTA;
    	
    	}
   
        //if the cell is alive, set alive to true and color it the corresponding color
        if (alive) {
                setAlive(true, tempColor);
                //if the cell is dead, set alive to false and color it grey
        } else {
                setAlive(false, DEFAULT_DEAD);
        }
}

	public void setAlive(boolean alive, Color color) {
		
		myColor = color;
		myAlive = alive;
	}

	public void setAliveNextTurn(boolean alive) {
		myAliveNextTurn = alive;
	}

	public boolean getAliveNextTurn() {
		return myAliveNextTurn;
	}


	public void setColor(Color color) {
		myColor = color;
	}

	public int getNeighbors() {
		return myNeighbors;
	}
	//calcNeighbors should find the number of neighbors surrounding any
    //cell, alive or dead
public void calcNeighbors(Cell[][] cell) {
		myNeighbors = 0;
		//wraparound for all sides of cell
		
	
	//remember: x is col, y is row. it goes cell[row][col] so cell[y][x]
	
		int rightcol = ((myX + 100) +1)%100;
		int leftcol = ((myX + 100) - 1)%100;
		int bottomrow =  ((myY + 80) - 1)%80;
		int toprow = ((myY + 80) +1)%80;
		 //checks top row for alive
		//cells 1 2 3
		for(int i = 0;i< 3;i++){
			//things with % tries to do wraparound	
			if(cell[toprow][((myX + 100 + i) - 1)%100].myAlive == true){
				myNeighbors ++;
			}
		}
		//checks bottom row for alive
		//cells 6 7 8
		for(int i = 0;i< 3;i++){
			if(cell[bottomrow][((myX + 100 + i) - 1)%100].myAlive == true){
				myNeighbors ++;
			}
		}
		//checks remaining side squares for alive
		//left side cell 4
		if(cell[myY][leftcol].myAlive == true){
			/* |1 2 3|
			 * |4 x 5|
			 * |6 7 8|
			 */
			myNeighbors ++;
		}
		
		//right side cell 5
		if(cell[myY][rightcol].myAlive == true){
			myNeighbors ++;
		}

		//prints neighbors to check
		//System.out.println("neighbors" + myNeighbors);
		

	}

	
	public void willIBeAliveNextTurn(){
		//something might be incorrect with this too
		//enter this sequence if cell is dead (not alive) to see if it will be alive
		if(myAlive == false){
			if (myNeighbors == 3){
				setAliveNextTurn(true);
			}
	
		}
		
		//if the cell is alive it follows this code to see if
        //the cell will be alive next turn based on its neighbors
		if(myAlive == true){
			if (myNeighbors == 2 || myNeighbors == 3){
				setAliveNextTurn(true);
				
			}

			else {
				//myAlive = false;
				setAliveNextTurn(false);
				
			}
		
		}
				//repaint();
	}
	
	public void draw(int x_offset, int y_offset, int width, int height,
			Graphics g) {
		// window size
		int xleft = x_offset + 1 + (myX * (width + 1));
		int xright = x_offset + width + (myX * (width + 1));
		int ytop = y_offset + 1 + (myY * (height + 1));
		int ybottom = y_offset + height + (myY * (height + 1));
		Color temp = g.getColor();

		g.setColor(myColor);
		g.fillRect(xleft, ytop, width, height);
	}
}
