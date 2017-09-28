// import MemoDsl
//
// MemoDsl.make {
//     to "Nirav Assar"
//     from "Barack Obama"
//     body "How are things? We are doing well. Take care"
//     idea "The economy is key"
//     request "Please vote for me"
//     xml
// }
import canteen.stock.FoodStockDSL
import canteen.CanteenDSL

CanteenDSL.open{
    capacity 50

    current_stocks {
        ready "noodle", 5
        ready "tea", 3
    }

    buy_stocks "noodle", 10 at_price
    buy_stocks "tea", 10 at_price
    buy_stocks "eggs", 10 at_price

    menu_composition {
        menu_name "mie goreng"
        raw_materials "rice", 1
        raw_materials "eggs", 1
    }

    menu_composition {
        menu_name "es teh"
        raw_materials "tea", 1
    }

    cooks "mie goreng", 2
    cooks "es teh", 2

    event_happened {
        customer_is_coming {
            he_ordered {
                menu "mie goreng", 1
                menu "es teh", 1
                takeaway
            }
            paid 100000
        }

        customer_is_coming {
            he_ordered {
                menu "mie goreng", 2
                menu "es teh", 2
                dine_in_for 2
            }
            paid 100000
            he_left
        }
    }

}
