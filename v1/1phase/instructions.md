mvn clean package                                      
cp target/kitchensink-1.0-SNAPSHOT.war \
~/jboss/jboss-eap-8.0/standalone/deployments/kitchensink.war
touch ~/jboss/jboss-eap-8.0/standalone/deployments/kitchensink.war.dodeploy


tail -f ~/jboss/jboss-eap-8.0/standalone/log/server.log


curl http://localhost:8080/kitchensink/rest/members   