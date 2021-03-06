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

package org.intellij.xquery.runner.ui.run;

import com.intellij.application.options.ModuleListCellRenderer;
import com.intellij.application.options.ModulesComboBox;
import com.intellij.execution.configurations.ModuleBasedConfiguration;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleManager;
import com.intellij.openapi.module.ModuleType;
import com.intellij.openapi.module.ModuleTypeManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.ui.configuration.ModulesAlphaComparator;
import com.intellij.ui.ComboboxSpeedSearch;
import com.intellij.ui.SortedComboBoxModel;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

// Modified copy of com.intellij.execution.ui.ConfigurationModuleSelector which is not available in anything but IDEA
public class ConfigurationModuleSelector {
    private final Project myProject;
    private final JComboBox<Module> myModulesList;

    public ConfigurationModuleSelector(final Project project, final JComboBox<Module> modulesList) {
        this(project, modulesList, "<no module>");
    }

    public ConfigurationModuleSelector(final Project project, final JComboBox<Module> modulesList, final String noModule) {
        myProject = project;
        myModulesList = modulesList;
        new ComboboxSpeedSearch(modulesList) {
            protected String getElementText(Object element) {
                if (element instanceof Module) {
                    return ((Module) element).getName();
                } else if (element == null) {
                    return noModule;
                }
                return super.getElementText(element);
            }
        };
        myModulesList.setModel(new SortedComboBoxModel<>(ModulesAlphaComparator.INSTANCE));
        myModulesList.setRenderer(new ModuleListCellRenderer(noModule));
    }

    public void applyTo(final ModuleBasedConfiguration configurationModule) {
        configurationModule.setModule((Module) myModulesList.getSelectedItem());
    }

    public void reset(final ModuleBasedConfiguration configuration) {
        final Module[] modules = ModuleManager.getInstance(getProject()).getModules();
        final List<Module> list = new ArrayList<>();
        for (final Module module : modules) {
            if (isModuleAccepted(module)) list.add(module);
        }
        setModules(list);
        myModulesList.setSelectedItem(configuration.getConfigurationModule().getModule());
    }

    public boolean isModuleAccepted(final Module module) {
        return ModuleTypeManager.getInstance().isClasspathProvider(ModuleType.get(module));
    }

    public Project getProject() {
        return myProject;
    }

    private void setModules(final Collection<Module> modules) {
        if (myModulesList instanceof ModulesComboBox) {
            ((ModulesComboBox) myModulesList).setModules(modules);
        } else {
            SortedComboBoxModel<Module> model = (SortedComboBoxModel<Module>) myModulesList.getModel();
            model.setAll(modules);
            model.add(null);
        }
    }

    public Module getModule() {
        return (Module) myModulesList.getSelectedItem();
    }
}
