import java.util.Scanner;
class Calcul10ator 
{
public static void main(String args[])
{
    Scanner sc=new Scanner(System.in);
    System.out.println("Numbers:");
    int a=sc.nextInt();
    int b=sc.nextInt();
    String Cal=sc.next();

    if(Cal.equals("+")){
    System.out.println("Addition "+(a+b));
}
    else if(Cal.equals("-")){
       System.out.println("Subtraction "+(a-b));
    }

    else if(Cal.equals("*")){
    System.out.println("Multiplication "+(a*b));
}

    else if(Cal.equals("/")){
    System.out.println("Division "+(a/b));
}

    else if(Cal.equals("bitr"))
    {
        System.out.println("Bitwise right "+(a>>b));
    }
    else if(Cal.equals("bitl"))
    {
        System.out.println("Bitwise left "+(a<<b));
    }
    else if(Cal.equals("&"))
     {
	System.out.println("Bitwise and "+(a&b));
    }
    else if(Cal.equals("|"))
     {
	System.out.println("Bitwise or "+(a|b));
    }
    else{
    System.out.println("Invalid operation");
}
    sc.close();
}
}
