/**
 *
 * @author ugenlik1
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.io.*;


public class Submission {
    
    
    
    public static void main(String[] args) {
        // TODO code application logic here
        read(
             args.length < 1 ? "input.txt" : args[0],
             args.length < 2 ? "output.txt" : args[1]);
    }
    
    public static void read(String inf, String outf) {
        try {
            
            
            BufferedReader bufRead = new BufferedReader(new FileReader("input.txt"));
            PrintWriter pw = new PrintWriter(new FileWriter("output.txt"), true); 
            
            String line;
            
            line = bufRead.readLine();
            
            while (line != null) {
                String[] v = line.split(" ");
                int nj = Integer.valueOf(v[0]);
                int Wj = Integer.valueOf(v[1]);
                
                int wi[] = new int[nj];
                int pi[] = new int[nj];
                
                for (int i = 0; i < nj; i++) {
                    line = bufRead.readLine();
                    v = line.split(" ");
                    wi[i] = Integer.valueOf(v[0]);
                    pi[i] = Integer.valueOf(v[1]);
                }
                
                long startTime = System.nanoTime();
                int best = solve(nj, Wj, wi, pi);
                long estimatedTime = System.nanoTime() - startTime;
                
                pw.println(nj + " " + best + " " + (estimatedTime / 1000000000));
                
                line = bufRead.readLine();
            }
            
            bufRead.close();
            pw.close();
            
        } catch (IOException e) {
            
            e.printStackTrace();
        }
    }
    
    private static int solve(int nj, int Wj, int[] wi, int[] pi) {
        
        int bestProfit = -1;
        
        int B[][];
        int numItems=nj;
        int maxWeight=Wj; 
        int w, k;
        int benefit[]=pi;
        int weight[]=wi;
        int remainingWeight;
        int setNumber = 1;
        
        
        
        // *** initialize
        B = new int [numItems+1][maxWeight+1]; 
        
        
        for (w = 0; w <= maxWeight; w++) 
            B[0][w] = 0; 
        
        
        // *** Now do the work!
        for (k = 1; k <= numItems; k++)
        {
            B[k][0] = 0;
            for (int w2=1; w2 <=maxWeight ;w2++)
            {
                if(weight[k-1]<=w2)
                {
                    if(benefit[k-1]+B[k-1][w2-weight[k-1]] > B[k-1][w2])
                    {
                        B[k][w2] = benefit[k-1]+B[k-1][w2-weight[k-1]];
                    }
                    else
                    {
                        B[k][w2] =  B[k-1][w2];
                    } 
                }
                else
                    B[k][w2] =  B[k-1][w2];
            }
            
        }
        
        
        
        
        
        
        return bestProfit=B[numItems][maxWeight];
    }
}
