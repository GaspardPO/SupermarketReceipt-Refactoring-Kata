package dojo.supermarket.model

enum class SpecialOfferType(val offerObject: SpecialOffer) {
    TenPercentDiscount (TenPercentDiscount()),
    TwoForAmount(TwoForAmount()),
    ThreeForTwo(ThreeForTwo()),
    FiveForAmount(FiveForAmount());
}

abstract class SpecialOffer(val numberOfProductInPack: Int = 1) {
    fun isApplicable(quantity: Double): Boolean = quantity.toInt() / this.numberOfProductInPack == 0
}

class TenPercentDiscount : SpecialOffer()
class TwoForAmount : SpecialOffer(2)
class ThreeForTwo : SpecialOffer(3)
class FiveForAmount : SpecialOffer(5)
