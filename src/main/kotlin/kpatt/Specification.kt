package kpatt

interface Specification<T> {

    infix fun isSatisfiedBy(candidate: T): Boolean

    infix fun and(other: Specification<T>): Specification<T>

    infix fun or(other: Specification<T>): Specification<T>

    operator fun not(): Specification<T>
}

abstract class CompositeSpecification<T> : Specification<T> {

    override fun and(other: Specification<T>) = AndSpecification(this, other)

    override fun or(other: Specification<T>)  = OrSpecification(this, other)

    override operator fun not() = NotSpecification(this)

}

class AndSpecification<T>(private val specOne: Specification<T>, private val specTwo: Specification<T>): CompositeSpecification<T>() {

    override infix fun isSatisfiedBy(candidate: T) = specOne.isSatisfiedBy(candidate) && specTwo.isSatisfiedBy(candidate)

}

class OrSpecification<T>(private val specOne: Specification<T>, private val specTwo: Specification<T>): CompositeSpecification<T>() {

    override infix fun isSatisfiedBy(candidate: T) = specOne.isSatisfiedBy(candidate) || specTwo.isSatisfiedBy(candidate)

}

class NotSpecification<T>(private val spec: Specification<T>): CompositeSpecification<T>() {

    override infix fun isSatisfiedBy(candidate: T) = !spec.isSatisfiedBy(candidate)

}


