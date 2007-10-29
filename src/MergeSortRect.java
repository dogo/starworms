/**
 *  file :  MergeSort.java
 *
 *  desc :  Implementation of merge sort for integer arrays
 */
import java.awt.*;

public class MergeSortRect {

    /**
    *  post : A[0] <= A[1] <= ... <= A[A.length-1]
    */   
    public static Rectangle[] sort( Rectangle[] A )
    {
        mergeHelper( A, 0, A.length-1);  
//         return A;
//         for(int w=0;w<(A.length);w++)       
//             System.out.println(A[w]); 
        return A;
    }
    
    /**
    *   pre :  0 <= p <= r <= A.length
    *
    *  post :  A[p] <= A[p + 1] <= ... <= A[r]
    */
    private static void mergeHelper( Rectangle[] A, int p, int r )
    {
        int q;
        
        if( p < r )
        {
            /* split the data into two halves */
            q = (p + r) / 2;            
            /* recursively sort each half */
            mergeHelper( A, p, q );
            mergeHelper( A, q + 1, r );
            
            /* ``merge'' the two halves */
            merge( A, p, q, r );
        }

    }
    
    /**
    *   pre :  (i)   0 <= p <= q < r <= A.length-1
    *          (ii)  A[p] <= A[p+1] <= ... <= A[q]
    *          (iii) A[q+1] <= A[q+2] <= ... <= A[r]
    *
    *  post :  A[p] <= A[p+1] <= ... <= A[r]
    */
    private static void merge( Rectangle[] A, int p, int q, int r )
    {
        int i, j, k;
        
        int N = r - p + 1;
        Rectangle[] copy = new Rectangle[N];
        
        /* copy data to be merged into array ``copy'' */
        for( i = 0; i < N; i++ )
            copy[i] = A[p + i];
        
        int mid = q - p;  // index of middle element in ``copy''
        int end = r - p;  // index of last element in ``copy''
        
        i = 0;
        j = mid + 1;
        k = p;
        while( i <= mid && j <= end )
        {
        
          /* invariant:
           *   A[p] <= A[p+1] <= ... <= A[k] <= min(copy[i], copy[j])
           */
        
            if( copy[i].getY() < copy[j].getY() )
            {
                A[k] = copy[i];
                i++;
            }
            else
            {
                A[k] = copy[j];
                j++;
            }
            k++;
        }
        
        // copy left over stuff
        int a0, aN;
        if( i > mid )
        {
            a0 = j;
            aN = end;
        }
        else
        {
            a0 = i;
            aN = mid;
        }
        
        /* A[k-1] <= copy[a0] <= copy[a0+1] <= ... <= copy[aN] */
        
        // copy rest
        for( i = a0; i <= aN; i++ )
        {
            A[k] = copy[i];
            k++;
        }
    }
    
}

