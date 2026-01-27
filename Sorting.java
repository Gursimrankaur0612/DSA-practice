import java.util.Scanner;

public class Sorting {
    void selectionsort(int arr[]) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int minIdx = i; 
            
            
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIdx]) {
                    minIdx = j; 
                }
            }
            
          
            int temp = arr[minIdx];
            arr[minIdx] = arr[i];
            arr[i] = temp;
        }
    }
    void bubblesort(int arr[])
    {
        int n=arr.length;
        for(int i=0;i<n-1;i++)
        {
            boolean swappz=false;
            for(int j=0;j<n-i-2;j++)
            {
                if(arr[j]>arr[j+1])
                {
                    int temp=arr[j];
                    arr[j]=arr[j+1];
                    arr[j+1]=temp;
                    swappz=true;
                }
                
            }
             if (swappz==false)
                    {
                        break;
                    }
        }
    
    }
    void insertionsort(int arr[])
    {
        int n=arr.length;
        for(int i=1;i<n;i++)
        {
            int key=arr[i];
            int j=i-1;
            while((j>-1) && (arr[j]>key))
            {
                arr[j+1]=arr[j];
                --j;
            }
            arr[j+1]=key;
        }

    }

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter size :");
        int n = in.nextInt();
        System.out.println("Enter elements: ");

        int arr1[] = new int[n];
        for (int i = 0; i < n; i++) {
            arr1[i] = in.nextInt();
        }
        
        Sorting ob = new Sorting();

        ob.selectionsort(arr1);
        System.out.print("Sorted array using selection sort: ");
        for (int i = 0; i < n; i++) {
            System.out.print(arr1[i] + " ");
        }
        System.out.println();

        ob.bubblesort(arr1);
        System.out.print("Sorted array using bubble sort: ");
        for (int i = 0; i < n; i++) {
            System.out.print(arr1[i] + " ");
        }

         System.out.println();
         
        ob.insertionsort(arr1);
        System.out.print("Sorted array using insertion sort: ");
        for (int i = 0; i < n; i++) {
            System.out.print(arr1[i] + " ");
        }
        
    }
}
