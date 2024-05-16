package com.cj.week_04.models

enum class UserTypeModel {
    STUDENT {
        override fun getString() = "학생"
    },

    PROFESSOR {
        override fun getString() = "교수"
    },

    EMPLOYEE {
        override fun getString() = "교직원"
    };

    abstract fun getString(): String
}
