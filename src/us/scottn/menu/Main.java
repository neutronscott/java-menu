/* menu.java
 * learning the java OO way
 * scott nicholas <m@san.aq> jan 29 2024
 */

package us.scottn.menu;

import java.util.Scanner;

public class Main
{
    static void a(Scanner in) {
      final Menu mainMenu = new Menu(
        "Submenu",
        new Menu.Item('Y', "Yay?", Main::y),
        new Menu.Item('M', "Main Menu", null)
      );

      // a single line!
      System.out.println("");
      mainMenu.run(in, "what: ");
    }

    static void b(Scanner in) {
      System.out.printf("input something: ");
      String s = in.nextLine();
      System.out.printf("[[[%s]]]\n", s);
    }

    static void y() {
      System.out.println("Yay!");
    }

    public static void main(String[] args)
    {
        Scanner console = new Scanner(System.in);
        Menu.Item s;

        final Menu mainMenu = new Menu(
            "Main Menu",
            new Menu.Item('A', "Submenu", () -> a(console)),
            new Menu.Item('B', "Input/Output", () -> b(console)),
            new Menu.Item('Q', "Quit", null)
        );

        do {
          s = mainMenu.displayAndSelect(console, "selection: ");
          if (s.f() != null) { s.run(); }
        } while (s.isQuit() == false);

        console.close();
    }
}
