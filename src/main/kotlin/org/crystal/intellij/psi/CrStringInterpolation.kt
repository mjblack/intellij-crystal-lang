package org.crystal.intellij.psi

import com.intellij.lang.ASTNode

class CrStringInterpolation(node: ASTNode) : CrElementImpl(node) {
    override fun accept(visitor: CrVisitor) = visitor.visitStringInterpolation(this)
}