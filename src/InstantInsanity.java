import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class InstantInsanity {
	static ArrayList<Integer> colorCubes = new ArrayList<Integer>();
	private static Queue<Integer> thread = new LinkedList<Integer>();
	
	private static final int numOfCubeFaces = 3;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ColorCube cube1 = new ColorCube(new int []{0,1}, new int []{1,2}, new int []{3,5});
		ColorCube cube2 = new ColorCube(new int []{0,1}, new int []{1,2}, new int []{3,5});
		ColorCube cube3 = new ColorCube(new int []{0,1}, new int []{1,2}, new int []{3,5});
		
		colorCubes.add(0);
		colorCubes.add(1);
		colorCubes.add(2);
		
		List<Boolean> hasSolution = new ArrayList<Boolean>();
		/*
		for(int i = 1; i < colorCubes.length(); i++) {
			int num, k;
			k = num = num | (i << 1);
			for(int j = i; j < colorCubes.length - i; j++) {
				k = k & 3 << (i + j)
			}
		}*/

		for(int i = 2; i < colorCubes.length; i++) {
			for(int j = 0; j < i; j++) {
				int notThisIndex = (Math.pow(2, i) - 1) - Math.pow(2, j);
				hasSolution.add(dfsCubes(notThisIndex, i));
			}
		}
		
		System.out.println(hasSolution);
	}

	
	public static boolean dfsCubes(int voidIndex, int subsetSize) {
		if(thread.size() == subsetSize) {
			count++;
			//save thread optional
			//Debug thread here
			return true;
		}
		for(int i = 0; i < subsetSize && i != voidIndex; i++) {
			thread.add(i);
			if(solutionHasNoConflict(voidIndex, subsetSize))
				dfsCubes(voidIndex, subsetSize);
			thread.remove();
		}
			
		if(count == 2)
			return true;
		
		return false;
	}
	
	private static boolean solutionHasNoConflict(int voidIndex, int subsetSize) {
		
		//return true if next pair matches previous
		if(thread.size() > 1) {
			Iterator<Integer> it = thread.iterator();
			//thread contains a 0,1,or 2 which represents left-right, front-back, top-bottom
			int oppPair1 = it.next();
			int oppPair2 = it.next();
			return colorCubes[thread.size() - 1].getColorFaces()[oppPair1]
					.equals(colorCubes[thread.size() - 2)].getColorFaces()[oppPair2]);
		}
			
		return true;
	}

}
