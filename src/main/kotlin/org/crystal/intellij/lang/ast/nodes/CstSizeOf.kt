package org.crystal.intellij.lang.ast.nodes

import org.crystal.intellij.lang.ast.location.CstLocation
import org.crystal.intellij.lang.ast.CstTransformer
import org.crystal.intellij.lang.ast.CstVisitor

class CstSizeOf(
    expression: CstNode,
    location: CstLocation? = null
) : CstUnaryExpression(expression, location) {
    fun copy(
        expression: CstNode = this.expression,
        location: CstLocation? = null
    ) = CstSizeOf(expression, location)
    
    override fun acceptSelf(visitor: CstVisitor) = visitor.visitSizeOf(this)

    override fun acceptTransformer(transformer: CstTransformer) = transformer.transformSizeOf(this)
}