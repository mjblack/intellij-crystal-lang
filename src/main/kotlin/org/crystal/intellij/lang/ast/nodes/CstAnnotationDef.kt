package org.crystal.intellij.lang.ast.nodes

import org.crystal.intellij.lang.ast.location.CstLocation
import org.crystal.intellij.lang.ast.CstTransformer
import org.crystal.intellij.lang.ast.CstVisitor

class CstAnnotationDef(
    val name: CstPath,
    location: CstLocation? = null
) : CstNode(location) {
    fun copy(
        name: CstPath = this.name,
        location: CstLocation? = this.location
    ) = CstAnnotationDef(name, location)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as CstAnnotationDef

        return name == other.name
    }

    override fun hashCode() = name.hashCode()

    override fun toString() = "AnnotationDef($name)"

    override fun acceptSelf(visitor: CstVisitor) = visitor.visitAnnotationDef(this)

    override fun acceptTransformer(transformer: CstTransformer) = transformer.transformAnnotationDef(this)
}