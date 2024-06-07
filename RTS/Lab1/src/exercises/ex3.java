package exercises;
import java.util.Random;
import java.util.Arrays;
public class ex3 {
    public static void main(String[] args)
    {

        int[] array = new int[10];


        Random rand= new Random();
        for(int i=0; i<10;i++)
        {
            array[i] = rand.nextInt(100);
        }


        Arrays.sort(array);
        System.out.println("the sorted array: ");
        for (int i=0; i<10;i++)
        {
            System.out.println(array[i]);
        }

    }
}
