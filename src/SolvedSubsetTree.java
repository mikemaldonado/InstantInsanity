
public class SolvedSubsetTree {
	int id;
	SolvedSubsetTree [] children;
	
	public SolvedSubsetTree(int cubeId, int childrenSize) {
		this.id = cubeId;
		children = new SolvedSubsetTree[childrenSize];
	}
	public void add(int cube, int size) {
		children[children.length - 1] = new SolvedSubsetTree(cube, size);
	}
}
