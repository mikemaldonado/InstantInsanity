import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class InstantInsanity {
	static Map<Integer, ColorCube> colorCubes = new HashMap<Integer, ColorCube>();
	private static Queue<Integer> thread = new LinkedList<Integer>();
	
	private static final int numOfCubeFaces = 3;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ColorCube cube1 = new ColorCube(new int []{0,1}, new int []{1,2}, new int []{3,5});
		ColorCube cube2 = new ColorCube(new int []{0,1}, new int []{1,2}, new int []{3,5});
		ColorCube cube3 = new ColorCube(new int []{0,1}, new int []{1,2}, new int []{3,5});
		
		colorCubes.put(0, cube1);
		colorCubes.put(1, cube2);
		colorCubes.put(2, cube3);
		
		List<Boolean> hasSolution = new ArrayList<Boolean>();
		boolean [] cubeRows = new boolean [colorCubes.size()];
		do {
			hasSolution.add(checkCubesForConflict(cubeRows));
		}while(nextSubset(cubeRows));
		
		System.out.println(hasSolution);
	}
	//updates cubeRows with our selected subset. Only the true values are valid
	private static  boolean nextSubset(boolean [] cubeRows) {
		int i = 0;
		while(i < cubeRows.length && cubeRows[i]) {
			cubeRows[i] = false;
			i++;
		}
		if ( i == cubeRows.length) {
			return false;
		}
		
		cubeRows[i] = true;
		
		return true;
	}
	
	
	private static boolean checkCubesForConflict(boolean [] cubeRows) {
		//retrieve keys of the cubes being used e.g. [2,4,5,8] -> work with cubes 2,4,5,8 only
		List<Integer> cubeIndices = getCubeIndices(cubeRows);
		return dfsCubes(cubeIndices, 0);
	}
	
	public static boolean dfsCubes(List<Integer> cubeIndices, int count) {
		if(thread.size() == cubeIndices.size()) {
			count++;
			//save thread optional
			//Debug thread here
			return true;
		}
		for(int j = 0; j < 3; j++) {
			thread.add(j);
			if(solutionHasNoConflict(cubeIndices))
				dfsCubes(cubeIndices, count);
			thread.remove();
		}
			
		if(count == 2)
			return true;
		
		return false;
	}
	
	private static boolean solutionHasNoConflict(List<Integer> cubeIndices) {
		
		//return true if next pair matches previous
		if(thread.size() > 1) {
			Iterator<Integer> it = thread.iterator();
			//thread contains a 0,1,or 2 which represents left-right, front-back, top-bottom
			int oppPair1 = it.next();
			int oppPair2 = it.next();
			return colorCubes.get(
					//get selected indices instead of contiguous indices
					//size of thread tells us where to look within our cubeIndices table
					cubeIndices.get(thread.size() - 1)).getColorFaces()[oppPair1]
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
