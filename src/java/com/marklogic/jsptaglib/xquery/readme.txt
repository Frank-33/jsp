There are two flavors of tags here.  Those which
accept conventional runtime expressions (rt package)
and those which evaluate JSP 2.0 Expression Language
expressions (the el package).

In a JSP 2.0 container, the Expression Language is
part of the builtin JSP syntax,  If you're running
in a JSP 2.0 container the EL syntax can be used
with the rt versions of the tags because the
expressions are evaluated before the tags are
invoked.  If your container does not provide EL
support, the el versions of these tags allow you
to use EL expressions with these tags (but only
these tags)

The el version of the tags depend on the Apache JSTL
implementation code (src/lib/standard.jar).  If you
only use the rt versions, the JSTL code is not needed
(unless you want to use the JSTL tags also, of course).

The use the rt versions of the tags, reference the
marklogicxquery-rt.tld definition in your JSPs.  To
use the el versions, use marklogicxquery.tld.

The implementation code for all the tags is in the
rt subpackage.  The code in the el package is a thin
layer over the rt code which evaluates each attribute
as an EL expression before invoking the rt code.

Rh