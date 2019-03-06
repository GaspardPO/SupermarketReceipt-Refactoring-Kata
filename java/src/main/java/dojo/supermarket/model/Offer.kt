package dojo.supermarket.model

class Offer(var offerType: SpecialOfferType, val product: Product, var argument: Double){

    fun isApplicable(fiveForAmount: SpecialOfferType, quantityAsInt: Int) =
            fiveForAmount === this.offerType && quantityAsInt >= this.offerType.getNbOfSimilarProductsInAPack

    fun computeDiscount(quantityAsDouble: Double, unitPrice: Double, product_type: Product): Discount? {
        val quantityAsInt = quantityAsDouble.toInt()

        var discount: Discount? = null

        val numberOfPacks = quantityAsInt / this.offerType.getNbOfSimilarProductsInAPack
        if (this.isApplicable(SpecialOfferType.TwoForAmount, quantityAsInt)) {
            val total = this.argument * numberOfPacks + quantityAsInt % 2 * unitPrice
            val discountN = unitPrice * quantityAsDouble - total
            discount = Discount(product_type, "2 for " + this.argument, discountN)
        }
        if (this.isApplicable(SpecialOfferType.ThreeForTwo, quantityAsInt)) {
            val discountAmount = quantityAsDouble * unitPrice - (numberOfPacks.toDouble() * 2.0 * unitPrice + quantityAsInt % 3 * unitPrice)
            discount = Discount(product_type, "3 for 2", discountAmount)
        }
        if (this.isApplicable(SpecialOfferType.TenPercentDiscount, quantityAsInt)) {
            discount = Discount(product_type, this.argument.toString() + "% off", quantityAsDouble * unitPrice * this.argument / 100.0)
        }
        if (this.isApplicable(SpecialOfferType.FiveForAmount, quantityAsInt)) {
            val discountTotal = unitPrice * quantityAsDouble - (this.argument * numberOfPacks + quantityAsInt % 5 * unitPrice)
            discount = Discount(product_type, this.offerType.getNbOfSimilarProductsInAPack.toString() + " for " + this.argument, discountTotal)
        }
        return discount
    }
}
