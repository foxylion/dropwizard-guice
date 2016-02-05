
build:
	cd dropwizard-guice && mvn install
	cd dropwizard-guice-example && mvn package

travis-deploy:
	gpg --import .travis/private-key.gpg
	(cd dropwizard-guice && mvn versions:set -DnewVersion=${TRAVIS_TAG})
	(cd dropwizard-guice && mvn clean deploy -P release --settings ../.travis/settings.xml)
