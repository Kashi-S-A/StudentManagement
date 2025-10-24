FROM tomcat:latest
MAINTAINER Kashinath <kashi.sa200@gmail.com>
EXPOSE 8080
COPY target/student-web-app.war /usr/local/tomcat/webapps/student-web-app.war
