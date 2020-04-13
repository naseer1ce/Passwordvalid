FROM maven:3-jdk-8
COPY target/pwdvalid-spring-boot.jar .
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "pwdvalid-spring-boot.jar"]

