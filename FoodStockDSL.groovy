package canteen.stock

import groovy.transform.ToString

class FoodStockDSL {
    def availableStock = []

    def static current_stocks (closure) {
        FoodStockDSL foodStockDSL = new FoodStockDSL()
        closure.delegate = foodStockDSL
        closure()
    }

    def ready(String stockName, int stockAmount) {
      def item = availableStock.find { p -> p.name == stockName }

      if ( item != null ) {
        item.amount += stockAmount
        println stockName + " gets " + stockAmount + " extras"
      } else {
        def foodStock = new FoodStock(name:stockName, amount:stockAmount)
        availableStock << foodStock
        print foodStock
        println " is ready"
      }
      println availableStock
    }

    //TODO: tambahin method buat buang makanan,
    def expired_food(String stockName, int stockAmount) {
        //Asumsi: Pegawai melakukan pengecekan manual
        def item = availableStock.find { p -> p.name == stockName }

      if ( item != null ) {
        item.amount -= stockAmount
        println stockName + " has " + stockAmount + " expired amount"
      } else {
        println "There is no stock by the name " + stockName
      }
      println availableStock

    }
}

// TODO: ubah method tostring biar lebih enak dibacanya
@ToString
class FoodStock {
    String name
    int amount
}
