package kpatt

interface Specification<T> {
    infix fun isSatisfiedBy(candidate: T): Boolean
}

infix fun <T> Specification<T>.and(other: Specification<T>) = AndSpecification(this, other)

infix fun <T> Specification<T>.or(other: Specification<T>)  = OrSpecification(this, other)

operator fun <T> Specification<T>.not() = NotSpecification(this)

class AndSpecification<T>(private val specOne: Specification<T>, private val specTwo: Specification<T>): Specification<T> {
    override infix fun isSatisfiedBy(candidate: T) = specOne.isSatisfiedBy(candidate) && specTwo.isSatisfiedBy(candidate)
}

class OrSpecification<T>(private val specOne: Specification<T>, private val specTwo: Specification<T>): Specification<T> {
    override infix fun isSatisfiedBy(candidate: T) = specOne.isSatisfiedBy(candidate) || specTwo.isSatisfiedBy(candidate)
}

class NotSpecification<T>(private val spec: Specification<T>): Specification<T> {
    override infix fun isSatisfiedBy(candidate: T) = !spec.isSatisfiedBy(candidate)
}


