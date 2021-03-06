package com.flowingcode.vaadin.addons.twincolgrid;

import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.IFrame;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout.Orientation;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.Route;

@SuppressWarnings("serial")
@Route("twincolgrid")
@CssImport("styles/shared-styles.css")
public class TwincolDemoView extends VerticalLayout {

	private static final String BOUND_DEMO = "Bound";
	private static final String FILTERABLE_DEMO = "Filterable";
	private static final String DRAGNDROP_DEMO = "Drag and Drop";
	private static final String BOUND_SOURCE = "https://github.com/FlowingCode/TwinColGridAddon/blob/master/src/test/java/com/flowingcode/vaadin/addons/twincolgrid/BoundDemo.java";
	private static final String FILTERABLE_SOURCE = "https://github.com/FlowingCode/TwinColGridAddon/blob/master/src/test/java/com/flowingcode/vaadin/addons/twincolgrid/FilterableDemo.java";
	private static final String DRAGNDROP_SOURCE = "https://github.com/FlowingCode/TwinColGridAddon/blob/master/src/test/java/com/flowingcode/vaadin/addons/twincolgrid/DragAndDropDemo.java";

	public TwincolDemoView() {

		SplitLayout layout = new SplitLayout();
		layout.setOrientation(Orientation.HORIZONTAL);
		layout.addToPrimary(new BoundDemo());
		layout.setSizeFull();
		IFrame iframe = new IFrame();
		iframe.getElement().setAttribute("frameborder", "0");
		iframe.getElement().setAttribute("srcdoc", getSrcdoc(BOUND_DEMO));
		iframe.setSizeFull();
		layout.addToSecondary(iframe);

		Checkbox codeCB = new Checkbox("Show Source Code");
		codeCB.setValue(true);
		codeCB.addValueChangeListener(cb -> {
			if (cb.getValue()) {
				layout.setSplitterPosition(50);
			}
			else {
				layout.setSplitterPosition(100);
			}
		});

		Tabs tabs = new Tabs();
		Tab demo1 = new Tab(BOUND_DEMO);
		Tab demo2 = new Tab(FILTERABLE_DEMO);
		Tab demo3 = new Tab(DRAGNDROP_DEMO);
		tabs.setWidthFull();
		tabs.add(demo1, demo2, demo3, codeCB);
		add(tabs, layout);
		tabs.setSelectedTab(demo1);

		setSizeFull();

		tabs.addSelectedChangeListener(e -> {
			this.removeAll();
			layout.removeAll();
			switch (e.getSelectedTab().getLabel()) {
			case BOUND_DEMO:
				iframe.getElement().setAttribute("srcdoc", getSrcdoc(BOUND_DEMO));
				layout.addToPrimary(new BoundDemo());
				layout.addToSecondary(iframe);
				add(tabs, layout);
				break;
			case FILTERABLE_DEMO:
				iframe.getElement().setAttribute("srcdoc", getSrcdoc(FILTERABLE_DEMO));
				layout.addToPrimary(new FilterableDemo());
				layout.addToSecondary(iframe);
				add(tabs, layout);
				break;
			case DRAGNDROP_DEMO:
				iframe.getElement().setAttribute("srcdoc", getSrcdoc(DRAGNDROP_DEMO));
				layout.addToPrimary(new FilterableDemo());
				layout.addToSecondary(iframe);
				add(tabs, layout);
				break;
			default:
				iframe.getElement().setAttribute("srcdoc", getSrcdoc(BOUND_DEMO));
				layout.addToPrimary(new BoundDemo());
				layout.addToSecondary(iframe);
				add(tabs, layout);
				break;
			}
		});
	}

	private String getSrcdoc(String demo) {
		String response;
		switch (demo) {
		case BOUND_DEMO:
			response = "<html style=\"overflow-y:hidden; height:100%;\"><body style=\"overflow-y: scroll; height:100%;\"><script src=\"https://gist-it.appspot.com/"
					+ BOUND_SOURCE + "\"></script></body></html>";
			break;
		case FILTERABLE_DEMO:
			response = "<html style=\"overflow-y:hidden; height:100%;\"><body style=\"overflow-y: scroll; height:100%;\"><script src=\"https://gist-it.appspot.com/"
					+ FILTERABLE_SOURCE + "\"></script></body></html>";
			break;
		case DRAGNDROP_DEMO:
			response = "<html style=\"overflow-y:hidden; height:100%;\"><body style=\"overflow-y: scroll; height:100%;\"><script src=\"https://gist-it.appspot.com/"
					+ DRAGNDROP_SOURCE + "\"></script></body></html>";
			break;
		default:
			response = "<html style=\"overflow-y:hidden; height:100%;\"><body style=\"overflow-y: scroll; height:100%;\"><script src=\"https://gist-it.appspot.com/"
					+ BOUND_SOURCE + "\"></script></body></html>";
			break;
		}
		return response;
	}
}
