package dojo.supermarket.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ReceiptTest{

    @Test
    fun should_return_productByQuantities_with_one_item(){
        val receipt = Receipt()

        val product = Product("toto", ProductUnit.Kilo)
        receipt.addProduct(product,1.0, 5.0)

        val actualMap = receipt.getProductbyQuantities()

        assertThat(actualMap).containsOnlyKeys(product)
                                .containsValue(1.0)
    }


    @Test
    fun should_return_productByQuantities_with_twos_items(){
        val receipt = Receipt()

        val product = Product("toto", ProductUnit.Kilo)
        val product2 = Product("toto2", ProductUnit.Kilo)
        receipt.addProduct(product,1.0, 5.0)
        receipt.addProduct(product2,2.0, 2.0)

        val actualMap = receipt.getProductbyQuantities()

        val expectedMap = hashMapOf(product to 1.0,
                                    product2 to 2.0)
        assertThat(actualMap).isEqualTo(expectedMap)
    }

    @Test
    fun should_return_productByQuantities_with_twice_the_same_item(){
        val receipt = Receipt()

        val product = Product("toto", ProductUnit.Kilo)
        receipt.addProduct(product,1.0, 5.0)
        receipt.addProduct(product,1.0, 5.0)

        val actualMap = receipt.getProductbyQuantities()

        val expectedMap = hashMapOf(product to 2.0)
        assertThat(actualMap).isEqualTo(expectedMap)
    }

}