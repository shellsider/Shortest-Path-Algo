import java.util.*;

public class Main
{
    static StringBuffer s1 = new StringBuffer();
    static void input(int[][] arr, int n)
    {
        Scanner ob = new Scanner(System.in);
        int i, j;
        for(i=0;i<n;i++)
            for(j=0;j<n;j++)
                arr[i][j] = ob.nextInt();
        System.out.println();
    }

    static void setNeg(int[] arr, int n)
    {
        int i, j;
        for(i=0;i<n;i++)
                arr[i] = -1;
    }

    static int search(String s, int n, int num)
    {
        int i, temp;
        for(i=0;i<s.length();i++){
            temp = Character.getNumericValue(s.charAt(i));
            if(temp == num)
                return 1;
        }
        return -1;
    }

    static String calculateGetString(int[][] arr, int[] output, int n, int origin)
    {
        String s = "";
        s = s + origin;
        int i, j, col = origin, k, min = 0;
        char temp;
        output[origin] = 0;

        for(i=0;i<n;i++)
            s1.append("*");
        s1.setCharAt(origin, '0');

        for(i=origin;s.length()<n;i=col)
        {
            for(j=0;j<n;j++)
            {
                if(output[j] == -1 && (arr[i][j] != 0))
                {
                    output[j] = arr[i][j] + min;
                    temp = Character.forDigit(col, 10);
                    s1.setCharAt(j, temp);
                    continue;
                }

                k = search(s, n, j);
                if(k == -1)
                {
                    if(arr[i][j] != 0 && min + arr[i][j] < output[j])
                    {
                        output[j] = min + arr[i][j];
                        temp = Character.forDigit(col, 10);
                        s1.setCharAt(j, temp);
                    }
                }
            }

            min = 1000;
            for(j=0;j<n;j++)
            {
                k = search(s, n, j);
                if(k == -1)
                {
                    if(output[j] < min && output[j] != -1)
                    {
                        min = output[j];
                        col = j;
                    }
                }
            }
            s = s + col;
        }
        return s;
    }

//    static void printOneD(int[] output, int n, String s)
//    {
//        int i;
//        System.out.println("Elements of output 1D array are: ");
//        for(i=0;i<n;i++)
//            System.out.print(output[i] + " ");
//        System.out.println("\n\nString is: " + s);
//        System.out.println("String Buffer is: " + s1 + "\n");
//    }

    static void getPath(int n, int destination, int origin)
    {
        int i = destination;
        char tempChar;
        while(i!=origin){
            System.out.print(i + " <- ");
            tempChar = s1.charAt(i);
            i = Character.getNumericValue(tempChar);
        }
        System.out.print(origin);
    }

    public static void main(String[] args)
    {
        Scanner ob = new Scanner(System.in);
        int[][] arr;
        int[] output;
        int n, origin, destination;
        String outString = "";
        System.out.print("Enter Number of elements you want to enter: ");
        n = ob.nextInt();
        arr = new int[n][n];
        output = new int[n];
        setNeg(output, n);
        System.out.println("Entering Graph Elements: ");
        input(arr, n);
        System.out.print("Enter Origin point: ");
        origin = ob.nextInt();
        System.out.print("Enter Destination point: ");
        destination = ob.nextInt();
        System.out.println();
        outString = calculateGetString(arr,output,n,origin);
//        printOneD(output,n,outString);
        System.out.println("Path is: ");
        getPath(n, destination, origin);
    }
}