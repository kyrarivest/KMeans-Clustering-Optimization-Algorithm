package pa06;

import java.util.ArrayList;

/**
 * A Sample represents a vector of doubles to be used in a clustering algorithm...
 * @authors Patrick Lee, Denise Zhong, Caelan Gawah-Meaden, Kyra Rivest
 *Added a toString() and a Distance() method onto the source code from Prof. Hickey's repo
 */

public class Sample implements Comparable<Sample> {
	public ArrayList<Double> sample;
	
	//constructors for different cases
	public Sample(double[] values) {
		this.sample = new ArrayList<Double>();
		for (int i=0; i<values.length; i++) {
			sample.add(values[i]);
		}	
	}

	public Sample(int[] values) {
		this.sample = new ArrayList<Double>();
		for (int i=0; i<values.length; i++) {
			sample.add((double) values[i]);
		}
	}

	public Sample() {
		this.sample = new ArrayList<Double>();
	}

	//Organize sample in a string for printing
	public String toString() {
		if(this.sample.size() > 0) {
			String output = "{";
			for (double n : this.sample) {   // more efficient because iterator method is used. (instead of get() )
				output +=  (int)n + ", ";
			}
			output = output.substring(0,output.length()-2);
			output += "}";
			return output;
		}else{
			return "null";
		}
	}
	
	//Finds the distance between two Samples
	public double distanceTo(Sample s2) { //needs interface if your parameter is assumed to be Sample type
		double sum = 0;
		for(int i = 0; i< s2.sample.size(); i++) {
			sum += Math.pow(this.sample.get(i)-s2.sample.get(i),2);
		}
		return Math.sqrt(sum);
	}

	//test (static method)
	public static void main(String[] args) {
		System.out.println("testing for the Sample class.");
		double[] p1 = {-3,0.2};
		double[] p2 = {7, -4};
		Sample s1 = new Sample(p1);
		Sample s2 = new Sample(p2);
		System.out.println("s1="+s1.toString());
		System.out.println("s2="+s2.toString());
		System.out.println(s1.distanceTo(s2));
		
	}
	


}
