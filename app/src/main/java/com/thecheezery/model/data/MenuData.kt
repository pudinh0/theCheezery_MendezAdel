package com.thecheezery.model.data

import com.thecheezery.model.domain.Category
import com.thecheezery.model.domain.Product

object MenuData {
    val products = mapOf(
        Category.HOT_DRINKS to listOf(
            Product("espresso", "Espresso", "Doble espresso recién hecho.", 3.0),
            Product("latte", "Vanilla latte", "Café con leche y vainilla.", 4.0),
            Product("matcha", "Matcha latte", "Té matcha con leche.", 4.0),
            Product("cocoa", "Hot chocolate", "Chocolate caliente con malvaviscos.", 3.5),
            Product("chai", "Chai latte", "Té negro con especias y leche.", 4.0),
        ),
        Category.COLD_DRINKS to listOf(
            Product("coldbrew", "Cold brew", "Café frío preparado lentamente.", 3.0),
            Product("icedlatte", "Iced latte", "Espresso frío con leche.", 4.0),
            Product("lemonade", "Berry lemonade", "Limonada con frutos rojos.", 3.5),
            Product("frappe", "Mocha frappe", "Café frío con chocolate.", 4.5),
            Product("tea", "Peach iced tea", "Té helado de durazno.", 3.0),
        ),
        Category.SALTIES to listOf(
            Product("crepes", "Chicken crepes", "Crepas de pollo con espinaca y champiñones.", 6.0),
            Product("club", "Club sandwich", "Sándwich de pollo con papas fritas.", 5.0),
            Product("panini", "Panini", "Panini de queso a la plancha.", 4.0),
            Product("philly", "Philly cheese steak", "Carne con queso, cebolla y pimientos.", 6.0),
            Product("nachos", "Nachos", "Totopos con carne y queso cheddar.", 7.0),
        ),
        Category.SWEETS to listOf(
            Product("tiramisu", "Tiramisu", "Postre de café con cacao.", 6.0),
            Product("cheesecake", "Berry cheesecake", "Pastel de queso con frutos rojos.", 5.0),
            Product("brownie", "Fudge brownie", "Brownie de chocolate con nuez.", 4.0),
            Product("cupcake", "Strawberry cupcake", "Cupcake de vainilla y fresa.", 3.5),
            Product("waffle", "Belgian waffle", "Waffle con fruta y crema.", 5.0),
        ),
        Category.COMBOS to listOf(
            Product("coffeecombo", "Coffee & brownie", "Café caliente con brownie.", 6.0),
            Product("lunchcombo", "Lunch combo", "Sándwich, papas y bebida fría.", 8.0),
            Product("sweetcombo", "Sweet break", "Cupcake con latte a elegir.", 6.5),
        ),
    )
}
