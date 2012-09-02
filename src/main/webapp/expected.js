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

function diffUsingJS () {
	var diffoutputdiv = $("diffoutput");
	var expected = dojo.attr("comparison","expected");
    if(expected != null) {
	var actual = dojo.attr("comparison","actual");
	while (diffoutputdiv.firstChild) diffoutputdiv.removeChild(diffoutputdiv.firstChild);

        var exp = prettyPrint(expected);
        var act = prettyPrint(actual);
	var output = prettydiff(apiParams(exp, act));
	dojo.place(output[0],diffoutputdiv);
    }
}
dojo.addOnLoad(function() {
  diffUsingJS();
});