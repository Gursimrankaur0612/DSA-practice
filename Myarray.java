import java.util.Scanner;
public class Myarray{
    public static void main(String[] args) {
        int arr1[]=new int[12];
        Scanner sc=new Scanner(System.in);

        System.out.println("Enter 12 elements:");
        for(int i=0;i<12;i++)
        {
            arr1[i]=sc.nextInt();
        }
        int index=0;
        int arr2[][]=new int[3][4];
        for(int i=0;i<3;i++)
        {
            for(int j=0;j<4;j++)
            {
                arr2[i][j]=arr1[index++];
                System.out.print(arr2[i][j]+" ");
            }
            System.out.println();
        }
        sc.close();
    }
    
}
