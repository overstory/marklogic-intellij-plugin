/*
 * Copyright 2013-2017 Grzegorz Ligas <ligasgr@gmail.com> and other contributors
 * (see the CONTRIBUTORS file).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

// This is a generated file. Not intended for manual editing.
package org.intellij.xquery.psi.impl;

import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import org.intellij.xquery.psi.*;
import com.intellij.psi.PsiReference;

public class XQueryFunctionCallImpl extends XQueryPsiElementImpl implements XQueryFunctionCall {

  public XQueryFunctionCallImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull XQueryVisitor visitor) {
    visitor.visitFunctionCall(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof XQueryVisitor) accept((XQueryVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public XQueryArgumentList getArgumentList() {
    return findNotNullChildByClass(XQueryArgumentList.class);
  }

  @Override
  @NotNull
  public XQueryFunctionName getFunctionName() {
    return findNotNullChildByClass(XQueryFunctionName.class);
  }

  public PsiReference getReference() {
    return XQueryPsiImplUtil.getReference(this);
  }

  public int getArity() {
    return XQueryPsiImplUtil.getArity(this);
  }

  @Override
  public String toString()
  {
    return "" + getFunctionName() + "#" + getArity() + " (" + getArgumentList().toString() + ")";
  }
}
