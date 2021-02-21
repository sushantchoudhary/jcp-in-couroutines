package com.appdev.schoudhary.jcpInKotlin

import java.util.SortedSet
import java.util.TreeSet

class Animals {
    var ark: Ark? = null
    var species: Species? = null
    var gender: Gender? = null

    fun loadTheArk(candidates: Collection<Animal?>): Int {
        val animals: SortedSet<Animal>
        var numPairs = 0
        var candidate: Animal? = null

        animals = TreeSet(SpeciesGenderComparator())
        animals.addAll(candidates)
        for (a in animals) {
            candidate = if (candidate == null || !candidate.isPotentialMate(a)) a else {
                ark?.load(AnimalPair(candidate, a))
                ++numPairs
                null
            }
        }
        return numPairs
    }

    inner class Animal {
        var species: Species? = null
        var gender: Gender? = null
        fun isPotentialMate(other: Animal): Boolean {
            return species == other.species && gender != other.gender
        }
    }

    enum class Species {
        AARDVARK, BENGAL_TIGER, CARIBOU, DINGO, ELEPHANT, FROG, GNU, HYENA, IGUANA, JAGUAR, KIWI, LEOPARD, MASTADON, NEWT, OCTOPUS, PIRANHA, QUETZAL, RHINOCEROS, SALAMANDER, THREE_TOED_SLOTH, UNICORN, VIPER, WEREWOLF, XANTHUS_HUMMINBIRD, YAK, ZEBRA
    }

    enum class Gender {
        MALE, FEMALE
    }

    inner class AnimalPair(private val one: Animal, private val two: Animal)

    inner class SpeciesGenderComparator : Comparator<Animal> {
        override fun compare(one: Animal, two: Animal): Int {
            val speciesCompare = one.species!!.compareTo(two.species!!)
            return if (speciesCompare != 0) speciesCompare else one.gender!!.compareTo(two.gender!!)
        }
    }

    inner class Ark {
        private val loadedAnimals: MutableSet<AnimalPair> = HashSet()
        fun load(pair: AnimalPair) {
            loadedAnimals.add(pair)
        }
    }
}