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

    if (input.match(/^([a-zA-Z0-9._\$]+(@[a-zA-Z0-9._\$]*){0,1}){0,1}\[.*(]|[.]{3})$/) != null)  {
    if (input.endsWith("..."))
      input = input+ "]";
    var start = input.indexOf('[');
    var prefix = input.substring(0, start);
    var ugly = input.substring(start).replace(/^\[/,"{").replace(/]$/,"}");
    var pretty = prettydiff(beautify(ugly))[0];
    return prefix + pretty.replace(/^\{/,"[").replace(/}$/,"]");
  } else {
    return input;
  }
}

function match (stacktrace) {
    var patterns = [
    	/expected:[\s]*<([\s\S]*)>[\s]+but[\s]+was:[\s]*<([\s\S]*)>/, // JUnit assertEquals
    	/Expected:[\s]*"([\s\S]*)"[\s]+but:[\s]was[\s]+"([\s\S]*)"/, // Hamcrest
    	/Expecting:[\s]*<"([\s\S]*)">[\s]+to be equal to:[\s]+<"([\s\S]*)">[\s]+but was not/ // AssertJ
    	];
    for (i=0; i<patterns.length; i++) {
      var matched = stacktrace.match(patterns[i]);
      if (matched != null)
          return matched;
    }
  return null;
}

function diffUsingJS () {
    var preNodes = dojo.query("#main-panel > pre");

    if (preNodes.length > 0) {
        var matched;
    	if (preNodes[1]) { // error message + stacktrace 
    		matched = match(preNodes[1].textContent);
    	}
    	if (!matched) { // only error message
    		matched = match(preNodes[0].textContent);
    	}
    	if (matched != null) {
    		var expected = matched[1];
    		var actual = matched[2];
    		var diffoutputdiv = $("diffoutput");
    		while (diffoutputdiv.firstChild)
    			diffoutputdiv.removeChild(diffoutputdiv.firstChild);

    		var exp = prettyPrint(expected).replace(/\r?\n/g, "<br/>\n");
    		var act = prettyPrint(actual).replace(/\r?\n/g, "<br/>\n");
    		var output = prettydiff(apiParams(exp, act));
    		dojo.place(output[0], diffoutputdiv);
    	}
    }
}
dojo.addOnLoad(function() {
  diffUsingJS();
});