
What is this?
-------------

This project is a custom JSP tag library that
allows you to execute XQuery requests on a
Content Interaction Server engine and receive
the results.  It provides an integration path
to help you make use of CIS content from within
a J2EE web application.

This code is open source and licensed under the
Apache 2.0 license.  For more information on the
license, visit http://www.apache.org/licenses/LICENSE-2.0


Where did this come from?
-------------------------

This project is hosted on the Mark Logic Developer
Support site http://xqzone.marklogic.com.  If you
did not get this code from there, please visit the
site to make sure you have the most current version.

xq:zone hosts other XQuery/Mark Logic open source
projects as well.


How do I install this?
----------------------

This is a custom JSP tag library, not a standalone
program, so installation depends on the container
you're using and the specifics of yuor application.

There are two primary components in the binary
distribution:

1) A Jar file which contains the compiled Java
classes that implement the tags.  The matching
TLD is also in the Jar file.  You'll need to
put the TLD somewhere in your web application
root and name it in the web.xml (see the online
tutorial at http://xqzone.marklogic.com) and
put the jar file in the web app's WEB-INF/lib
directory.

2) A Demo web application, packaged as a .war
file that is ready to drop into a JSP container
and run.  It has several example JSPs that show
how to use the Mark Logic XQuery tags.

A downloadable source bundle is available as
well as direct Subversion access to the source
tree.

If you run into any problems using these tags,
or discover any bugs, please join the mailing
list at http://xqzone.marklogic.com/discuss and
post there.

Good luck!

---------
Rh 7/18/2004
Mark Logic Corporation