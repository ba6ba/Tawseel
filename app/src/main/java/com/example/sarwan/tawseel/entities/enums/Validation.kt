package com.example.sarwan.tawseel.entities.enums

enum class ValidationType(var id: Int) {
    NO_RULE(0),
    MUST_NOT_BE_EMPTY(1),
    VALID_EMAIL(2),
    LENGTH_CONSTRAINT(3),
    VALID_PASSWORD(4),
    PHONE(5),
    PRICE(6);


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
    UPPER_CHARACTER(1),
    LOWER_CHARACTER(2),
    NUMERIC_CHARACTER(4),
    SPECIAL_CHARACTER(8),
    ALL(15);

    companion object {
        fun fromId(id: Int): ValidationRule {
            for (type in values()) {
                if (type.id == id) return type
            }
            return values()[0]
        }

        fun possibleUpperRuleValues() = arrayListOf(11, 7, 15, 13, 14, 3, 5, 9, 1)
        fun possibleLowerRuleValues() = arrayListOf(2, 11, 7, 15, 14, 3, 6, 10)
        fun possibleNumericRuleValues() = arrayListOf(4, 7, 15, 13, 14, 6, 5, 12)
        fun possibleSpecialRuleValues() = arrayListOf(8, 11, 15, 13, 14, 9, 10, 12)
    }
}

//uls -> 11
//uln -> 7
//ulsn -> 15
//uns -> 13
//lns -> 14
//ul -> 3
//ln -> 6
//un -> 5
//us -> 9
//ls-> 10
//ns-> 12