package com.example.sarwan.tawseel.entities.enums

enum class PasswordRegex(var regex: String) {
        upper_char(".*[A-Z].*"),
        lower_char(".*[a-z].*"),
        numeric(".*[0-9].*"),
        special_char(".*[~!@#\$%\\^&*()\\-_=+\\|\\[{\\]};:'\",<.>/?].*")
    }