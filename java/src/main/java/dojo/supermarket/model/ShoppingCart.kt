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
                val quantityAsInt = quantityAsDouble.toInt()

                var discount: Discount? = null
                var nbOfSimilarProductsInAPack = 1
                if (offer.offerType === SpecialOfferType.ThreeForTwo) {
                    nbOfSimilarProductsInAPack = 3
                } else if (offer.offerType === SpecialOfferType.TwoForAmount) {
                    nbOfSimilarProductsInAPack = 2
                }
                if (offer.offerType === SpecialOfferType.FiveForAmount) {
                    nbOfSimilarProductsInAPack = 5
                }

                val numberOfPacks = quantityAsInt / nbOfSimilarProductsInAPack
                if(offer.offerType === SpecialOfferType.TwoForAmount && quantityAsInt >= 2){
                    val total = offer.argument * numberOfPacks + quantityAsInt % 2 * unitPrice
                    val discountN = unitPrice * quantityAsDouble - total
                    discount = Discount(product_type, "2 for " + offer.argument, discountN)
                }
                if (offer.offerType === SpecialOfferType.ThreeForTwo && quantityAsInt > 2) {
                    val discountAmount = quantityAsDouble * unitPrice - (numberOfPacks.toDouble() * 2.0 * unitPrice + quantityAsInt % 3 * unitPrice)
                    discount = Discount(product_type, "3 for 2", discountAmount)
                }
                if (offer.offerType === SpecialOfferType.TenPercentDiscount) {
                    discount = Discount(product_type, offer.argument.toString() + "% off", quantityAsDouble * unitPrice * offer.argument / 100.0)
                }
                if (offer.offerType === SpecialOfferType.FiveForAmount && quantityAsInt >= 5) {
                    val discountTotal = unitPrice * quantityAsDouble - (offer.argument * numberOfPacks + quantityAsInt % 5 * unitPrice)
                    discount = Discount(product_type, nbOfSimilarProductsInAPack.toString() + " for " + offer.argument, discountTotal)
                }


                if (discount != null)
                    receipt.addDiscount(discount)
            }

        }
    }
}
