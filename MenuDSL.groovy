package canteen.menu

import groovy.transform.ToString

class MenuDSL {
    def avaiableMenu = []

    def avaiable_menu (closure) {
        def menuItemDSL = new MenuItemDSL()
        closure.delegate = menuItemDSL
        closure()

        this.avaiableMenu << menuItemDSL.menuItem

        println(this.avaiableMenu)
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
        def rawMaterial = new Ingredient(name: ingredientName, amount: ingredientAmount)

        //TODO: Tambahin check: kalo ingredient yang baru namanya udah ada di ingredient,
        //       item.amount += stockAmount

        menuItem.ingredients << rawMaterial
    }
}

@ToString
class MenuItem {
    String name
    int price
    int currentAvaiable
    def ingredients = []
}
@ToString
class Ingredient {
    String name
    int amount
}
