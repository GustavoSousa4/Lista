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

            System.out.println("----------------------LIS3---------------------");
            lis2.inverterLista();
            ListaSimplesDesordenada<String> lis3 = new ListaSimplesDesordenada<>();
            lis3.guardeUmItemNoInicio("Rest");
            lis3.guardeUmItemNoInicio("Ruby");
            lis3.guardeUmItemNoFinal("JavaScript");
            lis3.guardeUmItemNoInicio("Kotlin");
            System.out.println(lis3);
            lis3.concatenando(lis2);
            System.out.println(lis3);
            ListaSimplesDesordenada<String> lis5 = new ListaSimplesDesordenada<>();

            System.out.println("----------------------LIS4---------------------");
            ListaSimplesDesordenada<String> lis4 = new ListaSimplesDesordenada<>();
            lis4.guardeUmItemNoInicio("Rest");
            lis4.guardeUmItemNoInicio("Ruby");
            lis4.guardeUmItemNoInicio("Kotlin");
            lis4.guardeUmItemNoFinal("GO");

            lis5.guardeUmItemNoInicio("GO");
            lis5.guardeUmItemNoInicio("Ruby");
            lis5.guardeUmItemNoFinal("Rest");

            System.out.println("Elementos iguais: " + lis4.elemIguais(lis5));
            System.out.println(lis4);

        } catch (Exception e) {
        }
    }
}
