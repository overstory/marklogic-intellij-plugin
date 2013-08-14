package org.intellij.xquery.reference.variable;

import com.intellij.openapi.util.Condition;
import com.intellij.psi.PsiElementResolveResult;
import com.intellij.psi.ResolveResult;
import com.intellij.psi.ResolveState;
import com.intellij.psi.util.PsiTreeUtil;
import org.intellij.xquery.model.XQueryQName;
import org.intellij.xquery.psi.*;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.intellij.xquery.model.XQueryQNameBuilder.aXQueryQName;

/**
 * User: ligasgr
 * Date: 14/08/13
 * Time: 22:00
 */
public class XQueryVariableReferenceResolver {

    private final String checkedNamespacePrefix;
    private List<XQueryVarName> matchingVariableNames;
    private XQueryVarRef myElement;

    public XQueryVariableReferenceResolver(XQueryVarRef myElement) {
        this.myElement = myElement;
        checkedNamespacePrefix = myElement.getVarName().getVarNamespace() != null ? myElement.getVarName()
                .getVarNamespace
                        ().getText() : null;
    }

    @NotNull
    public ResolveResult[] getResolutionResults() {
        XQueryFile file = (XQueryFile) myElement.getContainingFile();
        matchingVariableNames = new ArrayList<XQueryVarName>();
        addReferencesFromLocalScopes();
        if (matchingVariableNames.size() > 0) {
            return convertToResolveResults(matchingVariableNames);
        }
        addVariableDeclarationReferencesFromFile(file, checkedNamespacePrefix);
        addVariableDeclarationReferencesFromImportedFiles(file);
        return convertToResolveResults(matchingVariableNames);
    }

    private void addReferencesFromLocalScopes() {
        VariableReferenceScopeProcessor processor = new VariableReferenceScopeProcessor(myElement);
        PsiTreeUtil.treeWalkUp(processor, myElement, null, ResolveState.initial());
        if (processor.getResult() != null)
            matchingVariableNames.add(processor.getResult());
    }

    private void addVariableDeclarationReferencesFromFile(XQueryFile file, String checkedNamespace) {
        for (XQueryVarDecl varDecl : file.getVariableDeclarations()) {
            addVariableAsTargetIfMatches(varDecl, checkedNamespace);
        }
    }

    private void addVariableAsTargetIfMatches(XQueryVarDecl variableDeclaration, String checkedNamespace) {
        if (variableDeclarationWithValidName(variableDeclaration)) {
            XQueryQName<XQueryVarName> source = aXQueryQName(myElement.getVarName())
                    .withPrefix(checkedNamespace)
                    .build();
            XQueryQName<XQueryVarName> checkedQName = aXQueryQName(variableDeclaration.getVarName())
                    .build();
            if (source.equals(checkedQName)) {
                matchingVariableNames.add(checkedQName.getNamedObject());
            }
        }
    }

    private void addVariableDeclarationReferencesFromImportedFiles(XQueryFile file) {
        if (variableHasNamespacePrefix()) {
            for (XQueryFile importedFile : getFilesFromImportWithMatchingNamespacePrefix(file)) {
                addVariableDeclarationReferencesFromFile(importedFile, importedFile.getModuleNamespaceName().getText());
            }
        }
    }

    private boolean variableHasNamespacePrefix() {
        return myElement.getVarName().getVarNamespace() != null;
    }

    private Collection<XQueryFile> getFilesFromImportWithMatchingNamespacePrefix(XQueryFile file) {
        return file.getImportedFilesThatExist(new Condition<XQueryModuleImport>() {
            @Override
            public boolean value(XQueryModuleImport moduleImport) {
                String namespacePrefix = myElement.getVarName().getVarNamespace().getText();
                return namespacePrefix.equals(moduleImport.getNamespaceName().getText());
            }
        });
    }

    private ResolveResult[] convertToResolveResults(List<XQueryVarName> resolveResults) {
        ResolveResult[] convertedResults = new ResolveResult[resolveResults.size()];
        for (int i = 0; i < resolveResults.size(); i++) {
            convertedResults[i] = new PsiElementResolveResult(resolveResults.get(i));
        }
        return convertedResults;
    }

    private boolean variableDeclarationWithValidName(XQueryVarDecl varDecl) {
        return varDecl.getVarName() != null && varDecl.getVarName().getTextLength() > 0;
    }
}
