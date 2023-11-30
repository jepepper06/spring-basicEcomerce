FROM openjdk:11
RUN mkdir /opt/app
COPY ["/target/**", "/opt/app/"]
EXPOSE 8443
CMD ["java", "-jar", "/opt/app/selling-app.jar"]