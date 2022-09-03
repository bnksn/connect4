import java.util.*;

public class Grid {
    
    private String[] col1;
    private String[] col2;
    private String[] col3;
    private String[] col4;
    private String[] col5;
    private String[] col6;
    private String[] col7;
    private HashMap<Integer, String[]> columns = new HashMap<Integer, String[]>(); //dictionary that converts bettwen integers and columns
    private boolean win;
    private int recentHeight;

    public Grid() {
        win = false;
        this.col1 = new String[]{"_", "_", "_", "_", "_", "_"};
        this.col2 = new String[]{"_", "_", "_", "_", "_", "_"};
        this.col3 = new String[]{"_", "_", "_", "_", "_", "_"};
        this.col4 = new String[]{"_", "_", "_", "_", "_", "_"};
        this.col5 = new String[]{"_", "_", "_", "_", "_", "_"};
        this.col6 = new String[]{"_", "_", "_", "_", "_", "_"};
        this.col7 = new String[]{"_", "_", "_", "_", "_", "_"};
        columns.put(1, col1);
        columns.put(2, col2);
        columns.put(3, col3);
        columns.put(4, col4);
        columns.put(5, col5);
        columns.put(6, col6);
        columns.put(7, col7);
    }

    public void showGrid(){
        System.out.println("");
        System.out.println("|" + getVal(1, 5) + "|" + getVal(2, 5) + "|" + getVal(3, 5) + "|" + 
        getVal(4, 5) + "|" + getVal(5, 5) + "|" + getVal(6, 5) + "|" + getVal(7, 5) + "|");
        System.out.println("|" + getVal(1, 4) + "|" + getVal(2, 4) + "|" + getVal(3, 4) + "|" + 
        getVal(4, 4) + "|" + getVal(5, 4) + "|" + getVal(6, 4) + "|" + getVal(7, 4) + "|");
        System.out.println("|" + getVal(1, 3) + "|" + getVal(2, 3) + "|" + getVal(3, 3) + "|" + 
        getVal(4, 3) + "|" + getVal(5, 3) + "|" + getVal(6, 3) + "|" + getVal(7, 3) + "|");
        System.out.println("|" + getVal(1, 2) + "|" + getVal(2, 2) + "|" + getVal(3, 2) + "|" + 
        getVal(4, 2) + "|" + getVal(5, 2) + "|" + getVal(6, 2) + "|" + getVal(7, 2) + "|");
        System.out.println("|" + getVal(1, 1) + "|" + getVal(2, 1) + "|" + getVal(3, 1) + "|" + 
        getVal(4, 1) + "|" + getVal(5, 1) + "|" + getVal(6, 1) + "|" + getVal(7, 1) + "|");
        System.out.println("|" + getVal(1, 0) + "|" + getVal(2, 0) + "|" + getVal(3, 0) + "|" + 
        getVal(4, 0) + "|" + getVal(5, 0) + "|" + getVal(6, 0) + "|" + getVal(7, 0) + "|");
        System.out.println("---------------");
        System.out.println(" 1-2-3-4-5-6-7 ");
        System.out.println("");
    }

    public String getVal(int col, int order){
        return columns.get(col)[order];
    }

    public void setVal(int col, int order, String set){
        columns.get(col)[order] = set;
    }

    public void drop(int col, String player){
        for (int i = 0; i < 6; i++) { //simulates a player selecting a column and using it
            if (getVal(col, i) == "_"){
                setVal(col, i, player);
                break;
            }
          }
    }

    public void setWin(){
        this.win = true;
    }

    public boolean checkWin(){
        if (this.win == false){
            return true;
        } else {
            return false;
        }
    }

