import java.util.*;
public class GameOfLifeV3 {
	public static void main(String[] args) throws InterruptedException {
		String a = "";
		Scanner scan = new Scanner(System.in);
		String[][] board = new String[11][11];
//Start Game
		System.out.print("Would you like to play a game (y/n)? ");
		a = scan.next();
		if (a.equalsIgnoreCase("y") == false)
			System.exit(0);
//Clears screen
		clearScreen();
//Shows empty board and instructions
		board = clearBoard(board);
		returnNewBoard(board);
		System.out.println();
		System.out.println();
		System.out.println("Here is your board with 0 as a live cell and + as space: ");
System.out.println();
		int end = 0;
		int counter = 0;
//Starts entering positions
		int ii = 0;
		while (end == 0 && counter <= 100){
			returnNewBoard(board);
			System.out.println();
			System.out.print("Would you like to make a move, stop moving and start the game, or undo a move (m/s/u)? ");
			a = scan.next();
			counter ++;
//Last move than start engine
			if (a.equalsIgnoreCase("start") || a.equalsIgnoreCase("s")) {
				Thread.sleep(2000);	
			end = 1; 
			}
//Undo move(s)
			if (a.equalsIgnoreCase("undo") || a.equalsIgnoreCase("u")) {
				a = grabA();
				if (a.length() <2 || a.length() > 4 || a.substring(0,1).equals("0")){
					clearScreen();
					System.out.println("Error, incorrect value entered!");
					Thread.sleep(750);	
					}
				if(!(a.length() <2 || a.length() > 4))
				{ii = 1;
					handleCoordinates(a,board,ii);
					returnNewBoard(board);
				}
				returnNewBoard(board);
			}
			if (a.equalsIgnoreCase("move") || a.equalsIgnoreCase("m")){
		a=grabA();
		if (!(a.length() <2 || a.length() > 4))
		{ii = 0;
			handleCoordinates(a,board,ii);
			returnNewBoard(board);
		}
		if (a.length() <2 || a.length() > 4 || a.substring(0,1).equals("0")){
			clearScreen();
			System.out.println("Error, incorrect value entered!");
			Thread.sleep(750);	
			}}}		
		
		int iiiiii = 0;
		int count = 0;
		while (iiiiii == 0){
	ArrayList<Integer> list = findAlive(board);
	int[][] touching = findTouchingNumber(list);	
	for (int i = 0; i < touching.length; i++)
	{
		if(touching[i][2] == 3)
			board = addCell(board,touching[i][0],touching[i][1]);
		if((touching[i][2] < 2) || (touching[i][2] > 3))
			board = removeCell(board,touching[i][0],touching[i][1]);		
	}
	if (list.size()/2 > 0){
	Thread.sleep(750);
	returnNewBoard(board);
	System.out.println("Iteration # " + count);
	list = clearValues(list);
	count ++;}
	if (list.size() == 0){
	returnNewBoard(board);
	System.out.println();
	System.out.println();
	System.out.println("You are Dead!");
	System.out.println();		
	iiiiii = 1;
	System.exit(0);}}}
	
public static int[][] findTouchingNumber(ArrayList<Integer> list)
	{int aa = 0;
	int[][] touching = new int[100][3];
	for (int y = 1; y < 11; y++)
	{	
		for (int x = 1; x < 11; x += 1){
			int touchingInt = 0;
			for (int a = 0; a < list.size(); a += 2)
		{if ((((Math.abs(x-list.get(a))) == 1) && ((Math.abs(y-list.get(a+1))) == 1)) || (((Math.abs(x-list.get(a))) == 1) && ((Math.abs(y-list.get(a+1))) == 0)) || (((Math.abs(x-list.get(a))) == 0) && ((Math.abs(y-list.get(a+1))) == 1))){
			touchingInt ++;
		}}	
		touching[aa][0] = x;
		touching[aa][1] = y;
		touching[aa][2] = touchingInt;
		aa++;
		}}
	return touching;
	}
	
public static ArrayList<Integer> clearValues(ArrayList<Integer> a)
	{for (int i = 0; i < a.size(); i++)
	{
		a.remove(0);
	}
	return a;}

public static String grabA()
	{
	Scanner scan = new Scanner(System.in);
	System.out.println();
	System.out.print("Please enter the coordinates like 'xy' without the quotes: ");
	String a = scan .next();
	return a;
	}
	
public static String setString(String a,int place)
	{
	String x = ""+a.charAt(place);
	return x;
	}
	
public static String[][] cellInput (String x, String y, String[][] board, int ii)
	{
	String[][] boardDuplicateTemp = board;
	int xCoordinate = Integer.parseInt(x);
	int yCoordinate = Integer.parseInt(y);
	if (ii == 0)
		//if ii add cell or else remove cell
	boardDuplicateTemp = addCell(board,xCoordinate,yCoordinate);
	if (ii != 0)
	boardDuplicateTemp = removeCell(board,xCoordinate,yCoordinate);
	return boardDuplicateTemp;
	}
	
public static String[][] addCell (String[][] board, int x, int y)
	{
	board[y][x] = "0 ";
	return board;
	}

public static String[][] removeCell (String[][] board, int x, int y)
	{
	board[y][x] = "+ ";
	return board;
	}
	
public static String[][] clearBoard(String[][] board)
	{
	for (int i = 0;i<board.length; i++)
	{
		for (int jij = 0;jij<board[i].length; jij++)
			board[i][jij] = "+ ";
	}
	for (int i = 1;i<board.length; i++)
	{
		String aa = ""+(i);
		board[0][i] = aa + " ";
		board[i][0] = aa + "  ";
	}
	board[10][0] = ""+10 + " ";
	board[0][0] = "   ";
	return board;
}
	
public static void clearScreen()
	{
		for (int ii = 0; ii <= 60; ii++)
		{
			System.out.println();
		}
	}
	
public static void returnNewBoard(String[][] board)
	{
		clearScreen();
		for (int i = 0;i<board.length; i++)
		{
			for (int z = 0;z<board[i].length; z++)
			{
				System.out.print(board[i][z]);
			}
			System.out.println();
		}
	}

public static ArrayList<Integer> findAlive(String[][] board)
	{
	ArrayList<Integer> list = new ArrayList<Integer>();
	for(int i = 0;i < board.length;i++)
	{
		for (int a = 0; a < board[i].length; a++)
		{
			if(board[i][a].equals("0 ")){
				list.add(a);	
				list.add(i);
	}}}
	return list;
	}

public static String[][] handleCoordinates(String a, String[][] board, int ii)
	{
	if (a.length() == 2)
	{
		String x = setString(a,0);
		String y = setString(a,1);
		board = cellInput (x, y, board,ii);
	}
	//for handling a 102 or 210 for 10,2
	if (a.length() == 3 && (a.substring(1).equals("10")))
	{
		board = cellInput(a.substring(0,1),"10",board,ii);
	}
	if (a.length() == 3 && (a.substring(0,2).equals("10")))
	{
		board = cellInput("10",a.substring(2),board,ii);
	}
	//handling two 10 values
	if (a.length() == 4)
	{
		board = cellInput("10","10",board,ii);
	}	
	return board;}

}
