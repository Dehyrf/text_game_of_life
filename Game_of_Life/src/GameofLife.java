import java.util.*;
public class GameofLife {
	public static void main(String[] args) throws InterruptedException {
		
	int loopCountMax = 1000; //change me to change max iterations of the game
	int millisecondsPerIteration = 250; //change to change fps
	int loopCount = 0;
	boolean noEnd = true;
	String tempString = printString("Would you like to play a game (y/n)? ");
		if (tempString.equalsIgnoreCase("y") == false)
			System.exit(0);
	int width = printInt("How wide do you want the board (up to 99)");
	int height = printInt("How tall do you want the board (up to 99)");
		if (height > 99 || height ==0)
			System.exit(0);
		if (width > 99||width==0)
			System.exit(0);
	String[][] board = new String[height +2][width+1];
	clearScreen();
	for (int i = 0;i<board.length; i++)
	{
		for (int jij = 0;jij<board[i].length; jij++)
			board[i][jij] = "+ ";
	}
	board[0][0] = "   ";
	board[1][0] = "   ";
	for (int i = 1;i<board.length-1;i++)
	{if (i < 10)
		tempString = "" + i + "  ";
	else 
		tempString = "" + i+ " ";
		board[i+1][0] = tempString;
	}	
	for (int i = 1;i<board[0].length;i++)
	{if (i < 10){
		tempString = "" + i + " ";
		board[1][i] = tempString;
		board[0][i] = "  ";}
	else 
		{tempString = "" + i + " ";
		board[1][i] = tempString.substring(1);
		board[0][i] = tempString.substring(0,1) + " ";
		}
	}	
	returnNewBoard(board);
	System.out.println();
	System.out.println();
	System.out.println("Here is your board with 0 as a live cell and + as space: ");
	System.out.println();
	while (noEnd){
		returnNewBoard(board);
		System.out.println();
		tempString = printString("Would you like to make a move, stop moving and start the game, undo a move, or exit (m/s/u/e)? ");
		if (tempString.equalsIgnoreCase("start") || tempString.equalsIgnoreCase("s")) {
			Thread.sleep(2000);	
			noEnd = false;}
		if (tempString.equalsIgnoreCase("undo") || tempString.equalsIgnoreCase("u")) {
			board = processChoice(board,0);
			returnNewBoard(board);}
		if (tempString.equalsIgnoreCase("move") || tempString.equalsIgnoreCase("m")){
			board = processChoice(board,1);
			returnNewBoard(board);}
		if (tempString.equalsIgnoreCase("exit") || tempString.equalsIgnoreCase("e")){
			System.exit(0);
	    }}
	noEnd = true;
	while (noEnd && loopCount <= loopCountMax){
		ArrayList<Integer> list = findAlive(board);
		int[][] touching = findTouchingNumber(list,board);	
		for (int i = 0; i < touching.length; i++)
		{
			if(touching[i][2] == 3)
				board = addCell(board,touching[i][0],touching[i][1]);
			if((touching[i][2] < 2) || (touching[i][2] > 3))
				board = removeCell(board,touching[i][0],touching[i][1]);		
		}
		if (list.size()/2 > 0){
		Thread.sleep(millisecondsPerIteration);
		returnNewBoard(board);
		System.out.println("Iteration # " + loopCount);
		list = clearValues(list);
		loopCount ++;}
		if (list.size() == 0){
		returnNewBoard(board);
		System.out.println();
		System.out.println();
		System.out.println("You are Dead!");
		System.out.println();		
		noEnd = false;
		System.exit(0);}	
	
	}
	}	
	
	
	
	
	
	
	
	
	
	
	
	public static String printString (String a)
	{
		Scanner scan = new Scanner(System.in);
		System.out.print(a);
	    String rtn = scan.next();
		return rtn;
	}
	public static int printInt (String a)
	{
		Scanner scan = new Scanner(System.in);
		System.out.print(a);
	    int rtn = scan.nextInt();
		return rtn;
	}
	public static void clearScreen()
	{
		for (int i = 0; i <= 60; i++)
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
	public static String[][] removeCell (String[][] board, int x, int y)
	{
	board[y][x] = "+ ";
	return board;
	}
	public static String[][] addCell (String[][] board, int x, int y)
	{
	board[y][x] = "0 ";
	return board;
	}
	public static String[][] processChoice (String[][] board, int choice) throws InterruptedException
	{
		System.out.println();
		int tempX=printInt("Please enter the X coordinate: ");
		int tempY=printInt("Please enter the Y coordinate: ");
		if (tempX > board[0].length || tempY > board.length || tempX ==0 || tempY ==0){
			clearScreen();
			System.out.println("Error, incorrect value entered!");
			Thread.sleep(750);	
			return board;
			}
		if (choice == 0){
			board = removeCell(board,tempX,tempY+1); }
		if (choice == 1){
			board = addCell(board,tempX,tempY+1); }
		return board;
	}
	public static int[][] findTouchingNumber(ArrayList<Integer> list,String[][] board)
	{int aa = 0;
	int[][] touching = new int[board.length*board[0].length][3];
	for (int y = 2; y < board.length; y++)
	{	
		for (int x = 1; x < board[y].length; x += 1){
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
	public static ArrayList<Integer> clearValues(ArrayList<Integer> a)
	{for (int i = 0; i < a.size(); i++)
	{
		a.remove(0);
	}return a;}}
