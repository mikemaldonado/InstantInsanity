
public class ColorCube {
	private static final int numOfFaces = 3;
	ColorPair [] colorFaces;

	public ColorCube(int [] oppositePair1, int []  oppositePair2, int []  oppositePair3) {
		colorFaces = new ColorPair[numOfFaces];
		colorFaces[0] = new ColorPair(oppositePair1[0], oppositePair1[1]);
		colorFaces[1] = new ColorPair(oppositePair2[0], oppositePair2[1]);
		colorFaces[2] = new ColorPair(oppositePair3[0], oppositePair3[1]);
	}
	
	class ColorPair {
		int firstPair;
		int secondPair;
		ColorPair() {
			firstPair = -1;
			secondPair = -1;
		}
		ColorPair(int first, int second) {
			firstPair = first;
			secondPair = second;
		}
		public int getFirstPair() {
			return firstPair;
		}
		public void setFirstPair(int firstPair) {
			this.firstPair = firstPair;
		}
		public int getSecondPair() {
			return secondPair;
		}
		public void setSecondPair(int secondPair) {
			this.secondPair = secondPair;
		}
		public boolean equals(ColorPair o) {
			if((this.firstPair == o.firstPair && this.secondPair == o.secondPair) || (this.firstPair == o.secondPair && this.secondPair == o.firstPair))
					return true;
			return false;
		}
			
	}
	
	public ColorPair[] getColorFaces() {
		return colorFaces;
	}
	public void setColorFaces(ColorPair[] colorFaces) {
		this.colorFaces = colorFaces;
	}
	
	
	
}
