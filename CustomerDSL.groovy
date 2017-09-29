package canteen.customer

import canteen.CanteenDSL
import groovy.transform.*

class CustomerDSL {
    CanteenDSL canteen
    def orderItems
    int numberOfPeople
    int totalPrice

    def he_ordered(closure) {
        def orderDSL = new OrderDSL(canteen:canteen)
        closure.delegate = orderDSL
        closure()

        this.orderItems = orderDSL.itemList
        this.totalPrice = orderDSL.totalPrice
        this.numberOfPeople = orderDSL.numberOfPeople

        println "A customer ordered items ${orderDSL.itemList}"
        println "with total price of ${orderDSL.totalPrice}"
    }

    def paid(int money) {
        if (money >= totalPrice){
            def change = money - this.totalPrice
            canteen.currentMoney += this.totalPrice
            println "Canteen's current money is " + canteen.currentMoney
            println "Customer received change of " + change + " rupiah"
        } else {
            println "Customer is being reported to the ITB security guard"
        }
    }

    def left() {
        canteen.canteenCapacity += this.numberOfPeople
        println this.numberOfPeople + " customer has left"
        println "Canteen has the capacity of " + canteen.canteenCapacity + " customers"
    }
}

class OrderDSL {
    CanteenDSL canteen
    def itemList = []
    int totalPrice = 0
    int numberOfPeople

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
    }

    def take_away() {
        println "He choose to take away his order"
    }

    def dine_in_for(int numberOfPeople) {
        if (canteen.canteenCapacity > 0){
            canteen.canteenCapacity -= numberOfPeople
            this.numberOfPeople = numberOfPeople
            println numberOfPeople + " dine in canteen"
            println "Canteen has the capacity of " + canteen.canteenCapacity + " customers"
        } else {
            println "Canteen's capacity is not enough. Customer must take away"
        }
    }
}

@ToString() class OrderItem {
    String name
    int amount
}
