import java.util.*;

public class Main {
  public static final int GRID_SIZE = 9;
  public static void main(String[] args) {
    int[][] grid = new int[][] {
      {0,0,3,0,2,0,6,0,0},
      {9,0,0,3,0,5,0,0,1},
      {0,0,1,8,0,6,4,0,0},
      {0,0,8,1,0,2,9,0,0},
      {7,0,0,0,0,0,0,0,8},
      {0,0,6,7,0,8,2,0,0},
      {0,0,2,6,0,9,5,0,0},
      {8,0,0,2,0,3,0,0,9},
      {0,0,5,0,1,0,3,0,0}
    };
    // int[][] grid = new int[][] {
    //   {4,8,3,9,2,1,6,5,7},
    //   {9,6,7,3,4,5,8,2,1},
    //   {2,5,1,8,7,6,4,9,3},
    //   {5,4,8,1,3,2,9,7,6},
    //   {7,2,9,5,6,4,1,3,8},
    //   {1,3,6,7,9,8,2,4,5},
    //   {3,7,2,6,8,9,5,1,4},
    //   {8,1,4,2,5,3,7,6,9},
    //   {6,9,5,4,1,7,3,8,0} // last one should be 2
    // };
    int numOfZeros = countZeros(grid);
    while (true) {
      showGrid(grid);

      // input
      int[] inputData = getInput();
      int row = inputData[0];
      int column = inputData[1];
      int value = inputData[2];
      
      if (isValueZero(value) || isNumberExisted(grid, row, column)) continue;
      putNumberToGrid(grid, row, column, value);
      if (--numOfZeros == 0) {
        System.out.println(solveBoard(grid) ? "Congrats!!!!!" : "Wrong answer");
        System.exit(0);
      }
    }
  }

  private static void showGrid(int[][] grid) {
    for (int i = 0; i < GRID_SIZE; i++) {
      for (int j = 0; j < GRID_SIZE; j++) {
        System.out.print(grid[i][j]);
        if (j % 3 == 2) System.out.print("|");
      }
      System.out.println();
    }
  }

  private static int[] getInput() {
    Scanner scan = new Scanner(System.in);
    System.out.println("Enter the row column value like 0 0 7 (index: 0-8) (value: 1-9) or quit");
    String input = scan.next();
    if (input.equals("quit")) System.exit(0);;
    int row = Integer.parseInt(input);
    int column = scan.nextInt();
    int value = scan.nextInt();
    System.out.println("row idx: " + row+ ", column idx: "+column + ", val: " +value);
    return new int[] {row, column, value};
  }
  private static boolean isValueZero(int value) {
    if (value == 0) {
      System.out.println("0 is invalid number");
      return true;
    }
    return false;
  }
  private static boolean isNumberExisted(int[][] grid, int row, int column) {
    if (grid[row][column] != 0) {
      System.out.println("The selected grid is already Exsisted.");
      return true;
    }
    return false;
  }
  private static void putNumberToGrid(int[][] grid, int row, int column, int value) {
    grid[row][column] = value;
  }
  private static int countZeros(int[][] grid) {
    int count = 0;
    for (int i = 0; i < GRID_SIZE; i++) {
      for (int j = 0; j < GRID_SIZE; j++) {
        if (grid[i][j] == 0) count++;
      }
    }
    return count;
  }
  
  private static boolean isRowValid(int[] row) {
    Set<Integer> set = new HashSet<>();
    for (int i = 0; i < row.length; i++) {
        if (row[i] == 0) continue;
        if (set.contains(row[i])) return false;
        set.add(row[i]);
    }
    return true;
  }

  private static boolean isColumnValid(int[][] grid, int column) {
    Set<Integer> set = new HashSet<>();
    for (int i = 0; i < grid.length; i++) {
        if (grid[i][column] == 0) continue;
        if (set.contains(grid[i][column])) return false;
        set.add(grid[i][column]);
    }
    return true;
  }

  private static boolean isGridsValid(int[][] grid, int row, int column) {
    Set<Integer> set = new HashSet<>();
    row *= 3;
    column *= 3;
    for (int i = row; i < row + 3; i++) {
        for (int j = column; j < column + 3; j++) {
            if (grid[i][j] == 0) continue;
            if (set.contains(grid[i][j])) return false;
            set.add(grid[i][j]);
        }
    }
    return true;
  }

  private static boolean solveBoard(int[][] grid) {
    // check all the rows
    for (int i = 0; i < GRID_SIZE; i++) {
        if (!isRowValid(grid[i])) return false;
    }
    // check all the columns
    for (int i = 0; i < GRID_SIZE; i++) {
        if (!isColumnValid(grid, i)) return false;
    }
    // check all 3*3 grids 
    for (int i = 0; i < 3; i++) {
        for (int j = 0; j < 3; j++){
            if (!isGridsValid(grid, i, j)) return false;
        }
    }
    return true;
  }
}
