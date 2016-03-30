# Developer work station configuration

<a name="ubuntu"/>
# Ubuntu 14.04
Install Ubuntu 14.04 (VM or full installation)
http://releases.ubuntu.com/14.04/

<a name="java"/>
# Java : JDK 7
In a shell (CTRL+ALT+T) :
```sh
sudo rm /opt/jdk-7u55-linux-x64.tar.gz
sudo wget --no-cookies --no-check-certificate --header "Cookie: oraclelicense=accept-securebackup-cookie" "http://download.oracle.com/otn-pub/java/jdk/7u55-b13/jdk-7u55-linux-x64.tar.gz" -O /opt/jdk-7u55-linux-x64.tar.gz
sudo rm -Rf /opt/jdk
sudo mkdir /opt/jdk
sudo tar -zxf /opt/jdk-7u55-linux-x64.tar.gz -C /opt/jdk
sudo update-alternatives --install /usr/bin/java java /opt/jdk/jdk1.7.0_55/bin/java 100
sudo update-alternatives --install /usr/bin/javac javac /opt/jdk/jdk1.7.0_55/bin/javac 100
sudo rm /etc/alternatives/java
sudo ln -s /opt/jdk/jdk1.7.0_55/bin/java /etc/alternatives/java
```

Edit /etc/profile :
```sh
sudo nano /etc/profile
```

Add following lines to that file :
```sh
JAVA_HOME=/usr/java/default
export JAVA_HOME
PATH=$JAVA_HOME/bin:$PATH
export PATH
```

Save (CTRL+O) then exit (CTRL+X), and type in the following :
```sh
. /etc/profile
```

Check Java is correctly installed :
```sh
java -version
```

<a name="postgresql"/>
# PostgreSQL 9.4
Add ppa UbuntuUpdates :
```sh
sudo wget --quiet -O - https://www.postgresql.org/media/keys/ACCC4CF8.asc | sudo apt-key add -
sudo sh -c 'echo "deb http://apt.postgresql.org/pub/repos/apt/ trusty-pgdg main" >> /etc/apt/sources.list.d/postgresql.list'
```

Install the following packets :
```sh
sudo apt-get update
sudo apt-get install postgresql-9.4 pgadmin3
```

Créer la base de données pixnfit et l'utilisateur :
```sh
su - postgres
createuser grails
createdb pixnfit
psql
alter user grails with encrypted password '##mon-m0t-de-p@sse-super-comp1ique-qu-i1-faut-@-b-s-o-l-u-m-e-n-t-ch@n9er##';
grant all privileges on database pixnfit to grails;
```

Start postgresql on startup :
```sh
sudo update-rc.d -f postgresql remove
sudo update-rc.d postgresql defaults 90 10
```

<a name="grails"/>
# Grails 2.5.4
Download grails

```sh
cd ~
wget --no-check-certificate https://github.com/grails/grails-core/releases/download/v2.5.4/grails-2.5.4.zip
```

Unzip
```sh
unzip grails-2.5.4.zip
```

Move to the right location
```sh
sudo mkdir /usr/lib/grails
sudo mv grails-2.5.4 /usr/lib/grails
```

<a name="intellijidea"/>
# IntellijIDEA 14.1.6

Download IntellijIDEA
```sh
cd ~
wget https://download.jetbrains.com/idea/ideaIU-14.1.6.tar.gz
tar -zxvf ideaIU-14.1.6.tar.gz
chmod +x idea-IU-141.3056.4/bin/idea.sh
```

Start
```sh
idea-IU-141.3056.4/bin/idea.sh
```


<a name="git"/>
# Git 1.9.1

Install Git :
```sh
sudo apt-get update
sudo apt-get install git
```

Check Git :
```sh
git --version
```


<a name="source"/>
# Source code

Create local repository
```sh
mkdir -p ~/git/pixnfit
```

Clone git repository :
```sh
cd ~/git/pixnfit
git clone https://github.com/fabier/pixnfit.git
```
