import java.util.Scanner;

public class Main {
  public static void main(String[] args) {

    int rows = 3;
    int columns = 3;
    char[][] ticTacToeGrid = new char[rows][columns];
    

    initiateBlankGrid(ticTacToeGrid, rows, columns);
    
    System.out.println("Hi! Let's play Tic Tac Toe!");
    System.out.println("Here's the empty grid: ");
    printGrid(ticTacToeGrid);
    System.out.println("These are the row and column numbers respectively: ");
    printRowAndColumnNumbers(ticTacToeGrid, rows, columns);
    System.out.println();
    System.out.println("Player 1 will have letter 'X', and Player 2 - letter 'O'!");
    System.out.println();
    initiateBlankGrid(ticTacToeGrid, rows, columns);

    while (true) {
      putSymbolOntoGrid(ticTacToeGrid, "Player 1", 'X');
      if (checkGameStatus(ticTacToeGrid, 'X', 'O')) {
          break;
      }
      putSymbolOntoGrid(ticTacToeGrid, "Player 2", 'O');
      if (checkGameStatus(ticTacToeGrid, 'X', 'O')) {
          break;
        }
    }
  }

  public static void initiateBlankGrid(char[][]grid, int rows, int columns) {
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < columns; j++) {
        grid[i][j] = ' ';
      }
    }
  }

  public static void printRowAndColumnNumbers(char[][]grid, int rows, int columns) {
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < columns; j++) {
        if (i == 0) {
          grid[i][j] = (char)(j + '0');
        }
        if (j == 0) {
          grid[i][j] = (char)(i + '0');
        }
      }
    }
    printGrid(grid);
  }

  public static void printGrid(char[][] grid) {
    System.out.println();
    for (int i = 0; i < grid.length; i++) {
            // Print the row
            for (int j = 0; j < grid.length; j++) {
                System.out.print(" " + grid[i][j] + " ");
                if (j < grid.length - 1) {
                    System.out.print("|");  // Print the vertical separator
                }
            }
            System.out.println();
            // Print the horizontal separator
            if (i < grid.length - 1) {
                for (int j = 0; j < grid.length; j++) {
                    System.out.print("---");
                    if (j < grid.length - 1) {
                        System.out.print("+");  // Print the intersection of lines
                    }
                }
                System.out.println();
            }
    }
    
    System.out.println();
  }

  public static int chooseRow(String player) {
    Scanner scan = new Scanner(System.in);
    int row = 0;
    while (true) {      
    System.out.println(player + ", please choose a row number: ");
      try {
        row = scan.nextInt();
        break;
      } catch (Exception e) {
        System.out.println("Error: " + e + "Please choose row No. 0, 1 or 2!");
        scan.next();
      }
    }
    return row;    
  }

  public static int chooseColumn(String player) {
  Scanner scan = new Scanner(System.in);
    int column = 0;
    while (true) {      
    System.out.println(player + ", please choose a column number: ");
      try {
        column = scan.nextInt();
        break;
      } catch (Exception e) {
        System.out.println("Error: " + e + "Please choose column No. 0, 1 or 2!");
        scan.next();
      }
    }
    return column;    
  }

  public static void putSymbolOntoGrid(char[][] grid, String player, char symbol) {
    int row = chooseRow(player);
    int column = chooseColumn(player);
    if (grid[row][column] == ' ') {
      grid[row][column] = symbol;
    } else {
      System.out.println("This spot on the grid already has been taken!");
      System.out.println("Other player, your turn!");
    }
    printGrid(grid);
    
  }

  public static boolean checkIfGameWon(char[][] grid, char symbol) {
    // check rows
    for (int i = 0; i < grid.length; i++) {
      boolean rowWin = true;
      for (int j = 0; j < grid[i].length; j++) {
        if (grid[i][j] != symbol) {
             rowWin = false;
            break;
        }
      }
      if (rowWin) {
        return true;
      }
    }

    // check columns
    for (int i = 0; i < grid.length; i++) {
       boolean colWin = true;
       for (int j = 0; j < grid.length; j++) {
        if (grid[j][i] != symbol) {
           colWin = false;
           break;
        }
      }
      if (colWin) {
        return true;
      }
    }
    
    //check diagonal (left to right)
    boolean diagWinLR = true;
    for (int i = 0; i < grid.length; i++) {
      if (grid[i][i] != symbol) {
        diagWinLR = false;
        break;
      }
    }
    if (diagWinLR) {
      return true;
    }

    // check diagonal (right to left)
    boolean diagWinRL = true;
    for (int i = 0; i < grid.length; i++) {
      if (grid[i][grid.length - 1 - i] != symbol) {
        diagWinRL = false;
        break;
      }
    }
    if (diagWinRL) {
      return true;
    }
   
    return false;
  }

  public static boolean checkGridIsFull(char[][] grid) {
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid.length; j++) {
        if (grid[i][j] == ' ') {
          return false;
        }
      }
    }
    return true;
  }

  public static boolean checkGameStatus(char[][] grid, char player1Symbol, char player2Symbol) {
  if (checkIfGameWon(grid, player1Symbol)) {
    System.out.println("Player 1 has won the game!");
    return true;
  }
  if (checkIfGameWon(grid, player2Symbol)) {
    System.out.println("Player 2 has won the game!");
    return true;
  }
  if (checkGridIsFull(grid)) {
    System.out.println("It's a draw!");
    return true;
  }
  return false;
  }
}
