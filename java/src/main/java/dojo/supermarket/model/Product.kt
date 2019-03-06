package dojo.supermarket.model

import java.util.Objects

class Product(val name: String, val unit: ProductUnit) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other.javaClass) return false
        val product = other as Product?
        return name == product!!.name && unit == product.unit
    }

    override fun hashCode(): Int {

        return Objects.hash(name, unit)
    }
}
