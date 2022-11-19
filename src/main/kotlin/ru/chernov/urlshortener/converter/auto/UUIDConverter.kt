package ru.chernov.urlshortener.converter.auto

import jakarta.persistence.AttributeConverter
import jakarta.persistence.Converter
import java.util.*


@Converter(autoApply = true)
class UUIDConverter : AttributeConverter<UUID?, String?> {

    override fun convertToDatabaseColumn(uuid: UUID?): String? {
        return uuid?.toString()
    }


    override fun convertToEntityAttribute(string: String?): UUID? {
        return string?.let { UUID.fromString(string) }
    }

}