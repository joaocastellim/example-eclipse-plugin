package com.plugin.helloworld.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.window.Window;

public class SampleHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindowChecked(event);
		
		var answer = MessageDialog.openQuestion(
				window.getShell(), 
				"Do you think you got it?", 
				"If you think you did it, answer yes!");
		
		MessageDialog.openInformation(
				window.getShell(),
				"Your answer is...",
				answer ? "Yes! :D" : "No! D;");
		
		InputDialog dlg = new InputDialog(window.getShell(), "Teste", 
	            "Test Value:", "", 
	            new IInputValidator() 
	    {
	        @Override
	        public String isValid(String newText) {
	            newText = newText.trim();
	            if (newText.isEmpty()) {
	                return "Empty Message";
	            }
	            return newText;
	        }
	    });
	    if (dlg.open() == Window.OK) {
	        return dlg.getValue().trim();
	    }
		
		
		return null;
	}
}
