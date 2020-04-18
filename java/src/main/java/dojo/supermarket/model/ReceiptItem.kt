package dojo.supermarket.model

import java.util.Objects

class ReceiptItem(val product: Product, val quantity: Double, val price: Double) {

    val totalPrice: Double = price * quantity

    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o == null || javaClass != o.javaClass) return false
        val that = o as ReceiptItem?
        return java.lang.Double.compare(that!!.price, price) == 0 &&
                java.lang.Double.compare(that.totalPrice, totalPrice) == 0 &&
                java.lang.Double.compare(that.quantity, quantity) == 0 &&
                product == that.product
    }

    override fun hashCode(): Int {

        return Objects.hash(product, price, totalPrice, quantity)
    }


}
