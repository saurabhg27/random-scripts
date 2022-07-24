package ball_sort_java;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


public class Solver {

	static final boolean debug = false;

	public static void main(String[] args) {
		
		//read image and get the string representation
		ImageReader.readColors("pic.jpg",6,5);
		
		//Actually solve the puzzle
		Solver thiss = new Solver();
		thiss.mainn();
	}

	private void mainn() {

		//TODO copy paste the image reader output here
		String stacks[] = new String[]{"bbba","fedc","aecc","dbcg","iifh","fhhe","dgei","aagi","gdfh","",""};

		int numberOfStacks = stacks.length;

		List<String> grid = configureGrid(stacks, numberOfStacks);
		System.out.println("Grid: "+grid);
		if (!checkGrid(grid)) {
			System.err.println("Invalid Grid ");
		}
		if (isSolved(grid, getStackHeight(grid))) {
			System.out.println("Problem is already solved");
		}
		List<String> visited = new ArrayList<>();
		List<List<Integer>> answerMod = new ArrayList<>();

		// Solve the puzzle instance
		solvePuzzle(grid, getStackHeight(grid), visited, answerMod);

		// Since the values of Answers are appended When the problem was completely solved and backwards from there
		Collections.reverse(answerMod);
		for (List<Integer> v : answerMod) {
			System.out.println("Move " + (v.get(0) + 1) + " to " + (v.get(1) + 1) + " " + v.get(2) + " times");
		}
	}

	List<String> configureGrid(String stacks[], int numberOfStacks) {

		List<String> grid = new ArrayList<>();
		for (int i = 0; i < numberOfStacks; i++)
			grid.add(stacks[i]);

		return grid;
	}

	// Function to find the max
	static int getStackHeight(List<String> grid) {
		int max = 0;
		for (String stack : grid)
			if (max < stack.length())
				max = stack.length();
		return max;
	}

	// Convert vector of strings to	canonicalRepresentation of strings
	static String canonicalStringConversion(List<String> grid) {
		List<String> copy = new ArrayList<>(grid);
		String finalString = "";
		Collections.sort(copy);
		for (String stack : copy) {
			finalString += (stack + ";");
		}
		return finalString;
	}

	// Function to check if it is solved or not
	static boolean isSolved(List<String> grid, int stackHeight) {

		for (String stack : grid) {
			if (stack.length() == 0)
				continue;
			else if (stack.length() < stackHeight)
				return false;
			else if (stdcount(stack, stack.charAt(0)) != stackHeight)
				return false;
		}
		return true;
	}

	private static int stdcount(String str, char ch) {
		int ans = 0;
		ans = (int) str.chars().filter(c -> c == ch).count();
		return ans;
	}

	static boolean isValidMove(String sourceStack, String destinationStack, int height) {
		boolean ans = _isValidMove( sourceStack,  destinationStack,  height);
		//System.out.println("src: "+sourceStack+" dst: "+destinationStack+" isValidMove: "+ans);
		return ans;
	}

	// Check if the move is valid
	static boolean _isValidMove(String sourceStack, String destinationStack, int height) {

		// Can't move from an empty stack or to a FULL STACK
		if (sourceStack.length() == 0 || destinationStack.length() == height)
			return false;

		int colorFreqs = stdcount(sourceStack, sourceStack.charAt(0));
		// If the source stack is same colored, don't touch it
		if (colorFreqs == height)
			return false;

		if (destinationStack.length() == 0) {

			// If source stack has only	same colored balls, don't touch it
			if (colorFreqs == sourceStack.length())
				return false;
			return true;
		}
		return sourceStack.charAt(sourceStack.length() - 1) == destinationStack.charAt(destinationStack.length() - 1);
	}

	// Function to solve the puzzle
	boolean solvePuzzle(List<String> grid, int stackHeight, List<String> visited, List<List<Integer>> answerMod)
	{
		if (stackHeight == -1) {
			stackHeight = getStackHeight(grid);
		}
		visited.add(canonicalStringConversion(grid));
		for (int i = 0; i < grid.size(); i++) {

			// Iterate over all the stacks
			String sourceStack = grid.get(i);
			for (int j = 0; j < grid.size(); j++) {
				if (i == j)
					continue;
				String destinationStack = grid.get(j);
				if (isValidMove(sourceStack, destinationStack, stackHeight)) {

					// Creating a new Grid with the valid move
					List<String> newGrid = new ArrayList<>(grid);

					// Adding the ball
					newGrid.set(j, newGrid.get(j)+newGrid.get(i).charAt(newGrid.get(i).length()-1));

					// Adding the ball
					newGrid.set(i, newGrid.get(i).substring(0,newGrid.get(i).length()-1));

					if (isSolved(newGrid, stackHeight)) {
						answerMod.add(Arrays.asList(i,j,1));
						if(debug) {
							System.out.println("solved: "+newGrid);
						}
						return true;
					}
					if(!visited.contains(canonicalStringConversion(newGrid))) {
						if(debug) {
							System.out.println(" visited: "+visited+" newgr: "+newGrid);
						}
						boolean solveForTheRest = solvePuzzle(newGrid, stackHeight, visited, answerMod);
						if (solveForTheRest) {
							List<Integer> lastMove = answerMod.get(answerMod.size()-1);

							// Optimisation - Concatenating consecutive moves of the same ball
							if (lastMove.get(0) == i && lastMove.get(1) == j) {
								int idx = answerMod.size()-1;
								int bla = answerMod.get(idx).get(2)+1;
								List<Integer> li = answerMod.get(idx);
								answerMod.set(idx, Arrays.asList(li.get(0),li.get(1),bla));
							}
							else {
								answerMod.add(Arrays.asList(i,j,1));
							}
							return true;
						}
					}
				}
			}
		}
		return false;
	}



	// Checks whether the grid is valid or not
	boolean checkGrid(List<String> grid)
	{

		int numberOfStacks = grid.size();
		int stackHeight = getStackHeight(grid);
		int numBallsExpected = ((numberOfStacks - 2) * stackHeight);
		// Cause 2 empty stacks
		int numBalls = 0;

		for (String i : grid)
			numBalls += i.length();
		
		if (numBalls != numBallsExpected) {
			System.out.println("Grid has incorrect # of balls");
			return false;
		}
		Map<Character, Integer> ballColorFrequency = new HashMap<>();
		for (String stack : grid) {
			for (char ball : stack.toCharArray()) {
				ballColorFrequency.put(ball, ballColorFrequency.getOrDefault(ball, 0) + 1);
			}
		}
		for (Entry<Character, Integer> ballColor : ballColorFrequency.entrySet()) {
			if (ballColor.getValue() != getStackHeight(grid)) {
				System.err.println("Color " + ballColor.getKey() + " is not " + getStackHeight(grid));
				return false;
			}
		}
		return true;
	}



}
