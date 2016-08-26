FROM tomcat:8.5

ADD grass-web-1.0.war /usr/local/tomcat/webapps
WORKDIR /usr/local/tomcat/webapps
RUN mv grass-web-1.0.war grass.war
