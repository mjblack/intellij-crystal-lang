package org.crystal.intellij.lang.psi

import com.intellij.lang.ASTNode

class CrMacroIfStatement(node: ASTNode) : CrExpressionImpl(node) {
    override fun accept(visitor: CrVisitor) = visitor.visitMacroIfStatement(this)
}