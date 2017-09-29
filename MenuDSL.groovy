package canteen.menu

import groovy.transform.*

class MenuDSL {
    def availableMenu = []

    def available_menu (closure) {
        def menuItemDSL = new MenuItemDSL()
        closure.delegate = menuItemDSL
        closure()

        this.availableMenu << menuItemDSL.menuItem
        print menuItemDSL.menuItem
        println " is Added to menu list"
    }
}

class MenuItemDSL {
    def menuItem = new MenuItem();

    def menu_name(String name){
        this.menuItem.name = name
    }

    def price(int price){
        this.menuItem.price = price
    }

    def ingredient(String ingredientName, int ingredientAmount){
        def item = menuItem.ingredients.find { p -> p.name == ingredientName }

        if ( item != null ) {
            item.amount += ingredientAmount
            println ingredientName + " gets " + ingredientAmount + " extras"
        } else {
            def rawMaterial = new Ingredient(name: ingredientName, amount: ingredientAmount)
            menuItem.ingredients << rawMaterial
        }
    }
}
@ToString
class MenuItem {
    String name
    int price
    int currentAvailable
    def ingredients = []
}
@ToString()
class Ingredient {
    String name
    int amount
}
