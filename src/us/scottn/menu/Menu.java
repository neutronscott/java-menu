/* menu.java
 * learning the java OO way
 * scott nicholas <m@san.aq> jan 29 2024
 * apr 11 2024: changed a bunch of stuff for better interface, went back to java 8 target
 */

package us.scottn.menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Menu {
    /* from <https://yohanan.org/steve/projects/java-code-conventions>
     * static variables: public, protected, package level, private
     * instance variables: ditto
     * constructors
     * methods: should be grouped by functionality
     * nested classes and interfaces
     */

    /* instance variables */
    private List<Item> items = new ArrayList<>();
    private String     name = "Menu";
    private int        maxNameLength = 0;

    /* constructors */
    public Menu(String name, Item ... items) {
        this.name = name;
        for (Item m : items) {
            this.items.add(m);
            if (m.name().length() > maxNameLength) {
                maxNameLength = m.name().length();
            }
        }
    }

    /* methods */
    public String getName() {
        return this.name;
    }

    public List<Item> getItems() {
        return this.items;
    }

    public Item findItem(char opt) {
        for (Item m : this.getItems()) {
            if (m.opt() == opt) {
                return m;
            }
        }
        return null;
    }

    public void displayMenu() {
        System.out.println(this.getName());
        for (Item m : this.getItems()) {
            System.out.printf("%s) %-" + this.maxNameLength + "s\n",
                    m.opt(), m.name());
        }
    }

    public Item displayAndSelect(Scanner console, String prompt) {
        char input;
        Item item;

        while (true) {
            this.displayMenu();
            System.out.printf(prompt);
            try {
                input = console.nextLine().toUpperCase().charAt(0);
            } catch (Exception e) {
                continue; // probably hit enter without any character
            }

            item = this.findItem(input);
            if (item == null) {
                System.out.println("Invalid selection.");
            } else {
                return item;
            }
        }
    }

    public void run(Scanner in, String prompt) {
      Item item = displayAndSelect(in, prompt);
      if (item.isQuit() == false) {
        item.run();
      }
    }

    /* nested classes */
//    record Item(char opt, String name, Runnable f) {
//      public void run() {
//        this.f.run();
//      }
//    }
    static class Item {
      private char opt;
      private String name;
      private Runnable f;

      public Item(char o, String n, Runnable g) {
        opt = o;
        name = n;
        f = g;
      }
      public char opt() { return opt; }
      public String name() { return name; }
      public Runnable f() { return f; }
      public void run() { f.run(); }
      public boolean isQuit() { return f == null; }
    }

} // Menu
