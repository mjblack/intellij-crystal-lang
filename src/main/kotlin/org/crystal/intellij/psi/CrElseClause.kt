package org.crystal.intellij.psi

import com.intellij.lang.ASTNode

open class CrElseClause(node: ASTNode) : CrElementImpl(node) {
    override fun accept(visitor: CrVisitor) = visitor.visitElseClause(this)
}