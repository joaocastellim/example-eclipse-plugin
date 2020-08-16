package com.plugin.formproperties.handlers;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.layout.LayoutConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

public class ConnectionPropertiesDialog extends TitleAreaDialog {

	public ConnectionPropertiesDialog(Shell parentShell) {
		super(parentShell);
	}


	@Override
	protected Control createDialogArea(Composite parent) {
		Composite contents = new Composite(parent, SWT.NONE);
		contents.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		
		setTitle("ThreadFix Configuration");
		
		Dialog.applyDialogFont(parent);
		
		Point defaultMargins = LayoutConstants.getMargins();
		GridLayoutFactory.fillDefaults()
		.numColumns(2)
		.margins(defaultMargins.x, defaultMargins.y)
		.generateLayout(contents);
		
		new Label(contents, SWT.LEFT).setText("ThreadFix Endpoint URL");
		new Label(contents, SWT.LEFT).setText("API Key");
		
		return contents;
	}
		
}
