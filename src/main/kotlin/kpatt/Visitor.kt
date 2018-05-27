/**
 * Each class here is marked as internal for convenient usage same names in your implementation
 * This file is a just demonstration of pattern 'Visitor'
 */
package kpatt


internal interface Visitor {
    fun visit(visitable: AppleStore)

    fun visit(visitable: GoogleMarket)
}

internal interface Visitable

internal class AppleStore(val mobilePhone: String = "IPhone"): Visitable {
    fun accept(visitor: Visitor) {
        visitor.visit(this)
    }
}

internal class GoogleMarket(val mobilePhone: String = "Pixel"): Visitable {
    fun accept(visitor: Visitor) {
        visitor.visit(this)
    }
}

internal class Person(var mobilePhone: String? = null): Visitor {
    override fun visit(visitable: AppleStore) {
        mobilePhone = visitable.mobilePhone
    }

    override fun visit(visitable: GoogleMarket) {
        mobilePhone = visitable.mobilePhone
    }
}