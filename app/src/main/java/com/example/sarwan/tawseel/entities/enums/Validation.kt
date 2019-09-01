package com.example.sarwan.tawseel.entities.enums

enum class ValidationType(var id: Int) {
    NO_RULE(0),
    MUST_NOT_BE_EMPTY(1),
    VALID_EMAIL(2),
    LENGTH_CONSTRAINT(3),
    VALID_PASSWORD(4),
    PHONE(5);


    companion object {
        fun fromId(id: Int): ValidationType {
            for (type in values()) {
                if (type.id == id) return type
            }
            return values()[0]
        }
    }

}

enum class ValidationRule(var id: Int) {
    NO_RULE(0),
    LOWER_CHARACTER(1),
    NUMERIC_CHARACTER(2),
    UPPER_CHARACTER(3),
    MIN_LENGTH(4),
    MAX_LENGTH(5);

    companion object {
        fun fromId(id: Int): ValidationRule {
            for (type in values()) {
                if (type.id == id) return type
            }
            return values()[0]
        }
    }
}