    public void conditionsWin(int usrColumn){
        for (int i = 0; i < 6; i++) { //this for loop sets recentHeight to be the height of the most recent drop
            if (getVal(usrColumn, i) != "_"){
                recentHeight = i;
            }
        }

        if (recentHeight >= 3){ //checks for 4 down
            List<String> down1 = Arrays.asList(getVal(usrColumn, recentHeight), getVal(usrColumn, recentHeight-1), getVal(usrColumn, recentHeight-2), getVal(usrColumn, recentHeight-3));
            if (new HashSet<String>(down1).size() <= 1) {
                setWin();
                showGrid();
            }
        }

        if (usrColumn <= 4){ //checks for 4 horizontal, recent drop on far left
            List<String> hor1 = Arrays.asList(getVal(usrColumn, recentHeight), getVal(usrColumn+1, recentHeight), getVal(usrColumn+2, recentHeight), getVal(usrColumn+3, recentHeight));
            if (new HashSet<String>(hor1).size() <= 1) {
                setWin();
                showGrid();
            }
        }

        if (2 <= usrColumn && usrColumn <= 5){ //checks for 4 horizontal, recent drop on middle left
            List<String> hor2 = Arrays.asList(getVal(usrColumn-1, recentHeight), getVal(usrColumn, recentHeight), getVal(usrColumn+1, recentHeight), getVal(usrColumn+2, recentHeight));
            if (new HashSet<String>(hor2).size() <= 1) {
                setWin();
                showGrid();
            }
        }

        if (usrColumn >= 4){ //checks for 4 horizontal, recent drop on far right
            List<String> hor3 = Arrays.asList(getVal(usrColumn, recentHeight), getVal(usrColumn-1, recentHeight), getVal(usrColumn-2, recentHeight), getVal(usrColumn-3, recentHeight));
            if (new HashSet<String>(hor3).size() <= 1) {
                setWin();
                showGrid();
            }
        }

        if (3 <= usrColumn && usrColumn <= 6){ //checks for 4 horizontal, recent drop on middle right
            List<String> hor4 = Arrays.asList(getVal(usrColumn-2, recentHeight), getVal(usrColumn-1, recentHeight), getVal(usrColumn, recentHeight), getVal(usrColumn+1, recentHeight));
            if (new HashSet<String>(hor4).size() <= 1) {
                setWin();
                showGrid();
            }
        }

        if (recentHeight <= 2 && usrColumn <= 4){ //checks for 4 on upper diagonal, recent drop on bottom
            List<String> upDiag1 = Arrays.asList(getVal(usrColumn, recentHeight), getVal(usrColumn+1, recentHeight+1), getVal(usrColumn+2, recentHeight+2), getVal(usrColumn+3, recentHeight+3));
            if (new HashSet<String>(upDiag1).size() <= 1) {
                setWin();
                showGrid();
            }
        }

        if (recentHeight >= 1 && recentHeight <= 3 && usrColumn >= 2 && usrColumn <= 5){ //checks for 4 on up diagonal, recent drop on bottom middle
            List<String> upDiag2 = Arrays.asList(getVal(usrColumn -1, recentHeight -1), getVal(usrColumn, recentHeight), getVal(usrColumn+1, recentHeight+1), getVal(usrColumn+2, recentHeight+2));
            if (new HashSet<String>(upDiag2).size() <= 1) {
                setWin();
                showGrid();
            }
        }

        if (recentHeight >= 2 && recentHeight <= 4 && usrColumn >= 3 && usrColumn <= 6){ //checks for 4 on up diagonal, recent drop on top middle
            List<String> upDiag3 = Arrays.asList(getVal(usrColumn -2, recentHeight -2), getVal(usrColumn-1, recentHeight-1), getVal(usrColumn, recentHeight), getVal(usrColumn+1, recentHeight+1));
            if (new HashSet<String>(upDiag3).size() <= 1) {
                setWin();
                showGrid();
            }
        }

        if (recentHeight >= 3 && usrColumn >= 4){ //checks for 4 on up diagonal, recent drop on top 
            List<String> upDiag4 = Arrays.asList(getVal(usrColumn -3, recentHeight -3), getVal(usrColumn-2, recentHeight-2), getVal(usrColumn-1, recentHeight-1), getVal(usrColumn, recentHeight));
            if (new HashSet<String>(upDiag4).size() <= 1) {
                setWin();
                showGrid();
            }
        }

        if (recentHeight <= 2 && usrColumn >= 4){ //checks for 4 on down diagonal, recent drop on bottom
            List<String> downDiag1 = Arrays.asList(getVal(usrColumn-3, recentHeight+3), getVal(usrColumn-2, recentHeight+2), getVal(usrColumn-1, recentHeight+1), getVal(usrColumn, recentHeight));
            if (new HashSet<String>(downDiag1).size() <= 1) {
                setWin();
                showGrid();
            }
        }

        if (recentHeight >= 1 && recentHeight <= 3 && usrColumn >= 3 && usrColumn <= 6){ //checks for 4 on down diagonal, recent drop on bottom middle
            List<String> downDiag4 = Arrays.asList(getVal(usrColumn -2, recentHeight +2), getVal(usrColumn-1, recentHeight+1), getVal(usrColumn, recentHeight), getVal(usrColumn+1, recentHeight-1));
            if (new HashSet<String>(downDiag4).size() <= 1) {
                setWin();
                showGrid();
            }
        }

        if (recentHeight >= 2 && recentHeight <= 4 && usrColumn >= 2 && usrColumn <= 5){ //checks for 4 on down diagonal, recent drop on top middle
            List<String> downDiag3 = Arrays.asList(getVal(usrColumn -1, recentHeight +1), getVal(usrColumn, recentHeight), getVal(usrColumn+1, recentHeight-1), getVal(usrColumn+2, recentHeight-2));
            if (new HashSet<String>(downDiag3).size() <= 1) {
                setWin();
                showGrid();
            }
        }

        if (recentHeight >= 3 && usrColumn <= 4){ //checks for 4 on down diagonal, recent drop on top
            List<String> downDiag4 = Arrays.asList(getVal(usrColumn , recentHeight), getVal(usrColumn+1, recentHeight-1), getVal(usrColumn+2, recentHeight-2), getVal(usrColumn+3, recentHeight-3));
            if (new HashSet<String>(downDiag4).size() <= 1) {
                setWin();
                showGrid();
            }
        }
    }
}

