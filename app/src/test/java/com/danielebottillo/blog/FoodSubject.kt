package com.danielebottillo.blog

import android.support.annotation.Nullable
import com.google.common.truth.Fact
import com.google.common.truth.FailureMetadata
import com.google.common.truth.Subject
import com.google.common.truth.Truth.assertAbout

class FoodSubject(metadata: FailureMetadata, actual: Food) : Subject<FoodSubject, Food>(metadata, actual) {

    fun isPizza(slices:Int) {
        if (actual() !is Food.Pizza) {
            failWithoutActual(Fact.simpleFact("expected to be a Pizza!"))
        }
        val target = actual() as Food.Pizza
        if (target.slices != slices) {
            failWithActual(Fact.simpleFact("expected to be have '$slices' slices but have '${target.slices}' instead"))
        }
    }
}

fun food(): Subject.Factory<FoodSubject, Food> {
    return Subject.Factory<FoodSubject, Food> { failureMetadata, target -> FoodSubject(failureMetadata, target) }
}

fun assertThat(@Nullable food: Food): FoodSubject {
    return assertAbout(food()).that(food)
}
