import java.util.Scanner;

public class connect4{

    public static void player1turn(Grid myGrid){
        myGrid.showGrid();
        Scanner scanner1 = new Scanner(System.in);
        System.out.println("Player 1 enter column: ");
        try{ //ensures a valid input
            int usrColumn = Integer.parseInt(scanner1.nextLine()); //converts user input to an integer
            if (myGrid.getVal(usrColumn, 5) == "_"){ //checks that the column is not full
                myGrid.drop(usrColumn, "x");
            } else {
                player1turn(myGrid);
            }
            myGrid.conditionsWin(usrColumn); //uses the most recent 'drop' to determine if a win has been achieved
        }
        catch(Exception e){
            player1turn(myGrid);
        }
    }

    public static void player2turn(Grid myGrid){
        myGrid.showGrid();
        Scanner scanner2 = new Scanner(System.in);
        System.out.println("Player 2 enter column: ");
        try{ //ensures a valid input
            int usrColumn = Integer.parseInt(scanner2.nextLine()); //converts user input to an integer
            if (myGrid.getVal(usrColumn, 5) == "_"){ //checks that the column is not full
                myGrid.drop(usrColumn, "o");
            } else {
                player2turn(myGrid);
            }
            myGrid.conditionsWin(usrColumn); //uses the most recent 'drop' to determine if a win has been achieved
        }
        catch(Exception e){
            player2turn(myGrid);
        }
    }

    public static void startGame(){
        Grid c4Grid = new Grid();
        while(c4Grid.checkWin()){
            player1turn(c4Grid);
            if(c4Grid.checkWin()){ //stops game if player 1 just won
                player2turn(c4Grid);
            }
        }

    }
    
    public static void main(String[] args){
        startGame();
    }
}
