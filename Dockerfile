FROM livingobjects/jre8
MAINTAINER smile_jt@qq.com
RUN ["mkdir","-p","/workplace"]
COPY base-admin.jar /workplace/app.jar
WORKDIR /workplace/
CMD ["java","-jar","-XX:MaxPermSize=128m","-Duser.timezone=GMT+08","app.jar"]
EXPOSE 9090