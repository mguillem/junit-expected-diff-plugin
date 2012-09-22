
JUnit Excepted Diff Plugin
==========

About
-----
A Jenkins plugin that adds diff view between the expected and the actual value.
Picture says a thousand words:
![][Screenshot]

Integrates with the output of JUnit asserts and 'FEST Assert' asserts.

Installation
-----
* Download plugin, install in Jenkins,
* For every job with JUnit results do:
  * Open job 'configure' page,
  * In 'Post-build' section click "Add-post-build-action",
  * Select from popup list "Addional test report features"
  * Then check "Expected - Actual diff view"
  * Save changes
* Enjoy


Troubleshooting
-----
At the moment this plugin works only with Freestyle Jenkins jobs.
For Maven2/3 jobs you just need to wait for https://issues.jenkins-ci.org/browse/JENKINS-5649.

[Screenshot]: https://sites.google.com/site/usultis/expected-actual.png?attredirects=0&d=1
