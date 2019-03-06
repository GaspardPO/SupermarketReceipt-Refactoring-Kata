package dojo.supermarket.model

import java.util.ArrayList
import java.util.HashMap

class ShoppingCart {

    val items = ArrayList<ProductQuantity>()
    var quantities_by_product: MutableMap<Product, Double> = HashMap()


    fun addItem(product: Product) {
        this.addItemQuantity(product, 1.0)
    }


    fun addItemQuantity(product: Product, quantity: Double) {
        items.add(ProductQuantity(product, quantity))
        if (quantities_by_product.containsKey(product)) {
            quantities_by_product[product] = quantities_by_product[product]!! + quantity
        } else {
            quantities_by_product[product] = quantity
        }
    }

    internal fun handleOffers(receipt: Receipt, offers: Map<Product, Offer>, catalog: SupermarketCatalog) {

        for (product_type in quantities_by_product.keys) {

            val quantityAsDouble = quantities_by_product[product_type]!!
            if (offers.containsKey(product_type)) {

                val offer = offers[product_type]!!
                val unitPrice = catalog.getUnitPrice(product_type)

                val discount: Discount? = offer.computeDiscount(quantityAsDouble, unitPrice, product_type)

                if (discount != null)
                    receipt.addDiscount(discount)
            }

        }
    }


}
