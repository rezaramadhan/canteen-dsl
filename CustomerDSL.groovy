package canteen.customer

import canteen.CanteenDSL
import groovy.transform.ToString

class CustomerDSL {
    CanteenDSL canteen
    def orderItems
    int numberOfPeople
    int totalPrice

    def he_ordered(closure) {
        def orderDSL = new OrderDSL(canteen:canteen)
        closure.delegate = orderDSL
        closure()

        orderItems = orderDSL.itemList
        totalPrice = orderDSL.totalPrice
    }

    def paid(int money) {
        //TODO: tambahin canteen.currentMoney pake harga total order,
        //      print bahwa duit kita nambah
        //      kalo si customer bayar pake uang yang lebih gede daripada order
        //      print bahwa kita ngasih kembalian ke customer itu
        //      kalo duitnya kurang print kalo si customer ini dilaporin ke satpam itb

    }

    def left() {
        canteen.canteenCapacity += numberOfPeople
        println "Canteen has the capacity of " + numberOfPeople + " customers"
    }
}

class OrderDSL {
    CanteenDSL canteen
    def itemList = []
    int totalPrice = 0

    def menu(String itemName, int itemAmount) {
        def findItem = itemList.find { p -> p.name == itemName }

        if ( findItem != null ) {
            findItem.amount += itemAmount
            println itemName + " gets " + itemAmount + " extras"
        } else {
            def item = new OrderItem(name:itemName, amount: itemAmount)
            itemList << item
        }
        println itemList

        //TODO: hitung total price, didapet dari itemList yang dibandingin sama harga menu
        //      di canteen.menuDSL.avaiableMenu
        println("   pesan menu")
        println(itemList)
    }

    def take_away() {
        print("Customer memilih untuk take-away makanannya.")
    }

    def dine_in_for(int numberOfPeople) {
        if (canteen.canteenCapacity > 0){
            canteen.canteenCapacity -= numberOfPeople
            println numberOfPeople + " dine in canteen"
        } else {
            println "Customer must take away"
        }
    }
}

@ToString
class OrderItem {
    String name
    int amount
}
