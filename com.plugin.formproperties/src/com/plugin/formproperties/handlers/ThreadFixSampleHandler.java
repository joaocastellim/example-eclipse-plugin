package com.plugin.formproperties.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.internal.preferences.ExportedPreferences;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.preferences.IExportedPreferences;
import org.eclipse.core.runtime.preferences.IPreferencesService;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;

public class ThreadFixSampleHandler extends AbstractHandler {
	
	public static final String PLUGIN_ID = "com.plugin.formproperties";

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindowChecked(event);
		createPreferences();
		
		TitleAreaDialog taDialog = new ThreadFixConnection(window.getShell());
		taDialog.open();		
		
		return null;
	}
	
	private void createPreferences() {
		try {
			IPreferencesService service = Platform.getPreferencesService();
			if (!service.getRootNode().nodeExists(PLUGIN_ID)) {
				System.out.println("Creating plugin preferences node.");
				IExportedPreferences pluginNode = (IExportedPreferences) ExportedPreferences.newRoot().node(PLUGIN_ID);
				service.applyPreferences(pluginNode);	
			} else {
				System.out.println("The plugin preferences node already exists");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
