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

    fun addProduct(p: Product, quantity: Double, price: Double) {
        this.items.add(ReceiptItem(p, quantity, price))
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

    fun getProductbyQuantities() : Map<Product, Double>{
        val laMap = HashMap<Product, Double>()
        for (item in items) {
            if (laMap.containsKey(item.product)) {
                laMap[item.product] = laMap[item.product]!! + item.quantity
            } else {
                laMap[item.product] = item.quantity
            }
        }

        return laMap
    }
}
