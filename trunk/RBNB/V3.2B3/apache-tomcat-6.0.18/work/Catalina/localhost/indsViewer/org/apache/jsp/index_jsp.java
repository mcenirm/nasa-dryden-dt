package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("<!--\r\n");
      out.write("\tviewerJSP\\index.jsp\r\n");
      out.write("\t\r\n");
      out.write("\tCopyright 2008 Creare Inc.\r\n");
      out.write("\t\r\n");
      out.write("\tLicensed under the Apache License, Version 2.0 (the \"License\"); \r\n");
      out.write("\tyou may not use this file except in compliance with the License. \r\n");
      out.write("\tYou may obtain a copy of the License at \r\n");
      out.write("\t\r\n");
      out.write("\thttp://www.apache.org/licenses/LICENSE-2.0 \r\n");
      out.write("\t\r\n");
      out.write("\tUnless required by applicable law or agreed to in writing, software \r\n");
      out.write("\tdistributed under the License is distributed on an \"AS IS\" BASIS, \r\n");
      out.write("\tWITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. \r\n");
      out.write("\tSee the License for the specific language governing permissions and \r\n");
      out.write("\tlimitations under the License.\r\n");
      out.write("\t\r\n");
      out.write("\t---  History  ---\r\n");
      out.write("\t2009/01/27  Updated the overall layout as per meeting on 2009/01/26\r\n");
      out.write("\t2009/02/17  Updated to include SVG viewer\r\n");
      out.write("\t2009/02/19  Major revision to use frames\r\n");
      out.write("\t2009/02/27  Major revision to use java bean to handle rmi to inds execution manager\r\n");
      out.write("\t2009/07/08  Revision to handle the inds execution manager terminate action\r\n");
      out.write("\t2009/07/23  Added response to stop browser caching\r\n");
      out.write("\t2009/08/01  Major revision to use divisions instead of frames\r\n");
      out.write("\t2009/10/06  Added pagination functionality\r\n");
      out.write("\t2009/11/13  Updated +/- pagination to show page length\r\n");
      out.write("\t\r\n");
      out.write("\t--- To Do ---\r\n");
      out.write("-->\r\n");

	response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
	response.setHeader("Pragma","no-cache"); //HTTP 1.0
	response.setDateHeader ("Expires", 0); //prevent caching at the proxy server

      out.write("\r\n");
      out.write("\r\n");
      out.write("<html xmlns=\"http://www.w3.org/1999/xhtml\" xml:lang=\"en\" lang=\"en\">\r\n");
      out.write("\r\n");
      out.write("<head>\r\n");
      out.write("\t<title>IndsViewer Version 0.11</title>\r\n");
      out.write("\t<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\r\n");
      out.write("\t<link rel=\"stylesheet\" type=\"text/css\" href=\"default.css\" />\r\n");
      out.write("\t<script type=\"text/javascript\" src=\"scripts.js\"></script>\r\n");
      out.write("</head>\r\n");
      out.write("\r\n");
      out.write("<body>\r\n");
      indsBean.ExecutionManagerBean INDS = null;
      synchronized (session) {
        INDS = (indsBean.ExecutionManagerBean) _jspx_page_context.getAttribute("INDS", PageContext.SESSION_SCOPE);
        if (INDS == null){
          INDS = new indsBean.ExecutionManagerBean();
          _jspx_page_context.setAttribute("INDS", INDS, PageContext.SESSION_SCOPE);
        }
      }
      out.write('\r');
      out.write('\n');
      indsBean.FormatBean Format = null;
      synchronized (session) {
        Format = (indsBean.FormatBean) _jspx_page_context.getAttribute("Format", PageContext.SESSION_SCOPE);
        if (Format == null){
          Format = new indsBean.FormatBean();
          _jspx_page_context.setAttribute("Format", Format, PageContext.SESSION_SCOPE);
        }
      }
      out.write("\r\n");
      out.write("\r\n");

	// :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
	// ::                     PARSE QUERY STRINGS                             ::
	// :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
	
	// Parse queryDisplay
	if (request.getParameter("display")!=null) 
		INDS.setQueryDisplay(request.getParameter("display"));
	
	// Parse queryCommand
	if (request.getParameter("command")!=null)
		INDS.setQueryCommand(request.getParameter("command"));
	else if (request.getParameter("cmd")!=null)
		INDS.setQueryCommand(request.getParameter("cmd"));
	
	// Parse queryAction
	if (request.getParameter("action")!=null)
		INDS.setQueryAction(request.getParameter("action"));
		
	// Reset the page count on either a new command or action
	if ((request.getParameter("action")!=null) || 
		(request.getParameter("command")!=null) ||
		(request.getParameter("cmd")!=null))
		INDS.setPage(0); 
	
	// Parse add/subtract page lines
	String pageParameter = request.getParameter("page");	
	if (pageParameter!=null)
		if (pageParameter.compareTo("addline")==0)
			INDS.setPageSize(INDS.getPageSize()+50); // increment by 50
		else if (pageParameter.compareTo("subtractline")==0)
			if (INDS.getPageSize()-50 > 0)
				INDS.setPageSize(INDS.getPageSize()-50);
			else
				INDS.setPageSize(1);
				
	// Execute current action to force the page total update after add/subtract!
	INDS.getActionResponse();
	
	// Update pageTotal
	int pageTotal = 1;
	if (INDS.getQueryCommand()!=null) {
		if (INDS.getQueryAction().compareTo("getCommandOut")==0)
			pageTotal = INDS.getCommandOutPageCount();
		else if (INDS.getQueryAction().compareTo("getCommandError")==0)
			pageTotal = INDS.getCommandErrorPageCount();
	}
	
	// Parse previous/next page
	// remember: setPage and getPage are referenced to zero...
	if (pageParameter!=null)
		if (pageParameter.compareTo("previous")==0)
			INDS.setPage(INDS.getPage()-1); // previous page		
		else if (pageParameter.compareTo("next")==0)
			INDS.setPage(INDS.getPage()+1); // next page
			
	if (INDS.getPage() >= pageTotal)
		INDS.setPage(0); // return to first page
	else if (INDS.getPage() < 0)
		INDS.setPage(pageTotal-1); // skip to last page
	
	// Parse formatting
	if (request.getParameter("leftWidth")!=null)
		Format.setLeftWidth(request.getParameter("leftWidth"));
	
	if (request.getParameter("centerWidth")!=null)
		Format.setCenterWidth(request.getParameter("centerWidth"));
		
	if (request.getParameter("rightWidth")!=null)
		Format.setRightWidth(request.getParameter("rightWidth"));
		
	if (request.getParameter("commandListHeight")!=null)
		Format.setCommandListHeight(request.getParameter("commandListHeight"));
	
	// Clock
	java.util.Date clock = new java.util.Date();
	
	// Execute the current command and get the action response
	String commandResults = INDS.getActionResponse();

      out.write("\r\n");
      out.write("\r\n");
      out.write("<!-- ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: -->\r\n");
      out.write("<!-- ::                          HEADER                                   :: -->\r\n");
      out.write("<!-- ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: -->\r\n");
      out.write("<div id=\"header\">\r\n");
      out.write("\tThis is the header\r\n");
      out.write("</div> <!-- header -->\r\n");
      out.write("\r\n");
      out.write("<!-- ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: -->\r\n");
      out.write("<!-- ::                          SVG RENDERING                            :: -->\r\n");
      out.write("<!-- ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: -->\r\n");
      out.print( "<div id=\"left\" class=\"column\" style=\"width: " );
      out.write(org.apache.jasper.runtime.JspRuntimeLibrary.toString((((indsBean.FormatBean)_jspx_page_context.findAttribute("Format")).getLeftWidth())));
      out.print( "\">" );
      out.write("\r\n");
      out.write("\t<div class=\"heading\">\r\n");
      out.write("\t\t<h1>Graphical Navigation</h1>\r\n");
      out.write("\t</div> <!-- heading -->\r\n");
      out.write("\t<!--[if IE]><br />&nbsp;&nbsp;Internet Explorer does not have a plugin for SVG.<![endif]-->\r\n");
      out.write("\t<![if !IE]>\r\n");
      out.write("\t\t<object data=\"/cgi-bin/xRender.py\" type=\"image/svg+xml\" width=\"100%\" height=\"92%\">\r\n");
      out.write("\t\t\tProblem with xRender.py.\r\n");
      out.write("\t\t</object>\r\n");
      out.write("\t\t<!-- <iframe id=\"svgiframe\" src=\"/cgi-bin/xRender.py\" frameborder=\"0\" width=\"99%\" height=\"92%\">Problem with xRender.py.\t</iframe>-->\r\n");
      out.write("\t<![endif]>\r\n");
      out.write("</div> <!-- left -->\r\n");
      out.write("\r\n");
      out.write("<div id=\"leftseparator\" class=\"columnseparator\" onmousedown=\"initalizeDragColumnResize(event,'left','center');\"></div><!-- leftseparator -->\r\n");
      out.write("\r\n");
      out.write("<!-- ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: -->\r\n");
      out.write("<!-- ::                     COMMAND & ACTION LISTS                        :: -->\r\n");
      out.write("<!-- ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: -->\r\n");
      out.print( "<div id=\"center\" class=\"column\" style=\"width: " );
      out.write(org.apache.jasper.runtime.JspRuntimeLibrary.toString((((indsBean.FormatBean)_jspx_page_context.findAttribute("Format")).getCenterWidth())));
      out.print( "\">" );
      out.write("\r\n");
      out.write("\t\r\n");
      out.write("\t<div class=\"heading\">\r\n");
      out.write("\t\t<h1>Command List</h1>\r\n");
      out.write("\t</div> <!-- heading -->\r\n");
      out.write("\t\r\n");
      out.write("\t");
  // Switch between viewing all and just current commands
		if (INDS.getQueryDisplay().compareTo("all")==0) { 
      out.write("\r\n");
      out.write("\t\t\t<a class=\"display\" href=\"index.jsp?display=current\">Display Current</a><br />\r\n");
      out.write("\t");
 } else { 
      out.write("\r\n");
      out.write("\t\t\t<a class=\"display\" href=\"index.jsp?display=all\">Display All</a><br />\r\n");
      out.write("\t");
 } 
      out.write("\r\n");
      out.write("\t\r\n");
      out.write("\t");
      out.print( "<div id=\"commandlist\" style=\"height: " );
      out.write(org.apache.jasper.runtime.JspRuntimeLibrary.toString((((indsBean.FormatBean)_jspx_page_context.findAttribute("Format")).getCommandListHeight())));
      out.print( "\">" );
      out.write("\r\n");
      out.write("\t\t");
      out.write(org.apache.jasper.runtime.JspRuntimeLibrary.toString((((indsBean.ExecutionManagerBean)_jspx_page_context.findAttribute("INDS")).getCommandList())));
      out.write("\r\n");
      out.write("\t</div> <!-- commandlist -->\r\n");
      out.write("\t\r\n");
      out.write("\t<div id=\"commandlistseparator\" class=\"rowseparator\" onmousedown=\"windowResize();\"><!-- --></div><!-- rowseparator -->\r\n");
      out.write("\t\r\n");
      out.write("\t<div class=\"heading\">\r\n");
      out.write("\t\t<h1>Execute Action</h1>\r\n");
      out.write("\t</div> <!-- heading -->\r\n");
      out.write("\t\r\n");
      out.write("\t<div id=\"actionlist\">\r\n");
      out.write("\t\tCommand ID: <i>");
      out.write(org.apache.jasper.runtime.JspRuntimeLibrary.toString((((indsBean.ExecutionManagerBean)_jspx_page_context.findAttribute("INDS")).getQueryCommand())));
      out.write("</i>\r\n");
      out.write("\t\t");
      out.write(org.apache.jasper.runtime.JspRuntimeLibrary.toString((((indsBean.ExecutionManagerBean)_jspx_page_context.findAttribute("INDS")).getActionList())));
      out.write("\r\n");
      out.write("\t\t");
 if ((INDS.getQueryAction().compareTo("getCommandOut")==0) || (INDS.getQueryAction().compareTo("getCommandError")==0)) { 
      out.write("\r\n");
      out.write("\t\t\tResponse page length: <a href=\"index.jsp?page=addline\">+</a>/<a href=\"index.jsp?page=subtractline\">-</a> ");
      out.print( INDS.getPageSize() );
      out.write(" lines\r\n");
      out.write("\t\t");
 } 
      out.write("\r\n");
      out.write("\t</div> <!-- actionlist -->\r\n");
      out.write("\t\t\r\n");
      out.write("</div> <!-- center -->\r\n");
      out.write("\r\n");
      out.write("<div id=\"rightseparator\" class=\"columnseparator\" onmousedown=\"initalizeDragColumnResize(event,'center','right');\"></div><!-- rightseparator -->\r\n");
      out.write("\r\n");
      out.write("<!-- ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: -->\r\n");
      out.write("<!-- ::                        ACTION RESPONSE                            :: -->\r\n");
      out.write("<!-- ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: -->\r\n");
      out.print( "<div id=\"right\" class=\"column\" style=\"width: " );
      out.write(org.apache.jasper.runtime.JspRuntimeLibrary.toString((((indsBean.FormatBean)_jspx_page_context.findAttribute("Format")).getRightWidth())));
      out.print( "\">" );
      out.write("\r\n");
      out.write("\t<div class=\"heading\">\r\n");
      out.write("\t\t<h1>Action Response</h1>\r\n");
      out.write("\t</div> <!-- heading -->\r\n");
      out.write("\t<div id=\"actionresponse\">\r\n");
      out.write("\t\t<div id=\"responsenavigationheader\">\r\n");
      out.write("\t\t\t");
 if (pageTotal > 1) { 
      out.write("\r\n");
      out.write("\t\t\t\t<a href=\"index.jsp?page=previous\"><img src=\"buttonPreviousOff.gif\" width=\"20px\" height=\"9px\" alt=\"previous\" onmouseover=\"this.src='buttonPreviousOn.gif';\" onmouseout=\"this.src='buttonPreviousOff.gif';\"/></a>\r\n");
      out.write("\t\t\t\t(");
      out.print( commandResults.length() );
      out.write(" char) ");
      out.print( clock.toString() );
      out.write(" &nbsp;&nbsp;page: ");
      out.print( INDS.getPage()+1 );
      out.write(" of ");
      out.print( pageTotal );
      out.write("\r\n");
      out.write("\t\t\t\t<a href=\"index.jsp?page=next\"><img src=\"buttonNextOff.gif\" width=\"20px\" height=\"9px\" alt=\"next\" onmouseover=\"this.src='buttonNextOn.gif';\" onmouseout=\"this.src='buttonNextOff.gif';\"/></a>\r\n");
      out.write("\t\t\t");
 } else { 
      out.write("\r\n");
      out.write("\t\t\t\t(");
      out.print( commandResults.length() );
      out.write(" char) ");
      out.print( clock.toString() );
      out.write("\r\n");
      out.write("\t\t\t");
 } 
      out.write("\r\n");
      out.write("\t\t</div> <!-- responsenavigationheader -->\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t<code><pre>");
      out.print( commandResults.replaceAll("<","&lt;").replaceAll(">","&gt;") );
      out.write("</pre></code>\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t<div id=\"responsenavigationfooter\">\r\n");
      out.write("\t\t\t");
 if (pageTotal > 1) { 
      out.write("\r\n");
      out.write("\t\t\t\t<a href=\"index.jsp?page=previous\"><img src=\"buttonPreviousOff.gif\" width=\"20px\" height=\"9px\" alt=\"previous\" onmouseover=\"this.src='buttonPreviousOn.gif';\" onmouseout=\"this.src='buttonPreviousOff.gif';\"/></a>\r\n");
      out.write("\t\t\t\t(");
      out.print( commandResults.length() );
      out.write(" char) ");
      out.print( clock.toString() );
      out.write(" &nbsp;&nbsp;page: ");
      out.print( INDS.getPage()+1 );
      out.write(" of ");
      out.print( pageTotal );
      out.write("\r\n");
      out.write("\t\t\t\t<a href=\"index.jsp?page=next\"><img src=\"buttonNextOff.gif\" width=\"20px\" height=\"9px\" alt=\"next\" onmouseover=\"this.src='buttonNextOn.gif';\" onmouseout=\"this.src='buttonNextOff.gif';\"/></a>\r\n");
      out.write("\t\t\t");
 } else { 
      out.write("\r\n");
      out.write("\t\t\t\t(");
      out.print( commandResults.length() );
      out.write(" char) ");
      out.print( clock.toString() );
      out.write("\r\n");
      out.write("\t\t\t");
 } 
      out.write("\r\n");
      out.write("\t\t</div> <!-- responsenavigationfooter -->\r\n");
      out.write("\t</div> <!-- actionResponse -->\r\n");
      out.write("</div> <!-- right -->\r\n");
      out.write("\r\n");
      out.write("<!-- ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: -->\r\n");
      out.write("<!-- ::                            FOOTER                                 :: -->\r\n");
      out.write("<!-- ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: -->\r\n");
      out.write("<div id=\"footer\">\r\n");
      out.write("\tThis is the footer\r\n");
      out.write("</div> <!-- footer -->\r\n");
      out.write("\r\n");
      out.write("</body>\r\n");
      out.write("</html>\r\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
