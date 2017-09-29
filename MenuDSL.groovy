package canteen.menu

import groovy.transform.ToString

class MenuDSL {
    def availableMenu = []

    def available_menu (closure) {
        def menuItemDSL = new MenuItemDSL()
        closure.delegate = menuItemDSL
        closure()

        this.availableMenu << menuItemDSL.menuItem

        println "Add menu. Available Menu: " + this.availableMenu
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
            print menuItem
            println " is ready"
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
@ToString
class Ingredient {
    String name
    int amount
}
