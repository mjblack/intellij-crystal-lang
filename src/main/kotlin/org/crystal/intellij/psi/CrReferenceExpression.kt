package org.crystal.intellij.psi

import com.intellij.lang.ASTNode

class CrReferenceExpression(
    node: ASTNode
) : CrExpressionImpl(node), CrSimpleNameElementHolder, CrMethodReceiver, CrExpressionWithReceiver {
    override fun accept(visitor: CrVisitor) = visitor.visitReferenceExpression(this)
}