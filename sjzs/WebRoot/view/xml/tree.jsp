<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.hnzskj.common.xml.ShowXml"%>
<%@page import="com.hnzskj.common.xml.XmlDTO"%>
<%@page import="com.hnzskj.common.xml.ModeDTO"%>
<%@page import="com.hnzskj.common.xml.PathMap"%>
<%@page import="java.math.BigInteger"%>
<%@ include file="/view/common/common.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%!
		String t="30"; 	//首节点距离顶部的距离
		String w="60";	//每个节点的宽度
		String h="20";	//每个节点的高度
		int ww=12;
		//BigInteger ids= new BigInteger("1");
	%>
	<%
		ShowXml show = (ShowXml)(session.getAttribute("showxml"));
		XmlDTO xmlDTO = show.getXmlDTO();
		PathMap paths = xmlDTO.getPathMap();
		String[] begin =null;
		ModeDTO beginMode = xmlDTO.beginModeDTO(true);
		//ids = ids.add(new BigInteger("1"));
		//String name = "v"+ids.add(new BigInteger("1"));
		begin = (new String[]{"v"+beginMode.getId(),beginMode.getTitle()+"","10",t,w,h});
		List<String[]> modeInfo = new ArrayList<String[]>();
		List<String> keys = paths.getValueByKey(beginMode.getId()+"");
		if(keys != null && keys.size()>0){
			for(String key:keys){
				createTree(xmlDTO,"v"+beginMode.getId(),modeInfo,xmlDTO.getModeById(key));
			}
		}
		
	%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
   <title>Tree</title>

	<!-- Sets the basepath for the library if not in same directory -->
	<script type="text/javascript">
		mxBasePath = 'supp';
	</script>

	<!-- Loads and initializes the library -->
	<script type="text/javascript" src="supp/js/mxClient.js"></script>
	<script type="text/javascript">
		/*
			Defines a custom shape for the tree node that includes the
			upper half of the outgoing edge(s).
		*/
		function TreeNodeShape() { };

		TreeNodeShape.prototype = new mxCylinder();
		TreeNodeShape.prototype.constructor = TreeNodeShape;

		// Defines the length of the upper edge segment.
		TreeNodeShape.prototype.segment = 20;

		// Needs access to the cell state for rendering
		TreeNodeShape.prototype.apply = function(state)
		{
			mxCylinder.prototype.apply.apply(this, arguments);
			this.state = state;
		};
		
		TreeNodeShape.prototype.redrawPath = function(path, x, y, w, h, isForeground)
		{
			var graph = this.state.view.graph;
			var hasChildren = graph.model.getOutgoingEdges(this.state.cell).length > 0;
			
			if (isForeground)
			{
				if (hasChildren)
				{
					// Painting outside of vertex bounds is used here
					path.moveTo(w / 2, h + this.segment);
					path.lineTo(w / 2, h);
					path.end();
				}	
			}
			else
			{
				path.moveTo(0, 0);
				path.lineTo(w, 0);
				path.lineTo(w, h);
				path.lineTo(0, h);
				path.close();
			}
		};
		
		mxCellRenderer.prototype.defaultShapes['treenode'] = TreeNodeShape;

		// Defines a custom perimeter for the nodes in the tree
		mxGraphView.prototype.updateFloatingTerminalPoint = function(edge, start, end, source)
		{
			var pt = null;
			
			if (source)
			{
				pt = new mxPoint(start.x + start.width / 2,
						start.y + start.height + TreeNodeShape.prototype.segment);
			}
			else
			{
				pt = new mxPoint(start.x + start.width / 2, start.y);
			}

			edge.setAbsoluteTerminalPoint(pt, source);
		};
	</script>

	<!-- Example code -->
	<script type="text/javascript">
		// Program starts here. Creates a sample graph in the
		// DOM node with the specified ID. This function is invoked
		// from the onLoad event handler of the document (see below).
		function main()
		{
			// Checks if browser is supported
			if (!mxClient.isBrowserSupported())
			{
				// Displays an error message if the browser is
				// not supported.
				mxUtils.error('Browser is not supported!', 200, false);
			}
			else
			{
				// Sets the collapse and expand icons. The values below are the default
				// values, but this is how to replace them if you need to.
				mxGraph.prototype.collapsedImage = new mxImage(mxClient.imageBasePath + '/collapsed.gif', 9, 9);
				mxGraph.prototype.expandedImage = new mxImage(mxClient.imageBasePath + '/expanded.gif', 9, 9);
				
				// Workaround for Internet Explorer ignoring certain styles
				var container = document.createElement('div');
				container.style.position = 'absolute';
				container.style.overflow = 'hidden';
				container.style.left = '0px';
				container.style.top = '0px';
				container.style.right = '0px';
				container.style.bottom = '0px';
				
				if (mxClient.IS_IE)
				{
					new mxDivResizer(container);
				}
				
				document.body.appendChild(container);
			
				// Creates the graph inside the given container
				var graph = new mxGraph(container);

				// Set some stylesheet options for the visual appearance
				var style = graph.getStylesheet().getDefaultVertexStyle();
				style[mxConstants.STYLE_SHAPE] = 'treenode';
				style[mxConstants.STYLE_GRADIENTCOLOR] = 'white';
				style[mxConstants.STYLE_SHADOW] = true;
				
				style = graph.getStylesheet().getDefaultEdgeStyle();
				style[mxConstants.STYLE_EDGE] = mxEdgeStyle.TopToBottom;
				style[mxConstants.STYLE_ROUNDED] = true;
				
				// Enables automatic sizing for vertices after editing and
				// panning by using the left mouse button.
				graph.setAutoSizeCells(true);
				graph.setPanning(true);
				graph.panningHandler.useLeftButtonForPanning = true;

				// Stops editing on enter or escape keypress
				var keyHandler = new mxKeyHandler(graph);
				
				// Enables automatic layout on the graph and installs
				// a tree layout for all groups who's children are
				// being changed, added or removed.
				var layout = new mxCompactTreeLayout(graph, false);
				layout.useBoundingBox = false;
				layout.edgeRouting = false;
				layout.levelDistance = 30;
				layout.nodeDistance = 10;

				var layoutMgr = new mxLayoutManager(graph);
				
				layoutMgr.getLayout = function(cell)
				{
					if (cell.getChildCount() > 0)
					{
						return layout;
					}
				};

				// Disallow any selections
				graph.setCellsSelectable(false);

				// Defines the condition for showing the folding icon
				graph.isCellFoldable = function(cell)
				{
					return this.model.getOutgoingEdges(cell).length > 0;
				};

				// Defines the position of the folding icon
				graph.cellRenderer.getControlBounds = function(state)
				{
					if (state.control != null)
					{
						var oldScale = state.control.scale;
						var w = state.control.bounds.width / oldScale;
						var h = state.control.bounds.height / oldScale;
						var s = state.view.scale;			

						return new mxRectangle(state.x + state.width / 2 - w / 2 * s,
							state.y + state.height + TreeNodeShape.prototype.segment * s - h / 2 * s,
							w * s, h * s);
					}
					
					return null;
				};

				// Implements the click on a folding icon
				graph.foldCells = function(collapse, recurse, cells)
				{
					this.model.beginUpdate();
					try
					{
						toggleSubtree(this, cells[0], !collapse);
						this.model.setCollapsed(cells[0], collapse);

						// Executes the layout for the new graph since
						// changes to visiblity and collapsed state do
						// not trigger a layout in the current manager.
						layout.execute(graph.getDefaultParent());
					}
					finally
					{
						this.model.endUpdate();
					}
				};
				// Gets the default parent for inserting new cells. This
				// is normally the first child of the root (ie. layer 0).
				var parent = graph.getDefaultParent();
								
				// Adds the root vertex of the tree
				graph.getModel().beginUpdate();
				try
				{
					var w = graph.container.offsetWidth;

					<%	
						out.println("var "+begin[0]+" = graph.insertVertex(parent, '"+begin[0]+"', '"+begin[1]+"', "+begin[2]+", "+begin[3]+", "+begin[4]+", "+begin[5]+");");
						for(String[] ss:modeInfo){
							out.println("var "+ss[1]+" = graph.insertVertex(parent, '"+ss[1]+"', '"+ss[2]+"', 0,0, "+ss[3]+", "+ss[4]+");");
							out.println("graph.insertEdge(parent, null, '', "+ss[0]+", "+ss[1]+");");
						}
					%>					
				}
				finally
				{
					// Updates the display
					graph.getModel().endUpdate();
				}
			}
		};

		// Updates the visible state of a given subtree taking into
		// account the collapsed state of the traversed branches
		function toggleSubtree(graph, cell, show)
		{
			show = (show != null) ? show : true;
			var cells = [];
			
			graph.traverse(cell, true, function(vertex)
			{
				if (vertex != cell)
				{
					cells.push(vertex);
				}

				// Stops recursion if a collapsed cell is seen
				return vertex == cell || !graph.isCellCollapsed(vertex);
			});

			graph.toggleCells(show, cells, true);
		};
	</script>
	</head>
	<body onload="main();">
	
<%!JspWriter localOut;	
	public void createTree(XmlDTO xml,String pName,List<String[]> dateList,ModeDTO mode) throws java.io.IOException{
		//ids = ids.add(new BigInteger("1"));
		//String name = "v"+ids.add(new BigInteger("1"));
		int num = ((mode.getTitle().length())>5)?5:mode.getTitle().length();
		dateList.add(new String[]{pName,"v"+mode.getId(),mode.getTitle().substring(0,num)+"",w,h});
		List<String> keys = xml.getPathMap().getValueByKey(mode.getId()+"");
		if(keys != null && keys.size()>0){
			for(String key:keys){
				createTree(xml,"v"+mode.getId(),dateList,xml.getModeById(key));
			}
		}		
	}
%>

</body>
</html>
