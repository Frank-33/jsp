There are two flavors of tags here.  Those which
accept conventional runtime expressions (rt package)
and those which may use Expression Language expressions
(the el package).

The el version of the tags depend on the Apache JSTL
implementation code (src/lib/standard.jar).  If you
only use the rt versions, the JSTL code is not needed.
However, the el form is recommended because it makes
it much easier to access the result fields set by
these tags.

The use the rt versions of the tags, reference the
marklogicxquery-rt.tld definition in your JSPs.  To
use the el versions, use marklogicxquery.tld.

The implementation code for all the tags is in the
rt subpackage.  The code in the el package is a thin
layer over the rt code which evaluates each attribute
as an EL expression before invoking the rt code.
