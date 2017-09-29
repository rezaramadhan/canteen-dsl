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
        if (money == totalPrice){
            canteen.currentMoney += totalPrice
            println "Canteen's current money is " + canteen.currentMoney
        } else if (money > totalPrice){
            def change = money - totalPrice
            println "Customer received change of " + change + " rupiah"
        } else {
            println "Customer is being reported to the ITB security guard"
        }


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

        //Count price and add to totalprice
        def itMenu = canteen.menuDSL.availableMenu.find { p -> p.name == itemName }

        if ( itMenu != null ) {
            totalPrice += itMenu.price * itemAmount
        } else {
            println "Menu " + itemName + " not found"
        }
        println "Ordered items ${itemList}"
        println "Total price ${totalPrice}"
    }

    def take_away() {
        println "Take away order"
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
