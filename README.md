### What's this?

This is a contribution from the University of Washington. 

* This code started out as a fork of Kuali Student ks-aggregate/trunk. Everything was imported except for the contents of the Impex MPX files which were very large. Those can be recreated by doing the manual impex procedure. It also contains all of the commit history from Subversion.
* Removed the following:
    * GWT source code
    * ks-enroll module
    * ks-ap module
    * ks-fa module
    * Deployment configuration that was taking up resources (e.g. activemq)
* Patches were applied to fix the following jiras:
    * [KSCM-1264](https://jira.kuali.org/browse/KSCM-1264)
    * [KSCM-2364](https://jira.kuali.org/browse/KSCM-2364)
    * [KSCM-2932](https://jira.kuali.org/browse/KSCM-2932)
    * [KSCM-2680](https://jira.kuali.org/browse/KSCM-2680)
    * [KSCM-2341](https://jira.kuali.org/browse/KSCM-2341)
    * [KSCM-2251](https://jira.kuali.org/browse/KSCM-2251)
    * [KSCM-2764](https://jira.kuali.org/browse/KSCM-2764)
* The tag 'Post-Cleanup' was created immediately after the cleanup
