package dojo.supermarket.model

enum class SpecialOfferType(val getNbOfSimilarProductsInAPack :Int = 1 ) {
    TenPercentDiscount,
    TwoForAmount(2),
    ThreeForTwo(3),
    FiveForAmount(5)
}
