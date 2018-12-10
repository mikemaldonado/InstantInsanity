import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.TreeSet;

public class InstantInsanity {
	static List<ColorCube> colorCubes = new ArrayList<ColorCube>();
	private static Queue<Integer> thread = new LinkedList<Integer>();
	private static List<SolvedSubsetTree> solvedSubsets = new ArrayList<SolvedSubsetTree>();
	
	private static final int numOfCubeFaces = 3;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ColorCube cube1 = new ColorCube(new int []{0,1}, new int []{1,2}, new int []{3,5});
		ColorCube cube2 = new ColorCube(new int []{0,1}, new int []{1,2}, new int []{3,5});
		ColorCube cube3 = new ColorCube(new int []{0,1}, new int []{1,2}, new int []{3,5});
		
		colorCubes.add(cube1);
		colorCubes.add(cube2);
		colorCubes.add(cube3);
		
		List<Boolean> hasSolution = new ArrayList<Boolean>();
		for(int i = 2; i < colorCubes.size(); i++) {
			int [] cubeRows = new int [i];
			do{
				hasSolution.add(dfsCubes(cubeRows, 0));
			}while(nextSubset(cubeRows));
		}
		
		
		System.out.println(hasSolution);
	}
	//updates cubeRows with our selected subset. Only the true values are valid
	private static  boolean nextSubset(int [] cubeRows) {
		int i = 0;
		for( ; i < cubeRows.length && cubeRows[i] == 25; i++) {
			int toppedOutIndex = cubeRows[i];
			//reset to value greater than left index to account for symmetry
			//i.e [0,1]..[0,25],[1,2]...[1,25],[2,3]
			cubeRows[i] = cubeRows[i+1] + 2;
			//1st parameter sets to significant index which changes for every 25 - leftsignificantIndex
			int childSize = toppedOutIndex - cubeRows[i];
			solvedSubsets.add(new SolvedSubsetTree(cubeRows[i+1], childSize));
		}
		if ( i == cubeRows.length) {
			return false;
		}
		
		cubeRows[i]++;
		
		return true;
	}
	
	/*
	private static boolean checkCubesForConflict(int [] cubeRows) {
		retrieve keys of the cubes being used e.g. [2,4,5,8] -> work with cubes 2,4,5,8 only
		List<Integer> cubeIndices = getCubeIndices(cubeRows);
		return dfsCubes(cubeIndices, 0);
	}
	*/
	public static boolean dfsCubes(int [] cubeRows, int count) {
		if(thread.size() == cubeRows.length) {
			count++;
			//save thread optional
			//Debug thread here
			return true;
		}
		for(int j = 0; j < 3; j++) {
			thread.add(j);
			if(solutionHasNoConflict(cubeRows)) {
				//get last tree created in solvedSubsets
				//param1: 
				//param2: remaining cubes after earlier cubes are taken e.g cube 3 can only have cube 4 ... cube n
				int lastIndex = cubeRows.length - 1;
				solvedSubsets.get(solvedSubsets.size() - 1).add(cubeRows[lastIndex], colorCubes.size() - cubeRows[lastIndex] );
				dfsCubes(cubeRows, count);
			}
			thread.remove();
		}
			
		if(count == 2)
			return true;
		
		return false;
	}
	
	private static boolean solutionHasNoConflict(int [] cubeRows) {
		
		//return true if next pair matches previous
		if(thread.size() > 1) {
			Iterator<Integer> it = thread.iterator();
			//thread contains a 0,1,or 2 which represents left-right, front-back, top-bottom
			int oppPair1 = it.next();
			int oppPair2 = it.next();
			return colorCubes.get(

					//size of thread tells us where to look within our cubeRows table
					cubeRows.get(thread.size() - 1).getColorFaces()[oppPair1]
					.equals(colorCubes.get(cubeIndices.get(thread.size() - 2)).getColorFaces()[oppPair2]);
		}
			
		return true;
	}
	
	private static List<Integer> getCubeIndices(boolean [] cubeRows) {
		List<Integer> cubeIndices = new ArrayList<Integer>();
		for(int i =0; i < cubeRows.length; i++) {
			if(cubeRows[i] == true)
				cubeIndices.add(i);
		}
		return cubeIndices;
	}

}
