Example usage is already in Main.class. Create a menu with:

```
final Menu mainMenu = new Menu(
  "Menu Title",
  new Menu.Item('A', "Simple function", Main::f),
  new Menu.Item('Y', "Complex function", () -> g(scanner)),
  new Menu.Item('Q', "Main Menu", null)
);

mainMenu.run(in, "prompt: ");
```
