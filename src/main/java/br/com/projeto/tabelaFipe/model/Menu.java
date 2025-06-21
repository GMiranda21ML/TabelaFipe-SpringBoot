package br.com.projeto.tabelaFipe.model;

public class Menu {
    public static void titulo() {
        System.out.println("\n" +
                "████████╗░█████╗░██████╗░███████╗██╗░░░░░░█████╗░    ███████╗██╗██████╗░███████╗\n" +
                "╚══██╔══╝██╔══██╗██╔══██╗██╔════╝██║░░░░░██╔══██╗    ██╔════╝██║██╔══██╗██╔════╝\n" +
                "░░░██║░░░███████║██████╦╝█████╗░░██║░░░░░███████║    █████╗░░██║██████╔╝█████╗░░\n" +
                "░░░██║░░░██╔══██║██╔══██╗██╔══╝░░██║░░░░░██╔══██║    ██╔══╝░░██║██╔═══╝░██╔══╝░░\n" +
                "░░░██║░░░██║░░██║██████╦╝███████╗███████╗██║░░██║    ██║░░░░░██║██║░░░░░███████╗\n" +
                "░░░╚═╝░░░╚═╝░░╚═╝╚═════╝░╚══════╝╚══════╝╚═╝░░╚═╝    ╚═╝░░░░░╚═╝╚═╝░░░░░╚══════╝\n");
    }

    public static void menu() {
        System.out.println("Digite 1 para Carro");
        System.out.println("Digite 2 para Caminhão");
        System.out.println("Digite 3 para Moto");
        System.out.print("Digite sua opção: ");
    }

}
