package canteen

import canteen.stock.*

class CanteenDSL {
    int canteenCapacity
    FoodStockDSL foodStockDSL
    int currentMoney

    // ini kayak init method gitu, set static
    def static open(closure) {
        CanteenDSL canteenDSL = new CanteenDSL()
        closure.delegate = canteenDSL
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

    def current_stocks(closure) {
        // ini tuh kayak semua fungsi yang dipanggil didalem current_stocks{}
        // bakal dipanggil sama kelas FoodStockDSL

        foodStockDSL = new FoodStockDSL()
        closure.delegate = foodStockDSL
        closure()
    }

    def buy_stocks(String stockName, int stockAmount) {
        //TODO: Tambahin check: kalo foodstock yang baru namanya udah ada di avaiableStock,
        //       item.amount += stockAmount

        def foodStock = new FoodStock(name:stockName, amount:stockAmount)

        foodStockDSL.avaiableStock << foodStock

        println foodStockDSL.avaiableStock
    }

    def at_price(int price) {
        // Method untuk beli barang x dengan harga price
        this.currentMoney -= price

        println("current canteen income is: " + currentMoney)
    }
}

//TODO: buat kelas menu item, itu
