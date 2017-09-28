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
        //TODO: tambahin canteenCapacity pake numberOfPeople
    }
}

class OrderDSL {
    CanteenDSL canteen
    def itemList = []
    int totalPrice = 0

    def menu(String itemName, int itemAmount) {
        def item = new OrderItem(name:itemName, amount: itemAmount)

        //TODO: Mirip sama yang sebelumnya, tambahin amount kalo udah ada di list

        itemList << item

        //TODO: hitung total price, didapet dari itemList yang dibandingin sama harga menu
        //      di canteen.menuDSL.avaiableMenu
        println("   pesan menu")
        println(itemList)
    }

    def take_away() {
        print("Customer memilih untuk take-away makanannya.")
    }

    def dine_in_for(int numberOfPeople) {
        // TODO: Kalo kantin masih cukup, kurangin canteen.canteenCapacity
        //       kalo ga cukup, print kalo customer ini disuruh takeaway
    }
}

@ToString
class OrderItem {
    String name
    int amount
}
