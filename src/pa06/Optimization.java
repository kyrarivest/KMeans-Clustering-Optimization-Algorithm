package pa06;

import java.io.FileNotFoundException;

/*
 * This class executes the algorithm and out prints the result
 * Each tick is one step in solving the algorithm which iterates through
 * each Sample point and assigns it to a Cluster, then find the average of the 
 * Cluster, sets a new Clusterpt, and reassigns the Sample points to new Clusters
 * @authors Patrick Lee, Denise Zhong, Caelan Gawah-Meaden, Kyra Rivest
 */
public class Optimization {
	
	public static final int ITERATIONS = 1000;
	
    public static void main(String[] args) throws FileNotFoundException {
    	
    	 //First initialize the kmeans with initial clusterpts(Centroids)
        KMeans kmeans = new KMeans();
        System.out.println(kmeans);
        kmeans.fillClusters();
     //   System.out.println(kmeans.originalData);
     //   System.out.println();

        //until the clusterpts stop moving, iterate through this loops which runs the algorithms
        for(int i=0; i < ITERATIONS; i++) {
          //  System.out.println(kmeans);
            kmeans.tick();
          //  System.out.println(kmeans);
        }
        System.out.println(kmeans);
    }
}