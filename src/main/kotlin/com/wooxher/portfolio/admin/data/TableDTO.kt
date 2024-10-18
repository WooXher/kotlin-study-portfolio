package com.wooxher.portfolio.admin.data

import com.wooxher.portfolio.main
import kotlin.reflect.KClass
import kotlin.reflect.full.memberProperties

class TableDTO(
    val name: String,
    val columns: List<String>,
    val record: List<List<String>>,
) {
    companion object{
        fun<T: Any> from(classInfo: KClass<T>, entites: List<Any>, vararg filterings: String ): TableDTO{
            val name = classInfo.simpleName ?: "Unknown"
            val columns = createColumns(classInfo, *filterings)
            val records = entites.map { entity ->
                columns.map{columns ->
                    classInfo.memberProperties
                        .find{columns.equals(it.name) }
                        ?.getter
                        ?.call(entity)
                        .toString()
                }. toList()
            }.toList()

            return TableDTO(name, columns, records)
        }

        private fun<T: Any> createColumns(classInfo: KClass<T>, vararg filterings: String): MutableList<String>{
            val mainColumns = classInfo.java.declaredFields
                .filter { !filterings.contains(it.name) }
                .map { it.name }
                .toMutableList()
            val baseColumns = classInfo.java.superclass.declaredFields
                .map{ it.name }
                .toMutableList()

            return (mainColumns + baseColumns).toMutableList()
        }
    }


}