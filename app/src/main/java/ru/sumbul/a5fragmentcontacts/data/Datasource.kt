package ru.sumbul.recycleviewaston6.data

import ru.sumbul.recycleviewaston6.model.Contact
import java.util.*


class Datasource {

    fun loadContacts(): kotlin.collections.List<Contact> {
        val data = mutableListOf<Contact>()
        (0..100).forEach { i -> data.add(i, Contact(getRandomName(), getRandomNumber())) }
        return data
    }

    companion object {
        private val LETTERS = "qwertyuiopasdfghjklzxcvbnm"
        private val NUMBERS = "0123456789"
    }

    private fun getRandomName(): String {
        val sizeOfRandomString = 7
        val random = Random()
        val sb = StringBuilder(sizeOfRandomString)
        for (i in 0 until sizeOfRandomString)
            sb.append(LETTERS[random.nextInt(LETTERS.length)])
        return sb.toString()
    }

    private fun getRandomNumber(): Int {
        val sizeOfRandomNumber = 7
        val random = Random()
        val sb = StringBuilder(sizeOfRandomNumber)
        for (i in 0 until sizeOfRandomNumber)
            sb.append(NUMBERS[random.nextInt(NUMBERS.length)])
        return sb.toString().toInt()
    }

}

private fun fillList(): List<String> {
    val data = mutableListOf<String>()
    (0..30).forEach { i -> data.add("$i element") }
    return data
}