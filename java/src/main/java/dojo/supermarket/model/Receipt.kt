package dojo.supermarket.model

import java.util.ArrayList

class Receipt {
    private val items = ArrayList<ReceiptItem>()
    private val discounts = ArrayList<Discount>()

    val totalPrice: Double?
        get() {
            var total = 0.0
            for (item in this.items) {
                total += item.totalPrice
            }
            for ((_, _, discountAmount) in this.discounts) {
                total -= discountAmount
            }
            return total
        }

    fun addProduct(p: Product, quantity: Double, price: Double, totalPrice: Double) {
        this.items.add(ReceiptItem(p, quantity, price, totalPrice))
    }

    fun getItems(): List<ReceiptItem> {
        return ArrayList(this.items)
    }

    fun addDiscount(discount: Discount) {
        this.discounts.add(discount)
    }

    fun getDiscounts(): List<Discount> {
        return discounts
    }
}
