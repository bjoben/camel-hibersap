
This is a simple example on how a Camel SAP connector using the Hibsersap library could be made.
It can be used to call SAP BAPI functions from camel endpoints.
It is built as an OSGi bundle and embeds all SAP JCo software (including the native libraries) as well as Hibersap itself.

Installation
================

This component must be built from source as it includes SAP JCo which the user needs to obtain somewhere else.

First, follow the instructions for installing and using Hibsersap at 
  
  http://hibersap.sourceforge.net/hibersap-1.0/reference/html_singlepage/HibersapReference.html
  
The native libraries extracted from the SAP software should be copied to 
     src/main/resources/native/x86 
and  src/main/resources/native/x86_64.

Also, see the Maven POM file for details. 
Maybe a maven profile to enable building as a bundle with everything embedded, or not, should be provided.



Usage
================
Example is included in the test directories although the actual tests are disabled by default.

1)
To create an hibersap endpoint, one must first create a session manager config.
You can use a file like hibersap.xml to configure everything, or you can wire it up in a spring file.
The camel component needs to find such a session manager config via it's name or id in the camel context.

2)
Implement some BAPI function mappings by creating java beans and annotate them with Hibersap annotations.

3) 
Populate and send the beans as usual to the hibersap endpoint.
Camel can now take part in BAPI communication.

4)
If used in an OSGi based container such as Karaf, this bundle is installed as any other camel component,
and then any other bundle can contain the BAPI bean mappings, Camel routes and Hibersap sessions that uses this component.


Enhancements
================
Transaction management
Thread pool management
etc
and so on


Licenses
================
- SAP JCo Software is distributed by SAP AG is covered by it's own license.
- Hibersap is released under LGPLv3
- Camel is released under Apache v2 by ASF. 
- This software is licensed under Apache v2 license 

All other dependencies used by respective library is documented by in their respective documentation.











