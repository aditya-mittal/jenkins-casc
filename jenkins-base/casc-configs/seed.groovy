pipelineJob('seed-job') {
    description("Auto-generated seed job")
    definition {
        cps {
            script ("""
                pipeline {
                    agent any
                    stages {
                        stage('seed-jobs') {
                            steps {
                                jobDsl scriptText: '''
                                    pipelineJob('seeded-pipeline-1') {
                                      definition {
                                        cps {
                                          script (\"""
                                            pipeline {
                                                agent any
                                                stages {
                                                    stage('build') {
                                                        steps {
                                                            sh script: 'echo "Build"'
                                                        }
                                                    }
                                                    stage('test') {
                                                        steps{
                                                            sh script : 'echo "Test"'
                                                        }
                                                    }
                                                    stage('publish') {
                                                        steps{
                                                            sh script : 'echo "Publish"'
                                                        }
                                                    }
                                                }
                                            }\""".stripIndent()
                                          )
                                        }
                                      }
                                    }
                                    pipelineJob('seeded-pipeline-2') {
                                      definition {
                                        cps {
                                          script (\"""
                                            pipeline {
                                                agent any
                                                stages {
                                                    stage('build') {
                                                        steps {
                                                            sh script: 'echo "Build"'
                                                        }
                                                    }
                                                    stage('test') {
                                                        steps{
                                                            sh script : 'echo "Test"'
                                                        }
                                                    }
                                                    stage('publish') {
                                                        steps{
                                                            sh script : 'echo "Publish"'
                                                        }
                                                    }
                                                }
                                            }\""".stripIndent()
                                          )
                                        }
                                      }
                                    }
                                '''
                            }
                        }
                    }
                }
            """)
        }
    }
}