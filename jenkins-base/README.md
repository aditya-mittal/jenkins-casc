### Why use this docker image

This image will contain all common plugins and configuration options for our various jenkins containers, such as:
- cluster provisioning jenkins
- application jenkins master node

This will allow handling things such as credential management, plugin updates etc consistently.
At the same time it will also allow extension of configuration where necessary.


#### Environment variables required:

JENKINS_URL=`something like https://env.jenkins.base.domain`

#### Secrets required (available under /run/secrets)

JENKINS_PASSWORD=`this will be the password of jenkins admin user - admin`

JENKINS_READ_ONLY_PASSWORD=`this will be the password of jenkins read only user - read-only`


#### Extending using configuration as code plugin

This image will have configuration as code plugin (https://github.com/jenkinsci/configuration-as-code-plugin) available by default.

Some default configuration is already available in file: `/usr/share/jenkins/ref/casc-configs/jenkins.yaml`.

Configuration can be:

- added/extended by adding additional yamls under the path `/usr/share/jenkins/ref/casc-configs`
- replaced/overridden by replacing `/usr/share/jenkins/ref/casc-configs/jenkins.yaml`

either via runtime mounts or baking it into the image.


#### Adding plugins to the base image

The file `plugins.txt` contains the plugins that will be installed by default.

Any additional plugins can either be added to the derived image or added to the base image by appending the plugin id to the file `plugins.txt`


### How to run on local

```bash
    docker build . -t <docker-regitery-host:port>/jenkins-base

    echo -n "jenkins_password" > /tmp/jenkins_password

    echo -n "jenkins_read_only_password" > /tmp/jenkins_read_only_password

    docker run -d -p 8080:8080 \
        -e "JENKINS_URL=http://localhost:8080" \
        -v /tmp/jenkins_password:/run/secrets/JENKINS_PASSWORD \
        -v /tmp/jenkins_read_only_password:/run/secrets/JENKINS_READ_ONLY_PASSWORD \
        jenkins-base:0.1
```