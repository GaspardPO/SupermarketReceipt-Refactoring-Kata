package dojo.supermarket.model

import java.util.ArrayList
import java.util.HashMap

class ShoppingCart {

    val listOfProductsAndTheirSizes = ArrayList<ProductAndItsSize>()
    var products_by_quantities: MutableMap<Product, Double> = HashMap()


    fun addItem(product: Product) {
        this.addItemQuantity(product, 1.0)
    }


    fun addItemQuantity(product: Product, size: Double) {
        listOfProductsAndTheirSizes.add(ProductAndItsSize(product, size))
        if (products_by_quantities.containsKey(product)) {
            products_by_quantities[product] = products_by_quantities[product]!! + size
        } else {
            products_by_quantities[product] = size
        }
    }




}
