package dojo.supermarket

import dojo.supermarket.model.Discount
import dojo.supermarket.model.Product
import dojo.supermarket.model.ProductUnit
import dojo.supermarket.model.Receipt
import org.approvaltests.Approvals
import org.junit.jupiter.api.Test

class ReceiptPrinterTest {
    var toothbrush = Product("toothbrush", ProductUnit.Each)
    var apples = Product("apples", ProductUnit.Kilo)
    var receipt = Receipt()

    @Test
    fun oneLineItem() {
        receipt.addProduct(toothbrush, 1.0, 0.99)
        Approvals.verify(ReceiptPrinter(40).printReceipt(receipt))
    }

    @Test
    fun quantityTwo() {
        receipt.addProduct(toothbrush, 2.0, 0.99)
        Approvals.verify(ReceiptPrinter(40).printReceipt(receipt))
    }

    @Test
    fun looseWeight() {
        receipt.addProduct(apples, 2.3, 1.99)
        Approvals.verify(ReceiptPrinter(40).printReceipt(receipt))
    }

    @Test
    fun total() {
        receipt.addProduct(toothbrush, 1.0, 0.99)
        receipt.addProduct(apples, 0.75, 1.99)
        Approvals.verify(ReceiptPrinter(40).printReceipt(receipt))
    }

    @Test
    fun discounts() {
        receipt.addDiscount(Discount(apples, "3 for 2", 0.99))
        Approvals.verify(ReceiptPrinter(40).printReceipt(receipt))
    }

    @Test
    fun printWholeReceipt() {
        receipt.addProduct(toothbrush, 1.0, 0.99)
        receipt.addProduct(toothbrush, 2.0, 0.99)
        receipt.addProduct(apples, 0.75, 1.99)
        receipt.addDiscount(Discount(toothbrush, "3 for 2", 0.99))
        Approvals.verify(ReceiptPrinter(40).printReceipt(receipt))
    }
}