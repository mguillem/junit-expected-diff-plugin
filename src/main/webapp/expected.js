var $ = dojo.byId;
var url = window.location.toString().split("#")[0];

function apiParams(expected, actual) {

        var api = {};
        //set defaults for all arguments
        api.comments = "indent";
        api.content = false;
        api.diffview = "sidebyside";
        api.force_indent = false;
        api.html = false;
        api.insize = 4;
        api.indent = "";
        api.lang = "auto";
        api.mode = "diff";
        api.quote = false;
        api.semicolon = false;
        api.style = "indent";
        api.topcoms = false;
        api.source = expected;
        api.diff = actual;
        api.sourcelabel = "Expected";
        api.difflabel = "Actual";
                     return api;
}


function beautify(source) {

        var api = {};
        //set defaults for all arguments
        api.comments = "indent";
        api.content = false;
        api.force_indent = false;
        api.html = false;
        api.insize = 4;
        api.indent = "";
        api.lang = "auto";
        api.mode = "beautify";
        api.quote = false;
        api.semicolon = false;
        api.style = "indent";
        api.topcoms = false;
        api.source = source;
        api.diff = "";
        api.sourcelabel = "Source";
        return api;
}

function prettyPrint(input) {

    if (input.match(/^([a-zA-Z0-9._\$]+(@[a-zA-Z0-9._\$]*){0,1}){0,1}\[.*]$/) != null)  {
    var start = input.indexOf('[');
    var prefix = input.substring(0, start);
    var ugly = input.substring(start).replace(/^\[/,"{").replace(/]$/,"}");
    var pretty = prettydiff(beautify(ugly))[0];
    return prefix + pretty;
  } else {
    return input;
  }
}

function match (stacktrace) {
var matched = stacktrace.match(/^[a-zA-Z]+[.][a-zA-Z]+[.][a-zA-Z]+:[\s\S]+expected:&lt;([\s\S]*)&gt;[\s\S]+but[\s\S]+was:&lt;([\s\S]*)&gt;/);
  if (matched != null)
   return matched;
  var sameMatched = stacktrace.match(/^[a-zA-Z]+[.][a-zA-Z]+[.][a-zA-Z]+:[\s\S]+expected[\s\S]+same:&lt;([\s\S]*)&gt;[\s\S]+was[\s\S]+not:&lt;([\s\S]*)&gt;/);
   if (sameMatched != null)
     return sameMatched;
  var isMatched =  stacktrace.match(/^[a-zA-Z]+[.][a-zA-Z]+[.][a-zA-Z]+:[\s\S]+Expected:[\s\S]+is[\s\S]+&lt;([\s\S]*)&gt;[\s\S]+got:[\s\S]*&lt;([\s\S]*)&gt;/);
  if (isMatched != null)
   return isMatched;
  var equalMatched = stacktrace.match(/^[a-zA-Z]+[.][a-zA-Z]+[.][a-zA-Z]+:[\s\S]+Expected:[\s\S]+&lt;([\s\S]*)&gt;[\s\S]+got:[\s\S]*&lt;([\s\S]*)&gt;/);
  if (equalMatched != null)
     return equalMatched;
  return
    stacktrace.match(/^[a-zA-Z]+[.][a-zA-Z]+[.][a-zA-Z]+:[\s\S]+expected[\s\S]+same[\s\S]+instance[\s\S]+but[\s\S]+found:&lt;([\s\S]*)&gt;[\s\S]+and:&lt;([\s\S]*)&gt;/);
}

function diffUsingJS () {
    var stacktraceHeader = dojo.filter(dojo.query("h3"), function(header){ return header.innerHTML == 'Stacktrace'; })[0];
 
    var stacktrace = stacktraceHeader.nextSibling.innerHTML;
    if(stacktrace != undefined) {
        var matched = match(stacktrace);
    	if (matched != null) {
    	var expected = matched[1];
	    var actual = matched[2];
    	var diffoutputdiv = $("diffoutput");
	while (diffoutputdiv.firstChild) diffoutputdiv.removeChild(diffoutputdiv.firstChild);

        var exp = prettyPrint(expected);
        var act = prettyPrint(actual);
	var output = prettydiff(apiParams(exp, act));
	dojo.place(output[0],diffoutputdiv);
	}
    }
}
dojo.addOnLoad(function() {
  diffUsingJS();
});