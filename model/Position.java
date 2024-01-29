package model;

// Position class holds the row and column positioning of objects
// Class created by : Azhan

public class Position {

    private int row;
    private int column;
    
    public Position(int row, int column){
        this.row = row;
        this.column = column;
    }

    // Gets the respective row position
    public int getRow() {
        return row;
    }

    // Gets the respective column position
    public int getColumn() {
        return column;
    }
    
}
