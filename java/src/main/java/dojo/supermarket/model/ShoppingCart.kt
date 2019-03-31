package dojo.supermarket.model

import java.util.ArrayList
import java.util.HashMap

class ShoppingCart {

    val items = ArrayList<ProductQuantity>()
    internal var productQuantities: MutableMap<Product, Double> = HashMap()


    fun getItems(): List<ProductQuantity> {
        return ArrayList(items)
    }

    fun addItem(product: Product) {
        this.addItemQuantity(product, 1.0)
    }


    fun addItemQuantity(product: Product, quantity: Double) {
        items.add(ProductQuantity(product, quantity))
        if (productQuantities.containsKey(product)) {
            productQuantities[product] = productQuantities[product]!! + quantity
        } else {
            productQuantities[product] = quantity
        }
    }

    internal fun handleOffers(receipt: Receipt, offers: Map<Product, Offer>, catalog: SupermarketCatalog) {
        for ((product, quantity) in productQuantities) {

            if (offers.containsKey(product)) {
                val offer = offers.getValue(product)
                val unitPrice = catalog.getUnitPrice(product)

                val discount: Discount? = offer.computeDiscount(quantity, unitPrice, product)

                if (discount != null)
                    receipt.addDiscount(discount)
            }

        }
    }

}
