package dojo.supermarket.model


class Offer(var specialOffer: SpecialOffer, val product: Product, var argument: Double){



    fun computeDiscount(quantity: Double, unitPrice: Double, product: Product): Discount? {
        var discount: Discount? = null

        if(this.specialOffer.isApplicable(quantity)){
            return null
        }



        when(this.specialOffer) {
            is TwoForAmount -> {
                val discountAmount = unitPrice * quantity - (argument * (quantity.toInt() / specialOffer.numberOfProductInPack) + quantity.toInt() % 2 * unitPrice)
                val description = "2 for " + this.argument
                discount = Discount(product, description, discountAmount)
            }
            is ThreeForTwo -> {
                val discountAmount = quantity * unitPrice - ((quantity.toInt() / specialOffer.numberOfProductInPack).toDouble() * 2.0 * unitPrice + quantity.toInt() % 3 * unitPrice)
                val description = "3 for 2"
                discount = Discount(product, description, discountAmount)
            }
            is TenPercentDiscount -> {
                val description = this.argument.toString() + "% off"
                val discountAmount = quantity * unitPrice * this.argument / 100.0
                discount = Discount(product, description, discountAmount)
            }
            is FiveForAmount -> {
                //val discountTotal = unitPrice * quantity - (this.argument * (quantity.toInt() / specialOffer.numberOfProductInPack) + quantity.toInt() % 5 * unitPrice)
                //val description = this.specialOffer.numberOfProductInPack.toString() + " for " + this.argument

                val (discountTotal, description) = this.specialOffer.getTrucs()
                discount = Discount(product, description, discountTotal)
            }
        }

        return discount
    }
}
