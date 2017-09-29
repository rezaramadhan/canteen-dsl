package canteen

import canteen.stock.*
import canteen.menu.*
import canteen.customer.*

class CanteenDSL {
    //NOTE: ambil avaiableMenu di menuDSL.avaiableMenu
    //NOTE: ambil avaiableStock di menuDSL.avaiableStock

    int canteenCapacity
    int currentMoney
    MenuDSL menuDSL
    FoodStockDSL foodStockDSL


    // ini kayak init method gitu, set static
    def static open(closure) {
        CanteenDSL canteenDSL = new CanteenDSL()
        closure.delegate = canteenDSL
        closure()
    }

    def current_stocks(closure) {
        // ini tuh kayak semua fungsi yang dipanggil didalem current_stocks{}
        // bakal dipanggil sama kelas FoodStockDSL

        foodStockDSL = new FoodStockDSL()
        closure.delegate = foodStockDSL
        closure()
    }

    def menu(closure) {
        menuDSL = new MenuDSL()
        closure.delegate = menuDSL
        closure()
    }

    def capacity(int i) {
        this.canteenCapacity = i
        println("Canteen capacity is set to: " + this.canteenCapacity)
    }

    def has_money(int i) {
        this.currentMoney = i
        println("Canteen capacity is set to: " + this.canteenCapacity)
    }


    def buy_stocks(String stockName, int stockAmount) {
        foodStockDSL.ready(stockName, stockAmount)
    }

    def at_price(int price) {
        // Method untuk beli barang x dengan harga price
        this.currentMoney -= price

        println("current canteen income is: " + currentMoney)
    }

    def cooks(String name, int amount) {
        println "Available Menu " + menuDSL.availableMenu
        def item = menuDSL.availableMenu.find { p -> p.name == name }
        if ( item == null ) {
          println "There is no ${name} in menu"
        } else {
          //Check if raw materials sufficient for cooking
          //Give out of stock item name if any
          def isStockSafe = true
          for (it in item.ingredients) {
            def stockItem = foodStockDSL.availableStock.find { p -> p.name = it.name }
            if (stockItem.amount < it.amount) {
              isStockSafe = false
              println "Insufficient amount of ${stockItem.name}"
            }
          }

          //If there are enough food stock, substract stock amount, add menu amount
          if ( isStockSafe ) {
            for (it in item.ingredients) {
              def stockItem = foodStockDSL.availableStock.find { p -> p.name = it.name }
              stockItem.amount -= it.amount
            }
            item.currentAvailable += amount
            println "Add ${amount} to menu ${name}"
          }
        }
    }

    def event_happened(closure) {
        closure()
    }

    def customer_is_coming(closure) {
        def customerDSL = new CustomerDSL(canteen:this)
        closure.delegate = customerDSL
        closure()
    }
}
