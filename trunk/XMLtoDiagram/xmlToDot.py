#!/usr/bin/env python
# pfh 12/22/08, starting from http://docs.python.org/library/xml.dom.minidom.html
# Test to see if Python is well suited to this sort of parse/process/emit
# coding, or if I should use another language.
# pfhubbar@ucsd.edu

# Using the minidom parser, might want to see about validating
from xml.dom.minidom import parse, parseString

# Hardwired input file!
dom1 = parse('P3_startup.xml');

# ---------------------------------------------------------------------------
# Most-basic test - this should be the top-level element in an INDS file
assert dom1.documentElement.tagName == "startup";

# ---------------------------------------------------------------------------
# This function returns a global integer, used to make nodes unique
idx = 0;
def nameIndex():
	global idx;
	idx = idx + 1
	return idx

# Handle the common case of a node whose label is a single attribute
def nodeSimpleLabel(node, nodeType, attribName):
	idx = nameIndex()
	nodeName = nodeType + '%d' % idx
	nodeString = nodeName + ' [label="' + nodeType + ' ('
	nodeString += node.getAttribute(attribName) + ')"]'
	
	print nodeString
	print 'RBNB -> ' + nodeName
	
# Special-purpose, single element whose label is logFile name
def nodeByLogfile(node, nodeType):
	return(nodeSimpleLabel(node, nodeType, 'logFile'))
	
# ---------------------------------------------------------------------------
# twoBox is for timedrive and UDP capture, where we want a second box node
# feeding into the main node.
def handleTwoBox(node, prefix, label):
	idx = nameIndex()
	
	uNodeName = '%s%d' % (prefix, idx)	
	pNodeName = uNodeName + 'port%d' % idx
	
	# Parse child node to get listener port
	inputElement = node.getElementsByTagName("input")
	
	# Define port name
	print pNodeName + ' [shape="box" label="%s"]' % inputElement[0].getAttribute("port")
	
	print uNodeName + ' [label="%s"]' % label
	
	print pNodeName + ' -> ' + uNodeName
	print uNodeName + ' -> RBNB'
	
def handleTomcat(node):
	print 'tomcat [label="tomcat"]'
	print 'RBNB -> tomcat'
	
def handleRBNB(node):
	print 'RBNB [label="%s"]' % node.getAttribute("name")
	
# ---------------------------------------------------------------------------
# Loop over DOM tree, calling each handler as many times as needed
def bigNodeMapper(inds):

	# First, input-only elements
	hms = inds.getElementsByTagName("httpMonitor")
	for hm in hms:
		nodeByLogfile(hm, "HTTPMonitor")
		
	udps = inds.getElementsByTagName("udpCapture")
	for udp in udps:
		handleTwoBox(udp, 'udpCap', 'UDPCapture')

	tds = inds.getElementsByTagName("timeDrive")
	for td in tds:
		handleTwoBox(td, 'timeDrive', 'TimeDrive')

	# Now switch to dual-direction arrows
	print 'edge [dir="both"]'
	
	dts = inds.getElementsByTagName("dataTurbine")
	for dt in dts:
		handleRBNB(dt)
		
	tcs = inds.getElementsByTagName("tomcat")
	for tc in tcs:
		handleTomcat(tc)

	tkps = inds.getElementsByTagName("trackKML")
	for tkp in tkps:
		nodeByLogfile(tkp, 'TrackKML')

	tdps = inds.getElementsByTagName("trackData")
	for tdp in tdps:
		nodeByLogfile(tdp, 'TrackData')
			
	pngs = inds.getElementsByTagName("png")
	for png in pngs:
		nodeByLogfile(png, 'png')
		
	tss = inds.getElementsByTagName("toString")
	for ts in tss:
		nodeByLogfile(ts, "ToString")

	tns = inds.getElementsByTagName("thumbNail")
	for tn in tns:
		nodeByLogfile(tn, "Thumbnail")
		
	xds = inds.getElementsByTagName("xmlDemux")
	for xd in xds:
		nodeByLogfile(xd, "XMLDemux")
		
	cds = inds.getElementsByTagName("csvDemux")
	for cd in cds:
		nodeByLogfile(cd, "CSVDemux")
# ---------------------------------------------------------------------------
# Main routine, emit header and call main loop/mapper	
def handleINDS(inds):
	print "digraph INDS {"
	print 'center="true"'
	print 'ratio="auto"'
	print 'orientation="portrait"'
	
	bigNodeMapper(inds)
	
	print "}"

# Top-level call
handleINDS(dom1)

# Release DOM, not really required but good practice
dom1.unlink();
