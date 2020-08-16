package com.plugin.view.parts;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.eclipse.e4.ui.di.Focus;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ExpandBar;
import org.eclipse.swt.widgets.ExpandItem;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.jface.layout.TreeColumnLayout;

public class MySampleView {


	public MySampleView() {
	}

	/**
	 * Create contents of the view part.
	 */
	@PostConstruct
	public void createControls(Composite parent) {
		parent.setLayout(new GridLayout(1, false));
	}

	@PreDestroy
	public void dispose() {
	}

	@Focus
	public void setFocus() {
	}
	public TreeViewer getTreeViewer() {
		return treeViewer;
	}
}
