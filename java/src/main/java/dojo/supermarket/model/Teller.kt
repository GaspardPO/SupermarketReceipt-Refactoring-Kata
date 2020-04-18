package dojo.supermarket.model

import java.util.HashMap

class Teller(private val catalog: SupermarketCatalog) {
    private val offers = HashMap<Product, Offer>()

    fun addSpecialOffer(offerType: SpecialOfferType, product: Product, argument: Double) {
        this.offers[product] = Offer(offerType, product, argument)
    }

    fun checksOutArticlesFrom(theCart: ShoppingCart): Receipt {
        val receipt = Receipt()

        for (pq in theCart.listOfProductsAndTheirSizes) {
            val p = pq.product
            val quantity = pq.quantity
            val unitPrice = this.catalog.getUnitPrice(p)
            receipt.addProduct(p, quantity, unitPrice)
        }
        this.handleOffers(receipt)

        return receipt
    }

    fun handleOffers(receipt: Receipt) {
        val quantities_by_product = receipt.getProductbyQuantities()
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
