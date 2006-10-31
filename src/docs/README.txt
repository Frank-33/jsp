
What is this?
-------------

This project is a custom JSP tag library that
allows you to execute XQuery requests on a
MarkLogic Server engine and receive
the results.  It provides an integration path
by which you can make use of your content from
within a J2EE web application.

This code is open source and licensed under the
Apache 2.0 license.  For more information on the
license, visit http://www.apache.org/licenses/LICENSE-2.0


Where did this come from?
-------------------------

This project is hosted on the Mark Logic Developer
Support site http://developer.marklogic.com.  If you
did not get this code from there, please visit the
site to make sure you have the most current version.

The site hosts other XQuery/Mark Logic open source
projects as well.


How do I install this?
----------------------

This is a custom JSP tag library, not a standalone
program, so installation depends on the container
you're using and the specifics of your application.

There are two primary components in the binary
distribution:

1) A Jar file which contains the compiled Java
classes that implement the tags.  The matching
TLD is also in the Jar file.  You'll need to
put the TLD somewhere in your web application
root and name it in the web.xml (see the online
tutorial at http://developer.marklogic.com) and
put the jar file in the web app's WEB-INF/lib
directory.

2) A Demo web application, packaged as a .war
file that is ready to drop into a JSP container
and run.  It has several example JSPs that show
how to use the Mark Logic XQuery tags.


How does this work?
-------------------

The JSP Tag Library is built on top of the
XQRunner library, which is a simplified API
for submitting queries to a MarkLogic Server
and obtaining the results.

XQRunner depends on either the XCC (recommended)
or the older XDBC connector library.  Compiled
versions of XQRunner and XCC are included in
the sample .war file but you should obtain the
latest versions of these components when
deploying the JSP tags.

XQrunner is also an open source project on
the Mark Logic developer site at the URL:
http://developer.marklogic.com/svn/xqrunner/releases/

XCC is an oficial component of the MarkLogic
Server product and can be found at the URL:
http://developer.marklogic.com/download/

----------------------------------------------

If you run into any problems using these tags,
or discover any bugs, please join the mailing
list at http://developer.marklogic.com/discuss
and post there.

Good luck!

---------
Rh 7/18/2004
Mark Logic Corporation

Rh 10/27/2006 (ver 0.9.1)
Rh 10/30/2006 (ver 0.9.2)