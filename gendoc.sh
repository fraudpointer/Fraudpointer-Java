#!/bin/bash
rm -f -R doc
javadoc -d doc -sourcepath src -classpath external_libraries/gson-1.7.1.jar:external_libraries/jersey-client-1.8.jar:external_libraries/jersey-core-1.8.jar:external_libraries/commons-codec-1.5.jar -public -windowtitle 'Fraudpointer API - Java Client' -overview src/overview.html -linksource com.fraudpointer -subpackages com.fraudpointer
