package pa06;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Iterator;

/**
 * KMeans is an ArrayList of CLusters and a single Cluster for 
 * the original data inputed. This class contains mostly methods used in
 * the Optimization class when the algorithm is run
 * @authors Patrick Lee, Denise Zhong, Caelan Gawah-Meaden, Kyra Rivest
 *
 */

public class KMeans {
    public ArrayList<Cluster> kClusters; 
    public Cluster originalData;

    //constructor
    public KMeans() throws FileNotFoundException {
        this.kClusters = new ArrayList<Cluster>();
        this.originalData = new Cluster();
        setOriginalData();
        setKClusters();
    }


    //set originalData cluster using parameters in the File
    public void setOriginalData() throws FileNotFoundException {
        Scanner in = new Scanner(System.in);
        System.out.print("Filename: ");
        String filename = in.nextLine();
        Scanner file = new Scanner(new File(filename));
        //int i = 1;
        while (file.hasNextInt()) {
            double[] parameter = new double[2];
            parameter[0] = file.nextInt();
            if(file.hasNextInt()){parameter[1] = file.nextInt();}
            this.originalData.add(parameter);
        //    System.out.println(i);
        //    i++;
        }
    }

    //add k clusters to the KClusters ArrayList
    public void setKClusters(){
        Scanner in = new Scanner(System.in);
        System.out.print("Number of clusters: ");
        int numClusters = in.nextInt();
        for (int i = 0; i < numClusters; i++) {
            Cluster cluster = new Cluster(this.originalData);
            while(isDuplicate(cluster)){
                cluster = new Cluster(this.originalData);
            }
            kClusters.add(cluster);
        }
       //System.out.println("finish setting k empty clusters");
    }

    //check if new clusterpt is same as old clusterpts
    public boolean isDuplicate(Cluster cluster){
        Sample newpt = cluster.clusterpt;
        for(Cluster c : kClusters){
            double dist = newpt.distanceTo(c.clusterpt);
            if (Math.abs(dist) < 0.00001){
                return true;
            }
        }
        return false;
    }

    @Override
    //Organize and print clusters
    public String toString(){
        String output = "KMeans kClusters : \n";
        for(Cluster cluster : this.kClusters){
            output += cluster.toString() + "\n";
        }
        return output;
    }

    //optimize each cluster pt.
    public void tick(){
        for(int i = 0; i < kClusters.size(); i++){
            kClusters.get(i).optimize();
        }
        fillClusters();
    }

    //fill k clusters with samples (distributed by closest distance)
    public void fillClusters() {
        for(int i = 0; i < kClusters.size(); i++) {
            kClusters.get(i).cleanCluster();
        }
        Iterator<Sample> itr = originalData.cluster.iterator();
        while(itr.hasNext()) {
            Sample element = itr.next();
            findCluster(element,kClusters).add(element);
        }
    }

    //used in fillClusters() methods..
    //find the cluster which is closest to the sample (element)
    public Cluster findCluster(Sample element, ArrayList<Cluster> kClusters) {
        double minDist = element.distanceTo(kClusters.get(0).clusterpt);
        Cluster closest = kClusters.get(0);

        Iterator<Cluster> itr = kClusters.iterator();  //Iterator used for efficiency (in case k is large)
        itr.next();
        while(itr.hasNext()){
            Cluster cluster = itr.next();
            double dist = element.distanceTo(cluster.clusterpt);
            if (dist < minDist) {
                minDist = dist;
                closest = cluster;
            }
        }
        return closest;
    }
}
