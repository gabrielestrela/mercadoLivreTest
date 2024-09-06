package com.star.corekotlin.mapper

interface Mapper<T, out S> {
    fun map(from: T): S
}
