package dojo.supermarket.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ReceiptItemTest{

    @Test
    fun should_return_total_price(){
        val item1 = ReceiptItem(Product("ll", ProductUnit.Kilo), 1.0, 2.0)
        assertThat(item1.totalPrice).isEqualTo(2.0)
    }

}