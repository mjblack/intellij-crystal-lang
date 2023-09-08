package org.crystal.intellij.lang.ast.nodes

import org.crystal.intellij.lang.ast.location.CstLocation

class CstEnumDef(
    val name: CstPath,
    val members: List<CstNode> = emptyList(),
    val baseType: CstNode? = null,
    location: CstLocation? = null
) : CstNode(location) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as CstEnumDef

        if (name != other.name) return false
        if (members != other.members) return false
        if (baseType != other.baseType) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + members.hashCode()
        result = 31 * result + (baseType?.hashCode() ?: 0)
        return result
    }

    override fun toString() = buildString {
        append("EnumDef(")
        append(name)
        if (members.isNotEmpty()) append(", members=$members")
        if (baseType != null) append(", baseType=$baseType")
        append(")")
    }
}