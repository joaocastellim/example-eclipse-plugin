package com.plugin.formproperties.handlers;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.TouchListener;
import org.eclipse.swt.events.TouchEvent;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;

import java.util.Arrays;

import org.eclipse.core.runtime.Platform;
import org.osgi.service.prefs.BackingStoreException;
import org.osgi.service.prefs.Preferences;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.IPreferencesService;
import org.eclipse.core.runtime.preferences.InstanceScope;

public class ThreadFixConnection extends TitleAreaDialog {
	private static final int TEST_CONNECTION = IDialogConstants.CLIENT_ID + 1;
	private static final String API_URL = "API_URL";
	private static final String API_KEY = "API_KEY";
	private Text urlText;
	private Text apikeyText;
	private Button okButton;
	
	boolean isConnected;

	/**
	 * Create the dialog.
	 * @param parentShell
	 */
	public ThreadFixConnection(Shell parentShell) {
		super(parentShell);
	}

	/**
	 * Create contents of the dialog.
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite area = (Composite) super.createDialogArea(parent);
		Composite container = new Composite(area, SWT.NONE);
		container.setLayout(new GridLayout(2, false));
		container.setLayoutData(new GridData(GridData.FILL_BOTH));
		
		setTitle("ThreadFix Configuration");
		
		Label enpointLabel = new Label(container, SWT.NONE);
		enpointLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		enpointLabel.setText("ThreadFix Endpoint URL");
		
		urlText = new Text(container, SWT.BORDER);
		urlText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label apiKeyLabel = new Label(container, SWT.NONE);
		apiKeyLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		apiKeyLabel.setText("API Key");
		
		apikeyText = new Text(container, SWT.BORDER);
		apikeyText.setText("");
		apikeyText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		
		loadPluginSettings();
		
		return area;
	}

	/**
	 * Create contents of the button bar.
	 * @param parent
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		Button testConnection = createButton(parent, TEST_CONNECTION, "Test Connection", true);
		testConnection.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				isConnected = urlText.getText().equals("1") && apikeyText.getText().equals("1");
				System.out.println(isConnected);
				if (!isConnected) {
					setErrorMessage("This API Key is no longer valid.");
				} else {
					setErrorMessage(null);
					setMessage("Successfully Connected.", IMessageProvider.INFORMATION);
					okButton.setEnabled(true);
				}
			}
		});
		createButton(parent, IDialogConstants.CANCEL_ID, IDialogConstants.CANCEL_LABEL, false);
		okButton = createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL, false);
		okButton.setEnabled(false);
		okButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				savePluginSettings(API_URL, urlText.getText());
				savePluginSettings(API_KEY, apikeyText.getText());
			}
		});
	}

	/**
	 * Return the initial size of the dialog.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(450, 300);
	}
	
	private void savePluginSettings(String key, String value) {
		try {
			Preferences preferences = Platform.getPreferencesService().getRootNode().node(ThreadFixSampleHandler.PLUGIN_ID);
			System.out.println(preferences.absolutePath());
			preferences.put(key, value);
			preferences.flush();
		} catch (BackingStoreException e) {
			e.printStackTrace();
		}

	}

	
	private void loadPluginSettings() {
		try {
			Preferences preferences = Platform.getPreferencesService().getRootNode().node(ThreadFixSampleHandler.PLUGIN_ID);
			preferences.sync();
			System.out.println(preferences.absolutePath());
			Arrays.asList(preferences.keys()).forEach(System.out::println);
			urlText.setText(preferences.get(API_URL, ""));
			apikeyText.setText(preferences.get(API_KEY, ""));
		} catch (BackingStoreException e) {
			e.printStackTrace();
		}
	}

}
