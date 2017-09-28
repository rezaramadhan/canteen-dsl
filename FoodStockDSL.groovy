package canteen.stock

import groovy.transform.ToString

class FoodStockDSL {
    def avaiableStock = []

    def static current_stocks (closure) {
        FoodStockDSL foodStockDSL = new FoodStockDSL()
        closure.delegate = foodStockDSL
        closure()
    }

    def ready(String stockName, int stockAmount) {
        //TODO: Tambahin check: kalo foodstock yang baru namanya udah ada di avaiableStock,
        //       item.amount += stockAmount

        def foodStock = new FoodStock(name:stockName, amount:stockAmount)
        avaiableStock << foodStock

        print foodStock
        println " is ready"
    }

}

@ToString
class FoodStock {
    String name
    int amount
}
