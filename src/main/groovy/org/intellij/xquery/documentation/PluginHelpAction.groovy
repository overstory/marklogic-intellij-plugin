/*
 * Copyright 2017 OverStory Ltd <copyright@overstory.co.uk> and other contributors
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

package org.intellij.xquery.documentation

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import org.intellij.xquery.runner.ui.run.main.RunConfigHelpDialog

import javax.swing.JFrame
import java.awt.Dimension

/**
 * Created by IntelliJ IDEA.
 * User: ron
 * Date: 4/12/17
 * Time: 12:38 AM
 */
class PluginHelpAction extends AnAction
{
	@Override
	void actionPerformed (AnActionEvent anActionEvent)
	{
		try {
			// FixMe: Do this better
			JFrame frame = new JFrame("HtmlEditorKit Test")

			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE)

			frame.setSize (new Dimension(300,500))

			frame.setLocationRelativeTo (null)
			frame.setVisible (true)

			new RunConfigHelpDialog (frame).show()
		} catch (Exception e) {
			e.printStackTrace ()
		}
	}
}
