package pa06;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 * A cluster is a cluster point (which is itself a sample)
 * and a list of Samples (the one's closest to that sample point, ideally).
 * @authors Patrick Lee, Denise Zhong, Caelan Gawah-Meaden, Kyra Rivest
 *
 */
public class Cluster {
    public ArrayList<Sample> cluster; 
    public Sample clusterpt;

    //Constructor creates new cluster with input
    public Cluster(Cluster originaldata){
           cluster = new ArrayList<Sample>();
           setClusterpt(originaldata);
    }

    //Constrctor creates new empty cluster
    public Cluster(){
        cluster = new ArrayList<Sample>();
        clusterpt = new Sample();
    }
    
    //used at the beginning when you initialize the clusters
    public void setClusterpt(Cluster originaldata) {
        this.clusterpt =  RdClusterpt(originaldata);
    }

    //used each tick when you make new refined clusters to set the new cluster point
    public void setClusterpt(Sample newpoint) {
        this.clusterpt = newpoint;
    }
    
    /*takes every sample out of the previous clusters so that when you refill 
     * them there's no duplicate samples
     */
    public void cleanCluster() {
        Iterator<Sample> itr = cluster.iterator();
        while(itr.hasNext()){
            itr.next();
            itr.remove();
        }
    }

    //randomly chooses a sample from orginal data, then put it as clusterpt.
    public Sample RdClusterpt(Cluster originaldata) {
        Random rd = new Random();
        int rdindex = rd.nextInt(originaldata.cluster.size());
        return originaldata.cluster.get(rdindex);
    }

    //methods to add a Sample to a Cluster
    public void add(double[] values){
        Sample newsample = new Sample(values);
        cluster.add(newsample);
    }

    public void add(int[] values){
        Sample newsample = new Sample(values);
        cluster.add(newsample);
    }

    public void add(Sample newsample){
        cluster.add(newsample);
    }

    //prints the cluster as a series of samples
    public String toString(){
        int i = 0;
        String output = "";
        if(clusterpt != null) {
            output += "Centroids : " + clusterpt.toString() +  " Cluster samples : ";
        }else{
            output +=  "OriginalData samples : ";
        }

        for(Sample sample : this.cluster){
            if(i<100){output += sample.toString() + " ";}
            i++;
        }
        return output;
    }

    //implements average each tick to reevaluate where the centroids are
    public void optimize(){
        this.setClusterpt(this.average());
    }

    //finds and returns the average of all the samples in the cluster
    public Sample average(){
    	int length = this.clusterpt.sample.size();
        double[] aveParameter = new double[length];

        if(length == 0){
            return this.clusterpt;
        }
    	for(int i = 0; i < length; i++) {
        	double sum = 0.0;
    		for(Sample sample : this.cluster){
    		    sum += sample.sample.get(i);
    		}
            aveParameter[i] = sum/this.cluster.size(); // averaging all over all samples in cluster array
        }
        Sample ave = new Sample(aveParameter);
    	return ave;
    }
}
