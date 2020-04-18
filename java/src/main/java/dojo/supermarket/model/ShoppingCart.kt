package dojo.supermarket.model

import java.util.ArrayList
import java.util.HashMap

class ShoppingCart {

    val listOfProductsAndTheirSizes = ArrayList<ProductAndItsSize>()
    fun addItem(product: Product) {
        this.addItemQuantity(product, 1.0)
    }

    fun addItemQuantity(product: Product, size: Double) {
        listOfProductsAndTheirSizes.add(ProductAndItsSize(product, size))
    }

}
