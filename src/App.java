import java.lang.*;

public class App {

    public static void main(String[] args) throws Exception {
        try {
            ListaSimplesDesordenada<String> lis = new ListaSimplesDesordenada<>();
            lis.guardeUmItemNoInicio("C");
            lis.guardeUmItemNoInicio("C++");
            lis.guardeUmItemNoFinal("Java");
            lis.guardeUmItemNoInicio("Python");

            // System.out.println(lis.getTamanhoLista());
            // System.out.println(lis.getQuantidadeElement("Java"));
            System.out.println("-------------TESTE-------------");
            System.out.println(lis);

            ListaSimplesDesordenada<String> lis1 = new ListaSimplesDesordenada<>();
            while (!lis.isVazia()) {
                lis1.guardeUmItemNoInicio(lis.recupereItemDoInicio());
                lis.removaItemDoInicio();
            }
            System.out.println("-------------------LIS--------------------");
            System.out.println(lis);
            System.out.println("-------------------LIS1--------------------");
            System.out.println(lis1);

            System.out.println("----------------------LIS2---------------------");
            ListaSimplesDesordenada<String> lis2 = new ListaSimplesDesordenada<>();
            lis2.guardeUmItemNoInicio("C");
            lis2.guardeUmItemNoInicio("C++");
            lis2.guardeUmItemNoFinal("Java");
            lis2.guardeUmItemNoInicio("Python");
            lis2.inverterLista();
            System.out.println(lis2);
            

            // System.out.println("lis2 " + lis2.recupereItemDoFinal());
        } catch (Exception e) {
        }
    }
}
