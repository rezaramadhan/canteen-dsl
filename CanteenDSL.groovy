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
        // TODO:
        //     1. cari list ingredient dari avaiableMenu
        //     2. kurangin ingredient yang ada di avaiableStock sesuai list ingredient
        //        yang tadi
        //     3. kalo jumlah ingredient cukup, tambahin currentAvaiable dari
        //        item yang dimasak di avaiableMenu.
        //        print message yang nyatain kalo berhasil masak
        //     4. kalo jumlah ga cukup, print message yang nyatain error
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
