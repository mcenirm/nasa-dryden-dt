#!/usr/bin/env python
""""
@file xmlDraw.py
@author Paul Hubbard pfhubbar@ucsd.edu
@date 1/7/09
@brief Parse INDS XML file into graphviz DOT language graph via CGI
@note Evolution of command-line processor, now refactoring to use CGI 
invocation and HTTP file upload.
@todo Add URLs to nodes for multi-system graphs

Much help from http://www.diveintopython.org/object_oriented_framework/defining_classes.html
"""

# Using the minidom parser, also sys for command line
import sys
import os
from cgi import FieldStorage
from os import environ
from cStringIO import StringIO
from urllib import quote, unquote
from string import capwords, strip, split, join
# My code!
import xmlToDot

# ---------------------------------------------------------------------------
# CGI class and HTML. Man, I hate mixing code and presentation.
class IndsCGI(object):
	header = 'Content-Type: text/html\n\n'
	url = '/cgi-bin/xmlDraw.py'
	fn = ''
	
	# HTML for the form page
	formhtml = '''<HTML><HEAD><TITLE>
CGI to process INDS XML into SVG</TITLE></HEAD>
<BODY>
INDS-to-DOT invoker page.
<FORM METHOD=post ACTION="%s" ENCTYPE="multipart/form-data">
Please select your INDS XML file:
<br />
<INPUT TYPE=file NAME=upfile VALUE="%s" SIZE="45">
<br />
<INPUT TYPE=submit>
</FORM></BODY></HTML>'''

	# Error page
	errhtml='''<HTML><HEAD><TITLE>Error</TITLE></HEAD>
	<BODY>Error!</BODY></HTML>'''
	
	# Results page for file data
	reshtml='''<HTML><HEAD><TITLE>XML to SVG Results</TITLE>
<BODY>
<img src="/%s">
</BODY></HTML>'''

	# Display input form
	def showForm(self):
		print IndsCGI.header + IndsCGI.formhtml % (IndsCGI.url, self.fn)

	# Display results page
	def doResults(self):
		# Code!		
		indsParser = xmlToDot.IndsToDot()
		indsParser.processFilehandle(self.fp)

		# Hardcoded files in and out for now, ditto command to produce SVG
		inFile = 'test.dot'
		outFile = 'test.svg'
		dotCmd = 'dot -Tsvg -Nfontname="/System/Library/Fonts/Times.dfont" %s -o %s'
		
		# dotString is the result of the processed XML
		dotString = indsParser.outputDot

		# Save DOT as file
		fout = open(inFile, "w")
		fout.write(dotString)
		fout.close()

		# Run it
		os.system(dotCmd % (inFile, outFile))
		
		# DDT
		print IndsCGI.header + IndsCGI.reshtml % outFile
		
	# Show error page
	def showError(self):
		print IndsCGI.header + IndsCGI.errhtml % (self.error)
	
	# Determine which page to return
	def go(self):
		self.error = ''
		self.upfile =''
		
		form = FieldStorage()
	
		if form.keys() == []:
			self.showForm()
			return
	
		if form.has_key('upfile'):
			upfile = form["upfile"]
			self.fn = upfile.filename or ''
			if upfile.file:
				self.fp = upfile.file
			else:
				self.fp = StringIO('(no data)')
		else:
			self.fp = StringIO('(no file)')
			self.fn = ''
	
		if not self.error:
			self.doResults()
		else:
			self.showError()
# End of class IndsCGI
		
# CGI magic
if __name__ == '__main__':
	page = IndsCGI()
	page.go()