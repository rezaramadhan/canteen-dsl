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
// import canteen.stock.FoodStockDSL
import canteen.CanteenDSL

CanteenDSL.open{
    capacity 50
    has_money 100000

    current_stocks {
        ready "noodle", 5
        ready "tea", 3
    }

    buy_stocks "noodle", 10
    at_price 20000

    buy_stocks "tea", 10
    at_price 2500

    buy_stocks "eggs", 10
    at_price 7000

    menu {
        available_menu {
            menu_name "mie goreng"
            ingredient "noodle", 1
            ingredient "eggs", 1
            ingredient "noodle", 1
            price 15000
        }

        available_menu {
            menu_name "es teh"
            ingredient "tea", 1
            price 3000
        }
    }


    cooks "mie goreng", 2
    cooks "es teh", 2

    event_happened {
        customer_is_coming {
            he_ordered {
                menu "mie goreng", 1
                menu "es teh", 1
                take_away()
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
            left()
        }
    }

}
