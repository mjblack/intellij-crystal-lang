package org.crystal.intellij.lang.ast.nodes

import org.crystal.intellij.lang.ast.location.CstLocation
import org.crystal.intellij.lang.ast.CstVisitor

class CstProcPointer(
    val obj: CstNode?,
    val name: String,
    val args: List<CstNode> = emptyList(),
    val isGlobal: Boolean = false,
    location: CstLocation? = null
) : CstNode(location) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as CstProcPointer

        if (obj != other.obj) return false
        if (name != other.name) return false
        if (args != other.args) return false
        if (isGlobal != other.isGlobal) return false

        return true
    }

    override fun hashCode(): Int {
        var result = obj?.hashCode() ?: 0
        result = 31 * result + name.hashCode()
        result = 31 * result + args.hashCode()
        result = 31 * result + isGlobal.hashCode()
        return result
    }

    override fun toString(): String = buildString {
        append("ProcPointer(")
        if (obj != null) append("obj=$obj, ")
        append(name)
        if (args.isNotEmpty()) append(", args=$args")
        if (isGlobal) append(", isGlobal")
        append(")")
    }

    override fun acceptSelf(visitor: CstVisitor) = visitor.visitProcPointer(this)

    override fun acceptChildren(visitor: CstVisitor) {
        obj?.accept(visitor)
        args.forEach { it.accept(visitor) }
    }
}