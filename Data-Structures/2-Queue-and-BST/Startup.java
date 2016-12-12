public class Startup{
	
	public static void main(String[] args){	
		final int nullSymbol = 0;

		Integer[] breadthFirstSequence = {27, 13, 59, 3, 17, 43, 107, 2, 11};
		
		if(args.length > 0){			
			breadthFirstSequence = produceIntegerArray(args);			
		}else{
			System.out.println("Using default breadth-first traversal breadthFirstSequence = " 
				+ java.util.Arrays.toString(breadthFirstSequence));
		}

		BinarySearchTree<Integer> tree = new BinarySearchTree<Integer>(breadthFirstSequence, nullSymbol);

		System.out.println("Height = " + tree.height());
		System.out.println("Width = " + tree.width());
		System.out.println("preOrderTraverse: " + tree.preOrderTraverse());		
		System.out.println("postOrderTraverse: " + tree.postOrderTraverse());		
		System.out.println("inOrderTraverse: " + tree.inOrderTraverse());
		System.out.println("breadthFirstTraverse: " + tree.breadthFirstTraverse());

		System.out.println("Removing 59");
		tree.remove(59);
		System.out.println("After removal of 59 ------------------------------");

		System.out.println("Height = " + tree.height());
		System.out.println("Width = " + tree.width());
		System.out.println("preOrderTraverse: " + tree.preOrderTraverse());		
		System.out.println("postOrderTraverse: " + tree.postOrderTraverse());		
		System.out.println("inOrderTraverse: " + tree.inOrderTraverse());
		System.out.println("breadthFirstTraverse: " + tree.breadthFirstTraverse());
		
		System.out.println("\n DONE!");
	}

	/**
	* Internal method for converting a given array of Strings to an array of integers.
	*/
	private static Integer[] produceIntegerArray(String[] args){
		Integer[] toReturn = new Integer[args.length];
		try{
			for(int i = 0; i < args.length; i++){
				toReturn[i] = Integer.parseInt(args[i]);
			}
		}catch(NumberFormatException e){
			System.out.println("All arguments must be integers");
			e.printStackTrace();
			System.exit(1);
		}
		return toReturn;
	}
}